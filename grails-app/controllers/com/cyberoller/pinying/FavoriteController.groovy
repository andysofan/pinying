package com.cyberoller.pinying

class FavoriteController {

	def favoriteService

	//收藏主页
	def index ()  {
		log.info "**********favorite"
		try{
			def productInstanceList = favoriteService.getProductList(params)
			def productInstanceCount = favoriteService.getProductCount(params)
			render view : "/favorite/index", model : [productInstanceList:productInstanceList, productInstanceCount:productInstanceCount]
		}catch(e){
			flash.message = e.getMessage()
			render view : "/favorite/index", model : [productInstanceList:null, productInstanceCount:0]
		}
	}

	//收藏-分类列表
	def getCategoryList(){
		try{
			def categoryInstanceList = favoriteService.getCategoryList()
			render template : "/favorite/categoryList", model : [categoryInstanceList:categoryInstanceList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/favorite/categoryList", model : [categoryInstanceList:null]
		}
	}

	//收藏-品牌列表
	def getBrandList(){
		try{
			def brandInstanceList = favoriteService.getBrandList()
			render template : "/favorite/brandList", model : [brandInstanceList:brandInstanceList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/favorite/brandList", model : [brandInstanceList:null]
		}
	}

	//切换收藏
    def toggleFavorite(Integer productId) { 
		def result=""
		try{
			result = favoriteService.toggleFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"
	}

	//判断是否收藏
	def isFavorite (Integer productId) {
		def result=""
		try{
			result = favoriteService.isFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"	    
	}
}