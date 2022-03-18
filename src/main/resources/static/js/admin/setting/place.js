var pageObj = {
    openAddPage: function () {
        modal.open({
            path: $url.getPath('add'),
            title: "수련 장소 등록"
        });
    },

    openEditPage: function (id) {
        modal.open({
            path: $url.getPath(`${id}/edit`),
            title: "수련 장소 수정"
        });
    },

    save: function () {
        var validData = {
            data: modal.getData(),
            url: $url.getPath('duplicate')
        };

        if (!$valid.duplicate(validData)) {
            $ajax.post({data: modal.getData()})
        } else {
            $("[errors]").text("");
            $("[errorclass]").each(function () {
                $(this).removeClass($(this).attr("errorclass"));
            });

            var errorField = $("#location");
            errorField.addClass(errorField.attr("errorclass"));

            var errorMsgField = $(`[errors='location']`);
            errorMsgField.text("이미 존재하는 장소입니다.");
        }
    },

    edit: function () {
        var validData = {
            data: modal.getData(),
            url: $url.getPath('valid')
        };

        if ($valid.duplicate(validData)) {
            $ajax.put({data: modal.getData()})
        }
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(!allChecked.length)) {
            $ajax.delete({data: allChecked});
        }
    }
}

var modal = $modal();
pageObj.pageStart = function () {
    modal.init($("#modal"), {
        width: 400
    })
}
