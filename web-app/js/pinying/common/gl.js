function mycarousel_initCallback(carousel)
{
    carousel.clip.hover(function() {
        carousel.stopAuto();
    }, function() {
        carousel.startAuto();
    });
};
function banner_initCallback(carousel) {
    jQuery('#bannerContainer .jcarousel-control a').bind('click', function() {
        carousel.scroll(jQuery.jcarousel.intval(jQuery(this).text()));
        return false;
    });
};
function bannercall(carousel, state) {
	$("#bannerContainer .jcarousel-control a").removeClass("current");
	var num = $("#bannerContainer .jcarousel-control a").size();
	$("#bannerContainer .jcarousel-control a").eq((carousel.first-1)%num).addClass("current");
}
function pic_initCallback(carousel) {
    jQuery('#picPageNav li').bind('click', function() {
        carousel.scroll(jQuery.jcarousel.intval(jQuery(this).attr("num")));
        return false;
    });
};
function piccall(carousel, state) {
	$("#picPageNav li").removeClass("selPic");
	$("#picPageNav li div").hide();
	var num = $("#picPageNav li").size();
	$("#picPageNav li").eq((carousel.first-1)%num).addClass("selPic").children("div").show();
}
function AddFavorite(sURL, sTitle) {
	try {
		window.external.addFavorite(sURL, sTitle);
	} catch (e) {
		try {
			window.sidebar.addPanel(sTitle, sURL, "");
		} catch (e) {
			alert("加入收藏失败，请使用Ctrl+D进行添加");
		}
	}
}
function SetHome(obj, vrl) {
	try {
		obj.style.behavior = 'url(#default#homepage)';
		obj.setHomePage(vrl);
	} catch (e) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'");
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage', vrl);
		}
	}
}
$(function(){
	$("#bigPic img").each(function(){
		var image=new Image();
		image.src=$(this).attr("src");
		$(this).attr("realSize",image.width+"/"+image.height);
	});
	$("#bannerContainer img").each(function(){
		var image=new Image();
		image.src=$(this).attr("src");
		$(this).attr("realSize",image.width+"/"+image.height);
	});
	$("img").each(function(){
		if(typeof($(this).attr("realSize")) != "undefined")	{
			if((parseInt($(this).attr("realSize").split("/")[0]) / parseInt($(this).attr("realSize").split("/")[1])) / (parseInt($(this).attr("defaultSize").split("/")[0]) / parseInt($(this).attr("defaultSize").split("/")[1])) > 1)
				$(this).width($(this).attr("defaultSize").split("/")[0]);
			else
				$(this).height($(this).attr("defaultSize").split("/")[1]);
		}
	});
	var size = $("#bannerContainer li").size();
	var str = "";
	for( var loop = 0; loop < size; loop++){
		var curr = "";
		if(loop === 0)
			curr = ' class="current"';
		str += '<a href="#"'+curr+'>'+(loop+1)+'</a>';	
	}
	$("#bannerContainer .jcarousel-control").append(str);
	if($("#bannerContainer").size() > 0){
		jQuery('#bannerContainer').jcarousel({
			auto: 5,
			scroll: 1,
			animation: 1000,
			wrap: "circular",
			initCallback: banner_initCallback,
			itemLastOutCallback:bannercall
		});
	}
	if($("#bigPic").size() > 0){
		jQuery('#bigPic').jcarousel({
			scroll: 1,
			animation: 1000,
			initCallback: pic_initCallback,
			itemVisibleInCallback:piccall
		});
	}
	if($("#picPageNav").size() > 0){
		jQuery('#picPageNav').jcarousel({
			scroll: 6,
			animation: 1000
		});
	}
	$("#picPageNav li").hover(function(){
			$(this).addClass("sel");
			$(this).children("div, span").show();
			if($(this).attr("class").indexOf("selPic") != -1)
				$(this).children("span").hide();	
		},function(){
			$(this).removeClass("sel");
			if($(this).attr("class").indexOf("selPic") != -1)
				$(this).children("span").hide();
			else
				$(this).children("div, span").hide();
	});
	$("#picPageNav li").click(function(){
		$("#picPageNav li").removeClass("selPic");
		$("#picPageNav li div").hide();
		$(this).children("div").show();	
		$(this).addClass("selPic");
		$(this).children("span").hide();
		return false;
	});
	//设置头部弹出层效果
	$("#myHDB, #login, #sitemap").hover(function(){
			var popupId = $(this).attr("popup");
			$("#"+popupId).show();
			var offset = $(this).offset();
			if(this.id === "myHDB"){
				$("#"+popupId).css("left",offset.left-9);
				//$("#message").hide();
			}
			if(this.id === "login")
				$("#"+popupId).css("left",offset.left-60);
			if(this.id === "sitemap")
				$("#"+popupId).css("left",offset.left-107);
				
		},function(){
			$("#"+$(this).attr("popup")).hide();	
		}
	);
	$("#loginPopup, #popupNav, #popupHDB").hover(function(){
			$(this).show();	
			$("#"+$(this).attr("show")).addClass("sel");
		},function(){
			$(this).hide();	
			$("#"+$(this).attr("show")).removeClass("sel");		
		}
	);
	
	//设置有默认文字的输入框效果
	$("input[default]").each(function(){
		if($(this).val() === $(this).attr("default"))
			$(this).css("color","#ccc");
		else
			$(this).css("color","#333");
	});
	$("input[default]").focus(function(){
		if($(this).val() === $(this).attr("default")){
			$(this).val("");
			$(this).css("color","#333");
			if($(this).attr("realtypes") === "password"){
				$(this).hide();
				$(this).next().show().focus().css("color","#333");
			}
		}			
	}).blur(function(){
		if($(this).val() === ""){
			$(this).val($(this).attr("default"));
			$(this).css("color","#ccc");
			if($(this).attr("realtypes") === "text"){
				$(this).hide();
				$prev = $(this).prev();
				$prev.show().css("color","#ccc").val($prev.attr("default"));
			}
		}
			
	});
	
	//城市选择器
//	var city = [{'cn':'上海','code':'s','href':'test'},{'cn':'北京','code':'b','href':'test2'}];
//	$('#selCity').citypicker({data:city});
	
	//排行榜展开收缩效果
	$("#sideBar .listTitle").hover(function(){
		$("#sideBar .listTitle").children("span").removeClass("highLight");		
		$("#sideBar .listTitle a").addClass("black");
		$("#sideBar .listTitle a.plate").addClass("highGray");
		$("#topList dl").hide();
		$(this).next().show();
		$(this).children("span").addClass("highLight");
		$(this).children("a").removeClass("black");
		$(this).children("a").removeClass("highGray");
	},function(){
	});
	$("#topList .listContent").hover(function(){
		$(this).show();
	},function(){
	});
	
	$("#hotList .listTitle").hover(function(){
		$("#hotList .listTitle").children("span").removeClass("highLight");		
		$("#hotList .listTitle a").addClass("black");
		$("#hotList .listTitle a.plate").addClass("highGray");
		$("#hotList dl").hide();
		$(this).next().show();
		$(this).children("span").addClass("highLight");
		$(this).children("a").removeClass("black");
		$(this).children("a").removeClass("highGray");
	},function(){
	});
	$("#hotList .listContent").hover(function(){
		$(this).show();
	},function(){
	});
	//会员弹出层效果
	var $con = $("#popupMember");
	if($("#member").size() > 0){
	$("#member2 .content > div, #member .content > div, #friend dt a").hover(function(){			 
		var offset = $("#member").offset();
		var offsets = $(this).offset();
		var w = ($(this).index()%4) * 4; 
		width = offset.left + 18 + w;
		//alert($("#member").width());
		if($("#member").width() < 286){
			var w = ($(this).index()%4) * 2; 
			width = offset.left + 0 + w;
		}
		if($("#member").width() < 238)
			width = offset.left - 230 + $("#member").width();
		$con.css({
			'top' : $(this).offset().top + 90,
			'left' : width
		});
		var widths = offsets.left - offset.left - 5 - w;
		if($("#member").width() < 286)
			widths = offsets.left - offset.left + 9 - w;
		if($("#member").width() < 238)
			widths = widths + 32;
		$("#popupMember .arrow").css({'left': widths});
		//$("#popupMember img").attr("src",$(this).find("img").attr("src").replace("-s.jpg","-m.jpg"));
		var data = $(this).attr("info").split("|");
		$("#memberName").html(data[0]);
		$("#memberCity").html(data[1]);
		$("#commonNum").html(data[2]);
		$("#fans").html(data[3]);
		$("#popupMember .personMail").attr("href", data[4]);
		$("#popupMember .focus").attr("href", data[5]);
		$("#pub").html(data[6]);
		$con.show();	
	},function(){
		$con.hide();	
	});}
	$("#popupMember").hover(function(){
		$(this).show();	
	},function(){
		$(this).hide();	
	});
	if($("#industryScroll").size() > 0){
		$('#industryScroll a').tabswitch({
			target: 'href',
			selected: 0,
			onselect: function($tab, $target){
				$tab.blur();
			}
		});
	}
	
	
	if($("#updateScroll").size() > 0){
		$('#updateScroll a').tabswitch({
			target: 'href',
			selected: 0,
			onselect: function($tab, $target){
				$tab.blur();
			}
		});
	}
	//品牌翻牌效果
	
	var nummove=0;
	var numout=0;
	$("#brand li").hover(
		function(){
		if(nummove==0)
		{
			var content = $("a",this).attr("content");
			nummove=1;
			$("a",this).children().eq(1).animate({ height: "0", "z-index":"0"}, 300,function(){nummove=0;});
		}
			},
			function(){
			if(numout==0)
			{
			var img = $("a",this).attr("imgs");
			numout=1;
			$("a",this).children().eq(1).animate({ height: "56px", "z-index":"10"}, 300,function(){nummove=0;});
			numout=0;
			}
			}
	)
	$("#bigPic").hover(function(){
			$(".opacity",this).css({ "height": "31px", "top":"-33px"});
			$(".bannerText",this).css({ "height": "20px", "top":"-64px"});	
		},
		function(){
			$(".opacity",this).css({ "height": "0", "top":"0"});
			$(".bannerText",this).css({ "height": "0", "top":"0"});
		});
	$("#bannerContainer .bannerCon").hover(
		function(){
			if($("#bannerContainer .jcarousel-control").hasClass("philan"))
				return false;
			$(".opacity",this).css({ "height": "32px", "top":"-32px"});
			$(".bannerText",this).css({ "height": "20px", "top":"-64px"});	
			$("#bannerContainer .jcarousel-control").css({"top":"250px"})	
		},
		function(){
			if($("#bannerContainer .jcarousel-control").hasClass("philan"))
				return false;
			$(".opacity",this).css({ "height": "0", "top":"0"});
			$(".bannerText",this).css({ "height": "0", "top":"0"});
			$("#bannerContainer .jcarousel-control").animate({top:"282px"}, 0);
		}
	);
	
	$("#bannerContainer .jcarousel-control a, #bannerContainer .jcarousel-control").hover(
		function(){
			if($("#bannerContainer .jcarousel-control").hasClass("philan"))
				return false;
			$("#bannerContainer .jcarousel-control").css({top:"250px"});	
			$("#bannerContainer .opacity").css({ "height": "32px", "top":"-32px"});
			$("#bannerContainer .bannerText").css({ "height": "20px", "top":"-64px"});
		},
		function(){
			if($("#bannerContainer .jcarousel-control").hasClass("philan"))
				return false;
			$("#bannerContainer .jcarousel-control").css({top:"250px"});	
			$("#bannerContainer .opacity").css({ "height": "32px", "top":"-32px"});
			$("#bannerContainer .bannerText").css({ "height": "20px", "top":"-64px"});
		}
	);
	
	/*tab 切换*/
	if($("#tabs1").size() > 0){
		$('#tabs1 a').tabswitch({
			target: 'href',
			onselect: 0,
			onselect: function($tab, $target){
				$tab.blur();
			}
		});
	}
	$("#bannerContainer .jcarousel-control a").click(function(){
		$("#bannerContainer .jcarousel-control a").removeClass("current");
		$(this).addClass("current");	
	});
	
	if($("#mycarousel").size() > 0){
		jQuery('#mycarousel').jcarousel({
			auto: 3,
			vertical: true,
			scroll: 1,
			wrap: "circular",
			initCallback: mycarousel_initCallback
		});
	}
	if($("#industryScroll").size() > 0){
		jQuery('#industryScroll').jcarousel({
			auto: 0,
			scroll: 4,
			wrap: null
		});
	}
	if($("#updateScroll").size() > 0){
		jQuery('#updateScroll').jcarousel({
			auto: 0,
			scroll: 4,
			wrap: null
		});
	}
	$("#detailCommon .reply").click(function(){
		$("#detailCommon .replyCon").show();
		return false;
	});
	
	$("#detailCommon .recommend, #detailAssess .recommend").hover(function(){
		$("#popupShare").show();
		var offset = $(this).offset();
		$("#popupShare").css({"left":offset.left, "top":offset.top + 19});
	},function(){
		$("#popupShare").hide();
	});
	$("#popupShare").hover(function(){
		$(this).show();	
		$("#detailCommon .recommend, #detailAssess .recommend").addClass("sel");
	},function(){
		$(this).hide();	
		$("#detailCommon .recommend, #detailAssess .recommend").removeClass("sel");
	});
	$("#mask").css({"width":$(document).width(), "height":$(document).height()});
	$(window).resize(function(){
		$("#mask").css({"width":$(document).width(), "height":$(document).height()});
		$("#popupCommon").css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 443)/2
		});
		$("#popupCreatGroup").css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 313)/2
		});
		$("#popupMap").css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 543)/2
		});
		$("#participate").css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 441)/2
		});
		$("#popupModifyGroup").css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 313)/2
		});
		$("#popupReply").css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 593)/2
		});
	});
	$("#recomm").click(function(){
		$("#mask").show();
		$("#popupCommon").show().css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 443)/2
		});
		return false;
	});
	$("#brandrecomm").click(function(){
		$("#mask").show();
		$("#popupCommon").show().css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 443)/2
		});
		return false;
	});
	$("#creatGroupBtn, #creatGroup").click(function(){
		$("#mask").show();
		$("#popupCreatGroup").show().css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 313)/2
		});
		return false;
	});
	$("#popupCreatGroup .save, #popupCreatGroup .cancel").click(function(){
		$("#popupCreatGroup, #mask").hide();
		return false;
	});
	$("#viewMap").click(function(){
		$("#mask").show();
		$("#popupMap").css({
			"visibility":'visible',
			"top":$(window).scrollTop()+100, "left":($(window).width() - 543)/2
		});
		return false;
	});
	$("#popupMap .close").click(function(){
		$("#mask").hide();
		$('#popupMap').css({'visibility': 'hidden'});
		return false;
	});
	/*$("#participateBtn").click(function(){
		$("#mask").show();
		$("#participate").show().css({
			"top":$(window).scrollTop()+100, "left":($(window).width() - 441)/2
		});
		return false;
	});*/
	$("#popupCommon .close,#participate .close").click(function(){
		$("#mask").hide();
		$(this).parent().parent().hide();
		return false;
	});
	var hdbOffset = $("#myHDB").offset();
	if(parseInt($("#message").children().eq(0).html())){
		$(window).resize(function(){
			var offset = $("#myHDB").offset();
			$("#message").css({top:offset.top + 26, left: offset.left+37}).show();
		});
		$("#message").css({top:hdbOffset.top + 26, left: hdbOffset.left+37}).show();
		$("#message > a.close").click(function(){$("#message").hide()});
		$("#mail span.num").show();
	}
	$("#selectAll").click(function(){
		if($(this).attr("checked")){
			$("#brandList dl input").attr("checked", true);	
		}else{
			$("#brandList dl input").attr("checked", false);	
		}
	});
	$("#detailCommon .toggleMessage").click(function(){
		$(this).nextAll("dl").toggle();
		if($(this).html().indexOf("展开") > -1){
			$(this).html("收起");
			$(this).addClass("close");
		}
		else{
			$(this).html("展开");
			$(this).removeClass("close");
		}
		return false;	
	});
	
	$(".focus").click(function(){
		$("#popupAtten").show().css({"top":$(window).scrollTop()+100, "left":($(window).width() - 440)/2});	
		return false;
	});
	$(window).resize(function(){
		$("#popupAtten").css({"top":$(window).scrollTop()+100, "left":($(window).width() - 440)/2});	
	});
	var selGroup;
	var showTemp = $("#popupSpecial");
	$("#popupAtten .group").hover(function(){
		var offset = $(this).offset();
		selGroup = this;
		showTemp.show().css({top: offset.top + 15, left: offset.left});
		$(selGroup).css("border-bottom-width","0");
	}, function(){
		showTemp.hide();
		showTemp = $("#popupSpecial");
		$(selGroup).css("border-bottom-width","1px");
	});
	$("#popupSpecial, #popupSpecial1").hover(function(){
		$(this).show();
		$(selGroup).css("border-bottom-width","0");
	}, function(){
		$(this).hide();
		showTemp = $(this);
		$(selGroup).css("border-bottom-width","1px");
	});
	$("#cGroupBtn").click(function(){
		var offset = $("#popupAtten .group").offset();
		$("#popupSpecial1").show().css({top: offset.top + 15, left: offset.left});;
		$("#popupSpecial").hide();
		return false;
	});
	
	$("#popupSpecial1 .save").click(function(){
		$("#popupSpecial1").hide();
		showTemp = $("#popupSpecial");
		return false;
	});
	$("#popupSpecial1 .cancel").click(function(){
		$("#popupSpecial1").hide();
		showTemp = $("#popupSpecial");
		return false;
	});
	$("#popupAtten .close").click(function(){
		$("#popupAtten").hide();
		return false;	
	});
	$("#popupAtten .prevBtn").click(function(){
		$("#popupAtten").hide();
		return false;	
	});
	$("#popupAtten .nextBtn").click(function(){
		$("#popupAtten").hide();
		return false;	
	});
});
