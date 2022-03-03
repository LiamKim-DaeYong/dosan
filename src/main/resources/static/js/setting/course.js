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
            url: page.path,
            type: 'POST',
            data: modal.getData(),
            contentType: 'application/json',
            success: function (data) {
                location.href = page.path;
            },
            error: function (error) {
                jsObj.errors.valid(error.responseJSON.errors);
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
            url: page.path,
            type: 'PUT',
            data: modal.getData(),
            contentType: 'application/json',
            success: function (data) {
                location.href = page.path;
            },
            error: function (error) {
                jsObj.errors.valid(error.responseJSON.errors);
            }
        });
    },

    delete: function () {
        var allChecked = jsObj.data.getAllChecked();

        if (!allChecked.length) {
            alert("삭제할 항목을 선택해 주세요.");
        } else {
            if(confirm("선택된 항목을 삭제 하시겠습니까?")) {
                $.ajax({
                    url: page.path,
                    type: 'DELETE',
                    data: JSON.stringify(allChecked),
                    contentType: 'application/json',
                    success: function (data) {
                        location.href = page.path;
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
        }
    },
}

var modal = jsObj.modal();
page.pageStart = function () {
    page.path = location.pathname;

    modal.init($("#modal"), {
        height: 200,
        width: 400
    });
};
