package com.szmallecar.domain.product

//import com.alibaba.fastjson.JSON

class ProductGoods {
	int		id
	
	String	name
	String	keyword
	String	code	//编号
	String	unit	//单位 例如 n件 n桶 n辆
	
	int		orderIndex = 0
	int		status = 1 //是否启用 1:启用 0:禁用
	String	thumbnail //缩略图 列表展示使用 默认是第一张相册的图片
	
	double	marketMinPrice = 0
	double	marketMaxPrice = 0
	//推荐
	Boolean recommend = false
	//是否热卖
	Boolean ishot = false
	//销量
	Integer quantity = 0
	//人气
	Integer popularity = 0
	
	Date 	dateCreated
	Date 	lastUpdated
	
	//ProductGoodsExt ext
	
	static	belongsTo = [category:ProductCategory,brand:Brand,topBrand:Brand]
	static	hasMany = [images:ProductImage]
	static	transients = ['parameters']
	
	def getParameters(){
		//JSON.parse(ext.parameters)
	}
	
	static mapping = {
		version	false
		table		'x_product_goods'
		id			column:'c_id'
		name		column:'c_name'
		keyword		column:'c_keyword'
		code		column:'c_code'
		unit		column:'c_unit'
		orderIndex	column:'c_order_index'
		status		column:'c_status'
		thumbnail	column:'c_thumbnail'
		marketMinPrice	column:'c_market_min_price'
		marketMaxPrice	column:'c_market_max_price'
		recommend column:'c_recommend'
		ishot	column:'c_ishot'
		quantity	column:'c_quantity'
		popularity	column:'c_popularity'
		images(sort:'orderIndex')
	}
	
    static constraints = {
		name(nullable:false,blank:false,size:1..200)
		keyword(nullable:true,size:1..255)
		code(nullable:true,size:1..255)
		unit(nullable:false,blank:false,size:1..200)
		category(nullable:true)
		brand(nullable:true)
		thumbnail(nullable:true)
		ishot(nullable:false)
		quantity(nullable:false)
		popularity(nullable:false)
    }
}