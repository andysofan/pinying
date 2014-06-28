package com.szmallecar.domain.product

class ProductCategory {
	

	int		id
	String	name	
	int		level = 0
	int		isLeaf = 1 //是否为叶子节点 1:是	0:不是
	int		orderIndex = 0
	int		needSubBrand = 0 //是否开启品牌子集选项 默认 0:不开启 1:开启
	int		status = 1 //是否启用 1:启用 0:禁用
	
	ProductCategory	parent
	
Date 	dateCreated
	
	Date 	lastUpdated
	
	static hasMany = [brandSet:ProductCategoryBrand,subs:ProductCategory,parameterGroups:ProductParameterGroup,specificationSet:CategorySpecification]
	
	
		
	static mapping = {
		
		version	false
		
		table		'x_product_category'
		
		id			column:'c_id'
		name		column:'c_name'
		level		column:'c_level'
		isLeaf		column:'c_is_leaf'
		orderIndex	column:'c_order_index'
		needSubBrand column:'c_need_sub_brand'
		status		column:'c_status'
		brandSet	sort:'orderIndex'
		subs		sort:'orderIndex'
		parameterGroups sort:'orderIndex'
		specificationSet sort:'id'
	
	}
	static constraints = {
		name(nullable:false,blank:false,size:1..255)
		parent(nullable:true)
    
	}
	
	def getBrands(){
		return brandSet.collect{it.brand}
	}
	
	def getSpecifications(){
		return specificationSet.collect{it.specification}
	}
}