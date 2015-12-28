$(function () {
    var flashnum = $("#banner").find("img").length;
    if (flashnum !== 0)$("#banner").find(".banner").css("height", "100%");

    $("#wp_news_w62").find(".news-item").each(function () {
        var $coltxt = $(this).find(".newly");
        var colwz = $coltxt.text();
        $coltxt.html(colwz);
    });
});

Date.prototype.Format = function (fmt) {
    var weeks = {
        "cn": ["日", "一", "二", "三", "四", "五", "六"],
        "en": ["Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"]
    };
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds(),            //毫秒
        "W": weeks.en[this.getDay()],			  //星期(英)
        "w": weeks.cn[this.getDay()]			  //星期(中)
    };

    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$(function () {

    $("#toptip_date").html(new Date().Format("今天是yyyy年MM月dd日 星期w"));

    $("#keyword").focus(function (event) {
        /* Act on the event */
        var val = $.trim($(this).val());
        if (val == "在此输入") {
            $(this).val("");
        }
    }).blur(function (event) {
        /* Act on the event */
        var val = $.trim($(this).val());
        if (val == "") {
            $(this).val("在此输入");
        }
    });

    $("#search-submit").click(function (event) {
        /* Act on the event */
        event.preventDefault();
        var val = $.trim($("#keyword").val());
        if (val !== "" && val !== "在此输入") {
            $("#searchform").submit();
        } else {
            alert("请输入关键词");
        }
    });

    var $menu = $(".main-menu");

    $("li", $menu).hover(function () {
        /* Stuff to do when the mouse enters the element */
        $(this).children('.sub-menu').fadeIn(400).siblings().children('.sub-menu').stop(true, true).hide();
    }, function () {
        /* Stuff to do when the mouse leaves the element */
        $(this).children('.sub-menu').fadeOut(200);
    });

    $(".links-wrap").each(function (index, el) {
        $(el).hover(function () {
            /* Stuff to do when the mouse enters the element */
            $(this).addClass('wrap-open').children('.link-items').slideDown(300);
        }, function () {
            /* Stuff to do when the mouse leaves the element */
            $(this).removeClass('wrap-open').children('.link-items').slideUp(100);
        });
    });

    var $gotop = $("#gotop");

    $(".back-top", $gotop).click(function (event) {
        /* Act on the event */
        event.preventDefault();
        $("html,body").stop().animate({scrollTop: 0}, 700);
    });

    $(window).scroll(function (event) {
        /* Act on the event */
        var windHeight = $(window).height();
        var scrolltop = $(window).scrollTop();
        var height = $gotop.outerHeight();
        var top = windHeight + scrolltop - 250 - height;

        if (scrolltop > 260) {
            $gotop.stop().show().animate({top: top}, 300);
        } else {
            $gotop.stop().hide();
        }
    });

});