var pageObj = {
    save: function () {
        const saveData = $form.getData();
        saveData.educationalList = $table.getData('educationalTable');
        saveData.employmentList = $table.getData('employmentTable');

        $ajax.post({
            data: saveData,
            success: function (userId) {
                $url.redirect(`/admin/user/educators/${userId}/detail`);
            }
        });
    },

    editView: function (userId) {
        $url.redirect(`/admin/user/educators/${userId}/edit`);
    },

    update: function () {
        const updateData = $form.getData();
        updateData.educationalList = $table.getData('educationalTable');
        updateData.employmentList = $table.getData('employmentTable');

        $ajax.put({
            data: updateData,
            success: function (userId) {
                $url.redirect(`/admin/user/educators/${userId}/detail`);
            }
        });
    }
}

pageObj.pageStart = function () {
};
