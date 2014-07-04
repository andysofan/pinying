package com.cyberoller.pinying



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


class KvController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	@Transactional(readOnly = true)
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Kv.list(params), model:[kvInstanceCount: Kv.count()]
    }
	
	@Transactional(readOnly = true)
    def show(Kv kvInstance) {
        respond kvInstance
    }

    def create() {
        respond new Kv(params)
    }

    @Transactional
    def save(Kv kvInstance) {
        if (kvInstance == null) {
            notFound()
            return
        }

        if (kvInstance.hasErrors()) {
            respond kvInstance.errors, view:'create'
            return
        }

        kvInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'kv.label', default: 'Kv'), kvInstance.id])
                redirect kvInstance
            }
            '*' { respond kvInstance, [status: CREATED] }
        }
    }

    def edit(Kv kvInstance) {
        respond kvInstance
    }

    @Transactional
    def update(Kv kvInstance) {
        if (kvInstance == null) {
            notFound()
            return
        }

        if (kvInstance.hasErrors()) {
            respond kvInstance.errors, view:'edit'
            return
        }

        kvInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Kv.label', default: 'Kv'), kvInstance.id])
                redirect kvInstance
            }
            '*'{ respond kvInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Kv kvInstance) {

        if (kvInstance == null) {
            notFound()
            return
        }

        kvInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Kv.label', default: 'Kv'), kvInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'kv.label', default: 'Kv'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

	//显示上传页
	def upload ()  {
		render view :"/kv/upload"
	}

	def uploadImage (Long id)  {
		try{
			def file = request.getFile("file")
			
			if(file.isEmpty()){
				throw new RuntimeException("空文件！")
			}
			
			def kvInstance = Kv.get(id)

			if(!kvInstance){
				throw new RuntimeException("未找到KV")
			}

			String applicationPath = request.getSession().getServletContext().getRealPath("")
			def fileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))

			def xfile = new File("${applicationPath}/images/pinying/images/kv_${id}.${fileExtension}")
			if(!xfile.exists()){
				xfile.mkdirs()//目录不存在则创建
			}
			file.transferTo(xfile)
			kvInstance.ximage = "kv_${id}.${fileExtension}"

			kvInstance.save(flush:true)
			redirect action:'show', id : id
		}catch(e){
			flash.message = e.getMessage()
			render view :"/kv/upload", params:[id: params?.id]
		}
		return
	}
}
