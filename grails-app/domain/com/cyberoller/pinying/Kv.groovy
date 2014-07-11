package com.cyberoller.pinying

class Kv {

	String xname
	String ximage
	Integer xorder
	String xurl
	String xdesc
	Boolean xisActive

	Date dateCreated
	Date lastUpdated

    static constraints = {
		xname(nullable:false, blank:false, unique:true, maxSize:100)
		ximage(nullable:true, blank:false, maxSize:100)
		xorder(nullable:false)
		xurl(nullable:false, blank:false, maxSize:200)
		xdesc(nullable:true, blank:false, maxSize:100)
		xisActive(nullable:false)
    }

	def beforeValidate ()  {
		if(!xisActive){
			xisActive = false
		}
	}

	String toString(){
		return "${xname}"
	}

	static mapping = {
		table 'x_kv'
	}
}