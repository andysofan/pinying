package com.cyberoller.pinying

class Brand {

	String	xname
	int		xorderIndex = 0
	int		xstatus = 1 //是否启用 1:启用 0:禁用
	Date 	dateCreated
	Date 	lastUpdated
	
    static constraints = {
		xname(nullable:false,blank:false,unique:true, size:1..255)
    }

    String toString(){
    	return "${xname}"
    }
    
	static mapping = {
		cache		true
		table		'x_brand'
	}
}