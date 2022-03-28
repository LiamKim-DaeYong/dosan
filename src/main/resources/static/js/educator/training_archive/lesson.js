var pageObj = {
    save: function (gradeType) {
        var dataArr = $("form").serializeArray();
        dataArr.push({name : 'gradeType', value : gradeType});

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.post({data: data, success: function (id) {
                    location.href = `/educator/training-archive/lesson/${gradeType}/${id}/detail`;
                }
            })
        }
    },

    edit: function (gradeType) {
        var dataArr = $("form").serializeArray();
        dataArr.push({name : 'gradeType', value : gradeType});

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.put({data: data, success: function (id) {
                    location.href = `/educator/training-archive/lesson/${gradeType}/${id}/detail`;
                }
            })
        }
    },

    delete: function () {
        var allChecked = $checkBox.getAllChecked();

        if ($valid.deletes(allChecked.length)){
            $ajax.delete({data: allChecked, url: $url.getPath()+'/delete'})
        }
    }
}

pageObj.pageStart = function () {
}