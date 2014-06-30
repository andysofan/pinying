<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Grails"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${resource(dir:"css/pinying", file:"common.css")}" />
	
	<script type="text/javascript" src="${resource(dir:"js/pinying/common", file:"jquery-1.8.2.min.js")}"></script>
	<script type="text/javascript" src="${resource(dir:"js/pinying/common", file:"jquery.hoverIntent.js")}"></script>
	<script type="text/javascript" src="${resource(dir:"js/pinying/common", file:"jquery.flexslider.js")}"></script>
	<script type="text/javascript" src="${resource(dir:"js/pinying", file:"common.js")}"></script>
	<script type="text/javascript" src="${resource(dir:"js/pinying/common", file:"new.js")}"></script>
	<script type="text/javascript" src="${resource(dir:"js/pinying/common", file:"type.js")}"></script>
	<script type="text/javascript" src="${resource(dir:"js/pinying", file:"filter.js")}"></script>
	<script type="text/javascript">
	    (function($){
	        $(function(){
	            $(".ad01 a:last-child").css("margin-right","0");
	            $(".ad02 a:last-child").css("margin-right","0");
	            $(".newsPicList li:last-child").css("margin-right","0");
	            $(".proList li:last-child").css("margin-right","0");
	            $(".shopList").eq(1).css("border-left","1px solid #fff");
	            $(".c5List li:last-child").css("border-right","0");
	            $(".messList").eq(2).css("border-right","0");
	
	            //图片样式
	            var i;
	            i = $(".car4sList li").size();
	            for(j=1;j<=i;j++){
	                if(j%4 == 0){
	                    $(".car4sList li").eq(j-1).css("border-right","none");
	                }
	                if(j>3){
	                    $(".car4sList li").eq(j).css("border-bottom","none")
	                }
	            }
	            //汽车品牌样式
	            var s;
	            s = $(".comList li").size();
	            for(m=1;m<=s;m++){
	                if(m%2 == 0){
	                    $(".comList li").eq(m-1).css("border-right","none");
	                }
	                if(m>3){
	                    $(".comList li").eq(m).css("border-bottom","none");
	                }
	            }
	            //排行样式
	            for(y=1;y<=11;y++){
	                $(".hotList li:nth-child("+ y +")").css("background","url(img/in_"+y+".gif) no-repeat 5px 5px");
	            }
	            //首页分类导航样式
	            $(".sublist li").hover(function(){
	                $(this).find(".subMenu").show();
	                $(this).css("border","1px solid #e9d6c7");
	                $(this).css("border-right","none");
	                $(this).css("background","#f9f5f4");
	                $(this).prev().css("border-bottom","1px solid #f9f5f4");
	                var liheight =$(this).height();
	                $(".subMenuStyle").css("height",liheight);
	            },function(){
	                $(this).find(".subMenu").hide();
	                $(this).css("border","1px solid #f9f5f4");
	                $(this).css("border-bottom","1px dashed #e9d6c7");
	                $(this).css("background","none");
	                $(this).prev().css("border-bottom","1px dashed #e9d6c7");
	                $(this).css("border-right","1px dashed #e9d6c7");
	
	                if (jQuery.browser.msie && (jQuery.browser.version == "6.0") && !jQuery.support.style) {
	                    $(this).css("border-right","0px dashed #e9d6c7");
	                }
	            });
	        })
	        $(document).ready(function(){
	            //下拉菜单样式
	            $(".selBig").click(function(){
	                $(this).siblings(".selShow").show();
	            });
	            $(".selShow").mouseleave(function(){
	                $(this).hide();
	            });
	            $(".selShow a").click(function(){
	                $(this).parent().siblings(".selBig").html($(this).html()).css("color","#333");
	                $(this).parent().hide();
	            });
	            $(".myHome").hover(function(){
	                $(".myBuyDiv").show();
	            },function(){
	                $(".myBuyDiv").hide();
	            });
	            $(".buyCar").hover(function(){
	                $(".myBuyCarDiv").show();
	            },function(){
	                $(".myBuyCarDiv").hide();
	            });
	        });
	
	    })(jQuery);
	</script>
	<script type="text/javascript">
	    (function($){
	        $.fn.hoverForIE6=function(option){
	            var s=$.extend({current:"hover",delay:10},option||{});
	            $.each(this,function(){
	                var timer1=null,timer2=null,flag=false;
	                $(this).bind("mouseover",function(){
	                    if (flag){
	                        clearTimeout(timer2);
	                    }else{
	                        var _this=$(this);
	                        timer1=setTimeout(function(){
	                            _this.addClass(s.current);
	                            flag=true;
	                        },s.delay);
	                    }
	                }).bind("mouseout",function(){
	                            if (flag){
	                                var _this=$(this);timer2=setTimeout(function(){
	                                    _this.removeClass(s.current);
	                                    flag=false;
	                                },s.delay);
	                            }else{
	                                clearTimeout(timer1);
	                            }
	                        })
	            })
	        }
	    })(jQuery);
	</script>

	<shiro:isLoggedIn>
	<script type="text/javascript">
		function toggleFavorite(obj, productId){
			$.ajax({
				url: "${createLink(controller:'favorite', action:'toggleFavorite')}",
				type: "POST",
				data: { productId : productId },
				dataType: "html"
			}).done(function(result){
				$(obj).html(result)
			}).fail(function(jqXHR, textStatus){
				alert( "Request failed: " + textStatus );
			});
		}
		
	</script>
	</shiro:isLoggedIn>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#searchAll").bind('keydown',function(event){
				if(event && event.keyCode==13){ // enter 键
					//要做的事情
					searchAll($("#searchAll").val())
				}
			}); 		
		});
		function searchAll(s){
			var s_name = s.replace(/\r\n/g, "");
				s_name = (s_name == '搜索')?'':s_name;
				
			var href="<g:createLink controller='search' action='index' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:sort, order:order]' />";
				href+="&name=" + s_name;
			window.location.href = href;			
		}
	</script>
	<g:layoutHead/>
