var pageObj = {
    save: function () {
        var dataArr = $("form").serializeArray();
        console.log(data)

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.post({data: data, success: function (id) {
                    location.href = `/admin/homepage/media-archive/promotional-video/${id}/detail`;
                }
            })
        }
    },

    update: function () {
        var dataArr = $("form").serializeArray();

        if (dataArr.length > 0) {
            var data = {};
            $.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $ajax.put({data: data, success: function (id) {
                    location.href = `/admin/homepage/media-archive/promotional-video/${id}/detail`;
                }
            })
        }
    },

    preview: function () {
        $("#player").empty();
        var youtubeCode = $("textarea").val();
        var code = youtubeCode.split("v=")[1];
        if (code == undefined) {
            alert("비디오를 찾을 수 없습니다.");
            return false;
        }
        $("#code").val(code)

        var iframeTag = `<iframe id="playerVideo" type="text/html" width="640" height="360"frameBorder="0" allowfullscreen
                          src="http://www.youtube.com/embed/${code}"
                          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"></iframe>`;
        $("#player").append(iframeTag)
    }
}

pageObj.pageStart = function () {
};
