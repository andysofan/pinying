package com.szmallecar.domain.product

class CategorySpecificationValue {
	long	id
	int		orderIndex = 0
	static	belongsTo = [specification:CategorySpecification,value:SpecificationValue]
	
	static mapping = {
		version	false
		table		'x_category_specification_value'
		orderIndex	column:'c_order_index'
		id			column:'c_id'
	}
	
    static constraints = {
    }
}