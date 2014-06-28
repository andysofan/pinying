$(function(){
	
	if($(".hpFocus").size()) $(".hpFocus").flexslider({
		animation: "slide",
		slideshowSpeed : 5000,
		directionNav : false
	});
	
	if($(".productdetail .hpslides").size()) $(".productdetail .hpslides").flexslider({
		animation: "slide",
		slideshowSpeed : 5000,
		controlNav : false
	});
	
	
	
	if($(".navlist ").size() > 0) $(".navlist").find("li").hoverIntent({
			sensitivity: 7,
			interval: 100,
			over: function(){
				$(this).addClass("blur");
				if($(this).hasClass("navi02")){
					$(this).children(".naviHover").slideDown(200);
				}
			},
			timeout:100,
			out: function(){
				$(this).removeClass("blur");
				if($(this).hasClass("navi02")){
					$(this).children(".naviHover").slideUp(200);
				}
			}
		}); 
		

	
	})
	
	
$(function(){
		$("#usrpassd").keyup(function(){$("#reusrpassd").blur();});
		$("#btn_reg").click(function(){
			if(accept()){
				$("#subform").submit();
			}
		});	
	});
	function accept(){
		if($('#accept_service').attr('checked')=='checked'){
			return true;
		}else{
			alert("请您接受《活动帮用户协议》");
			return false;
		}
	}

//input 聚焦
$(function () {
	common.inputFocus($('.inputFocus'));
})
	var common = {};

	common.inputFocus = function (obj) {
		var inStr;
		obj.focus(function (event) {
			if (event.target.tagName == 'TEXTAREA') {
				inStr = $(this).html();
			}
			inStr = $(this).attr('value');
			if (inStr == $(this).attr('data-value')) {
				$(this).removeAttr('value');
			}
			$(this).css('color', '#C8C5C4');
		})
		obj.focusout(function () {

			if (!$(this).attr('value')) {
				$(this).css('color', '#C8C5C4');
				$(this).attr('value', $(this).attr('data-value'));
			}
		})
	}