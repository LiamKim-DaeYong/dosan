const $valid = {
    delete: function () {
        return confirm("삭제 하시겠습니까?")
    },
    deletes: function (selectCondition) {
        if (!selectCondition) {
            alert("삭제할 항목을 선택해 주세요.");
        } else {
            return confirm("선택된 항목을 삭제 하시겠습니까?")
        }
    },
    duplicate: function (option) {
        var result = true;
        $.ajax({
            url: option.url,
            type: 'POST',
            data: JSON.stringify(option.data),
            contentType: 'application/json',
            async: false,
        }).done(function (data) {
            result = data;
        });

        return result
    }
}

export default $valid;
