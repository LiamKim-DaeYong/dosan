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
        $ajax.post({data: modal.getData()})
    },

    edit: function () {
        $ajax.put({
            url: $url.getPath($("#id").val()),
            data: modal.getData()
        })
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
