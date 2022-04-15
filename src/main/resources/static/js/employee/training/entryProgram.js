var pageObj = {
    createForm: function (change = false) {
        const type = $("input[name='inputType']:checked").val();

        if (validGroups()) {
            $ajax.post({
                url: $url.getPath('createForm'),
                data: {
                    type: type,
                    change: change,
                    groups: $("#groups").val()
                },
                success: function () {
                    $url.redirect();
                }
            });
        }

        function validGroups() {
            if (type == 'DIRECT' && !$("#groups").val()) {
                alert("총 그룹을 입력해 주세요.");
                return false;
            }

            return true;
        }
    },

    changeForm: function (inputType) {
        const type = $("input[name='inputType']:checked").val();

        if (confirm("입력방식 방식 변경 시 기존 작성된 내용은 삭제됩니다.\n변경하시겠습니까?")) {
            this.createForm(true);
        }

        radioValRecover(type);
    },

    addRow: function (targetId) {
        let addRow = $($table.addRow(targetId));

        let prev = addRow.prev('tr');
        let startTimeStr = prev.find('.timepicker').val().split(":");
        let minuteStr = prev.find('select').val();

        let startTime = new Date();
        startTime.setHours(parseInt(startTimeStr[0]));
        startTime.setMinutes(parseInt(startTimeStr[1]));

        addRow.find('.timepicker').prop("disabled", true);
        addRow.find('.timepicker').val(calcTime(startTime, parseInt(minuteStr)))
        addEventListener();
        autoSearchEventListener();
    },

    update: function () {
        $ajax.put({
            data: getPageData(),
        });
    },
}

pageObj.pageStart = function () {
    initEvent();
};

let subjectAutoSearch = {};
getData('/api/v1/subject-code/list').then(function (res) {
    subjectAutoSearch = $autoSearch({
        targetKey: "subject",
        itemMap: {id: "id", searchKey: "subject", value: "content"},
        dataList: res,
        callback: function (res) {
            const target = $(res.target);
            const values = res.values;

            target.val(values.subject);

            let textarea = target.closest('tr').find('textarea');
            textarea.val(values.content);
            textAreaHeightSet(textarea);
        }
    });
    subjectAutoSearch.initEvent();
});

let placeAutoSearch = {};
getData('/api/v1/place-code/list').then(function (res) {
    placeAutoSearch = $autoSearch({
        targetKey: "place",
        maskKey: ["placeNm"],
        itemMap: {id: "placeCodeId", searchKey: "placeNm"},
        dataList: res,
        callback: function (res) {
            const target = $(res.target);
            const values = res.values;

            target.val(values.placeNm);
        }
    });

    placeAutoSearch.initEvent();
});

let educatorAutoSearch = {};
getData('/api/v1/educators/list').then(function (res) {
    educatorAutoSearch = $autoSearch({
        targetKey: "educator",
        maskKey: ["userNm"],
        itemMap: {id: "userId", searchKey: "userNm", value: "phoneNum"},
        dataList: res,
        callback: function (res) {
            const target = $(res.target);
            const values = res.values;

            target.val(values.userNm);
        }
    });

    educatorAutoSearch.initEvent();
});

let employeeAutoSearch = {};
getData('/api/v1/employees/list').then(function (res) {
    employeeAutoSearch = $autoSearch({
        targetKey: "employee",
        maskKey: ["userNm"],
        itemMap: {id: "userId", searchKey: "userNm", value: "phoneNum"},
        dataList: res,
        callback: function (res) {
            const target = $(res.target);
            const values = res.values;

            target.val(values.userNm);
        }
    });

    employeeAutoSearch.initEvent();
});

async function getData(url) {
    return await $.ajax({type: "GET", url: url});
}

const initEvent = function () {
    $(".tab-area h3 a").click(function() {
        $(".tab-area .tab").removeClass("tabon");
        $(this).parent().parent().addClass("tabon");
    });

    $("textarea").each(function () {
        textAreaHeightSet($(this));
    });

    addEventListener();
}

const textAreaHeightSet = function (textarea) {
    textarea[0].style.height = '32px';
    var textEleHeight = textarea.prop('scrollHeight');
    textarea.css('height', textEleHeight);
}

const addEventListener = function () {
    $('.timepicker').timepicker({
        timeFormat: 'HH:mm',
        interval: 5,
        startTime: '12:00',
        dynamic: false,
        dropdown: true,
        scrollbar: false,
        change: function () {
            timeChangeEvent(this)
        },
    });

    $("select.table_short_input").change(function () {
        timeChangeEvent(this)
    });

    $("img[input-mask-remove]").click(function () {
        $(this).siblings("input[type='hidden']").remove();

        $(this).siblings("input[disabled]").each(function () {
            $(this).val("");
            $(this).removeClass("input-mask");
            $(this).attr("disabled", false);
        });

        this.remove();
    });
}

const autoSearchEventListener = function () {
    subjectAutoSearch.initEvent();
    placeAutoSearch.initEvent();
    educatorAutoSearch.initEvent();
    educatorAutoSearch.initEvent();
};

const timeChangeEvent = function (target) {
    let rows = $(target).closest('table').find('tr:not(:first)');

    rows.each(function (idx, row) {
        if (idx != 0) {
            let startTimeStr = $(rows[idx - 1]).find('.timepicker').val().split(":");
            let minuteStr = $(rows[idx - 1]).find('select').val();

            let startTime = new Date();
            startTime.setHours(parseInt(startTimeStr[0]));
            startTime.setMinutes(parseInt(startTimeStr[1]));

            $(row).find('.timepicker').val(calcTime(startTime, parseInt(minuteStr)));
        }
    })
}

const calcTime = function (startTime, minute) {
    let calcDate = new Date(startTime.getTime() + minute * 60000);
    let calcHour = (calcDate.getHours() >= 10) ? calcDate.getHours() : '0' + calcDate.getHours();
    let calcMin = (calcDate.getMinutes() >= 10) ? calcDate.getMinutes() : '0' + calcDate.getMinutes();

    return calcHour + ":" + calcMin;
}

const radioValRecover = function (recoverVal) {
    $("input[name='inputType']").each(function() {
        var $this = $(this);

        if($this.val() == recoverVal) {
            $this.prop('checked', true);
        } else {
            $this.prop('checked', false)
        }
    });
}

const getPageData = function () {
    return {
        manager: $("#manager").val()?{
            userId: $("#manager").siblings('input[type="hidden"]').val(),
        }: null,
        totalStudent: $("#totalStudent").val(),
        leaders: $("#leaders").val(),
        scheduleList: getGroupsList(),
        teachers: $("#teachers").val(),
        educators: $("#educators").val(),
        etc: $("#etc").val(),
    }

    function getGroupsList() {
        const resultList = [];
        $("article").each(function (idx, group) {
            const groupNum = idx + 1;

            $(group).find(".box > table[schedule_table]").each(function () {
                const training_date = this.id.split("_")[1];

                $table.getData(this.id).forEach(function (rowData) {
                    resultList.push({
                        id: rowData.id,
                        groups: groupNum,
                        rowNum: rowData.rowNum,
                        subject: rowData.subject,
                        content: rowData.content,
                        placeCode: rowData.placeCodeId?{
                            id: rowData.placeCodeId
                        }:null,
                        date: training_date + "T" + rowData.date,
                        times: rowData.times,
                        educator: rowData.userId ? {
                            userId: rowData.userId
                        } : null,
                        etc: rowData.etc
                    });
                });
            });
        });

        return resultList;
    }
}
