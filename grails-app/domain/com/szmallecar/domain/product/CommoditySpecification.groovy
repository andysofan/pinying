package com.szmallecar.domain.product

class CommoditySpecification {
	long	id
	
	String	sid
	double	cost = 0.0
	double	totalPrice = 0.0 //启用订金 这个表示总价
	int		inventory = 0
	
	int		status = 1 //是否上架 1：上架		0：下架
	
	Date 	lastUpdated
	
	static	belongsTo = [commodity:Commodity]
	static	hasMany = [values:CommoditySpecificationValue]
	
	static mapping = {
        version     true
		table		'x_commodity_specification'
		id			column:'c_id'
		sid			column:'c_sid'
		cost		column:'c_cost'
		totalPrice	column:'c_total_price'
		inventory	column:'c_inventory'
	}
	
    static constraints = {
    }
}
