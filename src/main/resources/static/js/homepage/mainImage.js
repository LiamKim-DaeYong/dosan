var pageObj = {
    save: function () {
        var form = $("form")[0];
        var formData = new FormData(form);

        $.ajax({
            url: $url.getPath(),
            type: "POST",
            data: JSON.stringify(formData),
            enctype: 'multipart/form-data',
            cache : false,
            processData: false,
            contentType: false,
            success: function (data) {
            },
            error: function (error) {
                $errors.valid(error);
            }
        });
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

pageObj.pageStart = function () {
};
