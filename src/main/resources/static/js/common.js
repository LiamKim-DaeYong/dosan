$errors = {
    valid: function (errors) {
        const globalErrorsTarget = $("[globalErrors]");
        $(".modal_error").each(function () {
            if ($(this).attr("override")) {
                $(this).text("");
            } else {
                $(this).remove();
            }
        });

        $("[errorclass]").each(function () {
            $(this).removeClass($(this).attr("errorclass"));
        });

        const fieldErrors = errors["fieldErrors"];
        if (fieldErrors) {
            fieldErrors.forEach(error => {
                const errorField = $("#" + error.field);
                errorField.addClass(errorField.attr("errorclass"));

                let errorMsgField
                if (errorField.attr("editor")) {
                    errorMsgField = $('[placeholder="' + error.field + '"]')
                    errorMsgField.text(error.message);
                } else {
                    if ($(`span[errors='${error.field}']`).length) {
                        $(`span[errors='${error.field}']`).text(error.message);
                    } else {
                        errorMsgField = `<span errors="${error.field}" class="modal_error">${error.message}</span>`
                        errorField.after(errorMsgField);
                    }
                }
            });
        }

        const globalErrors = errors["globalErrors"];
        if (globalErrors) {
            if (globalErrorsTarget.length) {
                globalErrors.forEach(error => {
                    globalErrorsTarget.append(`<span class="modal_error">${error.message}</span>`)
                });
            } else {
                globalErrors.forEach(error => {
                    alert(error.message);
                });
            }
        }
    }
}

$modal = function () {
    this.defaultOption = {
        height: 400,
        width: 400,
        title: 'title'
    }

    var init = function (element, options) {
        this.defaultOption = $.extend({}, this.defaultOption, options);

        this.target = $(element);
        this.$content = this.target.find('.modal-content');
        this.$title = this.target.find('.modal-header h3');
        this.$body = this.target.find('.modal-body');
        this.$content.css({'height': this.defaultOption.height, 'width': this.defaultOption.width});

        this.setTitle(this.defaultOption.title);
        this.setContent(this.defaultOption.path);
    };

    var open = function (options) {
        this.defaultOption = $.extend({}, this.defaultOption, options);

        this.setTitle(this.defaultOption.title);
        this.setContent(this.defaultOption.path);
        $(this.target).removeClass('fade');
    };

    var close = function () {
        this.target.find('input[type=text], textarea').val("");
        this.target.addClass('fade');
    };

    var getData = function () {
        var result = {};
        var query = this.target.find('input, textarea').serializeArray();
        query.forEach(function (data) {
            result[data.name] = data.value;
        });

        return result;
    };

    var setContent = function (path) {
        if (path) {
            var _this = this;
            $.get(path, function (data) {
                $(_this.$body).html(data);
                $event.init();
            }).fail(function (error) {
                $url.redirect("/login");
            });
        } else {
            $(this.$body).html('<div>Modal Body</div>');
        }
    };

    var setTitle = function (title) {
        this.defaultOption.title = title;
        $(this.$title).text(title);
    };

    var setData = function (obj) {
        for (key in obj) {
            $("#" + key).val(obj[key]);
        }
    };

    return {
        "init": init,
        "open": open,
        "close": close,
        "getData": getData,
        "setData": setData,
        "setContent": setContent,
        "setTitle": setTitle,
    }
}

$editor = {
    init: function (targetId) {
        const target = $("#" + targetId);
        target.summernote({
            height: 500,
            placeholder: `<span placeholder="${targetId}" className="modal_error"></span>`,
            callbacks: {
                onImageUpload: function (file) {
                    const img = new FormData();
                    img.append("file", file[0]);

                    $ajax.postMultiPart({
                        url: "/files/edit/upload",
                        data: img,
                        success: function (imgUrl) {
                            target.summernote('insertImage', imgUrl);
                        }
                    });
                },
            }
        });
    }
}

$event = {
    init: function () {
        this.inputAutocompleteOff();
        this.dateInputChangeEvent();
        this.searchWorldEnterEvent();
        $checkBox.init();
        $files.init();
        $formatter.init();
    },
    inputAutocompleteOff: function () {
        $('input').prop("autocomplete", "off");
    },
    dateInputChangeEvent: function () {
        $('input[type="date"]').change(function () {
            if ($(this).attr("to")) {
                let target = $("#" + $(this).attr("to"));

                if (target.val() && $(this).val() > target.val()) {
                    target.val($(this).val())
                }
            } else if ($(this).attr("from")) {
                let target = $("#" + $(this).attr("from"));

                if (target.val() && $(this).val() < target.val()) {
                    target.val($(this).val())
                }
            }
        });
    },
    searchWorldEnterEvent() {
        $("#searchWorld").keyup(function (e) {
            if (e.keyCode === 13) {
                e.preventDefault();
                $(".search_btn").click();
            }
        });
    },
}

