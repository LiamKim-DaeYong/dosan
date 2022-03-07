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
        $api.post({data: modal.getData()})
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
        height: 200,
        width: 400
    })
}