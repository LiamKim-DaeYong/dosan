const $table = {
    addRow: function (targetId) {
        const target = $("#" +targetId);
        const lastRow = target.find('tr').last().clone();
        const idx = target.find('tr').length;

        $.each(lastRow.find('input, textarea'), function () {
            if (this.type == 'checkbox') {
                $(this).prop("checked", false);
                this.name = idx;
                this.value = idx;
            } else {
                this.value = "";
            }
        });

        target.append(lastRow);
    },

    delRow: function (targetId) {
        const target = $("#" +targetId);
        const checkedList = $checkBox.getAllChecked(target);

        if ($valid.deletes(checkedList.length)) {
            $.each(checkedList, function (idx, value) {
                const row = target.find("input[name='" + value + "']").closest('tr');
                row.remove();
            });
        }
    },

    getData: function (targetId, type="all") {
        const HEADER_IDX = 0;
        const target = $("#" +targetId);
        const result = [];

        $.each(target.find('tr'), function (idx, row) {
            if (idx != HEADER_IDX) {
                if (type == "checked" && !$(row).find(">:first-child input").prop("checked")) {
                    return;
                }

                const rowData = $(row).find('input[type!="checkbox"], textarea');

                const obj = {};
                let noValueCnt = 0;
                $.each(rowData, function () {
                    if (!this.value) {
                        noValueCnt++;
                    }

                    obj[this.name] = this.value;
                });

                if (rowData.length != noValueCnt) {
                    result.push(obj);
                }
            }
        });

        return result;
    },
}

export default $table;
