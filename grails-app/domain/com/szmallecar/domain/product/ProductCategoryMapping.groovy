package com.szmallecar.domain.product

class ProductCategoryMapping {
	long	id
	
	int		orderIndex = 0
	static	belongsTo = [product:ProductGoods,category:ProductCategory]
	
	static mapping = {
		version	false
		table		'x_product_category_mapping'
		id			column:'c_id'
		orderIndex	column:'c_order_index'
	}
	
    static constraints = {
    }
}