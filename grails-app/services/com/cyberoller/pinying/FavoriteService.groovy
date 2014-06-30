package com.cyberoller.pinying

import org.springframework.transaction.annotation.Transactional

import org.hibernate.criterion.CriteriaSpecification

import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

import org.apache.shiro.SecurityUtils
import com.cyberoller.pinying.shiro.User

//export
import org.apache.poi.hslf.model.Picture
import org.apache.poi.hslf.usermodel.RichTextRun
import org.apache.poi.hslf.model.TextBox
import org.apache.poi.hslf.model.TextRun
import org.apache.poi.hslf.model.AutoShape
import org.apache.poi.hslf.model.Slide
import org.apache.poi.hslf.usermodel.SlideShow
import org.apache.poi.hslf.model.ShapeTypes
import java.awt.Color

class FavoriteService {

	static transactional = false
	def messageSource

	@Transactional(readOnly = true)
    def currentUserId() {
		def currentUserId
		try{
			def userInstance = User.withCriteria(uniqueResult:true){
				eq("username", SecurityUtils.getSubject().getPrincipal())
			}
			if(!userInstance){
				throw new RuntimeException("获取当前用户失败")
			}
			currentUserId = userInstance.id
		}catch(e){
			throw new RuntimeException("获取当前用户ID失败错误：${e.getMessage()}")
		}
		return currentUserId
    }

