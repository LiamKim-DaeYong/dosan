$(document).ready(function () {
    $(window).on('beforeunload', function () {
        return "작성 중인 글이 존재합니다. 페이지를 나가시겠습니까?";
    })

    $("#pdf_upload").click(function (e) {
        e.preventDefault();

        $("#pdf_input").click();
    })

    $("#pdf_input").on('change', function (e) {
        ACTIONS.PDF_READ($(this).context, e);
    });

    $("#img_upload").click(function (e) {
        e.preventDefault();

        $("#img_input").click();
    })

    $("#img_input").on('change', function (e) {
        ACTIONS.PREVIEW_READ($(this).context, e);
    });

    if (pdf != null) {
        let pdfHtml = `<div id="file_${pdf.id}" class="file_list_delete">
                        <input type="hidden" name="pdfId" value="${pdf.id}">
                        <span>${pdf.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_PDF(${pdf.id})">X</a>
                        </span>
                    </div>`;
        $("#pdf_name_box").empty();
        $("#pdf_name_box").append(pdfHtml);
    }

    if (preview != null) {
        let previewHtml = `<div id="file_${preview.id}" class="file_list_delete">
                        <input type="hidden" name="previewId" value="${preview.id}">
                        <span>${preview.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_PREVIEW(${preview.id})">X</a>
                        </span>
                    </div>`;

        let previewImg = `
                        <img src="/admin/homepage/file/preview/${preview.id}" alt="썸네일" width="500px" height="500px">
                   `;

        $("#image_name_box").empty();
        $("#image_name_box").append(previewHtml);

        $("#preview_box").empty();
        $("#preview_box").append(previewImg);
    }
})

ACTIONS = {
    SAVE: function () {
        if (!$("#title").val() || $("#title").val() === "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }

        if ($("[name='pdfId']").length <= 0) {
            alert("pdf를 업로드 해주세요.");
            return false;
        }

        $(window).off('beforeunload');
        document.getElementById('webtoon_form').submit();
    },
    PDF_READ: function (e, fileEvent) {
        if (e.files && e.files[0]) {
            if (ACTIONS.FILE_VALIDATION(e.files, "pdf") == false) {
                return false;
            }

            let files = fileEvent.target.files;
            let formData = new FormData();
            formData.append("file", files[0]);

            $.ajax({
                type: "POST",
                enctype: "multipart/form-data",
                url: "/admin/homepage/file/savePdf",
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (data) {
                    if (JSON.parse(data).result == "OK") {
                        let pdfId = JSON.parse(data).pdfId;
                        let previewId = JSON.parse(data).imgId;

                        ACTIONS.SET_PDFNAME(pdfId);
                        ACTIONS.SET_PREVIEWNAME(previewId);
                    } else {
                        alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                        return false;
                    }

                },
                error: function (xhr, status, error) {
                    alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                    return false;
                }
            });
        }
    },
    SET_PDFNAME: function (id) {
        $.ajax({
            type: "get",
            url: "/admin/homepage/file/get/file/"+id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (res) {
                if (res != null) {
                    let pdfname = `
                        <div  id="file_${res.id}" class="file_list_delete">
                            <input type="hidden" name="pdfId" value="${res.id}">
                            <span>${res.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_PDF(${res.id})">X</a>
                            </span>
                        </div>
                    `;

                    $("#pdf_name_box").empty();
                    $("#pdf_name_box").append(pdfname);
                }
            }
        })
    },
    PREVIEW_READ: function (e, fileEvent) {
        if (e.files && e.files[0]) {
            if (ACTIONS.FILE_VALIDATION(e.files, "img") == false) {
                return false;
            }

            let files = fileEvent.target.files;
            let formData = new FormData();
            formData.append("file", files[0]);

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
                    if (data.length > 0) {
                        let previewId = data[0];
                        ACTIONS.SET_PREVIEWNAME(previewId);
                    }
                },
                error: function (xhr, status, error) {
                    alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                    return false;
                }
            });
        }
    },
    SET_PREVIEWNAME: function (id) {
        $.ajax({
            type: "get",
            url: "/admin/homepage/file/get/file/"+id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (res) {
                if (res != null) {
                    let filename = `
                        <div id="file_${res.id}" class="file_list_delete">
                            <input type="hidden" name="previewId" value="${res.id}">
                            <span>${res.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_PREVIEW(${res.id})">X</a>
                            </span>
                        </div>
                    `;

                    let preview = `
                        <img src="/admin/homepage/file/preview/${res.id}" alt="썸네일일" width="500px" height="500px">
                   `;

                    $("#image_name_box").empty();
                    $("#image_name_box").append(filename);

                    $("#preview_box").empty();
                    $("#preview_box").append(preview);
                }

            }
        })
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
                    alert("파일 사이즈는 30MB 이내로 등록 가능합니다.");
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
            let maxSize = 30 * 1024 * 1024;
            return file.size <= maxSize;
        }
    },
    DEL_PDF: function (id) {
        $("[id^=file_]").remove();
        $("#preview_box").empty();
        $("#preview_box").append(`<span id="none_preview">이미지가 없습니다.</span>`)
    },
    DEL_PREVIEW: function (id) {
        $("#file_"+id).remove();
        $("#preview_box").empty();
        $("#preview_box").append(`<span id="none_preview">이미지가 없습니다.</span>`)
    }
}