$files = {
    filesMap: new Map(),
    init: function () {
        var _this = this;

        $(".file-input").on("change", function () {
            $.each(this.files, function (idx, file) {
                _this.addFile(file);
            });
        })
    },
    removeFile: function (file) {
        const filename = $(file).siblings('span').text();
        this.filesMap.delete(filename);
        $(file).closest('.file_wraper').remove();
    },
    addFile: function (file) {
        let filename = file.name;

        if (this.filesMap[filename]) return;

        this.filesMap.set(filename, file);
        const template = `
                    <div class="file_wraper">
                        <span>${filename}</span>
                        <button type="button" onclick="$files.removeFile(this)"><img src="${$url.getHostUrl()}/images/common/close.svg"></button>
                    </div>`;

        $(".filenames").append(template);
    },

    onlyInit: function (node, inputClass) {
        var _this = this;

        inputClass.children().remove();
        for (const file of node.files) {
            _this.onlyAddFile(file, inputClass)
        }
    },
    onlyAddFile: function (file, inputClass) {
        let filename = file.name;

        if (this.filesMap[filename]) return;

        this.filesMap.set(filename, file);
        const template = `
                    <div class="file_wraper">                      
                        <span>${filename}</span>
                        <button type="button" onclick="$files.removeFile(this)"><img src="${$url.getHostUrl()}/images/common/close.svg"></button>
                    </div>`;

        inputClass.append(template);
    },

    getFiles: function () {
        return Array.from(this.filesMap.values());
    },
}

$url = {
    getPath: function (extPath) {
        if (extPath && extPath[0] != '/') extPath = '/' + extPath;
        return location.pathname + (extPath ? extPath : '');
    },
    getHostUrl: function () {
        return location.protocol + "//" + this.getHost();
    },
    getHost: function () {
        return location.host;
    },
    redirect: function (path) {
        location.href = path ? path : this.getPath();
    },
    gotoUrl: function (id, url) {
        localStorage["clickMenus"] = id;
        location.href = url;
    }
}

$ajax = {
    defaultOption: {
        url: $url.getPath(),
        contentType: 'application/json',
    },

    post: function (options) {
        options = $.extend({}, this.defaultOption, options);

        $.ajax({
            url: options.url,
            type: 'POST',
            data: JSON.stringify(options.data),
            contentType: options.contentType,
        }).done(function (data) {
            if (options.success) {
                options.success(data);
            } else {
                $url.redirect();
            }
        }).fail(function (error) {
            if (options.error) {
                options.error(error.responseJSON);
            } else {
                $errors.valid(error.responseJSON);
            }
        });
    },
    postMultiPart: function (options) {
        options = $.extend({}, this.defaultOption, options);

        $.ajax({
            url: options.url,
            type: 'POST',
            data: options.data,
            processData: false,
            contentType: false,
        }).done(function (data) {
            if (options.success) {
                options.success(data);
            } else {
                $url.redirect();
            }
        }).fail(function (error) {
            if (options.error) {
                options.error(error.responseJSON);
            } else {
                $errors.valid(error.responseJSON);
            }
        });
    },
    put: function (options) {
        options = $.extend({}, this.defaultOption, options);

        $.ajax({
            url: options.url,
            type: 'PUT',
            data: JSON.stringify(options.data),
            contentType: options.contentType,
        }).done(function (data) {
            if (options.success) {
                options.success(data);
            } else {
                $url.redirect();
            }
        }).fail(function (error) {
            if (options.error) {
                options.error(error.responseJSON);
            } else {
                $errors.valid(error.responseJSON);
            }
        });
    },
    putMultiPart: function (options) {
        options = $.extend({}, this.defaultOption, options);

        $.ajax({
            url: options.url,
            type: 'PUT',
            data: options.data,
            processData: false,
            contentType: false,
        }).done(function (data) {
            if (options.success) {
                options.success(data);
            } else {
                $url.redirect();
            }
        }).fail(function (error) {
            if (options.error) {
                options.error(error.responseJSON);
            } else {
                $errors.valid(error.responseJSON);
            }
        });
    },
    get: function (options) {
        options = $.extend({}, this.defaultOption, options);

        $.ajax({
            url: options.url,
            type: 'GET',
            contentType: options.contentType,
        }).done(function (data) {
            if (options.success) {
                options.success(data);
            } else {
                $url.redirect();
            }
        }).fail(function (error) {
            if (options.error) {
                options.error(error.responseJSON);
            } else {
                $errors.valid(error.responseJSON);
            }
        });
    },

    delete: function (options) {
        options = $.extend({}, this.defaultOption, options);

        $.ajax({
            url: options.url,
            type: 'DELETE',
            data: JSON.stringify(options.data),
            contentType: options.contentType,
        }).done(function (data) {
            alert("삭제 되었습니다.");

            if (options.success) {
                options.success(data);
            } else {
                $url.redirect();
            }
        }).fail(function (error) {
            if (options.error) {
                options.error(error.responseJSON);
            } else {
                console.log(error);
                $errors.valid(error.responseJSON);
            }
        });
    }
}

