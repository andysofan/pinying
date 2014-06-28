package com.szmallecar.domain.product

class ProductParameter {
	int		id
	
	String	name
	int		type = 0 //参数类型 0：文本	1:单选	2:多选
	int		orderIndex = 0
	int		status = 1 //是否启用 1:启用 0:禁用
	int		function = 0 //功能 0:普通参数	1:用于搜索
	
	static	belongsTo = [group:ProductParameterGroup,category:ProductCategory]
	static	hasMany = [options:ProductParameterOption]
	
	static mapping = {
		version	false
		table		'x_product_parameter'
		id			column:'c_id'
		name		column:'c_name'
		orderIndex	column:'c_order_index'
		status		column:'c_status'
		options		sort:'orderIndex'
	}
	
    static constraints = {
		name(nullable:false,blank:false,size:1..200)
    }
}