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
            })
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
        var oEditors = [];
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: targetId,
            sSkinURI: "../lib/smarteditor/SmartEditor2Skin.html",
            fCreator: "createSEditor2"
        });
    }
}

$event = {
    init: function () {
        this.inputAutocompleteOff();
        this.initCheckboxEvent();
        this.fileInputInit();
    },

    inputAutocompleteOff: function () {
        $('input').prop("autocomplete", "off");
    },

    initCheckboxEvent: function () {
        $('input:checkbox[check="all"]').on('click', function () {
            $('input:checkbox').prop("checked", $(this).is(":checked"));
        });
    },

    fileInputInit: function () {
        $(".file-input").on("change", function(){
            var filename = $(this).val();
            var fileText = filename.split("\\");
            $(".filename").text(fileText[2]);
        })
    },
}

$checkBox = {
    getAllChecked: function () {
        var result = [];
        $('input:checkbox[check!="all"]:checked').each(function () {
            result.push($(this).val());
        });

        return result;
    }
}

$valid = {
    delete: function () {
        return confirm("삭제 하시겠습니까?")
    },
    deletes: function (condition) {
        if (condition) {
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

$(document).ready(function () {
    $event.init();

    var pageFunctionName = "pageObj";

    if (window[pageFunctionName] && window[pageFunctionName].pageStart) {
        window[pageFunctionName].pageStart();
    }
});
