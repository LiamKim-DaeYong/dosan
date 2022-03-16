$(document).ready(function () {
    $("#all_check").on('click', function () {
        let _this = $(this);
        $.each($(".gallery_delete"), function () {
            $(this).prop('checked', _this.is(':checked'));
        })
    })

    $("#dateButton").on('click', function () {
        ACTIONS.FILTER('DATE')
    })

    $("#filterButton").on('click', function () {
        ACTIONS.FILTER('FILTER');
    })

    $("#initButton").on('click', function () {
        $("#start, #end, #filter").val('');
    })

    $("input[data-type*='date']").each(function () {
        $(this).attr("maxLength", 10);
        $(this).keypress(function () {

            var val = ($(this).val() || "").replace(/\D/g, "");
            var regExpPattern = /^([0-9]{4})\-?([0-9]{1,2})?\-?([0-9]{1,2})?\-?([0-9]{1,2})?\-?([0-9]{1,2})?/,
                returnValue = val.replace(regExpPattern, function (a, b) {
                    var nval = [arguments[1]];
                    if (arguments[2]) nval.push(arguments[2]);
                    if (arguments[3]) nval.push(arguments[3]);
                    if (arguments[4]) nval.push(arguments[4]);
                    if (arguments[5]) nval.push(arguments[5]);
                    return nval.join("-");
                });
            $(this).val(returnValue);
        })
    })

    $(".datepicker").each(function () {
        $(this).datepicker({
            dateFormat: 'yy-mm-dd',
            showOtherMonths: true,
            showMonthAfterYear:true,
            changeYear: true,
            changeMonth: true,
            showOn: "focus",
            buttonImageOnly: true,
            buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
            buttonText: "선택",
            yearSuffix: "년",
            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            dayNamesMin: ['일','월','화','수','목','금','토'],
            dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
        });
    })
})

ACTIONS = {
    FILTER: function (st) {
        let start = $("#start").val();
        let end = $("#end").val();

        if (start || start != '') {
            if (ACTIONS.DATE_VALIDATION($("#start")) == false) {
                alert("검색일을 확인해주세요.");
                $("#start").focus();
                return false;
            }
        }

        if (end || end != '') {
            if (ACTIONS.DATE_VALIDATION($("#end")) == false) {
                alert("검색일을 확인해주세요.");
                $("#end").focus();
                return false;
            }
        }

        if ((start || start != '') && (end || end != '')) {
            if (start > end) {
                alert("검색일을 확인해주세요.");
                $("#start").focus();
                return false;
            }
        }

        if (st == 'DATE') {
            $("#filter").val('');
        }

        document.getElementById('filter_form').submit();
    },
    DATE_VALIDATION: function (date) {
        let valid = moment(date.val(), 'YYYY-MM-DD', true).isValid();
        let split = date.val().split('-');
        let year = Number(split[0]),
            month = Number(split[1]),
            day = Number(split[2]);

        if (valid == false) {
            return false;
        } else {
            if (month < 1 || month > 12) {
                return false;
            } else if (day < 1 || day > 31) {
                return false;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                return false;
            } else  if (month == 2) {
                let isLeap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                if (day > 29 || (day == 29 && !isLeap)) {
                    return false;
                }
            }
        }

        return true;
    },
    DELETE: function () {
        let idList = [];

        let checked = $(".gallery_delete:checked");
        $.each(checked, function () {
            let id = $(this).prop('id').split('delete_')[1];

            idList.push(id);
        })

        if (checked.length == 0) {
            alert("삭제할 목록을 체크해주세요.");
            return false;
        } else {
            if (confirm("삭제하시겠습니까?")) {
                $.ajax({
                    type: "POST",
                    url: "/gallery/delete",
                    contentType: 'application/json',
                    dataType: "JSON",
                    data: JSON.stringify(idList),
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                    },
                    success: function (res) {
                        if (res == true) {
                            window.location.href = window.location.href;
                        } else {
                            alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                            return false;
                        }
                    },
                    error: function (xhr, status, error) {
                        alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                        return false;
                    }
                })
            } else {
                return false;
            }
        }
    },
    PAGE: function (page) {
        let filterDto = {
            start : start,
            end: end,
            filter: filter
        }

        $.ajax({
            type: "POST",
            url: "/gallery/page/?page=" + page,
            contentType: "application/json",
            data: JSON.stringify(filterDto),
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (res) {
                $("#galleryFrag").replaceWith(res);
            }
        })
    }
}