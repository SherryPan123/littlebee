$(document).ready(function() {
	$('.hasjs').show();
	$("#maintable tbody tr:odd").addClass("odd");
	$("#maintable tbody tr:even").addClass("even");
})

$(function() {
	$('.main_rightNew_tit').find("li").hover(
        function() {
            $(this).addClass('li_bg').siblings().removeClass();
            $('.main_rightNew_con').find("div").eq($(this).index()).fadeIn(
                    100).siblings().hide();
        })

})
// 侧bannner效果
$(function() {
	var num = 0; // 初始化
	var timer;
	$('.main_banner_tex li').mouseover(
			function() {
				$(this).addClass('current').siblings().removeClass();
				$('.main_left_picture li').eq(
						$(this).addClass('current').index()).show().siblings()
						.hide();
				// $('.main_banner_tex1
				// li').eq($(this).index()).fadeIn(500).siblings().hide();

				// 当鼠标进入ol li是获取当前的li的角标， 由于鼠标离开时
				// 需要从鼠标离开的li的后一个开始轮播所以我们要把当前的li的角标+1 用num记住它
				num = $(this).index() + 1;
			})
	function autoplay() {
		if (num == 5) {
			num = 0;
		}
		$('.main_banner_tex li').eq(num).addClass('current').siblings()
				.removeClass();
		$('.main_left_picture li').eq(num).addClass('current').show()
				.siblings().hide();
		// $('.main_banner_tex1 li').eq(num).fadeIn(500).siblings().hide();
		num++;
	}
	timer = setInterval(autoplay, 5000);
	$('.main_banner_tex li').mouseover(function() {
		clearInterval(timer);
	}).mouseout(function() {
		timer = setInterval(autoplay, 5000);
	})
})