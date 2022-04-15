var pageObj = {
    save: function () {
        const saveData = $form.getData();
        saveData.educationalList = $table.getData('educationalTable');
        saveData.employmentList = $table.getData('employmentTable');

        $ajax.post({
            data: saveData,
            success: function (userId) {
                alert("초기 패스워드는 0000 입니다.\n마이 페이지에서 패스워드 변경 후 사용해 주세요.")
                $url.redirect(`/admin/user/employees/${userId}`);
            }
        });
    },

    editView: function (userId) {
        $url.redirect(`/admin/user/employees/${userId}/edit`);
    },

    update: function () {
        const updateData = $form.getData();
        updateData.educationalList = $table.getData('educationalTable');
        updateData.employmentList = $table.getData('employmentTable');

        $ajax.put({
            data: updateData,
            success: function (userId) {
                $url.redirect(`/admin/user/employees/${userId}`);
            }
        });
    },

    leave: function (id, leaveType) {
        var message = leaveType == 'retired' ? '퇴사' : '휴직';

        $ajax.put({
            url: $url.getPath() + "/" + leaveType,
            success: function (userId) {
                alert(message + '처리 되었습니다.')
                $url.redirect(`/admin/user/employees/${userId}`);
            }
        })
    },

    changePassword: function (userId) {
        modal.open({
            path: `/password/${userId}/change`,
            title: '비밀번호 재설정'
        });
    },
}

var modal = $modal();
pageObj.pageStart = function () {
    modal.init($("#modal"), {
        width: 400,
        height: 300
    });
};
