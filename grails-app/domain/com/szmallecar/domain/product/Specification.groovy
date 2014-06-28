package com.szmallecar.domain.product

class Specification implements Comparable<Specification>{
	int		id
	
	String	name
	String	memo
	int		type = 0 //规范类型 0:文本	1:图片 2:颜色
	int		orderIndex = 0
	int		status = 1 //是否启用 1:启用 0:禁用
	
	Date 	dateCreated
	Date 	lastUpdated
	
	static	hasMany = [values:SpecificationValue]
	
	static mapping = {
		version	false
		table		'x_specification'
		id			column:'c_id'
		name		column:'c_name'
		type		column:'c_type'
		orderIndex	column:'c_order_index'
		values		sort:'orderIndex'
	}
	
    static constraints = {
		name(nullable:false,blank:false,size:1..200)
		memo(nullable:true)
    }

	@Override
	public int compareTo(Specification o) {
		return Integer.valueOf(orderIndex).compareTo(o?.orderIndex);
	}}