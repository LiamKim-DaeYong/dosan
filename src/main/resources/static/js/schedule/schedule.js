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
})