$api = {
    addressApi: function (zipCodeId = 'zipCode', addressId = 'roadAddress', detailId = 'detailAddress') {
        new daum.Postcode({
            oncomplete: function (data) { //선택시 입력값 세팅
                document.getElementById(zipCodeId).value = data.zonecode; // 주소 넣기
                document.getElementById(addressId).value = data.address; // 주소 넣기
                document.getElementById(detailId).focus();
            }
        }).open();
    }
}

$search = {
    DATE: 'DATE', ALL: 'ALL',

    search: function (type) {
        switch (type) {
            case this.DATE :
                this.dateSearch();
                break;

            case this.ALL :
                this.allSearch();
                break;
        }
    },

    dateSearch: function () {
        var searchArr = this.findChildren($("[search='search']"));
        var dateObj = {};

        searchArr.forEach(search => {
            switch (search.tagName.toLowerCase()) {
                case "input" :
                    if (search.type !== 'date') {
                        $(search).prop('value', '');
                    } else {
                        dateObj[search.id] = search.value;
                    }
                    break;

                case "select" :
                    $(search).children(":eq(0)").prop("selected", true);
                    break;
            }
        })

        if (this.dateValidation(dateObj)) {
            if ($("form").length > 1) {
                $("form")[0].submit();
            } else {
                $("form").submit();
            }
        }
    },

    allSearch: function () {
        var searchArr = this.findChildren($("[search='search']"));
        var dateObj = {};

        searchArr.forEach(search => {
            if (search.type == 'date') {
                dateObj[search.id] = search.value;
            }
        })

        if (this.dateValidation(dateObj)) {
            $("form").submit();
        }
    },

    init: function () {
        $url.redirect();
    },

    dateValidation: function (dateObj) {
        var result = true;
        if ((dateObj["startDate"] != "" && dateObj["endDate"] != "")
            && (dateObj["startDate"] > dateObj["endDate"])) {
            alert("날짜를 다시 입력해 주세요.");
            result = false;
        }
        return result;
    },

    findChildren: function (parent) {
        var _this = this;
        var searchList = [];

        $.each(parent, function () {
            switch (this.tagName.toLowerCase()) {
                case "div" :
                    var childList = _this.findChildren($(this).children());
                    childList.forEach(child => searchList.push(child));
                    break;

                case "input" :
                case "select" :
                    searchList.push(this);
                    break;
            }
        })

        return searchList;
    }
}

$valid = {
    delete: function () {
        return confirm("삭제 하시겠습니까?")
    },
    deletes: function (selectCondition) {
        if (!selectCondition) {
            alert("삭제할 항목을 선택해 주세요.");
        } else {
            return confirm("선택된 항목을 삭제 하시겠습니까?")
        }
    },
    duplicate: function (option) {
        var result = true;
        $.ajax({
            url: option.url,
            type: 'POST',
            data: JSON.stringify(option.data),
            contentType: 'application/json',
            async: false,
        }).done(function (data) {
            result = data;
        });

        return result
    }
}

$checkBox = {
    init: function () {
        $('input:checkbox[check="all"]').on('click', function () {
            const table = $(this).closest('table');
            table.find('input:checkbox').prop("checked", $(this).is(":checked"));
        });
    },
    getAllChecked: function (target = $("table")) {
        var result = [];
        target.find('input:checkbox[check!="all"]:checked').each(function () {
            result.push($(this).val());
        });

        return result;
    }
}

