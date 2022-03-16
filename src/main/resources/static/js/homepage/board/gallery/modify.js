var imgDelIdList = [];
$(document).ready(function () {
    $(window).on('beforeunload', function () {
        return "작성 중인 글이 존재합니다. 페이지를 나가시겠습니까?";
    })

    if (modify_content != null) {
        $("#content").summernote('code', modify_content);
    }

    if (modify_content_val != null) {
        $("#contentVal").val(modify_content_val);
    }

    if (imgList.length > 0) {
        imgList.forEach(id => {
            imgIdList.push(id)
        })
    }
})

ACTIONS = {
    SAVE: function () {
        let imgList = [];
        let code = $("#content").summernote('code');

        if (!$("#title").val() || $("#title").val() == "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }

        if (modify_content_val != $(code).text()) {
            $("#contentVal").val($(code).text())
        }

        if (imgIdList.length > 0) {
            imgIdList.forEach(imgId => {
                let findText = '/file/preview/'+imgId;
                let find = $("#content").val().indexOf(findText);

                if (find < 0) {
                    ACTIONS.AJAX_DEL_FILE(imgId);
                } else {
                    imgList.push(imgId)
                }
            })
        }

        if (imgList.length < 1) {
            alert("이미지를 추가해주세요.");
            $("#content").summernote('focus');
            return false;
        }

        $("#contentVal").val($(code).text())

        $(window).off('beforeunload');
        document.getElementById("gallery_form").submit();
    },
    AJAX_DEL_FILE: function (id) {
        $.ajax({
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            url: "/gallery/file/delete/"+id
        })
    }
}