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
        $ajax.post({data: modal.getData()})
    },

    edit: function () {
        $ajax.put({data: modal.getData()})
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(!allChecked.length)){
            $ajax.delete({data: allChecked})
        }
    }
}

var modal = $modal();
pageObj.pageStart = function () {
    modal.init($("#modal"), {
        width: 400
    });
};
