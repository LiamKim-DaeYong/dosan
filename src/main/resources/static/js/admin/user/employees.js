var pageObj = {
    save: function () {
        const saveData = $form.getData();
        saveData.educationalList = $table.getData('educationalTable');
        saveData.employmentList = $table.getData('employmentTable');

        $ajax.post({
            data: saveData,
            success: function (userId) {
                $url.redirect(`/admin/user/employees/${userId}/detail`);
            }
        });
    },

    editView: function (userId) {
        location.href = `/admin/user/employees/${userId}/edit`;
    },

    update: function () {
        $ajax.put({
            data: $form.getData(),
            success: function (userId) {
                $url.redirect(`/admin/user/employees/${userId}/detail`);
            }
        });
    }
}

pageObj.pageStart = function () {
};
