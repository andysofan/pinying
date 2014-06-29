package com.cyberoller.pinying

import org.hibernate.criterion.CriteriaSpecification

import org.springframework.transaction.annotation.Transactional

class IndexService {
	
	static transactional = true
	
	//菜单-取品牌****************************************************************
	@Transactional(readOnly = true)
	def menuBrandList(){
		def brandInstanceList
		try{
			brandInstanceList = Brand.withCriteria {
				projections{
					property("id", "id")
					property("xname", "name")
				}
				order("xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
			
		}catch(e){
			throw new RuntimeException("菜单-取品牌列表错误:${e.getMessage()}")
		}
		return brandInstanceList
	}
	
	//菜单-取1级分类****************************************************************
	@Transactional(readOnly = true)
	def menuFirstLevelCategory(){
		def categoryInstanceList
		try{
			categoryInstanceList = ProductCategory.withCriteria {
				projections{
					property("id", "id")
					property("xname", "name")
				}
				eq("xlevel", 0)
				order("xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("菜单-取1级分类错误:${e.getMessage()}")
		}
		return categoryInstanceList
	}
	//菜单-取2级分类****************************************************************
	@Transactional(readOnly = true)
	def menuSecondLevelCategory(Long parentId){
		def categoryInstanceList
		try{
			categoryInstanceList = ProductCategory.withCriteria {
				createAlias "parent", "parent"
				projections{
					property("id", "id")
					property("xname", "name")
				}
				join("parent")
				eq("xlevel", 1)
				eq("parent.id", parentId)
				order("xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("菜单-取2级分类错误:pid=${parentId}, ${e.getMessage()}")
		}
		return categoryInstanceList
	}
	//主页-取推荐产品列表****************************************************************
	@Transactional(readOnly = true)
	def recommendProductList(){
		def productInstanceList
		try{
			productInstanceList = ProductGoods.withCriteria {
				projections{
					property("id", "id")
					property("xname", "name")
					property("xthumbnail", "thumbnail")
					property("xprice", "price")
				}
				eq("xrecommend", true)
				eq("xstatus", 1)
				order("xorderIndex", "asc")
				maxResults(4)
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("主页-取推荐产品列表错误:${e.getMessage()}")
		}
		return productInstanceList
	}
	//主页-取最热门产品****************************************************************
	@Transactional(readOnly = true)
	def hotestProduct(){
		def hotestProductInstance
		try{
			hotestProductInstance = ProductGoods.withCriteria(uniqueResult:true) {
				projections{
					property("id", "id")
					property("xname", "name")
					property("xthumbnail", "thumbnail")
					property("xprice", "price")
				}
				eq("xishot", true)
				eq("xstatus", 1)
				order("xorderIndex", "asc")
				maxResults(1)
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("主页-取最热门产品错误:${e.getMessage()}")
		}
		return hotestProductInstance
	}
	//主页-取人气产品列表****************************************************************
	@Transactional(readOnly = true)
	def popularProductList(){
		def popularProductInstanceList
		try{
			popularProductInstanceList = ProductGoods.withCriteria() {
				projections{
					property("id", "id")
					property("xname", "name")
					property("xthumbnail", "thumbnail")
					property("xprice", "price")
				}
				eq("xstatus", 1)
				order("xpopularity", "desc")
				maxResults(8)
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("主页-取人气产品列表错误:${e.getMessage()}")
		}
		return popularProductInstanceList
	}
	//品牌-取产品列表****************************************************************
	@Transactional(readOnly = true)
	def getProductListByBrandId(Long brandId, Object params){
		def productInstanceList
		try{
			productInstanceList = ProductGoods.withCriteria() {
				projections{
					property("id", "id")
					property("xname", "name")
					property("xthumbnail", "thumbnail")
					property("xprice", "price")
				}
				
				if(brandId > 0){
					eq("brand.id", brandId)
				}else{
					isNotNull("brand.id")
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
			throw new RuntimeException("品牌-取产品列表错误:${e.getMessage()}")
		}
		return productInstanceList
	}
	def getProductCountByBrandId(Long brandId, Object params){
		def productInstanceCount
		try{
			productInstanceCount = ProductGoods.withCriteria(uniqueResult:true) {
				projections{
					count()
				}
				if(brandId > 0){
					eq("brand.id", brandId)
				}else{
					isNotNull("brand.id")
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
			throw new RuntimeException("品牌-取产品数量错误:${e.getMessage()}")
		}
		return productInstanceCount
	}
	//分类-取品牌列表,根据1级分类ID****************************************************************
	//分类-获取分类名称
	@Transactional(readOnly = true)
	def getCategoryName (Long categoryId)  {
		def categoryName
		try{
			categoryName = ProductCategory.withCriteria(uniqueResult:true) {
				projections{
					property("xname")
				}
				eq("id", categoryId)
			}
		}catch(e){
			throw new RuntimeException("分类-取名称ID:${categoryId}错误:${e.getMessage()}")
		}
		return categoryName	    
	}
	//分类-获取品牌名称
	@Transactional(readOnly = true)
	def getBrandName (Long brandId)  {
		def brandName
		try{
			brandName = Brand.withCriteria(uniqueResult:true) {
				projections{
					property("xname")
				}
				eq("id", brandId)
			}
		}catch(e){
			throw new RuntimeException("分类-取品牌名称ID:${brandId}错误:${e.getMessage()}")
		}
		return brandName	    
	}
	@Transactional(readOnly = true)
	def getBrandListByParentCategoryId(Long categoryId, Object params){
		def brandInstanceList
		try{
			def categoryInstanceList = ProductCategory.withCriteria {
				createAlias "parent", "parent"
				projections{
					property("id","id")
				}
				join("parent")
	
				eq("parent.id", categoryId)
			}
			log.info "**********categoryInstanceList=${categoryInstanceList}"
			brandInstanceList = ProductCategoryBrand.withCriteria {
				createAlias "brand", "brand"
				projections{
					groupProperty("brand.id","id")
					groupProperty("brand.xname","name")
				}
				join("brand")
				inList("category.id", categoryInstanceList)
				if(params?.subCategoryId){
					eq("category.id", params.int('subCategoryId').toLong())
				}
				order("xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
			log.info "**********brandInstanceList=${brandInstanceList}"
		}catch(e){
			throw new RuntimeException("分类-取品牌列表,根据1级分类ID:${categoryId}错误:${e.getMessage()}")
		}
		return brandInstanceList
	}
	//分类-取子分类列表，根据1级分类ID****************************************************************
	@Transactional(readOnly = true)
	def getCategoryListByParentCategoryId(Long parentCategoryId){
		def categoryInstanceList
		try{
			categoryInstanceList = ProductCategory.withCriteria {
				createAlias "parent", "parent"
				projections{
					groupProperty("id","id")
					groupProperty("xname","name")
				}
				join("parent")
				eq("parent.id", parentCategoryId)
				order("xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("分类-取子分类列表，根据1级分类ID:${categoryId}错误:${e.getMessage()}")
		}
		return categoryInstanceList
	}
	//分类-取品牌列表,根据2级分类ID****************************************************************
	
	@Transactional(readOnly = true)
	def getBrandListByCategoryId(Long categoryId){
		def brandInstanceList
		try{
			brandInstanceList = ProductCategoryBrand.withCriteria {
				createAlias "brand", "brand"
				projections{
					property("brand.id","id")
					groupProperty("brand.xname","name")
				}
				join("brand")
				eq("category.id", categoryId)
				order("xorderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("分类-取品牌列表,根据1级分类ID:${categoryId}错误:${e.getMessage()}")
		}
		return brandInstanceList
	}
	//分类-取产品列表****************************************************************
	@Transactional(readOnly = true)
	def getProductListByCateogryId(Long parentCategoryId, Object params){
		log.info "**********getProductListByCateogryId:${parentCategoryId}"
		def productInstanceList
		try{
			def subCategoryInstanceList = ProductCategory.withCriteria {
				createAlias "parent", "parent"
				projections{
					property("id","id")
				}
				join("parent")
	
				eq("parent.id", parentCategoryId)
			}
			log.info "**********subCategoryInstanceList:${subCategoryInstanceList}"
			productInstanceList = ProductGoods.withCriteria() {
				projections{
					property("id", "id")
					property("xname", "name")
					property("xthumbnail", "thumbnail")
					property("xprice", "price")
				}
				//已启用
				eq("xstatus", 1)
				
				//产品分类
				inList("category.id", subCategoryInstanceList)
				if(params?.subCategoryId){
					eq("category.id", params.int('subCategoryId').toLong())
				}
				
				//品牌ID
				if(params?.brandId){
					eq("brand.id", params.int('brandId').toLong())
				}
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
				//排序
				if(params?.order && params?.sort){
					order(params?.sort, params?.order)
				}else{
					order("xorderIndex", "desc")
				}
				//偏移量
				if(params?.offset) firstResult(params.int('offset'))
				//最大返回行数
				maxResults(8)
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
			log.info "**********productInstanceList:${productInstanceList}"
		}catch(e){
			throw new RuntimeException("分类-取产品列表错误:${e.getMessage()}")
		}
		return productInstanceList
	}
	@Transactional(readOnly = true)
	def getProductCountByCateogryId(Long parentCategoryId, Object params){
		def productInstanceCount
		try{
			def subCategoryInstanceList = ProductCategory.withCriteria {
				createAlias "parent", "parent"
				projections{
					property("id","id")
				}
				join("parent")
	
				eq("parent.id", parentCategoryId)
			}

			productInstanceCount = ProductGoods.withCriteria(uniqueResult:true) {
				projections{
					count()
				}
				//已启用
				eq("xstatus", 1)
				
				//产品分类
				inList("category.id", subCategoryInstanceList)
				if(params?.subCategoryId){
					eq("category.id", params.int('subCategoryId').toLong())
				}
				
				//品牌ID
				if(params?.brandId){
					eq("brand.id", params.int('brandId'))
				}
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
		}catch(e){
			throw new RuntimeException("分类-取产品数量错误:${e.getMessage()}")
		}
		return productInstanceCount
	}
}
