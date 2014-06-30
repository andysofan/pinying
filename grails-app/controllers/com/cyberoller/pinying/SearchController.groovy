package com.cyberoller.pinying

class SearchController {

	def searchService

	//收藏主页
	def index ()  {
		log.info "**********search"
		try{
			def productInstanceList = searchService.getProductList(params)
			def productInstanceCount = searchService.getProductCount(params)
			render view : "/search/index", model : [productInstanceList:productInstanceList, productInstanceCount:productInstanceCount]
		}catch(e){
			flash.message = e.getMessage()
			render view : "/search/index", model : [productInstanceList:null, productInstanceCount:0]
		}
	}

	//收藏-分类列表
	def getCategoryList(){
		try{
			def categoryInstanceList = searchService.getCategoryList()
			render template : "/search/categoryList", model : [categoryInstanceList:categoryInstanceList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/search/categoryList", model : [categoryInstanceList:null]
		}
	}

	//收藏-品牌列表
	def getBrandList(){
		try{
			def brandInstanceList = searchService.getBrandList()
			render template : "/search/brandList", model : [brandInstanceList:brandInstanceList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/search/brandList", model : [brandInstanceList:null]
		}
	}

	//切换收藏
    def toggleFavorite(Long productId) { 
		def result=""
		try{
			result = searchService.toggleFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"
	}

	//判断是否收藏
	def isFavorite (Long productId) {
		def result=""
		try{
			result = searchService.isFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"	    
	}
}