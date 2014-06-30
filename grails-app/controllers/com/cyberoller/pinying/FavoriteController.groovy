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
    def toggleFavorite(Long productId) { 
		def result=""
		try{
			result = favoriteService.toggleFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"
	}

	//判断是否收藏
	def isFavorite (Long productId) {
		def result=""
		try{
			result = favoriteService.isFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"	    
	}
	//收藏-导出产品列表****************************************************************
	def export (Long id)  {
		try{
			def downName = System.currentTimeMillis()+"_${params?.id}.ppt"
			response.setHeader("Content-disposition", "attachment; filename=" + downName)
			response.contentType = "application/x-rarx-rar-compressed"
			def realPath = request.getSession().getServletContext().getRealPath("/");
			
			def downFilePath = favoriteService.export(id,realPath,downName)
			log.info "******************downFilePath=${downFilePath}"
			if(downFilePath){
				InputStream inputStream = new FileInputStream(downFilePath)
				def out = response.outputStream
				byte[] buffer = new byte[1024]
				int i = -1
				while ((i = inputStream.read(buffer)) != -1) {
					out.write(buffer, 0, i)
				}
				out.flush()
				out.close()
				inputStream.close()
				//删除临时文件
				def tmpFile = new File(downFilePath)
				tmpFile.delete();
			}
		}catch(e){
			flash.message = e.getMessage()
			redirect action:"index"
		}
	}
}