package com.szmallecar.domain.product

class SaleTag {
	int		id
	
	String	name
	String	iconFileId
	int		orderIndex = 0
	
	int		status = 1 //是否启用 1:启用 0:禁用
	
	Date 	dateCreated
	Date 	lastUpdated
	
	static mapping = {
		version	false
		table		'x_sale_tag'
		id			column:'c_id'
		name		column:'c_name'
		status		column:'c_status'
		iconFileId	column:'c_icon_file_id'
		orderIndex	column:'c_order_index'
	}
	
    static constraints = {
		name(nullable:false,blank:false,size:1..200)
		iconFileId(nullable:true)
    }
}