</head>
<body>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
    <div class="page">
         <div class="hearder">
         	<div class="tophd clearFix">
            	<div class="fl">
                	<a href="${createLink(controller:'index', action:'index')}" title="">
                    	<img src="${resource(dir:'images/pinying/images', file:'pylogo.png')}" alt="logo" width="200" height="50" />
                    </a>
                </div>
                <div class="fr card">
					<shiro:isLoggedIn>
						<shiro:principal/>
						<a title="退出" href="${createLink(controller:'auth', action:'signOut')}">退出</a>
						<shiro:hasRole name="${'manager'.toUpperCase()}">
							<a title="系统管理" href="${createLink(controller:'productGoods')}">系统管理</a> 
						</shiro:hasRole>
						<a class="spriteicon" title="我的收藏夹" href="${createLink(controller:'favorite')}">我的收藏夹</a>
					</shiro:isLoggedIn>
					<shiro:isNotLoggedIn>
						<a id="login" href="${createLink(controller:'auth')}">登录</a>
						<a id="register" href="${createLink(controller:'auth', action:'register')}">注册</a>
					</shiro:isNotLoggedIn>
                </div>
            </div>
            <div class="navlist ie7 clearFix">
            	<!-- nav start -->
                <ul class="nav fl">
                	<!-- 首页 -->
                    <li class="first">
                        <a href="${createLink( controller:'index', action:'index')}" title="首页" class="home i1">首页</a>
                    </li>
                    <!-- 品牌专区 -->
                    <li class="navi0 navi02">
                        <a href="${createLink(controller:'index', action:'brand', params:[brandId:-1])}" title="" class="i1">品牌专区</a>
                        <div class="naviHover" style="display:none;">
							<g:include controller="index" action="menuBrandList" />
                        </div>
                    </li>
                    <!-- 分类菜单 -->
                   	<g:include controller="index" action="menuFirstLevelCategory" />
                    <!-- 其他 -->
                    <li class="navi0">
                        <a href="${createLink(controller:'index', action:'brand', params:[brandId:-2])}" title="" class="i1">其他</a>
                    </li>
                    <!-- 关于我们 -->
                    <li class="navi02 navi0">
                        <a href="${createLink(controller:'about', action:'company')}" title="" class="i1">关于我们</a>
                        <div class="naviHover" style="display:none;">
                        	<dl>
                            	<dd>
                                	<a href="${createLink(controller:'about', action:'brand')}" title="">品牌建设</a>
                                </dd>
                            	<dd>
                                	<a href="${createLink(controller:'about', action:'company')}" title="">成功案例</a>
                                </dd>
                            	<dd>
                                	<a href="${createLink(controller:'about', action:'company')}" title="">公司简介</a>
                                </dd>
                            	<dd>
                                	<a href="${createLink(controller:'about', action:'job')}" title="">人才招聘</a>
                                </dd>
                            	<dd>
                                	<a href="${createLink(controller:'about', action:'contact')}" title="">联系我们</a>
                                </dd>
                            </dl>
                        </div>
                    </li>
                </ul>
            	<!-- nav end -->
            	<!-- Search start -->
                <div class="fl search">
                	<input type="text" value="" class="getFocus spriteicon" data-tip="搜索" id="searchAll" />
                    <button class="iconSearch spriteicon" type="button" >查找</button>
                </div>
            	<!-- Search end -->
            </div>
         </div>
         <div class="main clearFix">
         	<!-- left start -->
         	<div class="fl mt10 lm">
            	<div class="leftmain">
            		<h2>最新推荐</h2>
                    <div class="listnew">
						<!--推荐产品-->
						<g:include controller="index" action="recommendProductList" />


                        <div class="newcard">
							<!--最热礼品-->
                            <g:include controller="index" action="hotestProduct" />
                            <p class="mt10 hotcard">
                                <a href="#" title="">最热礼品</a>
                            </p>
                        </div>
                    </div>
                	<div class="leftbotom"></div>
                </div>
                <div class="morecard mt5">
                	<h2><a href="#" title="">更多推荐礼品&gt;&gt;</a></h2>
                </div>
            </div>
         	<!-- left end -->
         	<!-- right start -->
            <div class="fr hpRecPro">
            	<g:layoutBody/>
         	</div>
         	<!-- right end -->
         </div>
         <!-- footer start -->
         <div class="footer">
         	<div id="footerBottom" class="clearFix">
				版权所有 Copyright 2012 品迎 业务联系：info(#)bestgift.com 沪ICP备10221484
            </div>
         </div>
         <!-- footer end -->
    </div>
</body>
</html>
