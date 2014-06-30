import com.cyberoller.pinying.*
import com.cyberoller.pinying.shiro.*

import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.SecureRandomNumberGenerator

class BootStrap {

    def init = { servletContext ->
		//用户
		def adminUserInstance =  User.findByUsername("leaf@pinying".trim().toUpperCase())
		if(!adminUserInstance){
			String salt = new SecureRandomNumberGenerator().nextBytes().toHex()	 //生成随机数
			adminUserInstance = new User(
				  username: "leaf@pinying"
				, fullname : "leaf@pinying"
				, email:"leaf.shi@cyberoller.com"
				, passwordHash: new Sha512Hash("12345678", salt).toHex()
				, passwordSalt:salt
				, errortimes : 0
				, isBlocked : false
				, isActive : true
			).save(flush:true)
		}
		//角色
		def adminRoleInstance = Role.findByName("manager".trim().toUpperCase())
		if(!adminRoleInstance){
			adminRoleInstance = new Role(
			  name:"manager"
			, description:"角色-开发者：拥有所有权限"
			).addToPermissions("*:*")
			.addToUsers(adminUserInstance)
			.save(flush:true);
		}
		//角色
		def userRoleInstance = Role.findByName("user".trim().toUpperCase())
		if(!userRoleInstance){
			userRoleInstance = new Role(
			  name:"user"
			, description:"角色-用户：拥有pinying, favority, product所有的权限"
			)
			.addToPermissions("pinying:*")
			.addToPermissions("favorite:*")
			.addToPermissions("product:*")
			.save(flush:true);
		}
    	//def brandExtInstance = new BrandExt(id:1, desc:"ext").save(flush:true)
    	
    	/*品牌*******************************************************************************/
    	def brandInstance01 = new Brand(xname:"乐扣", xorderIndex : 0, xstatus : 1).save(flush:true)
		def brandInstance02 = new Brand(xname:"毕加索", xorderIndex : 1, xstatus : 1).save(flush:true)
		def brandInstance03 = new Brand(xname:"德世朗", xorderIndex : 2, xstatus : 1).save(flush:true)
		def brandInstance04 = new Brand(xname:"双立人", xorderIndex : 3, xstatus : 1).save(flush:true)
		def brandInstance05 = new Brand(xname:"乐美亚", xorderIndex : 4, xstatus : 1).save(flush:true)
		def brandInstance06 = new Brand(xname:"内野", xorderIndex : 5, xstatus : 1).save(flush:true)
		def brandInstance07 = new Brand(xname:"飞利浦", xorderIndex : 6, xstatus : 1).save(flush:true)
		def brandInstance08 = new Brand(xname:"现代", xorderIndex : 7, xstatus : 1).save(flush:true)
    	/*分类*******************************************************************************/
    	def productCategoryInstance01 = new ProductCategory(xname:"家居百货", xlevel:0, xorderIndex:0, xstatus:1).save(flush:true)
    	def productCategoryInstance0101 = new ProductCategory(xname:"家纺类", xlevel:1, xorderIndex:1, xstatus:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0102 = new ProductCategory(xname:"箱包类", xlevel:1, xorderIndex:2, xstatus:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0103 = new ProductCategory(xname:"日用百货", xlevel:1, xorderIndex:3, xstatus:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0104 = new ProductCategory(xname:"厨房用品", xlevel:1, xorderIndex:4, xstatus:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0105 = new ProductCategory(xname:"卫浴用品", xlevel:1, xorderIndex:5, xstatus:1, parent:productCategoryInstance01).save(flush:true)
    	
    	def productCategoryInstance02 = new ProductCategory(xname:"家电数码", xlevel:0, xorderIndex:0, xstatus:1).save(flush:true)
    	def productCategoryInstance0201 = new ProductCategory(xname:"鼠标", xlevel:1, xorderIndex:1, xstatus:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0202 = new ProductCategory(xname:"移动电源", xlevel:1, xorderIndex:2, xstatus:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0203 = new ProductCategory(xname:"路由器", xlevel:1, xorderIndex:3, xstatus:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0204 = new ProductCategory(xname:"电子相框", xlevel:1, xorderIndex:4, xstatus:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0205 = new ProductCategory(xname:"U盘", xlevel:1, xorderIndex:5, xstatus:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0206 = new ProductCategory(xname:"小家电", xlevel:1, xorderIndex:6, xstatus:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0207 = new ProductCategory(xname:"读卡器", xlevel:1, xorderIndex:7, xstatus:1, parent:productCategoryInstance02).save(flush:true)

    	def productCategoryInstance03 = new ProductCategory(xname:"办公商务", xlevel:0, xorderIndex:0, xstatus:1).save(flush:true)
    	def productCategoryInstance0301 = new ProductCategory(xname:"笔", xlevel:1, xorderIndex:1, xstatus:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0302 = new ProductCategory(xname:"便签", xlevel:1, xorderIndex:2, xstatus:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0303 = new ProductCategory(xname:"计算器", xlevel:1, xorderIndex:3, xstatus:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0304 = new ProductCategory(xname:"文具套装", xlevel:1, xorderIndex:4, xstatus:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0305 = new ProductCategory(xname:"名片夹", xlevel:1, xorderIndex:5, xstatus:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0306 = new ProductCategory(xname:"记事本", xlevel:1, xorderIndex:6, xstatus:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0307 = new ProductCategory(xname:"电脑包", xlevel:1, xorderIndex:7, xstatus:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0308 = new ProductCategory(xname:"卡包", xlevel:1, xorderIndex:7, xstatus:1, parent:productCategoryInstance03).save(flush:true)

    	def productCategoryInstance04 = new ProductCategory(xname:"医疗保健", xlevel:0, xorderIndex:0, xstatus:1).save(flush:true)
		def productCategoryInstance0401 = new ProductCategory(xname:"听诊器", xlevel:1, xorderIndex:1, xstatus:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0402 = new ProductCategory(xname:"口腔电筒", xlevel:1, xorderIndex:2, xstatus:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0403 = new ProductCategory(xname:"医用尺", xlevel:1, xorderIndex:3, xstatus:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0404 = new ProductCategory(xname:"医用急救包", xlevel:1, xorderIndex:4, xstatus:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0405 = new ProductCategory(xname:"医用笔插袋", xlevel:1, xorderIndex:5, xstatus:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0406 = new ProductCategory(xname:"叩诊锤", xlevel:1, xorderIndex:6, xstatus:1, parent:productCategoryInstance04).save(flush:true)
                                
    	def productCategoryInstance05 = new ProductCategory(xname:"工艺文化", xlevel:0, xorderIndex:0, xstatus:1).save(flush:true)
		def productCategoryInstance0501 = new ProductCategory(xname:"水晶工艺品", xlevel:1, xorderIndex:1, xstatus:1, parent:productCategoryInstance05).save(flush:true)
		def productCategoryInstance0502 = new ProductCategory(xname:"活性炭摆件", xlevel:1, xorderIndex:2, xstatus:1, parent:productCategoryInstance05).save(flush:true)
		def productCategoryInstance0503 = new ProductCategory(xname:"琉璃工艺品", xlevel:1, xorderIndex:3, xstatus:1, parent:productCategoryInstance05).save(flush:true)
    	/*产品*******************************************************************************/
		def productInstance01 = new ProductGoods(xname:"创意卷笔刀", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:1, xstatus:1, xthumbnail:"right2.jpg", xprice:10, xrecommend:true, xishot:true, xquantity:10, xpopularity:570, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance02 = new ProductGoods(xname:"驱蚊手镯", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:2, xstatus:1, xthumbnail:"right3.jpg", xprice:30, xrecommend:true, xishot:true, xquantity:10, xpopularity:270, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance03 = new ProductGoods(xname:"时尚沙发", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:3, xstatus:1, xthumbnail:"right4.jpg", xprice:50, xrecommend:true, xishot:true, xquantity:10, xpopularity:210, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance04 = new ProductGoods(xname:"多彩水壶", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:4, xstatus:1, xthumbnail:"right5.jpg", xprice:70, xrecommend:true, xishot:true, xquantity:10, xpopularity:170, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)

		def productInstance05 = new ProductGoods(xname:"时尚桌椅", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:5, xstatus:1, xthumbnail:"list1.jpg", xprice:90, xrecommend:true, xishot:true, xquantity:10, xpopularity:150, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance06 = new ProductGoods(xname:"创意沙发", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:6, xstatus:1, xthumbnail:"list2.jpg", xprice:110, xrecommend:true, xishot:true, xquantity:10, xpopularity:130, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance07 = new ProductGoods(xname:"电动汽车", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:7, xstatus:1, xthumbnail:"list3.jpg", xprice:130, xrecommend:true, xishot:true, xquantity:10, xpopularity:110, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance08 = new ProductGoods(xname:"环保家居", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:8, xstatus:1, xthumbnail:"list4.jpg", xprice:150, xrecommend:true, xishot:true, xquantity:10, xpopularity:90, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance09 = new ProductGoods(xname:"彩色手工艺", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:9, xstatus:1, xthumbnail:"list5.jpg", xprice:170, xrecommend:true, xishot:true, xquantity:10, xpopularity:70, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance10 = new ProductGoods(xname:"水果刀", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:10, xstatus:1, xthumbnail:"list6.jpg", xprice:210, xrecommend:true, xishot:true, xquantity:10, xpopularity:50, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance11 = new ProductGoods(xname:"景德镇陶瓷", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:11, xstatus:1, xthumbnail:"list7.jpg", xprice:270, xrecommend:true, xishot:true, xquantity:10, xpopularity:30, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		def productInstance12 = new ProductGoods(xname:"时尚水杯", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:12, xstatus:1, xthumbnail:"list8.jpg", xprice:570, xrecommend:true, xishot:true, xquantity:10, xpopularity:10, category:productCategoryInstance0101, brand:brandInstance01).save(flush:true)
		//无牌产品
		def productInstanceA1 = new ProductGoods(xname:"创意卷笔刀A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:1, xstatus:1, xthumbnail:"right2.jpg", xprice:10, xrecommend:true, xishot:true, xquantity:10, xpopularity:570, category:productCategoryInstance0101).save(flush:true)
		def productInstanceA2 = new ProductGoods(xname:"驱蚊手镯A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:2, xstatus:1, xthumbnail:"right3.jpg", xprice:30, xrecommend:true, xishot:true, xquantity:10, xpopularity:270, category:productCategoryInstance0101).save(flush:true)
		def productInstanceA3 = new ProductGoods(xname:"时尚沙发A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:3, xstatus:1, xthumbnail:"right4.jpg", xprice:50, xrecommend:true, xishot:true, xquantity:10, xpopularity:210, category:productCategoryInstance0101).save(flush:true)
		def productInstanceA4 = new ProductGoods(xname:"多彩水壶A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:4, xstatus:1, xthumbnail:"right5.jpg", xprice:70, xrecommend:true, xishot:true, xquantity:10, xpopularity:170, category:productCategoryInstance0101).save(flush:true)

		def productInstanceA5 = new ProductGoods(xname:"时尚桌椅A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:5, xstatus:1, xthumbnail:"list1.jpg", xprice:90, xrecommend:true, xishot:true, xquantity:10, xpopularity:150, category:productCategoryInstance0101).save(flush:true)
		def productInstanceA6 = new ProductGoods(xname:"创意沙发A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:6, xstatus:1, xthumbnail:"list2.jpg", xprice:110, xrecommend:true, xishot:true, xquantity:10, xpopularity:130, category:productCategoryInstance0101).save(flush:true)
		def productInstanceA7 = new ProductGoods(xname:"电动汽车A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:7, xstatus:1, xthumbnail:"list3.jpg", xprice:130, xrecommend:true, xishot:true, xquantity:10, xpopularity:110, category:productCategoryInstance0101).save(flush:true)
		def productInstanceA8 = new ProductGoods(xname:"环保家居A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:8, xstatus:1, xthumbnail:"list4.jpg", xprice:150, xrecommend:true, xishot:true, xquantity:10, xpopularity:90, category:productCategoryInstance0101).save(flush:true)
		def productInstanceA9 = new ProductGoods(xname:"彩色手工艺A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:9, xstatus:1, xthumbnail:"list5.jpg", xprice:170, xrecommend:true, xishot:true, xquantity:10, xpopularity:70, category:productCategoryInstance0101).save(flush:true)
		def productInstanceAA = new ProductGoods(xname:"水果刀A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:10, xstatus:1, xthumbnail:"list6.jpg", xprice:210, xrecommend:true, xishot:true, xquantity:10, xpopularity:50, category:productCategoryInstance0101).save(flush:true)
		def productInstanceAB = new ProductGoods(xname:"景德镇陶瓷A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:11, xstatus:1, xthumbnail:"list7.jpg", xprice:270, xrecommend:true, xishot:true, xquantity:10, xpopularity:30, category:productCategoryInstance0101).save(flush:true)
		def productInstanceAC = new ProductGoods(xname:"时尚水杯A", xpackage:"package",xmaterial:"material", xsize:"size", xcolor:"color", xdesc:"desc", xorderIndex:12, xstatus:1, xthumbnail:"list8.jpg", xprice:570, xrecommend:true, xishot:true, xquantity:10, xpopularity:10, category:productCategoryInstance0101).save(flush:true)
		
		/*品牌与分类*******************************************************************************/
		/*
		new ProductCategoryBrand(category:productCategoryInstance0101, brand: brandInstance01).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0102, brand: brandInstance01).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0103, brand: brandInstance01).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0104, brand: brandInstance01).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0105, brand: brandInstance01).save(flush:true)

		new ProductCategoryBrand(category:productCategoryInstance0201, brand: brandInstance02).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0202, brand: brandInstance02).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0203, brand: brandInstance02).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0204, brand: brandInstance02).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0205, brand: brandInstance02).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0206, brand: brandInstance02).save(flush:true)
		new ProductCategoryBrand(category:productCategoryInstance0207, brand: brandInstance02).save(flush:true)
		*/
		/*KV*******************************************************************************/
		new Kv(xname:"1", ximage:"kv1.jpg", xorder:1, xdesc:"1", xisActive:true).save(flush:true)
		new Kv(xname:"2", ximage:"kv2.jpg", xorder:1, xdesc:"1", xisActive:true).save(flush:true)
		new Kv(xname:"3", ximage:"kv3.jpg", xorder:1, xdesc:"1", xisActive:true).save(flush:true)
		new Kv(xname:"4", ximage:"kv4.jpg", xorder:1, xdesc:"1", xisActive:true).save(flush:true)
    }
    def destroy = {
    }
}
