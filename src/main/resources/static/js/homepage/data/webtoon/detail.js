ACTIONS = {
    PDF_OPEN: function (e) {
        let pdfId = e.id.split('pdf_')[1];
        console.log(pdfId)

        if (pdfId != null && pdfId != '') {
            $.ajax({
                type: 'get',
                url: '/admin/homepage/file/get/file/'+pdfId,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (res) {
                    if (res.filePath != null && res.filePath != '') {
                        window.open(res.filePath, "_blank")
                    } else {
                        alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                        return false;
                    }
                },
                error: function (xhr, status, error) {
                    alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                    return false;
                }
            })
        }
    },
    DELETE: function (id) {
        if (confirm("삭제하시겠습니까?")) {
            $.ajax({
                type: "get",
                url: '/webtoon/delete/'+id,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (res) {
                    if (res == true) {
                        window.location.href = "/webtoon/list";
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