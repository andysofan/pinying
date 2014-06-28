package com.cyberoller.pinying

import org.springframework.transaction.annotation.Transactional

import org.hibernate.criterion.CriteriaSpecification

import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

import org.apache.shiro.SecurityUtils
import com.cyberoller.pinying.shiro.User

import com.szmallecar.domain.product.ProductGoods

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

    def toggleFavorite(Integer productId) {
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
	def isFavorite (Integer productId)  {
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
					groupProperty("brand.name","name")
				}
				join("brand")
				inList("id", favoriteInstanceList)
				order("brand.orderIndex", "asc")
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
					groupProperty("category.name","name")
				}
				join("category")
				inList("id", favoriteInstanceList)
				order("category.orderIndex", "asc")
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
					property("name", "name")
					property("thumbnail", "thumbnail")
					property("marketMinPrice", "marketMinPrice")
					property("marketMaxPrice", "marketMaxPrice")
				}
				inList("id", favoriteInstanceList)
				if(params?.brandId){
					eq("brand.id", params.int('brandId'))
				}
				if(params?.categoryId){
					eq("category.id", params.int('categoryId'))
				}
				
				eq("status", 1)
				//名称
				if(params?.name){
					ilike("name", "%${params?.name}%")
				}
				//价格区间
				if(params?.marketMinPrice && params?.marketMaxPrice){
					or{
						between("marketMinPrice", params.int('marketMinPrice').toDouble(), params.int('marketMaxPrice').toDouble())
						between("marketMaxPrice", params.int('marketMinPrice').toDouble(), params.int('marketMaxPrice').toDouble())
					}
				}else{
					if(params?.marketMinPrice){
						or{
							ge("marketMinPrice", params.int('marketMinPrice').toDouble())
							ge("marketMaxPrice", params.int('marketMinPrice').toDouble())
						}
					}
					if(params?.marketMaxPrice){
						or{
							le("marketMinPrice", params.int('marketMaxPrice').toDouble())
							le("marketMaxPrice", params.int('marketMaxPrice').toDouble())
						}
					}
				}

				if(params?.order && params?.sort){
					order(params?.sort, params?.order)
				}else{
					order("orderIndex", "desc")
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
					eq("brand.id", params.int('brandId'))
				}
				if(params?.categoryId){
					eq("category.id", params.int('categoryId'))
				}
				eq("status", 1)
				//名称
				if(params?.name){
					ilike("name", "%${params?.name}%")
				}
				//价格区间
				if(params?.marketMinPrice && params?.marketMaxPrice){
					or{
						between("marketMinPrice", params.int('marketMinPrice').toDouble(), params.int('marketMaxPrice').toDouble())
						between("marketMaxPrice", params.int('marketMinPrice').toDouble(), params.int('marketMaxPrice').toDouble())
					}
				}else{
					if(params?.marketMinPrice){
						or{
							ge("marketMinPrice", params.int('marketMinPrice').toDouble())
							ge("marketMaxPrice", params.int('marketMinPrice').toDouble())
						}
					}
					if(params?.marketMaxPrice){
						or{
							le("marketMinPrice", params.int('marketMaxPrice').toDouble())
							le("marketMaxPrice", params.int('marketMaxPrice').toDouble())
						}
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
}