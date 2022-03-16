ACTIONS = {
    DELETE: function (id) {
        if (confirm("삭제하시겠습니까?")) {
            $.ajax({
                type: "get",
                url: '/inquiry/delete/'+id,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (res) {
                    if (res == true) {
                        window.location.href = "/inquiry/list";
                    } else {
                        alert("삭제 처리에 문제가 생겼습니다. 관리자에 문의해주세요.");
                        return false;
                    }
                },
                error: function (err) {
                    alert("삭제 처리에 문제가 생겼습니다. 관리자에 문의해주세요.");
                    return false;
                }
            })
        }
    },
    COMMENT_SAVE: function (id) {
        let inquiryId = id;
        let comment = $("#comment").val();

        if (!comment && comment == "") {
            alert("답변을 입력해주세요.");
            $("#comment").focus();
            return false;
        }

        let data = {
            inquiryId: inquiryId,
            comment: comment.replace(/(\n|\r\n)/g, '<br>')
        }

        ACTIONS.AJAX_SAVE(data);
    },
    AJAX_SAVE: function (data) {
        $.ajax({
            type: 'post',
            url: '/inquiry/comment/save',
            contentType: 'application/json',
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (res) {
                if (res != null) {
                    window.location.href = '/inquiry/detail/'+res;
                }
            }
        })
    },
    COMMENT_MODIFY: function (inquiryComment) {
        $("#show_comment").hide();
        $("#hide_comment").show();

        var text = inquiryComment.comment;
        text = text.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');

        $("#modify_comment").val(text);
        $("#modify_comment").focus();
    },
    MODIFY_CANCEL: function () {
        $("#show_comment").show();
        $("#hide_comment").hide();
    },
    MODIFY_SAVE: function (commentId) {
        let inquiryId = $("#inquiryId").val();
        let comment = $("#modify_comment").val();

        if (!comment && comment == "") {
            alert("답변을 입력해주세요.");
            $("#comment").focus();
            return false;
        }

        let data = {
            inquiryId: inquiryId,
            commentId: commentId,
            author: "수련원",
            comment: comment.replace(/(\n|\r\n)/g, '<br>')
        }

        ACTIONS.AJAX_SAVE(data);
    }
}