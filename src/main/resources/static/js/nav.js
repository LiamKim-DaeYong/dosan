$(document).ready(function () {
    $('.nav_off').on('click', function () {
        $(this).toggleClass('menu_close');
        $('nav').toggleClass('close_header');
        $('.container').toggleClass('content_width');
    });
});