var pageObj = {
    check: function (id) {
        $ajax.put({data: id, url: $url.getPath()+'/check'});
    },

    print: function () {
        var allChecked = $checkBox.getAllChecked();
        console.log(allChecked)
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(!allChecked.length)){
            $ajax.delete({data: allChecked})
        }
    },
}

pageObj.pageStart = function () {
};