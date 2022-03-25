var pageObj = {
    viewToday: function (action) {
        console.log("today",action);
        calendar.today();
        pageObj.setRenderRangeText();
    },

    viewPrev: function (action) {
        console.log("today",action);
        calendar.prev();
        pageObj.setRenderRangeText();
    },

    viewNext: function (action) {
        console.log("today",action);
        calendar.next();
        pageObj.setRenderRangeText();
    },

    viewMonth: function (action) {
        $(".calendar-btn-month").addClass("active");
        $(".calendar-btn-week").removeClass("active");
        $(".calendar-btn-day").removeClass("active");

        var viewName = 'month';
        var options = calendar.getOptions();
        options.month.visibleWeeksCount = 4;
        options.month.isAlways6Week = false;
        calendar.setOptions(options, true);
        calendar.changeView(viewName, true);
        pageObj.setRenderRangeText();
    },

    viewWeek: function (action) {
        $(".calendar-btn-week").addClass("active");
        $(".calendar-btn-month").removeClass("active");
        $(".calendar-btn-day").removeClass("active");

        var viewName = 'week';
        var options = calendar.getOptions();
        calendar.setOptions(options, true);
        calendar.changeView(viewName, true);
        pageObj.setRenderRangeText();
    },

    viewDay: function (action) {
        $(".calendar-btn-day").addClass("active");
        $(".calendar-btn-month").removeClass("active");
        $(".calendar-btn-week").removeClass("active");

        var viewName = 'day';
        var options = calendar.getOptions();
        calendar.setOptions(options, true);
        calendar.changeView(viewName, true);
        pageObj.setRenderRangeText();
    },

    setRenderRangeText: function (action) {
        var renderRange = document.getElementById('renderRange');
        var options = calendar.getOptions();
        var viewName = calendar.getViewName();

        var html = [];
        if (viewName === 'day') {
            html.push(pageObj.currentCalendarDate('YYYY.MM.DD'));
        } else if (viewName === 'month' &&
            (!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
            html.push(pageObj.currentCalendarDate('YYYY.MM'));
        } else {
            html.push(moment(calendar.getDateRangeStart().getTime()).format('YYYY.MM.DD'));
            html.push(' ~ ');
            html.push(moment(calendar.getDateRangeEnd().getTime()).format(' MM.DD'));
        }
        renderRange.innerHTML = html.join('');
    },

    currentCalendarDate: function (format) {
        var currentDate = moment([calendar.getDate().getFullYear(), calendar.getDate().getMonth(), calendar.getDate().getDate()]);
        return currentDate.format(format);
    },

    save: function (e) {
        const saveSchedule = {
            categoryNm: e.calendarId,
            title: e.title,
            isAllDay: e.isAllDay,
            start: moment(e.start._date).format("YYYY-MM-DD HH:mm"),
            end: moment(e.end._date).format("YYYY-MM-DD HH:mm"),
            category: e.isAllDay ? 'allday' : 'time',
            location: e.location
        };

        $ajax.put({
            data: [saveSchedule],
            success: function (data) {
                const resSchedule = {
                    calendarId: data.categoryNm,
                    id: data.id,
                    title: data.title,
                    start: data.start,
                    end: data.end,
                    category: data.isAllDay ? 'allday' : 'time',
                    location: data.location
                }
                calendar.createSchedules([resSchedule]);
            },
            error: function (error) {
                console.log("errorReason", error);
            }
        });
    },

    delete: function (scheduleData) {
        const {schedule} = scheduleData;

        const deleteSchedule = {
            calendarId: schedule.calendarId,
            categoryNm: schedule.calendarId,
            id: schedule.id,
            scheduleId: schedule.id,
            title: schedule.title,
            isAllDay: schedule.isAllDay,
            start: moment(schedule.start._date).format("YYYY-MM-DD HH:mm"),
            end: moment(schedule.end._date).format("YYYY-MM-DD HH:mm"),
            category: schedule.isAllDay ? 'allday' : 'time',
            location: schedule.location
        };

        console.log("deleteSchedule", deleteSchedule);

        $ajax.delete({
            data: deleteSchedule,
            success: function (data) {
                if (data) {
                    alert("succes");
                    console.log("calendarSchedule", data);
                } else {
                    return;
                }
            },
            error: function (error) {
                console.log("errorReason", error);
            }
        });

        calendar.deleteSchedule(schedule.id, schedule.calendarId);
    },

    update: function (event) {
        const {schedule, changes} = event;
        const tempSchedule = Object.assign(schedule, changes);

        const saveSchedule = {
            calendarId: tempSchedule.calendarId,
            categoryNm: tempSchedule.calendarId,
            id: tempSchedule.id,
            scheduleId: tempSchedule.id,
            title: tempSchedule.title,
            isAllDay: tempSchedule.isAllDay,
            start: moment(tempSchedule.start._date).format("YYYY-MM-DD HH:mm"),
            end: moment(tempSchedule.end._date).format("YYYY-MM-DD HH:mm"),
            category: tempSchedule.isAllDay ? 'allday' : 'time',
            location: tempSchedule.location
        };

        console.log("saveSchedule", saveSchedule);

        $ajax.put({
            url: `/admin/scheduleEtc/chairman/updateSchedule`, data: [saveSchedule]
        });

        calendar.updateSchedule(schedule.id, schedule.calendarId, changes);
    },

    clickSchedule: function (e) {
       console.log('clickMSchedule',e)
    },

    clickMore: function (e) {
       console.log('clickMore',e)
    },
}

var calendar;
pageObj.pageStart = function () {
     calendar = new tui.Calendar('#calendar', {
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

    $ajax.post({ url: $url.getPath() + '/schduleCategory',
        success: function (res) {
            if (res) {
                if(res == "CHAIRMAN") {
                    const charimanCategory = {
                        id: '이사장일정',
                        name: '이사장일정',
                        color: "#ffffff",
                        bgColor: "#613bde",
                        dragBgColor: "#613bde",
                        borderColor: "#613bde"
                    }
                    calendar.setCalendars([charimanCategory]);
                } else if (res == "DIRECTOR") {
                    const directorCategory = {
                        id: '원장일정',
                        name: '원장일정',
                        color: "#ffffff",
                        bgColor: "#9e5fff",
                        dragBgColor: "#9e5fff",
                        borderColor: "#9e5fff"
                    }
                    calendar.setCalendars([directorCategory]);
                } else if (res == "ETC") {
                    const etcCategory = {
                        id: '기타일정',
                        name: '기타일정',
                        color: "#ffffff",
                        bgColor: "#000000",
                        dragBgColor: "#000000",
                        borderColor: "#000000"
                    }
                    calendar.setCalendars([etcCategory]);
                }
            } else {
                console.log("해당하는 권한의 카테고리가 존재하지 않습니다.")
            }
        },
        error: function () {
            alert("!!!!!");
        }
    });

    $ajax.post({
        url: $url.getPath() + '/schedule',
        success: function (res) {
            /*console.log("res",res);*/
            if (res) {
                const scheduleList = [];
                res.forEach(res => {
                    const schedule = {
                        calendarId: res.categoryNm,
                        id: res.id,
                        title: res.title,
                        start: res.start,
                        end: res.end,
                        category: res.isAllDay ? 'allday' : 'time',
                        location: res.location
                    }
                    scheduleList.push(schedule);
                });
                calendar.createSchedules(scheduleList);
            } else {
                alert("해당하는 권한의 스케줄이 존재하지 않습니다");
            }
        },
        error: function () {
        }
    });

    calendar.on('beforeCreateSchedule', (e) => {
        console.log("beforeCreateSchedule", e);
        pageObj.save(e)
    });

    calendar.on('beforeUpdateSchedule', event => {
        pageObj.update(event);
    });

    calendar.on('beforeDeleteSchedule', scheduleData => {
        pageObj.delete(scheduleData);
    });

    calendar.on('clickSchedule', (e) => {
        pageObj.clickSchedule(e)
    });

    calendar.on('clickMore', (e) => {
        pageObj.clickMore(e)
    });

    //페이지 첫로딩시 월간보기
    var options = calendar.getOptions();
    options.month.visibleWeeksCount = 4;
    options.month.isAlways6Week = false;
    var viewName = 'month';
    calendar.setOptions(options, true);
    calendar.changeView(viewName, true);
    pageObj.setRenderRangeText();
};
