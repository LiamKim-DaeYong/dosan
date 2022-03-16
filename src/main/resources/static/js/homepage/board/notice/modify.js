var fileIdList = [];
var fileDelIdList = [];
$(document).ready(function () {
    $(window).on('beforeunload', function () {
        return "작성 중인 글이 존재합니다. 페이지를 나가시겠습니까?";
    })

    if (modify_content != null) {
        $("#content").summernote('code', modify_content)
    }

    if (modify_content_val != null) {
        $("#contentVal").val(modify_content_val);
    }

    $("#file_upload").click(function (e) {
        e.preventDefault();

        $("#file_input").click();
    })

    $("#file_input").on('change', function (e) {
        ACTIONS.FILE_READ($(this).context, e);
    })

    fileList.forEach(file => {
        fileIdList.push(file.id);
        let html = `<div id="file_${file.id}" class="file_list_delete">
                        <input type="hidden" name="fileIdList[]" value="${file.id}">
                        <span>${file.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_FILE(${file.id})">X</a></span>
                    </div>`;

        $("#file_name_box").append(html);
    })

    imgList.forEach(img => {
        $("#hidden_img_box").append(`<input type="hidden" id="img_${img.id}" name="imgIdList[]" value="${img.id}">`);
        imgIdList.push(img.id);
    })
})

ACTIONS= {
    SAVE: function () {
        let imgList = [];
        let fileList = [];
        let code = $("#content").summernote('code');
        let content = $(code).text().replace(/\s+/g, '');

        if (!$("#title").val() || $("#title").val() == "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }

        if (imgIdList.length > 0) {
            imgIdList.forEach(imgId => {
                let findText = '/admin/homepage/file/preview/'+imgId;
                let find = $("#content").val().indexOf(findText);

                if (find < 0) {
                    $("#img_"+imgId).remove();
                    ACTIONS.AJAX_DEL_FILE(imgId);
                } else {
                    imgList.push(imgId);
                }
            })
        }

        if (fileDelIdList.length > 0) {
            fileDelIdList.forEach(fileId => {
                for (let i = 0; i < fileDelIdList.length; i++) {
                    if (fileIdList[i] != fileId) {
                        fileList.push(fileIdList[i]);
                    }
                }
                ACTIONS.AJAX_DEL_FILE(fileId)
            })
        } else {
            fileList = fileIdList;
            fileIdList = new Array();
        }

        if (content.length < 1 && imgList.length < 1) {
            alert("내용을 입력해주세요.");
            $("#content").summernote('focus');
            return false;
        }

        $("#contentVal").val($(code).text())

        $(window).off('beforeunload');
        document.getElementById("notice_form").submit();
    },
    FILE_READ: function (e, fileEvent) {
        if (e.files && e.files[0]) {
            if (ACTIONS.FILE_VALIDATION(e.files) == false) {
                return false;
            }

            let files = fileEvent.target.files;
            let formData = new FormData();
            for (let i = 0; i < files.length; i++) {
                formData.append("file", files[i]);
            }

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
                        ACTIONS.SET_FILENAME(data);
                    }
                },
                error: function (xhr, status, error) {
                    alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                    return false;
                }
            });
        } else {
            alert("업로드할 파일이 없습니다.");
            return false;
        }
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
                    if (res != null) {
                        let filename = `
                            <div id="file_${res.id}" class="file_list_delete">
                                <input type="hidden" name="fileIdList[]" value="${res.id}">
                                <span>${res.orgFilename} <a href="javascript:void(0)" onclick="ACTIONS.DEL_FILE(${res.id})">X</a>
                                </span>
                            </div>
                        `;

                        $("#file_name_box").append(filename);
                        fileIdList.push(res.id);
                    }
                }
            })
        }
    },
    FILE_VALIDATION: function (fileArr) {
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

                if($.inArray(fileExt, ['gif','png','jpg','jpeg','txt','doc','docx','xls','xlsx','hwp','hwpx','pdf','ppt','pptx','zip','7z']) == -1) {
                    alert('등록 할수 없는 파일입니다.');
                    result = false;
                    return false;
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
    DEL_FILE: function (id) {
        $("#file_"+id).remove();

        fileDelIdList.push(id);
    },
    AJAX_DEL_FILE: function (id) {
        $.ajax({
            type: "GET",
            url: "/admin/homepage/file/delete/"+id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
        })
    }
}