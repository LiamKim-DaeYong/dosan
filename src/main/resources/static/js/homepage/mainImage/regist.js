$(document).ready(function () {
    $(window).on('beforeunload', function () {
        return "작성 중인 글이 존재합니다. 페이지를 나가시겠습니까?";
    })

    $("#postSeq option:eq(1)").attr("selected", "selected")

    $("#btn_upload").click(function (e) {
        e.preventDefault();

        $("#input_file").click();
    })

    $("#input_file").on('change', function (e) {
        ACTIONS.FILE_READ($(this).context, e);
    });
})

ACTIONS = {
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
            if (ACTIONS.FILE_VALIDATION(e.files, "img") == false) {
                return false;
            }

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
                    let imgTag = `<img src="/admin/homepage/file/preview/${res.id}" style="width:80%; max-height:500px" id="img_target" />`

                    $("#image_name_box").append(fileName);
                    $("#preview_box").append(imgTag);
                }
            })
        }
    },
    FILE_VALIDATION: function (fileArr, type) {
        var result = true;
        if (fileArr.length > 0) {
            let fileList = [];
            for (let i = 0; i < fileArr.length; i++) {
                fileList.push(fileArr[i]);
            }

            fileList.forEach(file => {
                let fileLength = file.name.length;
                let lastDot = file.name.lastIndexOf('.')+1;
                let fileExt = file.name.substring(lastDot, fileLength).toLowerCase();

                if (ACTIONS.SIZE_CHECK(file) === false) {
                    alert("최대 3MB의 파일만 등록 가능합니다.");
                    result = false;
                    return false;
                }

                if (type == "pdf") {
                    if($.inArray(fileExt, ["pdf"]) == -1) {
                        alert('등록 할수 없는 파일입니다.');
                        result = false;
                        return false;
                    }
                } else if (type == "img") {
                    if($.inArray(fileExt, ['png','jpg','jpeg']) == -1) {
                        alert('등록 할수 없는 파일입니다.');
                        result = false;
                        return false;
                    }
                }
            })
        }

        return result;
    },
    SIZE_CHECK: function (file) {
        if (file.size) {
            let maxSize = 3 * 1024 * 1024;
            return file.size <= maxSize;
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