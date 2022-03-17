var pageObj = {
    save: function () {
        var dataArr = $("form").serializeArray();

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.post({data: data, success: function (id) {
                    location.href = `/data/promotion`;
                }
            })
        }
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