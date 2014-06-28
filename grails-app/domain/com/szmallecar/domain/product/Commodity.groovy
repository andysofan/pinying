package com.szmallecar.domain.product

//import com.szmallecar.domain.distributor.Distributor

class Commodity {
	long	id
	
	double	cost = 0.0
	double	totalPrice = 0.0 //启用订金 这个表示总价
	int		isSpecCost = 0 //是否规格定价 0不使用 1使用规格定价
	int		type //类型 0:普通	1:订购金
	int		inventory = 0 //库存
	int		status = 0 //是否上架 1：上架		0：下架
	int		orderIndex = 0

    double weight = 0.0 //重量 用于计算运费
    double extWeight = 0.0 //续重
	
	ProductGoods product
	
	Date 	dateCreated
	Date 	lastUpdated
	
//	static	belongsTo = [distributor:Distributor]
	static	hasMany = [specifications:CommoditySpecification]
//	static	transients = ['specificationAndValueList']
	
//	//cache
//	def		specificationAndValueList
	
	static mapping = {
		version     true
		table		'x_commodity'
		id			column:'c_id'
		cost		column:'c_cost'
		totalPrice	column:'c_total_price'
		isSpecCost	column:'c_is_spec_cost'
		type		column:'c_type'
		inventory	column:'c_inventory'
		orderIndex	column:'c_order_index'
		status		column:'c_status'
        weight     column: 'c_weight'
        extWeight   column:'c_ext_weight'
	}
	
    static constraints = {
    }
	
//	def getSpecificationAndValueList(){
//		if(!specificationAndValueList){
//			def specList = CommoditySpecification.findAllByCommodityAndStatus(commodity,1)
//			specList.each {spec->
//				spec.val
//			}
//		}
//		return specificationAndValueList
//	}
}