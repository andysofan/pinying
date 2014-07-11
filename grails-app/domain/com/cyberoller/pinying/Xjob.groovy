package com.cyberoller.pinying

class Xjob {

	String xcode
	String xname
	Integer xquantity
	String xduty
	String xrequire

	Boolean xisActive

	Date dateCreated
	Date lastUpdated

    static constraints = {
		xcode(nullable:false, blank:false, unique:true, maxSize:2)
		xname(nullable:false, blank:false, maxSize:100)
		xquantity(nullable:false)
		xduty(nullable:false, blank:false, maxSize:1000)
		xrequire(nullable:false, blank:false, maxSize:1000)
		xisActive(nullable:false)
    }

	def beforeValidate ()  {
	    if(!xisActive){
			xisActive = false
		}
	}

	String toString(){
		return "${xcode}"
	}

	static mapping = {
		table "x_job"
	}
}