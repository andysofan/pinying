/*品迎*/

package com.szmallecar.controller

import com.szmallecar.service.PinyingService

class PinyingController {

	def pinyingService

	def index() {
		log.info "**********index"
		render view : "/pinying/index"
	}
	
	//菜单-获取品牌列表
	def menuBrandList(){
		log.info "**********menuBrandList"
		try{
			def menuBrandList = pinyingService.menuBrandList()
			render template : "/pinying/menuBrandList", model : [menuBrandList:menuBrandList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/menuBrandList", model : [menuBrandList:null]
		}
	}
	//菜单-取1级分类
	def menuFirstLevelCategory(){
		log.info "**********menuFirstLevelCategory"
		try{
			def firstLevelCategoryInstanceList = pinyingService.menuFirstLevelCategory()
			render template : "/pinying/menuFirstLevelCategory", model : [firstLevelCategoryInstanceList:firstLevelCategoryInstanceList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/menuFirstLevelCategory", model : [firstLevelCategoryInstanceList:null]
		}
	}
	//菜单-取2级分类
	def menuSecondLevelCategory(Integer categoryId){
		log.info "**********menuSecondLevelCategory=${categoryId}"
		try{
			def secondLevelCategoryInstanceList = pinyingService.menuSecondLevelCategory(categoryId)
			render template : "/pinying/menuSecondLevelCategory", model : [secondLevelCategoryInstanceList:secondLevelCategoryInstanceList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/menuSecondLevelCategory", model : [secondLevelCategoryInstanceList:null]
		}
	}
	//主页-取推荐产品列表
	def recommendProductList(){
		log.info "**********recommendProductList"
		try{
			def recommendProductList = pinyingService.recommendProductList()
			render template : "/pinying/recommendProductList", model : [recommendProductList:recommendProductList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/recommendProductList", model : [recommendProductList:null]
		}
	}
	//主页-取最热门产品
	def hotestProduct(){
		log.info "**********hotestProduct"
		try{
			def hotestProductInstance = pinyingService.hotestProduct()
			render template : "/pinying/hotestProductList", model : [hotestProductInstance:hotestProductInstance]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/hotestProductList", model : [hotestProductInstance:null]
		}
	}
	//主页-取人气产品列表
	def popularProductList(){
		log.info "**********popularProductList"
		try{
			def popularProductList = pinyingService.popularProductList()
			render template : "/pinying/popularProductList", model : [popularProductList:popularProductList]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/popularProductList", model : [popularProductList:null]
		}
	}
	//品牌-主页
	def brand (Integer brandId)  {
		log.info "**********brand:${brandId}"
		try{
			def productInstanceList = pinyingService.getProductListByBrandId(brandId, params)
			def productInstanceCount = pinyingService.getProductCountByBrandId(brandId, params)
			render view : "/pinying/brand", model : [productInstanceList:productInstanceList, productInstanceCount:productInstanceCount]
		}catch(e){
			flash.message = e.getMessage()
			render view : "/pinying/brand", model : [productInstanceList:null, productInstanceCount:0]
		}
	}
	//分类-主页
	def category (Integer id)  {
		log.info "**********category:${id}"
		try{
			//取标题
			def title = pinyingService.getCategoryName(id)
			def productInstanceList = pinyingService.getProductListByCateogryId(id, params)
			def productInstanceCount = pinyingService.getProductCountByCateogryId(id, params)
			render view : "/pinying/category", model : [title:title, productInstanceList:productInstanceList, productInstanceCount:productInstanceCount]
		}catch(e){
			flash.message = e.getMessage()
			render view : "/pinying/category", model : [title:"", productInstanceList:null, productInstanceCount:0]
		}
	}
	//获取分类名称
	def getCategoryName (Integer categoryId)  {
	    log.info "**********getCategoryName:${categoryId}"
		def result = ""
		try{
			//取标题
			result = pinyingService.getCategoryName(categoryId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"
	}
	//获取分类名称
	def getBrandName (Integer brandId)  {
	    log.info "**********getBrandName:${brandId}"
		def result = ""
		try{
			//取标题
			result = pinyingService.getBrandName(brandId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"
	}
	//分类-取品牌列表,根据1级分类ID
	def getBrandListByParentCategoryId(Integer parentCategoryId){
		log.info "**********getBrandListByParentCategoryId${parentCategoryId}"
		try{
			def brandListByParentCategoryId = pinyingService.getBrandListByParentCategoryId(parentCategoryId, params)
			render template : "/pinying/brandListByParentCategoryId", model : [brandListByParentCategoryId:brandListByParentCategoryId]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/brandListByParentCategoryId", model : [brandListByParentCategoryId:null]
		}
	}
	//分类-取子分类列表，根据1级分类ID
	def getCategoryListByParentCategoryId(Integer parentCategoryId){
		log.info "**********getCategoryListByParentCategoryId:${parentCategoryId}"
		try{
			def categoryListByParentCategoryId = pinyingService.getCategoryListByParentCategoryId(parentCategoryId)
			render template : "/pinying/categoryListByParentCategoryId", model : [categoryListByParentCategoryId:categoryListByParentCategoryId]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/categoryListByParentCategoryId", model : [categoryListByParentCategoryId:null, subCategoryId : null]
		}
	}
	//分类-取品牌列表,根据2级分类ID
	def getBrandListByCategoryId(Integer categoryId){
		log.info "**********getBrandListByCategoryId:${categoryId}"
		try{
			def getBrandListByCategoryId = pinyingService.getBrandListByCategoryId(categoryId)
			render template : "/pinying/brandListByCategoryId", model : [getBrandListByCategoryId:getBrandListByCategoryId]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/brandListByCategoryId", model : [getBrandListByCategoryId:null]
		}
	}
	//分类-取产品列表
	def getProductListByCateogryId(Integer categoryId, Integer offset){
		log.info "**********getProductListByCateogryId:${categoryId},offset:${offset}"
		try{
			def getProductListByCateogryId = pinyingService.getProductListByCateogryId(categoryId, offset, params)
			render template : "/pinying/productListByCateogryId", model : [getProductListByCateogryId:getProductListByCateogryId]
		}catch(e){
			flash.message = e.getMessage()
			render template : "/pinying/productListByCateogryId", model : [getProductListByCateogryId:null]
		}
	}
}