var pageObj = {
    findView: function () {
        $ajax.get({
            url: $url.getPath() + "/findClients",
            success: function (result) {
                modal.open({
                    path: $url.getPath('findClients'),
                    title: `고객정보`,
                    data: modal.setData(result.content)
                });
            }
        })
    },
    addView: function () {
        modal.open({
            path: $url.getPath('addClients'),
            title: `신규고객등록`,
        });
    },
    select: function () {
        var clientId = $('input[name="id"]:checked').val();
        var nmId = 'clientNm' + clientId;
        var clientNm = $('#nmId');
        $("#clientId").val(clientId);
        $("#clientNm").val(clientNm);
        console.log(clientId);

        modal.close();
    }
}

var modal = $modal();
pageObj.pageStart = function () {
    modal.init($("#modal"), {
        width: 600
    });
};