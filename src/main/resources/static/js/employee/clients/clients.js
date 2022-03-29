var pageObj = {

    save: function () {
        var data = $form.getData();
        data["address"] = {
                'zipCode': data.zipCode,
                'roadAddress': data.roadAddress,
                'detailAddress': data.detailAddress
            }

        $ajax.post({
            data: data,
            success: function (id) {
                $url.redirect(`/employee/clients/${id}/detail`);
            }
        });
    },

    edit: function () {
        var data = $form.getData();
        data["address"] = {
            'zipCode': data.zipCode,
            'roadAddress': data.roadAddress,
            'detailAddress': data.detailAddress
        }

        $ajax.put({
            data: data,
            success: function (id) {
                $url.redirect(`/employee/clients/${id}/detail`);
            }
        })
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(allChecked.length)) {
            $ajax.delete({
                data: allChecked,
                url: $url.getPath()
            })
        }
    }
}

pageObj.pageStart = function () {
};