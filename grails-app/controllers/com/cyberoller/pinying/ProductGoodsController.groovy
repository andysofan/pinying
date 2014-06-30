package com.cyberoller.pinying



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductGoodsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductGoods.list(params), model:[productGoodsInstanceCount: ProductGoods.count()]
    }

    def show(ProductGoods productGoodsInstance) {
        respond productGoodsInstance
    }

    def create() {
        respond new ProductGoods(params)
    }

    @Transactional
    def save(ProductGoods productGoodsInstance) {
        if (productGoodsInstance == null) {
            notFound()
            return
        }

        if (productGoodsInstance.hasErrors()) {
            respond productGoodsInstance.errors, view:'create'
            return
        }

        productGoodsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productGoods.label', default: 'ProductGoods'), productGoodsInstance.id])
                redirect productGoodsInstance
            }
            '*' { respond productGoodsInstance, [status: CREATED] }
        }
    }

    def edit(ProductGoods productGoodsInstance) {
        respond productGoodsInstance
    }

    @Transactional
    def update(ProductGoods productGoodsInstance) {
        if (productGoodsInstance == null) {
            notFound()
            return
        }

        if (productGoodsInstance.hasErrors()) {
            respond productGoodsInstance.errors, view:'edit'
            return
        }

        productGoodsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProductGoods.label', default: 'ProductGoods'), productGoodsInstance.id])
                redirect productGoodsInstance
            }
            '*'{ respond productGoodsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductGoods productGoodsInstance) {

        if (productGoodsInstance == null) {
            notFound()
            return
        }

        productGoodsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProductGoods.label', default: 'ProductGoods'), productGoodsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productGoods.label', default: 'ProductGoods'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	//显示上传页
	def upload ()  {
		render view :"/productGoods/upload", params:[type:params?.type]
	}

	def uploadImage ()  {
		try{
			def file = request.getFile("file")
			
			if(file.isEmpty()){
				throw new RuntimeException("空文件！")
			}
			String productId = params.int('id').toLong()
			
			def productInstance = ProductGoods.get(productId)

			if(!productInstance){
				throw new RuntimeException("未找到产品")
			}

			String applicationPath = request.getSession().getServletContext().getRealPath("")
			def fileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))

			switch(params?.type){
				case "xthumbnail":
					def xfile = new File("${applicationPath}/images/pinying/images/${productId}xthumbnail.${fileExtension}")
					if(!xfile.exists()){
						xfile.mkdirs()//目录不存在则创建
					}
					file.transferTo(xfile)
					productInstance.xthumbnail = "${productId}xthumbnail.${fileExtension}"
					break
				case "ximage1":
					def xfile = new File("${applicationPath}/images/pinying/images/${productId}ximage1.${fileExtension}")
					if(!xfile.exists()){
						xfile.mkdirs()//目录不存在则创建
					}
					file.transferTo(xfile)
					productInstance.ximage1 = "${productId}ximage1.${fileExtension}"
					break
				case "ximage2":
					def xfile = new File("${applicationPath}/images/pinying/images/${productId}ximage2.${fileExtension}")
					if(!xfile.exists()){
						xfile.mkdirs()//目录不存在则创建
					}
					file.transferTo(xfile)
					productInstance.ximage2 = "${productId}ximage2.${fileExtension}"
					break
				case "ximage3":
					def xfile = new File("${applicationPath}/images/pinying/images/${productId}ximage3.${fileExtension}")
					if(!xfile.exists()){
						xfile.mkdirs()//目录不存在则创建
					}
					file.transferTo(xfile)
					productInstance.ximage3 = "${productId}ximage3.${fileExtension}"
					break
				default:
					throw new RuntimeException("未知的类型:${params?.type}")
					break
			}
			productInstance.save(flush:true)
			redirect action:'show', id : productId
		}catch(e){
			flash.message = e.getMessage()
			render view :"/productGoods/upload", params:[id: params?.id, type:params?.type]
		}
		return
	}
}
