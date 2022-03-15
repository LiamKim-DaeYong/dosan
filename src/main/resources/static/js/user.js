var pageObj = {
    save: function () {
        var dataArr = $("form").serializeArray();

        if (dataArr.length > 0) {
            var data = {};
            jQuery.each(dataArr, function() {
                data[this.name] = this.value;
            });

            if ($("#checkId").val() == 'Y') {
                $api.post({data: data, success: function (res) {
                        alert("등록되었습니다.");
                        location.href = `/user/employees/${data["userId"]}/detail`;
                    }
                })
            } else {
                alert("아이디 중복체크를 해주세요.");
                return false;
            }
        }
    },
    edit: function () {
        var dataArr = $("form").serializeArray();

        if (dataArr.length > 0) {
            var data = {};
            jQuery.each(dataArr, function () {
                data[this.name] = this.value;
            });

            $api.post({data: data, success: function (res) {
                    alert("등록되었습니다.");
                    $url.redirect();
                }
            })
        }
    },
    checkId: function () {
        var userId = $("#userId").val();

        if (!userId || userId == '') {
            alert("아이디를 입력해 주세요.");
            $("#userId").focus();
            return false;
        } else {
            $api.post({data: {userId: userId}, url: `/user/check/id`,
                success: function (data) {
                    if (data == true) {
                        $("#checkIdMsg").html("사용할 수 없는 아이디입니다.");
                        $("#checkIdMsg").css('color', 'red');
                        document.getElementById('checkId').value = 'N';
                    } else {
                        $("#checkIdMsg").html("사용할 수 있는 아이디입니다.");
                        $("#checkIdMsg").css('color', 'black');
                        document.getElementById('checkId').value = 'Y';
                    }
                }
            })
        }
    },
    initPassword: function (userId) {
        if (userId != null && userId != '') {
            $api.post({data: {userId: userId}, url: `/user/${userId}/password/init`})
        }
    }
}

pageObj.pageStart = function () {
};