    def toggleFavorite(Long productId) {
		def result = ""
		Favorite.withTransaction { status ->
			try{
				//获取用户ID
				def currentUserId = this.currentUserId()
				//取收藏
				def favoriteInstance = Favorite.withCriteria(uniqueResult:true){
					eq("user.id", currentUserId)
					eq("productGoods.id", productId)
				}
				if(favoriteInstance){
					favoriteInstance.delete(flush:true)
					//取消收藏成功!
					result = "收藏"
				}else{
					favoriteInstance = new Favorite(
					  user : User.get(currentUserId)
					, productGoods : ProductGoods.get(productId)
					)
					//绑定
					/*
					BindDynamicMethod mybind = new BindDynamicMethod()
					def mymap = ['user.id' :currentUserId, 'productGoods.id':productId]
					def myargs =  [favoriteInstance, mymap]
					mybind.invoke(favoriteInstance, 'bind', (Object[]) myargs)
					*/
					log.info "*****************favoriteInstance=user:${favoriteInstance.user}, product:${favoriteInstance.productGoods}"
					//检查
					if(!favoriteInstance.validate()){
						def errorMsg = ""
						favoriteInstance.errors.allErrors.each{ errorInstance ->
							errorMsg = "${errorMsg};${messageSource.getMessage(errorInstance, Locale.ENGLISH)}"
						}
						errorMsg = errorMsg.encodeAsHTML()
						
						throw new RuntimeException("检查失败：${errorMsg}")
					}
					//保存
					favoriteInstance.save(flush:true)
					//收藏成功
					result = "取消收藏"
				}
			}catch(e){
				status.setRollbackOnly();
				throw new RuntimeException("失败：${e.getMessage()}")
			}
		}
		return result
    }
	//判断是否已经收藏
	@Transactional(readOnly = true)
	def isFavorite (Long productId)  {
		def result = ""
		try{
			//获取用户ID
			def currentUserId = this.currentUserId()
			//取收藏
			def favoriteInstance = Favorite.withCriteria(uniqueResult:true){
				eq("user.id", currentUserId)
				eq("productGoods.id", productId)
			}
			if(favoriteInstance){
				//已经收藏过
				result = "取消收藏"
			}else{
				//还未收藏
				result = "收藏"
			}
		}catch(e){
			throw new RuntimeException("判断收藏失败：${e.getMessage()}")
		}
		log.info "***************isFavorite, productId=${productId}, result=${result}"
		return result
	}
	//收藏-取品牌列表
	@Transactional(readOnly = true)
	def getBrandList (Object params)  {
		def brandInstanceList
		try{
			//获取用户ID
			def currentUserId = this.currentUserId()
			//取收藏列表
			def favoriteInstanceList = Favorite.withCriteria {
				createAlias "productGoods", "product"
				projections{
					groupProperty("product.id")
				}
				join("productGoods")
				eq("user.id", currentUserId)
			}
			log.info "**********favoriteInstanceList=${favoriteInstanceList}"
			//
			brandInstanceList = ProductGoods.withCriteria {
				createAlias "brand", "brand"
				projections{
					groupProperty("brand.id","id")
					groupProperty("brand.xname","name")
				}
				join("brand")
				inList("id", favoriteInstanceList)
				order("brand.xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
			log.info "**********brandInstanceList=${brandInstanceList}"
		}catch(e){
			throw new RuntimeException("收藏-取品牌列表错误:${e.getMessage()}")
		}
		return brandInstanceList
	}

	//收藏-取分类列表
	@Transactional(readOnly = true)
	def getCategoryList(Object params){
		def categoryInstanceList
		try{
			//获取用户ID
			def currentUserId = this.currentUserId()
			//取收藏列表
			def favoriteInstanceList = Favorite.withCriteria {
				createAlias "productGoods", "product"
				projections{
					groupProperty("product.id")
				}
				join("productGoods")
				eq("user.id", currentUserId)
			}
			categoryInstanceList = ProductGoods.withCriteria {
				createAlias "category", "category"
				projections{
					groupProperty("category.id","id")
					groupProperty("category.xname","name")
				}
				join("category")
				inList("id", favoriteInstanceList)
				order("category.xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("收藏-取分类列表错误:${e.getMessage()}")
		}
		return categoryInstanceList
	}

	//收藏-取产品列表****************************************************************
	@Transactional(readOnly = true)
	def getProductList(Object params){
		def productInstanceList
		try{
			//获取用户ID
			def currentUserId = this.currentUserId()
			//取收藏列表
			def favoriteInstanceList = Favorite.withCriteria {
				createAlias "productGoods", "product"
				projections{
					groupProperty("product.id")
				}
				join("productGoods")
				eq("user.id", currentUserId)
			}
			//取产品列表
			productInstanceList = ProductGoods.withCriteria() {
				projections{
					property("id", "id")
					property("xname", "name")
					property("xthumbnail", "thumbnail")
					property("xprice", "price")
				}
				inList("id", favoriteInstanceList)
				if(params?.brandId){
					eq("brand.id", params.int('brandId').toLong())
				}
				if(params?.categoryId){
					eq("category.id", params.int('categoryId').toLong())
				}
				
				eq("xstatus", 1)
				//名称
				if(params?.name){
					ilike("xname", "%${params?.name}%")
				}
				//价格区间
				if(params?.marketMinPrice && params?.marketMaxPrice){
					between("xprice", params.int('marketMinPrice').toDouble(), params.int('marketMaxPrice').toDouble())
				}else{
					if(params?.marketMinPrice){
						ge("xprice", params.int('marketMinPrice').toDouble())
					}
					if(params?.marketMaxPrice){
						le("xprice", params.int('marketMaxPrice').toDouble())
					}
				}

				if(params?.order && params?.sort){
					order(params?.sort, params?.order)
				}else{
					order("xorderIndex", "desc")
				}

				if(params?.offset) firstResult(params.int('offset'))

				maxResults(8)
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("收藏-取产品列表错误:${e.getMessage()}")
		}
		return productInstanceList
	}
	def getProductCount(Object params){
		def productInstanceCount
		try{
			//获取用户ID
			def currentUserId = this.currentUserId()
			//取收藏列表
			def favoriteInstanceList = Favorite.withCriteria {
				createAlias "productGoods", "product"
				projections{
					groupProperty("product.id")
				}
				join("productGoods")
				eq("user.id", currentUserId)
			}
			productInstanceCount = ProductGoods.withCriteria(uniqueResult:true) {
				projections{
					count()
				}
				inList("id", favoriteInstanceList)
				if(params?.brandId){
					eq("brand.id", params.int('brandId').toLong())
				}
				if(params?.categoryId){
					eq("category.id", params.int('categoryId').toLong())
				}
				eq("xstatus", 1)
				//名称
				if(params?.name){
					ilike("xname", "%${params?.name}%")
				}
				//价格区间
				if(params?.marketMinPrice && params?.marketMaxPrice){
					between("xprice", params.int('marketMinPrice').toDouble(), params.int('marketMaxPrice').toDouble())
				}else{
					if(params?.marketMinPrice){
						ge("xprice", params.int('marketMinPrice').toDouble())
					}
					if(params?.marketMaxPrice){
						le("xprice", params.int('marketMaxPrice').toDouble())
					}
				}
			}
			if(!productInstanceCount){
				productInstanceCount = 0
			}
		}catch(e){
			throw new RuntimeException("收藏-取产品列表数据错误:${e.getMessage()}")
		}
		return productInstanceCount
	}
	//收藏-导出产品列表****************************************************************
	def export (Long id, String realPath,String downName )  {
		FileOutputStream out = null;
		def downFile
		try{
			def downPath =  new File("${realPath}/exportTmp")

				downFile = "${downPath}/${downName}"

			def productInstanceList = ProductGoods.withCriteria(){
				if(id){
					eq("id", id)
				}
			}
			if(!productInstanceList){
				throw new RuntimeException("没有收藏")
			}

			if(!downPath.exists()){
				downPath.mkdirs()//目录不存在则创建
			}

			SlideShow ppt = new SlideShow();
			out = new FileOutputStream(downFile);
			productInstanceList.each{productInstance ->
					Slide slide1 = ppt.createSlide();
					log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>添加商品名称文字开始>>>>>>>>>>>>>>>>>>>>>>>>>>");
					// 创建并置入带有样式的文本  
			        AutoShape _autoShape = new AutoShape(ShapeTypes.Rectangle); //设置形状  
					// 创建并置入简单文本  
			        TextRun _autoText = _autoShape.createTextRun();  
			        // AutoShape 对象可以设置多个不同样式文本  
			        RichTextRun rtGoodsName = _autoText.appendText("商品名称：${productInstance?.xname}\t");  
					rtGoodsName.setFontColor(Color.red);  
					rtGoodsName.setAlignment(TextBox.AlignLeft);
					rtGoodsName.setFontSize(18);
					rtGoodsName.setFontName("Arial");
					rtGoodsName.setBold(true);
					rtGoodsName.setItalic(true);
					rtGoodsName.setUnderlined(true);
				    
					slide1.addShape(_autoShape);
					
					log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<添加商品名称文字结束<<<<<<<<<<<<<<<<<<<<<<<<<<");
					
					log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>添加描述文字开始>>>>>>>>>>>>>>>>>>>>>>>>>>");
					
					if(productInstance?.xdesc){
						TextBox txt = new TextBox();
						txt.setText("商品描述：${productInstance?.xdesc}");
						txt.setAnchor(new java.awt.Rectangle(150, 410, 430, 50));
						RichTextRun rt = txt.getTextRun().getRichTextRuns()[0];
						rt.setFontSize(18);
						rt.setFontName("Arial");
						rt.setBold(true);
						rt.setItalic(true);
						rt.setUnderlined(true);
						rt.setFontColor(Color.red);
						rt.setAlignment(TextBox.AlignLeft);
						slide1.addShape(txt);
					}
					log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<添加描述文字结束<<<<<<<<<<<<<<<<<<<<<<<<<<");
					log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>添加图片开始>>>>>>>>>>>>>>>>>>>>>>>>>>");

					File ximage1 = new File("${realPath}/images/pinying/images/${productInstance.ximage1}");
					if(ximage1.exists()){
						int idx = ppt.addPicture(ximage1, Picture.JPEG);
						Picture pict = new Picture(idx);
						pict.setAnchor(new java.awt.Rectangle(140, 30, 450, 317));
						slide1.addShape(pict);
					}
					/*
					File ximage2 = new File("${realPath}/images/pinying/images/${productInstance.ximage2}");
					if(ximage2.exists()){
						int idx = ppt.addPicture(ximage2, Picture.JPEG);
						Picture pict = new Picture(idx);
						pict.setAnchor(new java.awt.Rectangle(140, 30, 450, 317));
						slide1.addShape(pict);
					}
					File ximage3 = new File("${realPath}/images/pinying/images/${productInstance.ximage3}");
					if(ximage3.exists()){
						int idx = ppt.addPicture(ximage3, Picture.JPEG);
						Picture pict = new Picture(idx);
						pict.setAnchor(new java.awt.Rectangle(140, 30, 450, 317));
						slide1.addShape(pict);
					}
					*/
					log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<添加图片结束<<<<<<<<<<<<<<<<<<<<<<<<<<");
			}
			ppt.write(out);
		}catch(e){
			throw new RuntimeException("导出收藏错误:${e.getMessage()}")
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return downFile
	}
}