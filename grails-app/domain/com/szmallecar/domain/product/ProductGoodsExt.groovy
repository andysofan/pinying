package com.szmallecar.domain.product

class ProductGoodsExt {
	int		id
	
	String	desc
	String	parameters
	
	static	belongsTo = [product:ProductGoods]
	
	static mapping = {
		version	false
		table		'x_product_goods_ext'
		id			column:'c_id'
		desc		column:'c_desc'
		parameters	column:'c_paramemeter',type:'text'
	}
	
    static constraints = {
		desc nullable:true
		parameters nullable:true
    }
}