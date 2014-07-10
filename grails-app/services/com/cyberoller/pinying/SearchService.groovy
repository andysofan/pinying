package com.cyberoller.pinying

import org.springframework.transaction.annotation.Transactional

import org.hibernate.criterion.CriteriaSpecification

import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

import org.apache.shiro.SecurityUtils
import com.cyberoller.pinying.shiro.User

class SearchService {

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

	//收藏-取品牌列表
	@Transactional(readOnly = true)
	def getBrandList (Object params)  {
		def brandInstanceList
		try{
			brandInstanceList = ProductGoods.withCriteria {
				createAlias "brand", "brand"
				projections{
					groupProperty("brand.id","id")
					groupProperty("brand.xname","name")
				}
				join("brand")
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
			categoryInstanceList = ProductGoods.withCriteria {
				createAlias "category", "category"
				projections{
					groupProperty("category.id","id")
					groupProperty("category.xname","name")
				}
				join("category")
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
			//取产品列表
			productInstanceList = ProductGoods.withCriteria() {
				projections{
					property("id", "id")
					property("xname", "name")
					property("xthumbnail", "thumbnail")
					property("xprice", "price")
				}
				if(params?.brandId && params.int('brandId') > 0){
					eq("brand.id", params.int('brandId').toLong())
				}
				if(params?.categoryId && params.int('categoryId') > 0){
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
			productInstanceCount = ProductGoods.withCriteria(uniqueResult:true) {
				projections{
					count()
				}
				if(params?.brandId && params.int('brandId') > 0){
					eq("brand.id", params.int('brandId').toLong())
				}
				if(params?.categoryId && params.int('categoryId') > 0){
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
}