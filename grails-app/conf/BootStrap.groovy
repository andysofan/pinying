import com.szmallecar.domain.product.*
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
    	//def brandExtInstance = new BrandExt(id:1, desc:"ext").save(flush:true)
    	
    	/********************************************************************************/
    	def brandInstance01 = new Brand(id:1, name:"乐扣", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 0, status : 1, firstWord:97, parent:null).save(flush:true)
		def brandInstance02 = new Brand(id:2, name:"毕加索", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 1, status : 1, firstWord:97, parent:null).save(flush:true)
		def brandInstance03 = new Brand(id:3, name:"德世朗", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 2, status : 1, firstWord:97, parent:null).save(flush:true)
		def brandInstance04 = new Brand(id:4, name:"双立人", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 3, status : 1, firstWord:97, parent:null).save(flush:true)
		def brandInstance05 = new Brand(id:5, name:"乐美亚", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 4, status : 1, firstWord:97, parent:null).save(flush:true)
		def brandInstance06 = new Brand(id:6, name:"内野", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 5, status : 1, firstWord:97, parent:null).save(flush:true)
		def brandInstance07 = new Brand(id:7, name:"飞利浦", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 6, status : 1, firstWord:97, parent:null).save(flush:true)
		def brandInstance08 = new Brand(id:8, name:"现代", url:"url", logoFileId:"url", level : 0, isLeaf : 0, orderIndex : 7, status : 1, firstWord:97, parent:null).save(flush:true)
    	/********************************************************************************/
    	def productCategoryInstance01 = new ProductCategory(id:1, name:"家居百货", level:0, isLeaf:1, orderIndex:0, needSubBrand:0, status:1).save(flush:true)
    	def productCategoryInstance0101 = new ProductCategory(id:2, name:"家纺类", level:1, isLeaf:1, orderIndex:1, needSubBrand:0, status:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0102 = new ProductCategory(id:3, name:"箱包类", level:1, isLeaf:1, orderIndex:2, needSubBrand:0, status:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0103 = new ProductCategory(id:4, name:"日用百货", level:1, isLeaf:1, orderIndex:3, needSubBrand:0, status:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0104 = new ProductCategory(id:5, name:"厨房用品", level:1, isLeaf:1, orderIndex:4, needSubBrand:0, status:1, parent:productCategoryInstance01).save(flush:true)
    	def productCategoryInstance0105 = new ProductCategory(id:6, name:"卫浴用品", level:1, isLeaf:1, orderIndex:5, needSubBrand:0, status:1, parent:productCategoryInstance01).save(flush:true)
    	
    	def productCategoryInstance02 = new ProductCategory(id:7, name:"家电数码", level:0, isLeaf:1, orderIndex:0, needSubBrand:0, status:1).save(flush:true)
    	def productCategoryInstance0201 = new ProductCategory(id:8, name:"鼠标", level:1, isLeaf:1, orderIndex:1, needSubBrand:0, status:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0202 = new ProductCategory(id:9, name:"移动电源", level:1, isLeaf:1, orderIndex:2, needSubBrand:0, status:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0203 = new ProductCategory(id:10, name:"路由器", level:1, isLeaf:1, orderIndex:3, needSubBrand:0, status:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0204 = new ProductCategory(id:11, name:"电子相框", level:1, isLeaf:1, orderIndex:4, needSubBrand:0, status:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0205 = new ProductCategory(id:12, name:"U盘", level:1, isLeaf:1, orderIndex:5, needSubBrand:0, status:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0206 = new ProductCategory(id:13, name:"小家电", level:1, isLeaf:1, orderIndex:6, needSubBrand:0, status:1, parent:productCategoryInstance02).save(flush:true)
    	def productCategoryInstance0207 = new ProductCategory(id:14, name:"读卡器", level:1, isLeaf:1, orderIndex:7, needSubBrand:0, status:1, parent:productCategoryInstance02).save(flush:true)

    	def productCategoryInstance03 = new ProductCategory(id:15, name:"办公商务", level:0, isLeaf:1, orderIndex:0, needSubBrand:0, status:1).save(flush:true)
    	def productCategoryInstance0301 = new ProductCategory(id:16, name:"笔", level:1, isLeaf:1, orderIndex:1, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0302 = new ProductCategory(id:17, name:"便签", level:1, isLeaf:1, orderIndex:2, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0303 = new ProductCategory(id:18, name:"计算器", level:1, isLeaf:1, orderIndex:3, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0304 = new ProductCategory(id:19, name:"文具套装", level:1, isLeaf:1, orderIndex:4, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0305 = new ProductCategory(id:20, name:"名片夹", level:1, isLeaf:1, orderIndex:5, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0306 = new ProductCategory(id:21, name:"记事本", level:1, isLeaf:1, orderIndex:6, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0307 = new ProductCategory(id:22, name:"电脑包", level:1, isLeaf:1, orderIndex:7, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)
    	def productCategoryInstance0308 = new ProductCategory(id:23, name:"卡包", level:1, isLeaf:1, orderIndex:7, needSubBrand:0, status:1, parent:productCategoryInstance03).save(flush:true)

    	def productCategoryInstance04 = new ProductCategory(id:24, name:"医疗保健", level:0, isLeaf:1, orderIndex:0, needSubBrand:0, status:1).save(flush:true)
		def productCategoryInstance0401 = new ProductCategory(id:25, name:"听诊器", level:1, isLeaf:1, orderIndex:1, needSubBrand:0, status:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0402 = new ProductCategory(id:26, name:"口腔电筒", level:1, isLeaf:1, orderIndex:2, needSubBrand:0, status:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0403 = new ProductCategory(id:27, name:"医用尺", level:1, isLeaf:1, orderIndex:3, needSubBrand:0, status:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0404 = new ProductCategory(id:28, name:"医用急救包", level:1, isLeaf:1, orderIndex:4, needSubBrand:0, status:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0405 = new ProductCategory(id:29, name:"医用笔插袋", level:1, isLeaf:1, orderIndex:5, needSubBrand:0, status:1, parent:productCategoryInstance04).save(flush:true)
		def productCategoryInstance0406 = new ProductCategory(id:30, name:"叩诊锤", level:1, isLeaf:1, orderIndex:6, needSubBrand:0, status:1, parent:productCategoryInstance04).save(flush:true)
                                
    	def productCategoryInstance05 = new ProductCategory(id:31, name:"工艺文化", level:0, isLeaf:1, orderIndex:0, needSubBrand:0, status:1).save(flush:true)
		def productCategoryInstance0501 = new ProductCategory(id:32, name:"水晶工艺品", level:1, isLeaf:1, orderIndex:1, needSubBrand:0, status:1, parent:productCategoryInstance05).save(flush:true)
		def productCategoryInstance0502 = new ProductCategory(id:33, name:"活性炭摆件", level:1, isLeaf:1, orderIndex:2, needSubBrand:0, status:1, parent:productCategoryInstance05).save(flush:true)
		def productCategoryInstance0503 = new ProductCategory(id:34, name:"琉璃工艺品", level:1, isLeaf:1, orderIndex:3, needSubBrand:0, status:1, parent:productCategoryInstance05).save(flush:true)
    	/*推荐和热门*******************************************************************************/
		def productInstance01 = new ProductGoods(
		  id : 1, name:"创意卷笔刀", keyword:"创意卷笔刀", code:"01", unit:"个", orderIndex:1, status:1, thumbnail:"right2.jpg", marketMinPrice:120, marketMaxPrice:120
		, recommend:true, ishot:true, quantity:10, popularity:20, category:productCategoryInstance0101, brand:brandInstance01, topBrand:brandInstance01
		).save(flush:true)

		def productInstance02 = new ProductGoods(
		  id:2, name:"驱蚊手镯", keyword:"驱蚊手镯", code:"02", unit:"个", orderIndex:2, status:1, thumbnail:"right3.jpg", marketMinPrice:1400, marketMaxPrice:1400
		, recommend:true, ishot:true, quantity:10, popularity:20, category:productCategoryInstance0101, brand:brandInstance01, topBrand:brandInstance01
		).save(flush:true)

		def productInstance03 = new ProductGoods(
		  id:3, name:"时尚沙发", keyword:"时尚沙发", code:"03", unit:"个", orderIndex:3, status:1, thumbnail:"right4.jpg", marketMinPrice:768, marketMaxPrice:768
		, recommend:true, ishot:true, quantity:10, popularity:20, category:productCategoryInstance0101, brand:brandInstance01, topBrand:brandInstance01
		).save(flush:true)

		def productInstance04 = new ProductGoods(
		  id:4, name:"多彩水壶", keyword:"多彩水壶", code:"04", unit:"个", orderIndex:4, status:1, thumbnail:"right5.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:true, ishot:true, quantity:10, popularity:20, category:productCategoryInstance0101, brand:brandInstance01, topBrand:brandInstance01
		).save(flush:true)
		/*人气*******************************************************************************/
		def productInstance05 = new ProductGoods(
		  id:5, name:"时尚桌椅", keyword:"时尚桌椅", code:"05", unit:"个", orderIndex:5, status:1, thumbnail:"list1.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0101, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		def productInstance06 = new ProductGoods(
		  id:6, name:"创意沙发", keyword:"创意沙发", code:"06", unit:"个", orderIndex:6, status:1, thumbnail:"list2.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0102, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		def productInstance07 = new ProductGoods(
		  id:7, name:"电动汽车", keyword:"电动汽车", code:"07", unit:"个", orderIndex:7, status:1, thumbnail:"list3.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0103, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		def productInstance08 = new ProductGoods(
		  id:8, name:"环保家居", keyword:"环保家居", code:"08", unit:"个", orderIndex:8, status:1, thumbnail:"list4.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0104, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		def productInstance09 = new ProductGoods(
		  id:9, name:"彩色手工艺", keyword:"彩色手工艺", code:"09", unit:"个", orderIndex:9, status:1, thumbnail:"list5.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0101, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		def productInstance10 = new ProductGoods(
		  id:10, name:"水果刀", keyword:"水果刀", code:"10", unit:"个", orderIndex:10, status:1, thumbnail:"list6.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0101, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		def productInstance11 = new ProductGoods(
		  id:11, name:"景德镇陶瓷", keyword:"景德镇陶瓷", code:"11", unit:"个", orderIndex:11, status:1, thumbnail:"list7.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0101, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		def productInstance12 = new ProductGoods(
		  id:12, name:"时尚水杯", keyword:"时尚水杯", code:"12", unit:"个", orderIndex:12, status:1, thumbnail:"list8.jpg", marketMinPrice:34, marketMaxPrice:34
		, recommend:false, ishot:false, quantity:10, popularity:6666, category:productCategoryInstance0101, brand:brandInstance08, topBrand:brandInstance08
		).save(flush:true)
		/*品牌与分类*******************************************************************************/
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
    }
    def destroy = {
    }
}
