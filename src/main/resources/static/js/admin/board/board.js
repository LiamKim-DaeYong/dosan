var pageObj = {
    init: function () {
    },
    delete: function () {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "/board/delete")
        document.body.appendChild(form);
        form.submit();
    },
    addView: function () {
        const title = $('#title').val();
        const content = this.editor.getData();
        const newFile = this.multifile.getData('new');
        const deleteFile = this.multifile.getData('deleted');
        console.log('= 저장 =');
        console.log('제목: ', title);
        console.log('내용: ', content);
        console.log('삭제: ', deleteFile);
        console.log('신규: ', newFile);

        // 게시판
        let formData = new FormData();
        formData.append('content', new Blob([JSON.stringify({
            title: title,
            content, content
        })], {type: "application/json"}));

        // 파일 업로드
        formData.append('delete', new Blob([JSON.stringify(deleteFile)], {type: "application/json"}));

        // 파일 삭제
        newFile.forEach(file => formData.append('file', file));

        console.log(typeof formData);
        console.log($api);
        console.log($api.postMultiPart);
        $ajax.postMultiPart({
            data: formData,
            success: () => {
            },
            error: () => {
            }
        })
    }
}

pageObj.pageStart = function () {
    pageObj.init();
    pageObj.editor.initView();
    pageObj.multifile.initView();
}

pageObj.editor = $.extend($editor, {
    initView: function () {
        this.targetId = "content";
        this.init(this.targetId);
    },
});

pageObj.multifile = $.extend($multifile, {
    initView: function () {
        this.targetid = "file_input";
        this.init('file_input', 'file_list');
        this.setData([{filename: '1234'}, {filename: 'abcd'}])
    },

});
