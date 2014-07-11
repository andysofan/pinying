package com.cyberoller.pinying



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class XjobController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Xjob.list(params), model:[xjobInstanceCount: Xjob.count()]
    }

    def show(Xjob xjobInstance) {
        respond xjobInstance
    }

    def create() {
        respond new Xjob(params)
    }

    @Transactional
    def save(Xjob xjobInstance) {
        if (xjobInstance == null) {
            notFound()
            return
        }

        if (xjobInstance.hasErrors()) {
            respond xjobInstance.errors, view:'create'
            return
        }

        xjobInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'xjob.label', default: 'Xjob'), xjobInstance.id])
                redirect xjobInstance
            }
            '*' { respond xjobInstance, [status: CREATED] }
        }
    }

    def edit(Xjob xjobInstance) {
        respond xjobInstance
    }

    @Transactional
    def update(Xjob xjobInstance) {
        if (xjobInstance == null) {
            notFound()
            return
        }

        if (xjobInstance.hasErrors()) {
            respond xjobInstance.errors, view:'edit'
            return
        }

        xjobInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Xjob.label', default: 'Xjob'), xjobInstance.id])
                redirect xjobInstance
            }
            '*'{ respond xjobInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Xjob xjobInstance) {

        if (xjobInstance == null) {
            notFound()
            return
        }

        xjobInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Xjob.label', default: 'Xjob'), xjobInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'xjob.label', default: 'Xjob'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
