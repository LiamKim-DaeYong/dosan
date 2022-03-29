var pageObj = {
    save: function () {
        console.log($form.getData())
        $ajax.postMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/homepage/main-image/${id}/detail`)
            }
        });
    },

    update: function () {
        $ajax.putMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/homepage/main-image/${id}/detail`)
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
