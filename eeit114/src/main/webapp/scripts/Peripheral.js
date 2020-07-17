let slideNum = 0;
let slideCount = $(".slides li").length;
let lastIndex = slideCount - 1;
let slidemove;
var s;
$(".dot li").mouseenter(function() {
	slideNum = $(this).index();
	show();
})
function show() {
	$(".dot li").eq(slideNum).css("background", "white").siblings().css(
			"background", "transparent");
	slidemove = 0 - (slideNum * 800);
	$("ul.slides").css("left", slidemove);
}
$("#prevSlide").click(function() {
	slideNum--;
	if (slideNum < 0)
		slideNum = lastIndex;
	show();
})
$("#nextSlide").click(function() {
	slideNum++;
	if (slideNum > lastIndex)
		slideNum = 0;
	show();
})
function set() {
	slideNum += 1;
	if (slideNum > lastIndex)
		slideNum = 0;
	show();
	console.log(slideNum);
}
function inter() {
	s = window.setInterval(set, 2000)
}
$(document).ready(function() {
	inter();
})
function clear() {
	clearInterval(s);
}
$("#pic").hover(function() {
	clear();
}, function() {
	inter();
});