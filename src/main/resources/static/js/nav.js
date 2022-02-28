$(document).ready(function () {
    $('nav ul li').on('click', function () {
        $(this).toggleClass('nav_on');
        $(this).children('ul').slideToggle();
        $('nav ul li').not(this).removeClass('nav_on').children('ul').slideUp();
    });

    $('.nav_off').on('click', function () {
        $(this).toggleClass('menu_close');
        $('nav').toggleClass('close_header');
        $('.container').toggleClass('content_width');
    });
});