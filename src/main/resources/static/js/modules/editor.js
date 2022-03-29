const $editor = {
    target: {},
    init: function (targetId) {
        this.target = $("#" + targetId);
        this.target.summernote({
            height: 500,
            placeholder: `<span placeholder="${targetId}" className="modal_error"></span>`,
            callbacks: {
                onImageUpload : function(file) {
                    const img = new FormData();
                    img.append("file", file[0]);

                    $ajax.postMultiPart({
                        url: "/files/edit/upload",
                        data: img,
                        success: function (imgUrl) {
                            target.summernote('insertImage', imgUrl);
                        }
                    });
                },
            }
        });
    }
}

export default $editor;
