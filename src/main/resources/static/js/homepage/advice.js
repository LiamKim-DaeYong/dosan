var pageObj = {
    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(!allChecked.length)){
            $ajax.delete({data: allChecked})
        }
    },

    check: function (id) {
        $ajax.put({data: id, url: $url.getPath()+'/check'});
    }
}

pageObj.pageStart = function () {
};