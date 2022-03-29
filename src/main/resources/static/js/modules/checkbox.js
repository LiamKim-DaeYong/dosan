const $checkBox = {
    init: function () {
        $('input:checkbox[check="all"]').on('click', function () {
            const table = $(this).closest('table');
            table.find('input:checkbox').prop("checked", $(this).is(":checked"));
        });
    },
    getAllChecked: function (target=$("table")) {
        var result = [];
        target.find('input:checkbox[check!="all"]:checked').each(function () {
            result.push($(this).val());
        });

        return result;
    }
}

export default $checkBox
