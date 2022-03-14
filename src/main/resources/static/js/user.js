var pageObj = {
    save: function () {
        var dataArr = $("form").serializeArray();
        if (dataArr) {
            data = {};
            jQuery.each(dataArr, function() {
                data[this.name] = this.value;
            });

            console.log(data)
            // $api.post({data: data})
        }
    }
}

pageObj.pageStart = function () {
};
