 jQuery(document).ready(function($){

	//选择分类 品牌 价格
	/*
	$('.filter ul li a').live('click' , function(){
		
		var li = $(this).parent(); //当前选中的li
		if( li.hasClass('active') ){ //已经选中
			return;
		}

		var text = $(this).html();

		//处理样式
		li.parent().children().each(function(i){
			$(this).removeClass('active');
		});
		li.addClass('active');

		//获取当前选中项的类别
		var category_id = $(this).parent().parent().parent().attr('id');

		//组织并写入数据
		var html = '<li category="' + category_id + '"><a href="#" title="">' + text + '<em class="filter_cancel">X</em></a></li>';
		$('#appenhtml ul li').each(function(i){
			
			if( !$(this).hasClass('itearch') ){
				
				if( $(this).attr('category') == category_id ){ //同类别，替换
					$(this).after( html );
					$(this).remove();
					html = '';
					return;
				}

			}

		});

		if( html != '' ){ //第一次写入
			$('#itearch').before( html );
		}
		set_product_total();

	});
	*/
	//删除
	/*
	$('.filter_cancel').live('click' , function(){

		var li = $(this).parent().parent();
		var category_id = li.attr('category');
			li.remove();

		$('#' + category_id + ' ul li').each(function(){
			if( $(this).hasClass('active') ){
				$(this).removeClass('active');
			}
		});
		set_product_total();

	});
	*/
	//清空
	/*
	$('#filter_clean').live('click' , function(){
		
		$('#appenhtml ul li').each(function(i){
			
			if( !$(this).hasClass('itearch') ){
				$(this).remove();
			}

		});

		$('.filter ul li').each(function(i){
		
			if( $(this).hasClass('active') ){
				$(this).removeClass('active');
			}

		});
		$('#filter_total').html(0);

	});
	*/
	//使用ajax 统计现在的选择的产品个数
	function set_product_total(){
		var category_ids = [];
		$('#appenhtml ul li').each(function(i){
			
			if( !$(this).hasClass('itearch') ){
				category_ids.push($(this).attr('category'));
			}

		});

		//ajax 调用后台，获取产品数量；可能会用到 category_ids 作为参数
		//这里假定返回3000
		$('#filter_total').html(3000);

	}

});