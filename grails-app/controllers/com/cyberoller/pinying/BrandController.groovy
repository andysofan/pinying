package com.cyberoller.pinying



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BrandController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Brand.list(params), model:[brandInstanceCount: Brand.count()]
    }

    def show(Brand brandInstance) {
        respond brandInstance
    }

    def create() {
        respond new Brand(params)
    }

    @Transactional
    def save(Brand brandInstance) {
        if (brandInstance == null) {
            notFound()
            return
        }

        if (brandInstance.hasErrors()) {
            respond brandInstance.errors, view:'create'
            return
        }

        brandInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'brand.label', default: 'Brand'), brandInstance.id])
                redirect brandInstance
            }
            '*' { respond brandInstance, [status: CREATED] }
        }
    }

    def edit(Brand brandInstance) {
        respond brandInstance
    }

    @Transactional
    def update(Brand brandInstance) {
        if (brandInstance == null) {
            notFound()
            return
        }

        if (brandInstance.hasErrors()) {
            respond brandInstance.errors, view:'edit'
            return
        }

        brandInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Brand.label', default: 'Brand'), brandInstance.id])
                redirect brandInstance
            }
            '*'{ respond brandInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Brand brandInstance) {

        if (brandInstance == null) {
            notFound()
            return
        }

        brandInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Brand.label', default: 'Brand'), brandInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'brand.label', default: 'Brand'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
