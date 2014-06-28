package com.szmallecar.domain.product

class ProductImage {
	long	id
	
	public static final int T_WIDTH = 50
	public static final int T_HEIGHT = 50
	public static final int M_WIDTH = 300
	public static final int M_HEIGHT = 300
	public static final int L_WIDTH = 800
	public static final int L_HEIGHT = 800
	
	String	name
	int		orderIndex = 0

	String	source
	String	large
	String	medium
	String	thumbnail
	
	Date 	dateCreated
	
	static	belongsTo = [product:ProductGoods]
	
	static mapping = {
		version	false
		table		'x_product_Image'
		id			column:'c_id'
		name		column:'c_name'
		orderIndex	column:'c_order_index'
		source		column:'c_source'
		large		column:'c_large'
		medium		column:'c_medium'
		thumbnail	column:'c_thumbnail'
	}
	
    static constraints = {
		name(nullable:true)
    }
}