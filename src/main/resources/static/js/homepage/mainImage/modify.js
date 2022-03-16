var fileIdList = [];
$(document).ready(function () {
    $(window).on('beforeunload', function () {
        return "작성 중인 글이 존재합니다. 페이지를 나가시겠습니까?";
    })

    if (data) {
        ACTIONS.DATA_INIT(data);
    }

    $("#btn_upload").click(function (e) {
        e.preventDefault();

        $("#input_file").click();
    })

    $("#input_file").on('change', function (e) {
        ACTIONS.FILE_READ($(this).context, e);
    });
})

ACTIONS = {
    DATA_INIT: function (data) {
        if (data.fileId != null && data.fileId != '') {
            $.ajax({
                type: "get",
                url: "/admin/homepage/file/get/file/"+data.fileId,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (res) {
                    if (res != null) {
                        let filename = `
                            <div id="file_${res.id}" class="file_list_delete">
                                <span>${res.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_FILE(${res.id})">X</a>
                                </span>
                                 <input type="hidden" name="fileId" value="${res.id}">
                            </div>
                        `;

                        $("#image_name_box").empty();
                        $("#image_name_box").append(filename);
                    }
                }
            })

            let html = `<img src="/admin/homepage/file/preview/${data.fileId}" alt="${data.title}" id="saved_fileId" style="width:80%; max-height:500px">`;
            $("#preview_box").empty();
            $("#preview_box").append(html);
        }

        if (data.postYn) {
            let result = true;
            if (data.postYn == "Y") {
                $(`#postSeq option:eq(${data.postSeq})`).prop("selected", true);
                result = false;
            } else {
                $("#postSeq option:eq(0)").prop("selected", true);
            }

            $("#postSeq").prop('disabled', result);
        }
    },
    SAVE: function () {
        if (!$("#title").val() || $("#title").val() === "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }

        if ($("#postYn").val() === "Y" && $("#postSeq").val() == "0") {
            alert("게시상태인 경우에 순서가 0번째가 될 수 없습니다.");
            return false;
        } else if ($("#postYn").val() === "N" && $("#postSeq").val() !== "0") {
            alert("미게시상태인 경우 순서설정할 수 없습니다.");
            return false;
        }

        if ($("[name='fileId']").length <= 0) {
            alert("이미지를 등록해주세요.");
            return false;
        } else {
            if (confirm("이미지를 등록하시겠습니까?") == true) {
                $(window).off('beforeunload');
                document.getElementById('main_form').submit();
            } else {
                return false;
            }
        }
    },
    FILE_READ: function (e, fileEvent) {
        if (e.files && e.files[0]) {
            let files = fileEvent.target.files;

            let formData = new FormData();
            for (let i = 0; i < files.length; i++) {
                formData.append("file", files[i]);
                formData.append("width", '1920');
                formData.append("height", '930');
                formData.append("pageName", "mainImage");
            }

            $.ajax({
                type: "POST",
                enctype: "multipart/form-data",
                url: "/admin/homepage/file/check/widthAndHeight",
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (res) {
                    if (res == true) {
                        ACTIONS.SET_PREVIEW(formData);
                    } else {
                        alert("이미지 가로, 세로 크기가 맞지 않습니다.");
                        return false;
                    }
                },
            })

            return false;
        }
    },
    SET_PREVIEW: function (formData) {
        $.ajax({
            type: "POST",
            enctype: "multipart/form-data",
            url: "/admin/homepage/file/saveFiles",
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (data) {
                $("#image_name_box").empty();
                $("#preview_box").empty();
                if (data.length > 0) {
                    ACTIONS.SET_FILENAME(data);
                }
            },
            error: function (xhr, status, error) {
                alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                return false;
            }
        });
    },
    SET_FILENAME: function (idList) {
        for (let i = 0; i < idList.length; i++) {
            $.ajax({
                type: "get",
                url: "/admin/homepage/file/get/file/"+idList[i],
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (res) {
                    let fileName = `
                            <div id="file_${res.id}" class="file_list_delete">
                                <span>${res.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_FILE(${res.id})">X</a>
                                </span>
                                <input type="hidden" name="fileId" value="${res.id}">
                            </div>
                        `;
                    let imgTag = `<img src="/admin/homepage/file/preview/${res.id}" style="width:80%; max-height:500px;" id="img_target" />`

                    $("#image_name_box").append(fileName);
                    $("#preview_box").append(imgTag);
                }
            })
        }
    },
    DEL_FILE: function (id) {
        $("#image_name_box").empty();
        $("#image_name_box").append(`<span id="none_name">이미지가 없습니다.</span>`);

        $("#preview_box").empty();
        $("#preview_box").append(`<span id="none_preview">이미지가 없습니다.</span>`);
    },
    POST_YN_CHANGE: function (e) {
        let result = true;
        if (e.value == "Y") {
            $("#postSeq option:eq(1)").prop("selected", true);
            result = false;
        } else {
            $("#postSeq option:eq(0)").prop("selected", true);
        }

        $("#postSeq").prop('disabled', result);
    }
}