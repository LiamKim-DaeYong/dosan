var pageObj = {

    save: function () {
        var data = $form.getData();
        data["address"] = {
                'zipCode': data.zipCode,
                'roadAddress': data.roadAddress,
                'detailAddress': data.detailAddress
            }

        $ajax.post({
            data: data,
            success: function (id) {
                $url.redirect(`/employee/clients/${id}/detail`);
            }
        });
    },

    edit: function () {
        var data = $form.getData();
        data["address"] = {
            'zipCode': data.zipCode,
            'roadAddress': data.roadAddress,
            'detailAddress': data.detailAddress
        }

        $ajax.put({
            data: data,
            success: function (id) {
                $url.redirect(`/employee/clients/${id}/detail`);
            }
        })
    }
}

pageObj.pageStart = function () {
};

function validation() {
    var clientNm = $("input[name='clientNm']");
    if (clientNm[0].value == null || clientNm[0].value == "") {
        alert("단체명을 입력해 주세요.");
        return false;
    }

    var manager = $("input[name='manager']");
    if (manager[0].value == null || manager[0].value == "") {
        alert("담당자를 입력해 주세요.");
        return false;
    }

    var clientType = $("input[type='radio']:checked").val();
    if (clientType == null || clientType == "") {
        alert("구분을 선택해 주세요.");
        return false;
    }

    var phoneNum = $("input[name='phoneNum']");
    if (phoneNum[0].value == null || phoneNum[0].value == "") {
        alert("연락처1을 입력해 주세요.");
        return false;
    }

    var roadAddress = $("input[name='roadAddress']");
    var detailAddress = $("input[name='detailAddress']");
    if (roadAddress[0].value == null || roadAddress[0].value == ""
        || detailAddress[0].value == null || detailAddress[0].value == "") {
        alert("주소를 입력해 주세요.");
        return false;
    }

    var bussinessNum = $("input[name='bussinessNum']");
    if (bussinessNum[0].value == null || bussinessNum[0].value == "") {
        alert("사업자등록번호를 입력해 주세요.");
        return false;
    }

    return true;
}