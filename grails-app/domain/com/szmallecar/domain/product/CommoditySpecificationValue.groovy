package com.szmallecar.domain.product

class CommoditySpecificationValue {
	long	id

	static	belongsTo = [parnet:CommoditySpecification,value:SpecificationValue]
		
	static mapping = {
		version	false
		table		'x_commodity_specification_value'
		id			column:'c_id'
	}
	
    static constraints = {
    }
}