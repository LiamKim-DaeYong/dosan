$errors = {
    valid: function (errors) {
        const fieldErrors = errors["fieldErrors"];

        if (fieldErrors) {
            $("[errors]").remove();
            $("[errorclass]").each(function () {
                $(this).removeClass($(this).attr("errorclass"));
            });

            fieldErrors.forEach(error => {
                const errorField = $("#" + error.field);
                errorField.addClass(errorField.attr("errorclass"));

                let errorMsgField
                if (errorField.attr("editor")) {
                    errorMsgField = $('[placeholder="' + error.field + '"]')
                    errorMsgField.text(error.message);
                } else {
                    errorMsgField = `<span errors="${error.field}" class="modal_error">${error.message}</span>`
                    errorField.after(errorMsgField);
                }
            });
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
                onImageUpload : function(file) {
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
        $checkBox.init();
        $files.init();
    },

    inputAutocompleteOff: function () {
        $('input').prop("autocomplete", "off");
    }
}

$files = {
    filesMap: new Map(),
    init: function () {
        var _this = this;

        $(".file-input").on("change", function() {
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
                        <button type="button" onclick="$files.removeFile(this)">제거</button>
                        <span>${filename}</span>
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
                        <button type="button" onclick="$files.removeFile(this)">제거</button>
                        <span>${filename}</span>
                    </div>`;

        inputClass.append(template);
    },

    getFiles: function () {
        return Array.from(this.filesMap.values());
    },
}

$url = {
    getPath: function (extPath) {
        if(extPath && extPath[0] != '/') extPath = '/' + extPath;
        return location.pathname + (extPath? extPath : '');
    },
    getHost: function () {
        return location.hostname;
    },
    redirect: function (path) {
        location.href = path?path:this.getPath();
    },
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

    delete: function (options) {
        options = $.extend({}, this.defaultOption, options);

        $.ajax({
            url: options.url,
            type: 'DELETE',
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
                console.log(error);
                $errors.valid(error.responseJSON);
            }
        });
    }
}

$api = {
    addressApi: function (zipCodeId='zipCode', addressId='roadAddress', detailId='detailAddress') {
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
            $("form").submit();
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

                case "input" : case "select" :
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
    getAllChecked: function (target=$("table")) {
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
        const target = $("#" +targetId);
        const lastRow = target.find('tr').last().clone();
        const idx = target.find('tr').length;

        $.each(lastRow.find('input, textarea'), function () {
            if (this.type == 'checkbox') {
                $(this).prop("checked", false);
                this.name = idx;
                this.value = idx;
            } else {
                this.value = "";
            }

            if (this.id) {
                const newId = this.id.split("_")[0] + "_" + idx;

                if ($(this).is("[auto-search]")) {
                    const autoSearchTarget = lastRow.find("[auto-search-target='" + this.id + "']");
                    $(autoSearchTarget).attr("auto-search-target", newId);
                }

                this.id = newId;
            }
        });

        target.append(lastRow);
    },

    delRow: function (targetId) {
        const target = $("#" +targetId);
        const checkedList = $checkBox.getAllChecked(target);

        if ($valid.deletes(checkedList.length)) {
            $.each(checkedList, function (idx, value) {
                const row = target.find("input[name='" + value + "']").closest('tr');
                row.remove();
            });
        }
    },

    getData: function (targetId, type="all") {
        const HEADER_IDX = 0;
        const target = $("#" +targetId);
        const result = [];

        $.each(target.find('tr'), function (idx, row) {
            if (idx != HEADER_IDX) {
                if (type == "checked" && !$(row).find(">:first-child input").prop("checked")) {
                    return;
                }

                const rowData = $(row).find('input[type!="checkbox"], textarea');

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

$autoSearch = {
    dataList: [],
    target: '',
    init: function (dataList) {
        this.dataList = dataList;

        const _this = this;
        $("[auto-search]").keyup(function () {
            const x = $(this).offset().left;
            const y = $(this).offset().top;
            _this.show($(this), x, y);

            if (!$(this).val().length) {
                _this.close();
            }

            $(this).focusout(function () {
                $('html').click(function (e) {
                    if (!($(e.target).parent('div').attr("id") == "autoSearch")) {
                        _this.close();
                    }
                });
            });

        });

        if (!$("#autoSearch").length) {
            $("body").append(`<div id="autoSearch" class="autoSearch" style="position: absolute; z-index: 1000; display: none"></div>`);
        }
    },
    show: function (target, x, y) {
        this.target = target;
        $("#autoSearch").html("");
        $.each(this.dataList, function () {
            if (this.key.includes(target.val())) {
                $("#autoSearch").append(`
                    <div onclick="$autoSearch.item_click('${this.key}', '${this.value}')">${this.key} | ${this.value}</div>
                `)
            }
        });

        $("#autoSearch").css("height", "100");
        $("#autoSearch").css("width", "100");
        $("#autoSearch").css("left", x);
        $("#autoSearch").css("top", parseInt(target.css("height")) + y);
        $("#autoSearch").css("display", "block");
    },
    item_click: function(key, value) {
        this.target.val(key);
        $('[auto-search-target="' + this.target.attr("id") + '"]').val(value);
        this.close();
    },
    close: function () {
        $("#autoSearch").css("display", "none");
    },
    setData: function (list) {
        this.dataList = list;
    },
}

$(document).ready(function () {
    $event.init();

    var pageFunctionName = "pageObj";

    if (window[pageFunctionName] && window[pageFunctionName].pageStart) {
        window[pageFunctionName].pageStart();
    }
});
