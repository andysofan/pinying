package com.szmallecar.domain.product

class ProductCategoryBrand {
	int		id
	
	int		orderIndex = 0
	
	Date 	dateCreated
	Date 	lastUpdated
	
	static	belongsTo = [category:ProductCategory,brand:Brand]
	
	static mapping = {
		version	false
		table		'x_product_category_brand'
		id			column:'c_id'
		orderIndex	column:'c_order_index'
	}
	
    static constraints = {
    }
}