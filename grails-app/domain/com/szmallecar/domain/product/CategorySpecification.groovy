package com.szmallecar.domain.product

class CategorySpecification {
	long	id
	int		orderIndex = 0
	static	belongsTo = [category:ProductCategory,specification:Specification]
	static	hasMany = [values:CategorySpecificationValue]
	
	static mapping = {
		version	false
		table		'x_category_specification'
		id			column:'c_id'
		values		sort:'id'
		orderIndex	column:'c_order_index'
	}
	
    static constraints = {
    }
}