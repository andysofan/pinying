package com.szmallecar.domain.product

class SpecificationValue implements Comparable<SpecificationValue>{
	int		id
	
	String	name
	int		orderIndex = 0
	int		status = 1 //是否启用 1:启用 0:禁用
	String	iconFileId
    String  memo
	
	Date 	dateCreated
	Date 	lastUpdated
	
	static	belongsTo = [specification:Specification]
	
	static mapping = {
		version	false
		table		'x_specification_value'
		id			column:'c_id'
		name		column:'c_name'
		orderIndex	column:'c_order_index'
		iconFileId	column:'c_icon_file_id'
        memo        column:'c_memo'
	}
	
    static constraints = {
		name(nullable:false,blank:false,size:1..200)
		iconFileId(nullable:true)
        memo(nullable: true)
    }

	@Override
	public int compareTo(SpecificationValue o) {
		int result = orderIndex.compareTo(o.orderIndex);
		if(result == 0){
			result = id.compareTo(o.id)
		}
		return result
	}}