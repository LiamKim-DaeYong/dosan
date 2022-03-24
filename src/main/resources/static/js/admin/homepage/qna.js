var pageObj = {
    comment: {
        save: function (id) {
            var data = $("#comment").val();
            $ajax.post({data: {comment: data}, url: `/admin/homepage/qna/${id}/comment`});
        },

        update: function () {
            $("#commentY").hide();
            $("#commentN").show();
        },

        delete: function (id) {
            $ajax.delete({url: `/admin/homepage/qna/${id}/comment/delete`});
        }
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(!allChecked.length)){
            $ajax.delete({data: allChecked, url: $url.getPath()+'/delete'})
        }
    }
}

pageObj.pageStart = function () {
};
