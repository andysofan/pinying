package com.szmallecar.domain.product

class Brand {
	int		id
	
	String	name
	String	url
	String	logoFileId
	int		level = 0
	int		isLeaf = 1 //是否为叶子节点 1:是	0:不是
	int		orderIndex = 0
	int		status = 1 //是否启用 1:启用 0:禁用

	int		firstWord	//品牌首字母
	
	Brand	parent
	//BrandExt	ext
		
	Date 	dateCreated
	Date 	lastUpdated
	
	static hasMany = [subBrands:Brand]
    static	transients = ['topBrand']

    def getTopBrand(){
        def b = this
        while(b.level > 0){
            b = b.parent
        }
        return b
    }

	static mapping = {
		version		false
		cache		true
		table		'x_brand'
		id			column:'c_id'
		name		column:'c_name'
		url			column:'c_url'
		logoFileId	column:'c_logo_file_id'
		level		column:'c_level'
		isLeaf		column:'c_is_leaf'
		orderIndex	column:'c_order_index'
		status		column:'c_status'
		firstWord	column:'c_first_word'
		subBrands	sort:'orderIndex'
	}
	
    static constraints = {
		name(nullable:false,blank:false,size:1..255)
		url(nullable:true)
		logoFileId(nullable:true)
		parent(nullable:true)
    }
	
	static getTopBrandsWithFirstWord(){
		getTopBrandsWithFirstWord(null)
	}
	
	static getTopBrandsWithFirstWord(Brand ...brands){
		getTopBrandsWithFirstWord(brands.toList())
	}
	
	static getTopBrandsWithFirstWord(List<Brand> brandList){
		if(brandList){
			Collections.sort(brandList,new Comparator<Brand>(){
				public int compare(Brand b1,Brand b2){
					return b1.firstWord.compareTo(b2.firstWord)
				}
			})
		}else{
			brandList = Brand.findAll(sort:'firstWord'){
				level == 0
			}
		}
		def brandResult=[]
/*		if(brandList){
			def fw=0,i=0,bn
			brandList.each{
				if(it.firstWord == fw){
					bn=brandResult[i]
				}else{
					bn=[]
					brandResult[i]=bn
					i++
					fw = it.firstWord
					
				}
				bn<<it
			}
		}
		
		return brandList
		*/
		/*把所有相同首字母的放在同一个列表，然后把这些列表保存到一个大列表*/
		if(brandList){
			def fw=0,lastFw=0,bn
			brandList.each{brand->
				if(fw!=brand.firstWord){
					if(fw!=0){
						brandResult << bn
					}
					bn = []
				}
				bn << brand
				lastFw = brand.firstWord
			}
			if(fw!=lastFw){
				brandResult << bn
			}
		}
		return brandResult
	}

    def exeAllSubs(c) {
        exe(this,c)
    }

    private void exe(Brand b,c){
        c.call(b)
        if(!b.isLeaf){
            for(Brand sb:b.subBrands){
                exe(sb,c)
            }
        }
    }
}