$form = {
    getData: function (target = $("form")) {
        const formData = new FormData(target[0]);

        if ($("input[type='file']").length) {
            formData.delete("files");

            for (const file of $files.getFiles()) {
                formData.append("files", file);
            }

            return formData;
        } else {
            return Object.fromEntries(formData);
        }
    }
}

$table = {
    addRow: function (targetId) {
        const target = $("#" + targetId);
        const lastRow = target.find('tr').last().clone();
        const idx = target.find('tr').length;

        $(lastRow).find(".input-mast-btn").remove();
        $(lastRow).find("input[input-mast-id]").remove();

        $.each(lastRow.find('input, textarea, select'), function () {
            $(this).removeAttr("style");
            $(this).removeAttr("readonly")
            $(this).removeAttr("disabled")
            if (this.type == 'checkbox') {
                $(this).prop("checked", false);
                this.name = idx;
                this.value = idx;
            } else {
                this.value = "";
            }

            if (this.id) {
                this.id = this.id.split("_")[0] + "_" + idx;
            }

            if ($(this).prop('tagName') == 'SELECT') {
                $(this).find("option").removeAttr("selected");
                $(this).find("option:eq(0)").prop("selected", true);
            }
        });

        target.append(lastRow);
        $event.init();

        return lastRow;
    },

    delRow: function (targetId, firstRowDel=true) {
        const target = $("#" + targetId);
        const checkedList = $checkBox.getAllChecked(target);

        if ($valid.deletes(checkedList.length)) {
            let rowCnt = $(target).find('tr:not(:first)').length;


            if (firstRowDel) {
                deletRows();
            } else {
                if (rowCnt === checkedList.length) {
                    $(target).find('tr:not(:lt(2))').each(function () {
                        $(this).remove();
                    });
                } else {
                    deletRows();
                }
            }
        }

        function deletRows() {
            $.each(checkedList, function (idx, value) {
                const row = target.find("input[name='" + value + "']").closest('tr');
                row.remove();
            });
        }
    },

    getData: function (targetId, type = "all") {
        const HEADER_IDX = 0;
        const target = $("#" + targetId);
        const result = [];

        $.each(target.find('tr'), function (idx, row) {
            if (idx != HEADER_IDX) {
                if (type == "checked" && !$(row).find(">:first-child input").prop("checked")) {
                    return;
                }

                const rowData = $(row).find('input[type!="checkbox"], textarea, select');

                const obj = {};
                let noValueCnt = 0;
                $.each(rowData, function () {
                    if (!this.value) {
                        noValueCnt++;
                    }

                    obj[this.name] = this.value;
                });

                if (rowData.length != noValueCnt) {
                    result.push(obj);
                }
            }
        });

        return result;
    },
}

