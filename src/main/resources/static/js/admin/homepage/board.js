var pageObj = {
    save: function (type) {
        var dataArr = $("form").serializeArray();

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.post({data: data, success: function (id) {
                    location.href = `/admin/homepage/board/${type}/${id}/detail`;
                }
            })
        }
    },

    update: function (type) {
        var dataArr = $("form").serializeArray();

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.put({data: data, success: function (id) {
                    location.href = `/admin/homepage/board/${type}/${id}/detail`;
                }
            })
        }
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
