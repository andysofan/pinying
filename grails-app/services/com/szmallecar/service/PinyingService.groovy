/*品迎*/

package com.szmallecar.service

import org.hibernate.criterion.CriteriaSpecification

import org.springframework.transaction.annotation.Transactional

import com.szmallecar.domain.product.*

class PinyingService {
	
	static transactional = true
	
	//菜单-取品牌****************************************************************
	@Transactional(readOnly = true)
	def menuBrandList(){
		def brandInstanceList
		try{
			brandInstanceList = Brand.withCriteria {
				projections{
					property("id", "id")
					property("name", "name")
				}
				order("orderIndex", "asc")
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
					property("name", "name")
				}
				eq("level", 0)
				order("orderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("菜单-取1级分类错误:${e.getMessage()}")
		}
		return categoryInstanceList
	}
	//菜单-取2级分类****************************************************************
	@Transactional(readOnly = true)
	def menuSecondLevelCategory(Integer parentId){
		def categoryInstanceList
		try{
			categoryInstanceList = ProductCategory.withCriteria {
				createAlias "parent", "parent"
				projections{
					property("id", "id")
					property("name", "name")
				}
				join("parent")
				eq("level", 1)
				eq("parent.id", parentId)
				order("orderIndex", "asc")
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
					property("name", "name")
					property("thumbnail", "thumbnail")
					property("marketMinPrice", "marketMinPrice")
					property("marketMaxPrice", "marketMaxPrice")
				}
				eq("recommend", true)
				eq("status", 1)
				order("orderIndex", "asc")
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
					property("name", "name")
					property("thumbnail", "thumbnail")
					property("marketMinPrice", "marketMinPrice")
					property("marketMaxPrice", "marketMaxPrice")
				}
				eq("ishot", true)
				eq("status", 1)
				order("orderIndex", "asc")
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
					property("name", "name")
					property("thumbnail", "thumbnail")
					property("marketMinPrice", "marketMinPrice")
					property("marketMaxPrice", "marketMaxPrice")
				}
				eq("status", 1)
				order("popularity", "desc")
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
	def getProductListByBrandId(Integer brandId, Object params){
		def productInstanceList
		try{
			productInstanceList = ProductGoods.withCriteria() {
				projections{
					property("id", "id")
					property("name", "name")
					property("thumbnail", "thumbnail")
					property("marketMinPrice", "marketMinPrice")
					property("marketMaxPrice", "marketMaxPrice")
				}
				
				if(brandId > 0){
					eq("brand.id", brandId)
				}else{
					isNotNull("brand.id")
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
			throw new RuntimeException("品牌-取产品列表错误:${e.getMessage()}")
		}
		return productInstanceList
	}
	def getProductCountByBrandId(Integer brandId, Object params){
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
			throw new RuntimeException("品牌-取产品列表数据错误:${e.getMessage()}")
		}
		return productInstanceCount
	}
	//分类-取品牌列表,根据1级分类ID****************************************************************
	//分类-获取分类名称
	@Transactional(readOnly = true)
	def getCategoryName (Integer categoryId)  {
		def categoryName
		try{
			categoryName = ProductCategory.withCriteria(uniqueResult:true) {
				projections{
					property("name")
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
	def getBrandName (Integer brandId)  {
		def brandName
		try{
			brandName = Brand.withCriteria(uniqueResult:true) {
				projections{
					property("name")
				}
				eq("id", brandId)
			}
		}catch(e){
			throw new RuntimeException("分类-取品牌名称ID:${brandId}错误:${e.getMessage()}")
		}
		return brandName	    
	}
	@Transactional(readOnly = true)
	def getBrandListByParentCategoryId(Integer categoryId, Object params){
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
					groupProperty("brand.name","name")
				}
				join("brand")
				inList("category.id", categoryInstanceList)
				if(params?.subCategoryId){
					eq("category.id", params.int('subCategoryId'))
				}
				order("orderIndex", "asc")
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
	def getCategoryListByParentCategoryId(Integer parentCategoryId){
		def categoryInstanceList
		try{
			categoryInstanceList = ProductCategory.withCriteria {
				createAlias "parent", "parent"
				projections{
					groupProperty("id","id")
					groupProperty("name","name")
				}
				join("parent")
				eq("parent.id", parentCategoryId)
				order("orderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("分类-取子分类列表，根据1级分类ID:${categoryId}错误:${e.getMessage()}")
		}
		return categoryInstanceList
	}
	//分类-取品牌列表,根据2级分类ID****************************************************************
	
	@Transactional(readOnly = true)
	def getBrandListByCategoryId(Integer categoryId){
		def brandInstanceList
		try{
			brandInstanceList = ProductCategoryBrand.withCriteria {
				createAlias "brand", "brand"
				projections{
					property("brand.id","id")
					groupProperty("brand.name","name")
				}
				join("brand")
				eq("category.id", categoryId)
				order("orderIndex", "asc")
				resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			}
		}catch(e){
			throw new RuntimeException("分类-取品牌列表,根据1级分类ID:${categoryId}错误:${e.getMessage()}")
		}
		return brandInstanceList
	}
	//分类-取产品列表****************************************************************
	@Transactional(readOnly = true)
	def getProductListByCateogryId(Integer parentCategoryId, Object params){
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
					property("name", "name")
					property("thumbnail", "thumbnail")
					property("marketMinPrice", "marketMinPrice")
					property("marketMaxPrice", "marketMaxPrice")
				}
				//已启用
				eq("status", 1)
				
				//产品分类
				inList("category.id", subCategoryInstanceList)
				if(params?.subCategoryId){
					eq("category.id", params.int('subCategoryId'))
				}
				
				//品牌ID
				if(params?.brandId){
					eq("brand.id", params.int('brandId'))
				}
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
				//排序
				if(params?.order && params?.sort){
					order(params?.sort, params?.order)
				}else{
					order("orderIndex", "desc")
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
	def getProductCountByCateogryId(Integer parentCategoryId, Object params){
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
				eq("status", 1)
				
				//产品分类
				inList("category.id", subCategoryInstanceList)
				if(params?.subCategoryId){
					eq("category.id", params.int('subCategoryId'))
				}
				
				//品牌ID
				if(params?.brandId){
					eq("brand.id", params.int('brandId'))
				}
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
		}catch(e){
			throw new RuntimeException("分类-取产品数量错误:${e.getMessage()}")
		}
		return productInstanceCount
	}
}
