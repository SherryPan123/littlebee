var topw11 = document.getElementById("topw11");
var beginw11 = document.getElementById("beginw11");
var endw11 = document.getElementById("endw11");
var tabw11 = document.getElementById("tabw11");
//复制内容
endw11.innerHTML = beginw11.innerHTML;
//调整table的宽度
tabw11.width = topw11.offsetWidth * 2;
//循环函数
function marqueew11() {
    if (endw11.offsetWidth - topw11.scrollLeft <= 0) {
        topw11.scrollLeft -= beginw11.offsetWidth;
    } else {
        topw11.scrollLeft += 1;
    }
}
//定时器
var mymarw11 = setInterval(marqueew11, 50);
//鼠标移入
topw11.onmouseover = function () {
    clearInterval(mymarw11);
};
//鼠标移出
topw11.onmouseout = function () {
    mymarw11 = setInterval(marqueew11, 50);
};

$(function(){
    $(".hotnews").sudyTab({
        handle:".nav-link",
        content:".hotnews-list"
    });
    $(".focus-wrap").sudyfocus({
        p:10,
        zWidth:565,
        zHeight:308,
        title:{
            href:true
        },
        pagination:true,
        trigger:"click"
    });
});
