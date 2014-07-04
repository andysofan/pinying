package com.cyberoller.pinying.shiro



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

	def userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

	/**
	 * 重置密码
	 **/
	def resetPassword(Long id){
        def userInstance = User.get( id )
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), username])
            redirect(controller : "user", action: "index")
            return
        }
		render view : '/user/resetPassword', model : [userInstance: userInstance]
	}

	/**
	 * 重置密码
	 **/
	
    def reset(Long id, Long version){
		def userInstance = User.get( id )
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), username])
            redirect(controller : "user", action: "index")
            return
        }
        
		if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                redirect(controller : "user", action: "show", id: id)
                return
            }
        }
		try{
			userService.resetPassword(userInstance, params.password, params.passwordConfirm)
			flash.message = message(code: 'user.password.reset', args: [username])
		}catch(e){
			flash.message = e.getMessage()
			render(view: "/user/resetPassword", model: [userInstance: userInstance])
		}
		redirect(controller : "user", action: "show", id : id)
    }



	/**
	 * web重置密码 lxb
	 **/
	
	def resetPasswordPage(){
		render view : '/user/web_resetPassword', model : [userInstance: userService.resetPasswordPageService()]
	}



	/**
	 * web重置密码 lxb
	 **/
	@Transactional
    def webResetPassword(Long id, Long version){
		def userInstance = User.get( id )

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params?.username])
            redirect(controller : "index", action: "index")
            return
        }
        
		if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                redirect(controller : "user", action: "resetPasswordPage")
                return
            }
        }
		try{
			userService.webResetPassword(userInstance, params.password, params.passwordConfirm)
			flash.message = message(code: 'user.password.reset', args: [params?.username])
		}catch(e){
			flash.message = e.getMessage()
			render(view: "/user/web_resetPassword", model: [userInstance: userInstance])
		}
		redirect(uri:'/')
    }
	


	/**
	 * web忘记密码 lxb
	 **/
	
	def webForgetPassword(){
		render view : '/user/web_forgetPassword'
	}



    
	/**
	 * 启用帐号
	 **/
	 @Transactional
    def activeUser(Long id, Long version){
        def userInstance = User.get( id )
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), username])
            redirect(action: "list")
            return
        }
        
		if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                redirect(action: "show", id : id)
                return
            }
        }
		
		if (userService.activeUser(id) == true) {
			flash.message = message(code: 'user.active.success', args: [userInstance?.username])
			redirect(action: "show", id : id)
		}
		else {
			flash.message = message(code: 'user.active.failed', args: [userInstance?.username])
			redirect(action: "show", id : id)
		}
    }

	/**
	 * 停用帐号
	 **/
	 @Transactional
    def inActiveUser(Long id, Long version){
        def userInstance = User.get( id )
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), username])
            redirect(action: "list")
            return
        }
        
		if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                redirect(action: "show", id : id)
                return
            }
        }
		
		if (userService.inActiveUser(id) == true) {
			flash.message = message(code: 'user.inActive.success', args: [userInstance?.username])
			redirect(action: "show", id : id)
		}
		else {
			flash.message = message(code: 'user.inActive.failed', args: [userInstance?.username])
			redirect(action: "show", id : id)
		}
    }
	@Transactional
	def unlockUser (Long id, Long version)  {
		try{
			def userInstance = User.get( id )
			if (!userInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), username])
				redirect(action: "list")
				return
			}
			
			if (version != null) {
				if (userInstance.version > version) {
					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
							  [message(code: 'user.label', default: 'User')] as Object[],
							  "Another user has updated this User while you were editing")
					redirect(action: "show", id : id)
					return
				}
			}
			
			userService.unlockUser(id)
			flash.message = "解锁成功"
		}catch(e){
			flash.message = "解锁失败:${e.getMessage()}"
		}
		redirect(action: "show", id : id)
	}
}
