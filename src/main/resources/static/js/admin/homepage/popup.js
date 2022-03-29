var pageObj = {
    save: function () {
        $ajax.postMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/homepage/popup/${id}/detail`)
            }
        });
    },

    update: function () {
        $ajax.putMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/homepage/popup/${id}/detail`)
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
};
