package com.cyberoller.pinying

class AboutController {

	//品牌建设
    def brand() { 
    	render view :"/about/brand"
    }
    
    //公司简介
    def company() {
    	render view :"/about/company"
    }
    
    //人才招聘
    def job(){
    	render view :"/about/job"
    }
    def job2(){
    	render view :"/about/job2"
    }
    
    //联系我们
    def contact(){
    	render view :"/about/contact"
    }
}
