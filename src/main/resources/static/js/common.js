var jsObj = {};

jsObj.api = {
    addressApi: function (zipCodeId, addressId, detailId) {
        new daum.Postcode({
            oncomplete: function (data) { //선택시 입력값 세팅
                document.getElementById(zipCodeId).value = data.zonecode; // 주소 넣기
                document.getElementById(addressId).value = data.address; // 주소 넣기
                document.getElementById(detailId).focus();
            }
        }).open();
    }
}

jsObj.errors = {
    valid: function (errors) {
        if (errors && errors.length) {
            $("[errors]").text("");
            $("[errorclass]").each(function () {
                $(this).removeClass($(this).attr("errorclass"));
            });

            errors.forEach(error => {
                var errorField = $("#" + error.field);
                errorField.addClass(errorField.attr("errorclass"));

                var errorMsgField = $('[errors="' + error.field + '"]')
                errorMsgField.text(error.message);
            })
        }
    }
}

jsObj.modal = function () {
    var init = function (element, options) {
        this.defaultOption = {
            height: 400,
            width: 200,
            title: 'title',
            content: '<div>Modal Body</div>'
        }

        this.defaultOption = $.extend({}, this.defaultOption, options);

        this.target = $(element);
        this.$content = this.target.find('.modal-content');
        this.$title = this.target.find('.modal-header h5');
        this.$body = this.target.find('.modal-body');
        this.$content.css({'height': this.defaultOption.height, 'width': this.defaultOption.width});

        this.setTitle(this.defaultOption.title);
        this.setContent(this.defaultOption.content);
    };

    var open = function (options) {
        this.defaultOption = $.extend({}, this.defaultOption, options);

        this.setTitle(this.defaultOption.title);
        this.setContent(this.defaultOption.content);

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

        return JSON.stringify(result);
    };

    var setContent = function (content) {
        this.defaultOption.content = content;
        $(this.$body).html(content);
        jsObj.event.init();
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

jsObj.editor = {
    init: function (targetId) {
        var oEditors = [];
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: targetId,
            sSkinURI: "../lib/smarteditor/js/SmartEditor2Skin.html",
            fCreator: "createSEditor2"
        });
    }
}

jsObj.event = {
    init: function () {
        this.inputAutocompleteOff();
        this.initCheckboxEvent();
    },

    inputAutocompleteOff: function () {
        $('input').prop("autocomplete", "off");
    },

    initCheckboxEvent: function () {
        $('input:checkbox[check="all"]').on('click', function () {
            $('input:checkbox').prop("checked", $(this).is(":checked"));
        });
    }
}

jsObj.data = {
    getAllChecked: function () {
        var result = [];
        $('input:checkbox[check!="all"]:checked').each(function () {
            result.push($(this).val());
        });

        return result;
    },
}

$(document).ready(function () {
    jsObj.event.init();

    if (window["page"] && window["page"].pageStart) {
        window["page"].pageStart();
    }
});
