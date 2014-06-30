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
}
