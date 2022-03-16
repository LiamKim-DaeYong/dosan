var pageObj = {

}

$(document).ready(function () {

    var CalendarList = [];

    function CalendarInfo() {
        this.id = null;
        this.name = null;
        this.checked = true;
        this.color = null;
        this.bgColor = null;
        this.borderColor = null;
        this.dragBgColor = null;
    }

    function addCalendar(calendar) {
        CalendarList.push(calendar);
    }

    var calendar;
    var id = 0;

    calendar = new CalendarInfo();
    id += 1;
    calendar.id = String(id);
    calendar.name = '찾아가는 학교 수련';
    calendar.color = '#ffffff';
    calendar.bgColor = '#00a9ff';
    calendar.dragBgColor = '#00a9ff';
    calendar.borderColor = '#00a9ff';
    addCalendar(calendar);

    calendar = new CalendarInfo();
    id += 1;
    calendar.id = String(id);
    calendar.name = '수련원 입교수련';
    calendar.color = '#ffffff';
    calendar.bgColor = '#03bd9e';
    calendar.dragBgColor = '#03bd9e';
    calendar.borderColor = '#03bd9e';
    addCalendar(calendar);

    calendar = new CalendarInfo();
    id += 1;
    calendar.id = String(id);
    calendar.name = '개인일정';
    calendar.color = '#ffffff';
    calendar.bgColor = '#9e5fff';
    calendar.dragBgColor = '#9e5fff';
    calendar.borderColor = '#9e5fff';
    addCalendar(calendar);

    calendar = new CalendarInfo();
    id += 1;
    calendar.id = String(id);
    calendar.name = '기타일정';
    calendar.color = '#ffffff';
    calendar.bgColor = '#000000';
    calendar.dragBgColor = '#000000';
    calendar.borderColor = '#000000';
    addCalendar(calendar);

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
                options.month.visibleWeeksCount = 0;
                viewName = 'month';
            } else if (action == "toggle-weeks2") {
                viewName = 'week';
            } else if (action == "toggle-weeks3") {
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
        /*ACTIONS.dispatch(ACTIONS.CALENDAR_LIST, clickDate);*/
        const schedule = {
            calendarId: e.calendarId,
            id: String(Math.random() * 100000000000000000),
            title: e.title,
            isAllDay: e.isAllDay,
            start: e.start,
            end: e.end,
            category: e.isAllDay ? 'allday' : 'time',
            location: e.location             // 장소 정보도 입력할 수 있네요!
        };
        calendar.createSchedules([schedule]);
    });

    calendar.on('beforeUpdateSchedule', event => {
        const {schedule, changes} = event;

        calendar.updateSchedule(schedule.id, schedule.calendarId, changes);
    });

    calendar.on('beforeDeleteSchedule', scheduleData => {
        const {schedule} = scheduleData;

        calendar.deleteSchedule(schedule.id, schedule.calendarId);
    });

    calendar.on('clickSchedule', (e) => {
        var clickDate = moment(e.schedule.start._date).format("YYYY-MM-DD");
        /*ACTIONS.dispatch(ACTIONS.CALENDAR_LIST, clickDate);*/
    });

    calendar.on('clickMore', (e) => {
        var clickDate = moment(e.date._date).format("YYYY-MM-DD");
        /*ACTIONS.dispatch(ACTIONS.CALENDAR_LIST, clickDate);*/
    });

    //페이지 첫로딩시 월간보기
    var options = calendar.getOptions();
    options.month.visibleWeeksCount = 0;
    var viewName = 'month';
    calendar.setOptions(options, true);
    calendar.changeView(viewName, true);
    calendar.setCalendars(CalendarList);

    setRenderRangeText();
    setDashboardData();

})