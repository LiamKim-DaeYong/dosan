var pageObj = {
    save: function () {
        var form = $("form")[0];
        var formData = new FormData(form);

        $ajax.postMultiPart({
            data: formData,
            enctype: 'multipart/form-data',
            cache : false,
            processData: false,
            contentType: 'multipart/form-data',
            success: function (id) {
                location.href = `/admin/homepage/main-image/${id}/detail`;
            }
        })
    },

    update: function () {
        var form = $("form")[0];
        var formData = new FormData(form);

        $ajax.postMultiPart({
            data: formData,
            enctype: 'multipart/form-data',
            cache : false,
            processData: false,
            contentType: 'multipart/form-data',
            success: function (id) {
                location.href = `/admin/homepage/main-image/${id}/detail`;
            }
        })
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
