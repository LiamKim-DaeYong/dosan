var pageObj = {
    init: function () {
        $editor.init("content");
    },
    delete: function () {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "/board/delete")
        document.body.appendChild(form);
        form.submit();
    }
}

pageObj.pageStart = function () {
    pageObj.init();
}
