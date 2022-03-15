var pageObj = {

}

$(document).ready(function () {

    var calendar = new tui.Calendar('#calendar', {
        defaultView: 'month',
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

    function MenuClick(actionListener, queryString = "data-action") {
        let menuEls = document.querySelectorAll(`a[${queryString}]`);
        menuEls.forEach(el => el.addEventListener("click", evt => {
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
        }

        setRenderRangeText();
        calendar.clear();
        setDashboardData();
    });

    MenuClick((evt, target, action) => {
        var options = calendar.getOptions();
        var viewName = '';

        if (action == "toggle-monthly") {
            options.month.visibleWeeksCount = 0;
            viewName = 'month';
        } else if (action == "toggle-weeks2") {
            options.month.visibleWeeksCount = 2;
            viewName = 'month';
        } else if (action == "toggle-weeks3") {
            options.month.visibleWeeksCount = 3;
            viewName = 'month';
        }

        calendar.setOptions(options, true);
        calendar.changeView(viewName, true);

        setDropdownCalendarType();
        setRenderRangeText();
        calendar.clear();
        setDashboardData();
    });

    function setDropdownCalendarType() {
        var calendarTypeName = document.getElementById('calendarTypeName');
        var calendarTypeIcon = document.getElementById('calendarTypeIcon');
        var options = calendar.getOptions();
        var type = calendar.getViewName();
        var iconClassName;

        if (type === 'day') {
            type = 'Daily';
            iconClassName = 'calendar-icon ic_view_day';
        } else if (type === 'week') {
            type = 'Weekly';
            iconClassName = 'calendar-icon ic_view_week';
        } else if (options.month.visibleWeeksCount === 2) {
            type = '2 weeks';
            iconClassName = 'calendar-icon ic_view_week';
        } else if (options.month.visibleWeeksCount === 3) {
            type = '3 weeks';
            iconClassName = 'calendar-icon ic_view_week';
        } else {
            type = 'Monthly';
            iconClassName = 'calendar-icon ic_view_month';
        }

        calendarTypeName.innerHTML = type;
        calendarTypeIcon.className = iconClassName;
    }

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

    let openModifyModal = function(clickDate) {

    }

    calendar.on('beforeCreateSchedule', (e) => {
        var clickDate = moment(e.start._date).format("YYYY-MM-DD");
        e.guide.clearGuideElement();
        /*ACTIONS.dispatch(ACTIONS.CALENDAR_LIST, clickDate);*/
        calendar.clear();
        openModifyModal(clickDate);
    });
    calendar.on('clickSchedule', (e) => {
        var clickDate = moment(e.schedule.start._date).format("YYYY-MM-DD");
        /*ACTIONS.dispatch(ACTIONS.CALENDAR_LIST, clickDate);*/
        calendar.clear();
        openModifyModal(clickDate);
    });
    calendar.on('clickMore', (e) => {
        calendar.hideMoreView();
        var clickDate = moment(e.date._date).format("YYYY-MM-DD");
        /*ACTIONS.dispatch(ACTIONS.CALENDAR_LIST, clickDate);*/
        calendar.clear();
        openModifyModal(clickDate);
    });

    //페이지 첫로딩시 3주보기
    var options = calendar.getOptions();
    options.month.visibleWeeksCount = 3;
    var viewName = 'month';
    calendar.setOptions(options, true);
    calendar.changeView(viewName, true);

    setDropdownCalendarType();
    setRenderRangeText();
    setDashboardData();

})