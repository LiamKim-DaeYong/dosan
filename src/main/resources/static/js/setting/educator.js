var pageObj = {
    openAddPage: function (title) {
        modal.open({
            path: $url.getPath('add'),
            title: `${title} 등록`
        });
    },

    openEditPage: function (id, title) {
        modal.open({
            path: $url.getPath(`${id}/edit`),
            title: `${title} 수정`
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

        if ($valid.deletes(!allChecked.length)){
            $api.delete({data: allChecked})
        }
    }
}

var modal = $modal();
pageObj.pageStart = function () {
    pageObj.path = location.pathname;

    modal.init($("#modal"), {
        height: 200,
        width: 400
    });
};
