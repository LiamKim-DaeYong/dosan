var pageObj = {
    save: function (branchType) {
        var dataArr = $("form").serializeArray();
        dataArr.push({name : 'branchType', value : branchType});

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.post({data: data, success: function (id) {
                    location.href = `/educator/training-archive/branch/${branchType}/${id}/detail`;
                }
            })
        }
    },

    edit: function (branchType) {
        var dataArr = $("form").serializeArray();
        dataArr.push({name : 'branchType', value : branchType});

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.put({data: data, success: function (id) {
                    location.href = `/educator/training-archive/branch/${branchType}/${id}/detail`;
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