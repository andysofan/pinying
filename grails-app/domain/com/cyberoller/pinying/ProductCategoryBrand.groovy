package com.cyberoller.pinying

class ProductCategoryBrand {

	int		xorderIndex = 0
	Date 	dateCreated
	Date 	lastUpdated
	
	static	belongsTo = [category:ProductCategory,brand:Brand]
		
    static constraints = {
    }
    
	static mapping = {
		table		'x_product_category_brand'
	}

}