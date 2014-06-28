package com.szmallecar.domain.product

class BrandExt {
	

	int		id
	String	desc
	
	//static	belongsTo = [brand:Brand]
	
	
	
	static mapping = {
		
		version	false
		
		table		'x_brand_ext'
		
		id			column:'c_id'
		desc		column:'c_desc'
	
	}
	
    
	
	static constraints = {
		desc(nullable:true)
    }

}