$autoSearch = (function () {
    const template = `
            <div id="autoSearch" 
                class="autoSearch fade" 
                style="position: absolute; 
                z-index: 1000;">                
            </div>`;

    const init = function (config) {
        this.target = '';
        this.targetKey = config.targetKey;
        this.dataList = config.dataList;
        this.itemMap = config.itemMap;
        this.callback = config.callback;
        this.maskKey = config.maskKey;

        this.initEvent = function () {
            if (!$("#autoSearch").length) {
                $("body").append(template);
            }


            let inputTarget = $("[auto-search='" + this.targetKey + "']");
            inputTarget.blur(function () {
                if (!$("#autoSearch").is(":hover")) {
                    close();
                }
            });

            const _this = this;
            inputTarget.keyup(function () {
                setTarget(this);

                if (!this.disabled) {
                    addItem(_this, this.value);
                }

                if (!$("#autoSearch").children().length) {
                    close();
                }

                if (!$(this).val().length) {
                    close();
                }
            });
        };
    };

    const setTarget = function (target) {
        this.target = $(target);
    };

    const getTarget = function () {
        return this.target;
    };

    const addItem = function (config, value) {
        $("#autoSearch").html("");
        const itemMap = config.itemMap
        $.each(config.dataList, function () {
            if (this[itemMap.searchKey].includes(value)) {
                let itemGroup = "<div class=\"search-item\">";
                for (const key of Object.keys(itemMap)) {
                    if (key == "id") {
                        itemGroup += `<input name="${itemMap[key]}" type="hidden" value="${this[itemMap.id]}" />`;
                    } else {
                        let value = this[itemMap[key]];

                        if (!value) {
                            value = "";
                        }

                        itemGroup += `<div style="display: table-cell"><span name="${itemMap[key]}">${value.replace(/(\n|\r\n)/g, '<br>')}</span></div>`
                    }
                }

                itemGroup += "</div>";
                $("#autoSearch").append(itemGroup);
            }
        });

        $(".search-item").click(function () {
            close();
            itemClick(config, this);
        });

        const target = getTarget();
        show(target.offset().left, parseInt(target.css("height")) + target.offset().top);
    };

    const show = function (x, y) {
        $("#autoSearch").css("left", x);
        $("#autoSearch").css("top", y);
        $("#autoSearch").addClass("open");
        $("#autoSearch").removeClass("fade");
    };

    const close = function () {
        $("#autoSearch").addClass("fade");
        $("#autoSearch").removeClass("open");
        $("#autoSearch.fade").click(function (e) {
            e.preventDefault();
        })
    };

    const itemClick = function (config, item) {
        const itemMap = config.itemMap;
        const dataList = config.dataList;
        let result = {};

        let data = dataList.find(data => {
            return data[itemMap.id] == $(item).children(`input[name='${itemMap.id}']`).val();
        });

        if (config.maskKey) {
            getTarget().before(`<input type="hidden" name="${itemMap.id}" value="${data[itemMap.id]}" input-mast-id />`)
        }

        result.target = getTarget();
        result.values = data;

        if (config.callback) {
            config.callback(result);

            if (config.maskKey) {
                inputMask(config.maskKey);
            }
        }
    };

    const inputMask = function (maskKey) {
        for (const key of maskKey) {
            let maskTarget = {};

            if (key == $(getTarget()).attr("name")) {
                maskTarget = $(getTarget());
                maskTarget.after(`<img class="input-mast-btn" style="width: 10px" 
                                        src="${$url.getHostUrl()}/images/common/close.svg" 
                                        input-mask-remove />`)
            } else {
                maskTarget = $(getTarget()).siblings(`[name='${key}']`);
            }

            maskTarget.attr("disabled", true);
            maskTarget.addClass("input-mask")
        }

        $("img[input-mask-remove]").click(function () {
            $(this).siblings("input[type='hidden']").remove();

            $(this).siblings("input[disabled]").each(function () {
                $(this).val("");
                $(this).removeClass("input-mask");
                $(this).attr("disabled", false);
            });

            this.remove();
        });
    };

    return function (_config) {
        const builder = $.extend(true, {}, _config);
        return new init(builder);
    };
})();

$formatter = {
    init: function () {
        this.userIdFormatter();
        this.yearFormatter();
        this.emailFormatter();
        this.phoneNumFormatter();
    },

    userIdFormatter: function () {
        $("input[formatter='userId']").keyup(function () {
            this.value = parse(this.value);
        });

        const parse = function (value) {
            if (!value) {
                return "";
            }

            return value.replace(/[^a-z0-9]/g, "");
        }
    },

    emailFormatter: function () {
        $("input[formatter='email']").keyup(function () {
            this.value = parse(this.value);
        });

        const parse = function (value) {
            if (!value) {
                return "";
            }

            return value.replace(/[^a-z0-9@._-]/g, "");
        }
    },

    yearFormatter: function () {
        $("input[formatter='year']").keyup(function () {
            $(this).attr("maxlength", 4);
            this.value = parse(this.value);
        });

        const parse = function (value) {
            if (!value) {
                return "";
            }
            return value.replace(/[^0-9]/g, "");
        }
    },

    phoneNumFormatter: function () {
        $("input[formatter='phoneNum']").keyup(function () {
            $(this).attr("maxlength", 13);
            this.value = parse(this.value);
        });

        const parse = function (value) {
            if (!value) {
                return "";
            }

            value = value.replace(/[^0-9]/g, "");

            let result = [];
            let restNumber = "";

            if (value.startsWith("02")) {
                result.push(value.substring(0, 2));
                restNumber = value.substring(2);
            } else if (value.startsWith("1")) {
                restNumber = value;
            } else {
                result.push(value.substring(0, 3));
                restNumber = value.substring(3);
            }

            if (restNumber.length === 7) {
                result.push(restNumber.substring(0, 3));
                result.push(restNumber.substring(3));
            } else {
                result.push(restNumber.substring(0, 4));
                result.push(restNumber.substring(4));
            }

            return result.filter((val) => val).join("-");
        };
    },
};

$(document).ready(function () {
    $event.init();

    var pageFunctionName = "pageObj";
    if (window[pageFunctionName] && window[pageFunctionName].pageStart) {
        window[pageFunctionName].pageStart();
    }
});
