var pageObj = {
    openAddPage: function () {
        modal.open({
            path: $url.getPath('add'),
            title: "전직 등록"
        });
    },

    openEditPage: function (id) {
        modal.open({
            path: $url.getPath(`${id}/edit`),
            title: "전직 수정"
        });
    },

    save: function () {
        var option = {
            data: modal.getData(),
            url: $url.getPath('duplicate')
        };

        if (!$valid.duplicate(option)) {
            $api.post({data: modal.getData()})
        } else {
            $("[errors]").text("");
            $("[errorclass]").each(function () {
                $(this).removeClass($(this).attr("errorclass"));
            });

            var errorField = $("#formerName");
            errorField.addClass(errorField.attr("errorclass"));

            var errorMsgField = $(`[errors='formerName']`);
            errorMsgField.text("이미 존재하는 전직입니다.");
        }
    },

    edit: function () {
        $api.put({data: modal.getData()})
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(!allChecked.length)) {
            $api.delete({data: allChecked});
        }
    }
}

var modal = $modal();
pageObj.pageStart = function () {
    pageObj.path = location.pathname;
    modal.init($("#modal"), {
        width: 400
    })
}