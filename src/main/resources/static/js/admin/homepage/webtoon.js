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
                location.href = `/admin/homepage/media-archive/webtoon/${id}/detail`;
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
                location.href = `/admin/homepage/media-archive/webtoon/${id}/detail`;
            }
        })
    }
}

pageObj.pageStart = function () {
};
