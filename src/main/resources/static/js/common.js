$errors = {
    valid: function (errors) {
        var fieldErrors = errors["fieldErrors"];

        if (fieldErrors) {
            $("[errors]").text("");
            $("[errorclass]").each(function () {
                $(this).removeClass($(this).attr("errorclass"));
            });

            fieldErrors.forEach(error => {
                var errorField = $("#" + error.field);
                errorField.addClass(errorField.attr("errorclass"));

                var errorMsgField = $('[errors="' + error.field + '"]')
                errorMsgField.text(error.message);
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

/**
 * 에디터 뷰
 */
// $editor = {
//     /**
//      * 뷰 초기화
//      * @param targetId Html 요소 ID
//      */
//     init: function (targetId) {
//         this.targetId = targetId;
//         this.target = [];
//         nhn.husky.EZCreator.createInIFrame({
//             oAppRef: this.target,
//             elPlaceHolder: targetId,
//             sSkinURI: "/lib/smarteditor/SmartEditor2Skin.html",
//             fCreator: "createSEditor2"
//         });
//     },
//     /**
//      * 에디터 내용 조회
//      * @returns Html 문자열
//      */
//     getData: function() {
//         pageObj.editor.target.getById[this.targetId].exec("UPDATE_CONTENTS_FIELD");
//         return $('#' + this.targetId).val();
//     }
// }

$editor = {
    init: function (targetId) {
        const target = $("#" + targetId);
        target.summernote({
            height: 500,
            placeholder: '<span errors="content" className="modal_error"></span>',
            callbacks: {
                onImageUpload : function(file) {
                    const img = new FormData();
                    img.append("file", file[0]);
                    console.log(file)

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
    },
}

/**
 * 첨부 파일
 */
$multifile = {
    init: function(targetId, targetListId) {
        let _this = this;
        this.targetId = targetId;
        this.target = $('#' + targetId);
        this.targetListId = targetListId;
        this.targetList = $('#' + targetListId);

        this.serverFiles = [];
        this.newFiles = [];
        this.target = $('#' + targetId).MultiFile({
            list: `#` + targetListId,
            STRING: {
                file: '<span class="filename" onclick="$(this).parent().prev().click()">$file</span>',
                remove: '<img src="/lib/multifile/img/delete.png" height="16" width="16" alt="x"/>',
                duplicate: '$file은 이미 존재합니다.',
            },
            onFileAppend: _this.onFileAppend.bind(_this),
        })
    },
    onFileAppend: function(element, value, master_element) {
        this.newFiles = master_element.files
    },
    getData: function(type) {
        if(type == "new") {
            return this.newFiles;
        } else if (type == "notDeleted") {
            return this.serverFiles.filter(f => Boolean(f.__delete__));
        } else if (type == "deleted") {
            return this.serverFiles.filter(f => !Boolean(f.__delete__));
        } else if (type == "server") {
            return this.serverFiles;
        }
    },
    deleteServerFile: function(target, fileObject) {
        fileObject.__delete__ = true;
        console.log('서버 파일 삭제 > ', fileObject);
        target.remove();
    },
    downloadFile: function(target, fileObject) {
        console.log('서버 파일 다운로드 > ', fileObject);
    },
    setData: function(dataList) {
        this.serverFiles = dataList;
        dataList.forEach(row => {
            let fileEl = this.createFileRow(row.filename, row);
            this.targetList.append(fileEl);
        })
    },
    createFileRow: function(filename, fileObject) {
        // `<div class="MultiFile-label">
        //     <a class="MultiFile-remove" href="#attachment" onclick="console.log('on delete')"><img src="/lib/multifile/img/delete.png" height="16" width="16" alt="x"></a>
        //     <span>
        //         <span class="MultiFile-label" title="File selected: 다운로드.jpg">
        //             <span class="MultiFile-title">
        //                 <em title="Click to remove" onclick="console.log('download')">사전 다운로드.jpg</em>
        //             </span>
        //         </span>
        //     </span>
        // </div>`
        let rootEL = document.createElement('div');
        rootEL.classList.add('MultiFile-label');
        let deleteEl = document.createElement('a');
        deleteEl.classList.add('MultiFile-remove');
        deleteEl.href = '#attachment';
        deleteEl.addEventListener('click', () => this.deleteServerFile(rootEL, fileObject));
        rootEL.appendChild(deleteEl);
        let deleteIconEl = document.createElement('img');
        deleteIconEl.src = '/lib/multifile/img/delete.png';
        deleteIconEl.height = 16;
        deleteIconEl.width = 16;
        deleteIconEl.alt = 'x';
        deleteEl.appendChild(deleteIconEl);
        let fileEl = document.createElement('span');
        rootEL.appendChild(fileEl);
        let fileLabelEl = document.createElement('span');
        fileLabelEl.classList.add('MultiFile-label')
        fileEl.appendChild(fileLabelEl);
        let fileTitleEl = document.createElement('span')
        fileTitleEl.classList.add('MultiFile-title')
        fileLabelEl.appendChild(fileTitleEl);
        let filenameEl = document.createElement('em');
        filenameEl.appendChild(document.createTextNode(filename));
        filenameEl.addEventListener('click', () => this.downloadFile(rootEL, fileObject));
        fileLabelEl.appendChild(filenameEl);
        console.log('rootEl', rootEL)
        return rootEL;
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

        delete this.filesMap[filename];
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

        if ($(".file-input")) {
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

$(document).ready(function () {
    $event.init();

    var pageFunctionName = "pageObj";

    if (window[pageFunctionName] && window[pageFunctionName].pageStart) {
        window[pageFunctionName].pageStart();
    }
});
