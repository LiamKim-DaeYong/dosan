var pageObj = {

}

$(document).ready(function () {

    var calendar = new tui.Calendar('#calendar', {
        defaultView: 'month',
        useCreationPopup: true,
        useDetailPopup: true,
        month: {
            daynames: ['일', '월', '화', '수', '목', '금', '토'], // Translate the required language.
        },
        week: {
            daynames: ['일', '월', '화', '수', '목', '금', '토'], // Translate the required language.
        },
    });

    $ajax.post({url: `/admin/scheduleEtc/{type}/schduleCategory`,
        success: function (res) {
            if (res) {
                const categoryList = [];
                res.forEach(res => {
                    const category = {
                        id: res.name,
                        name: res.name,
                        color: res.color,
                        bgColor: res.bgColor,
                        dragBgColor: res.dragBgColor,
                        borderColor: res.borderColor
                    }
                    categoryList.push(category);
                });
                /*console.log("calendarCategory",categoryList);*/
                calendar.setCalendars(categoryList);
            } else {
                console.log("해당하는 권한의 카테고리가 존재하지 않습니다.")
            }
        },
        error: function () {
          alert("!!!!!");
        }
    });

    $ajax.post({url: `/admin/scheduleEtc/{type}/schedule`,
        success: function (res) {
        /*console.log("res",res);*/
            if (res) {
                const scheduleList = [];
                res.forEach(res => {
                    const schedule = {
                        calendarId: res.categoryName,
                        id: res.scheduleId,
                        title: res.title,
                        start: res.start,
                        end: res.end,
                        category: res.isAllDay ? 'allday' : 'time',
                        location: res.location
                    }
                    scheduleList.push(schedule);
                });
                /*console.log("scheduleList",scheduleList);*/
                calendar.createSchedules(scheduleList);
            } else {
                alert("해당하는 권한의 스케줄이 존재하지 않습니다");
            }
        },
        error: function () {
            alert("!!!!!");
        }
    });

    function BtnClick(actionListener, queryString = "data-action") {
        let btnEls = document.querySelectorAll(`button[${queryString}]`);
        btnEls.forEach(el => el.addEventListener("click", evt => {
            const target = evt.target;
            const action = target.getAttribute("data-action");
            actionListener(evt, target, action)
        }));
    }

    BtnClick((evt, target, action) => {

        if (action == "move-today") {
            calendar.today();
        } else if (action == "move-prev") {
            calendar.prev();
        } else if (action == "move-next") {
            calendar.next();
        } else {
            var options = calendar.getOptions();
            var viewName = '';

            if (action == "toggle-monthly") {
                if ( $(".calendar-btn-month").hasClass("active")) {
                    $(".calendar-btn-week").removeClass("active");
                    $(".calendar-btn-day").removeClass("active");
                } else {
                    $(".calendar-btn-month").addClass("active");
                    $(".calendar-btn-week").removeClass("active");
                    $(".calendar-btn-day").removeClass("active");
                }
                options.month.visibleWeeksCount = 4;
                options.month.isAlways6Week = false;
                viewName = 'month';
            } else if (action == "toggle-week") {
                if ( $(".calendar-btn-week").hasClass("active")) {
                    $(".calendar-btn-month").removeClass("active");
                    $(".calendar-btn-day").removeClass("active");
                } else {
                    $(".calendar-btn-week").addClass("active");
                    $(".calendar-btn-month").removeClass("active");
                    $(".calendar-btn-day").removeClass("active");
                }
                viewName = 'week';
            } else if (action == "toggle-day") {
                if ( $(".calendar-btn-day").hasClass("active")) {
                    $(".calendar-btn-week").removeClass("active");
                    $(".calendar-btn-month").removeClass("active");
                } else {
                    $(".calendar-btn-day").addClass("active");
                    $(".calendar-btn-month").removeClass("active");
                    $(".calendar-btn-week").removeClass("active");
                }
                viewName = 'day';
            }

            calendar.setOptions(options, true);
            calendar.changeView(viewName, true);
        }

        setRenderRangeText();
        setDashboardData();
    });

    function setRenderRangeText() {
        var renderRange = document.getElementById('renderRange');
        var options = calendar.getOptions();
        var viewName = calendar.getViewName();

        var html = [];
        if (viewName === 'day') {
            html.push(currentCalendarDate('YYYY.MM.DD'));
        } else if (viewName === 'month' &&
            (!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
            html.push(currentCalendarDate('YYYY.MM'));
        } else {
            html.push(moment(calendar.getDateRangeStart().getTime()).format('YYYY.MM.DD'));
            html.push(' ~ ');
            html.push(moment(calendar.getDateRangeEnd().getTime()).format(' MM.DD'));
        }
        renderRange.innerHTML = html.join('');
    }

    function currentCalendarDate(format) {
        var currentDate = moment([calendar.getDate().getFullYear(), calendar.getDate().getMonth(), calendar.getDate().getDate()]);

        return currentDate.format(format);
    }

    function setDashboardData() {
        var date = currentCalendarDate('YYYY-MM');
        var options = calendar.getOptions();
    }

    calendar.on('beforeCreateSchedule', (e) => {
        console.log("beforeCreateSchedule",e);
        var test;

        console.log("1",calendar);
        const saveSchedule = {
            calendarId: e.calendarId,
            categoryName: e.calendarId,
            title: e.title,
            isAllDay: e.isAllDay,
            start: moment(e.start._date).format("YYYY-MM-DD HH:mm"),
            end: moment(e.end._date).format("YYYY-MM-DD HH:mm"),
            category: e.isAllDay ? 'allday' : 'time',
            location: e.location
        };

       $ajax.put({url: `/admin/scheduleEtc/chairman`,data: [saveSchedule],
        success: function (data) {
            const resSchedule = {
                calendarId: data.categoryName,
                id: data.scheduleId,
                title: data.title,
                start: data.start,
                end: data.end,
                category: data.isAllDay ? 'allday' : 'time',
                location: data.location
            }
            calendar.createSchedules([resSchedule]);
        },
           error: function (error) {
               console.log("errorReason",error);
           }
       });
    });

    calendar.on('beforeUpdateSchedule', event => {
        const {schedule, changes} = event;
        console.log("updateEvent",event);
        console.log("beforeUpdateSchedule",schedule);
        console.log("changes",changes);
        const tempSchedule = Object.assign(schedule,changes);

        const saveSchedule = {
            calendarId: tempSchedule.calendarId,
            categoryName: tempSchedule.calendarId,
            id: tempSchedule.id,
            scheduleId: tempSchedule.id,
            title: tempSchedule.title,
            isAllDay: tempSchedule.isAllDay,
            start: moment(tempSchedule.start._date).format("YYYY-MM-DD HH:mm"),
            end: moment(tempSchedule.end._date).format("YYYY-MM-DD HH:mm"),
            category: tempSchedule.isAllDay ? 'allday' : 'time',
            location: tempSchedule.location
        };

        console.log("saveSchedule",saveSchedule);

        $ajax.put({url: `/admin/scheduleEtc/chairman/updateSchedule`,data: [saveSchedule],
            success: function (data) {
                if (data) {
                    alert("succes");
                    console.log("calendarSchedule",data);
                } else {
                    return;
                }
            },
            error: function (error) {
                console.log("errorReason",error);
            }
        });

        calendar.updateSchedule(schedule.id, schedule.calendarId, changes);
    });

    calendar.on('beforeDeleteSchedule', scheduleData => {
        const {schedule} = scheduleData;
        console.log(schedule);

        const deleteSchedule = {
            calendarId: schedule.calendarId,
            categoryName: schedule.calendarId,
            id: schedule.id,
            scheduleId: schedule.id,
            title: schedule.title,
            isAllDay: schedule.isAllDay,
            start: moment(schedule.start._date).format("YYYY-MM-DD HH:mm"),
            end: moment(schedule.end._date).format("YYYY-MM-DD HH:mm"),
            category: schedule.isAllDay ? 'allday' : 'time',
            location: schedule.location
        };

        $ajax.delete({url: `/admin/scheduleEtc/chairman`,data: deleteSchedule,
            success: function (data) {
                if (data) {
                    alert("succes");
                    console.log("calendarSchedule",data);
                } else {
                    return;
                }
            },
            error: function (error) {
                console.log("errorReason",error);
            }
        });

        calendar.deleteSchedule(schedule.id, schedule.calendarId);
    });

    calendar.on('clickSchedule', (e) => {
        var clickDate = moment(e.schedule.start._date).format("YYYY-MM-DD");
        console.log('clickSchedule', e);
    });

    calendar.on('clickMore', (e) => {
        var clickDate = moment(e.date._date).format("YYYY-MM-DD");
    });

    //페이지 첫로딩시 월간보기
    var options = calendar.getOptions();
    /*options.month.visibleWeeksCount = 4;*/
    options.month.visibleWeeksCount = 4;
    options.month.isAlways6Week = false;
    var viewName = 'month';
    calendar.setOptions(options, true);
    calendar.changeView(viewName, true);

    setRenderRangeText();
    setDashboardData();

})