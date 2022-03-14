var pageObj = {
    save: function () {
        var password = $("input[type='password']");

        if (password[0].value == null || password[0].value == "") {
            alert("비밀번호를 입력해 주세요.");
            return false;
        } else if (password[0].value != password[1].value) {
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }

        $.ajax({
            url: $url.getPath(),
            type: 'POST',
            data: password[0].value,
            contentType: 'application/json',
            success: function (data) {
                alert("비밀번호가 변경 되었습니다.");
                location.href = '/dashboard/admin';
            }
        });
    }
}

pageObj.pageStart = function () {
};
