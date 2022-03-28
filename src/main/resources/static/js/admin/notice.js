var pageObj = {
    init: function () {
        $editor.init("content");
    },

    save: function () {
        $ajax.postMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/notice/${id}`);
            }
        })
    },

    update: function () {
        $ajax.putMultiPart({
            data: $form.getData(),
            success: function (id) {
                $url.redirect(`/admin/notice/${id}`);
            }
        });
    }
}

pageObj.pageStart = function () {
    pageObj.init();
}
