$(document).ready(function () {
    $('nav a').on('click', function () {
        localStorage["clickMenus"] = $(this).attr("id");
    });

    if (!localStorage["clickMenus"] || $url.getPath().includes("dashboard")) {
        localStorage["clickMenus"] = "menu_dashboard";
    }

    var target = $("#" + localStorage["clickMenus"]);

    if (target.children("img").length) {
        target.closest("li").addClass("nav_on");
    } else {
        target.closest("ul").css("display", "block");
        target.closest("li").addClass("nav_on_sub");
        target.closest("ul").parent("li").addClass("nav_on");
    }

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
