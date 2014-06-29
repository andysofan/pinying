package com.cyberoller.pinying

class ProductCategory {

	ProductCategory	parent
	String	xname	
	int		xlevel = 0
	int		xorderIndex = 0
	int		xstatus = 1 //是否启用 1:启用 0:禁用
	Date 	dateCreated
	Date 	lastUpdated
	
	static hasMany = [subs:ProductCategory]
			
	static constraints = {
		xname(nullable:false,blank:false,size:1..255)
		parent(nullable:true)
	}
	
	static mapping = {
		version	false
		table 'x_product_category'
	}
}