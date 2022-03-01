var page = {
    saveForm: function () {
        $.get(`${page.path}/add`, function (data) {
            modal.open({
                title: "교육과목 등록",
                content: data
            });
        });
    },
    save: function () {
        $.ajax({
            url: `${page.path}/add`,
            type: 'POST',
            data: modal.getData(),
            contentType: 'application/json',
            success: function (data) {
                location.href = page.path;
            },
            error: function (error) {
                jsObj.valid.errors(error.responseJSON);
            }
        });
    },
    editForm: function (id) {
        $.get(`${page.path}/${id}/edit`, function (data) {
            modal.open({
                title: "교육과목 수정",
                content: data
            });
        });
    },

    edit: function () {
        $.ajax({
            url: `${page.path}/edit`,
            type: 'PUT',
            data: modal.getData(),
            contentType: 'application/json',
            success: function (data) {
                location.href = page.path;
            },
            error: function (error) {
                jsObj.valid.errors(error.responseJSON);
            }
        });
    }
}

var modal = jsObj.modal();
page.pageStart = function () {
    page.path = location.pathname;

    modal.init($("#modal"), {
        height: 200,
        width: 400
    });
};
