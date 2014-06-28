package com.szmallecar.domain.product

class ProductParameterGroup {
	int		id
	
	String	name
	int		orderIndex = 0
	int		status = 1 //是否启用 1:启用 0:禁用
	
	static	belongsTo = [category:ProductCategory]
	static	hasMany = [parameters:ProductParameter]
	
	static mapping = {
		version	false
		table		'x_product_parameter_group'
		id			column:'c_id'
		name		column:'c_name'
		orderIndex	column:'c_order_index'
		status		column:'c_status'
		parameters	sort:'orderIndex'
	}
	
    static constraints = {
		name(nullable:false,blank:false,size:1..200)
    }
}