package com.cyberoller.pinying

class ProductGoods {
	
	//名称
	String	xname
	//包装
	String xpackage
	//材质
	String xmaterial
	//尺寸
	String xsize
	//颜色
	String xcolor
	//描述
	String xdesc
	//价格
	double xprice = 0
	
	Integer	xorderIndex = 0
	Integer	xstatus = 1 //是否启用 1:启用 0:禁用
	String xthumbnail //缩略图 列表展示使用 默认是第一张相册的图片
	//大图
	String ximage1
	String ximage2
	String ximage3
	
	//推荐
	Boolean xrecommend = false
	//是否热卖
	Boolean xishot = false
	//销量
	Integer xquantity = 0
	//人气
	Integer xpopularity = 0
	
	Date 	dateCreated
	Date 	lastUpdated
	
	static	belongsTo = [category:ProductCategory,brand:Brand]
	
    static constraints = {
		xname(nullable:false,blank:false,size:1..200, unique:'brand')
		category(nullable:true)
		brand(nullable:true)
		xthumbnail(nullable:true)
		ximage1(nullable:true)
		ximage2(nullable:true)
		ximage3(nullable:true)
		xishot(nullable:false)
		xquantity(nullable:false)
		xpopularity(nullable:false)
		xpackage(nullable:false)
		xmaterial(nullable:false)
		xsize(nullable:false)
		xcolor(nullable:false)
		xdesc(nullable:false)
    }

	def beforeInsert(){
		if(category && brand){
			ProductCategoryBrand.withNewSession { session ->
				def  productCategoryBrandInstance = ProductCategoryBrand.withCriteria(uniqueResult:true){
					eq("category", category)
					eq("brand", brand)
				}
				if(!productCategoryBrandInstance) {
					productCategoryBrandInstance = new ProductCategoryBrand(
					  category : category
					, brand: brand
					).save(flush:true)
				}
			}
		}
	}

	def beforeUpdate ()  {
		if(category && brand){
			ProductCategoryBrand.withNewSession { session ->
				def  productCategoryBrandInstance = ProductCategoryBrand.withCriteria(uniqueResult:true){
					eq("category", category)
					eq("brand", brand)
				}
				if(!productCategoryBrandInstance) {
					productCategoryBrandInstance = new ProductCategoryBrand(
					  category : category
					, brand: brand
					).save(flush:true)
				}
			}
		}	    
	}
    
    String toString(){
    	return "${xname}"
    }
    
	static mapping = {
		table 'x_product_goods'
	}

}