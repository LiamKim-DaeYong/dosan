ACTIONS = {
    DELETE: function (id) {
        if (confirm("삭제하시겠습니까?")) {
            $.ajax({
                type: "get",
                url: '/report/delete/'+id,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (res) {
                    if (res == true) {
                        window.location.href = "/report/list";
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
    }
}