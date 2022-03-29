var pageObj = {
    init: function () {
        $editor.init("content");
    },

    save: function (type) {
        $ajax.postMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/homepage/board/${type}/${id}/detail`);
            }
        })
    },

    update: function (type) {
        $ajax.putMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/homepage/board/${type}/${id}/detail`);
            }
        });
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(!allChecked.length)){
            $ajax.delete({data: allChecked, url: $url.getPath()+'/delete'})
        }
    }
}

pageObj.pageStart = function () {
    pageObj.init();
};
