package com.cyberoller.pinying

import org.springframework.transaction.annotation.Transactional

import org.hibernate.criterion.CriteriaSpecification

import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

import org.apache.shiro.SecurityUtils
import com.cyberoller.pinying.shiro.User

import com.szmallecar.domain.product.ProductGoods

class FavoriteService {

	static transactional = false
	def messageSource

	@Transactional(readOnly = true)
    def currentUserId() {
		def currentUserId
		try{
			def userInstance = User.withCriteria(uniqueResult:true){
				eq("username", SecurityUtils.getSubject().getPrincipal())
			}
			if(!userInstance){
				throw new RuntimeException("获取当前用户失败")
			}
			currentUserId = userInstance.id
		}catch(e){
			throw new RuntimeException("获取当前用户ID失败错误：${e.getMessage()}")
		}
		return currentUserId
    }

    def toggleFavorite(Integer productId) {
		def result = ""
		Favorite.withTransaction { status ->
			try{
				//获取用户ID
				def currentUserId = this.currentUserId()
				//取收藏
				def favoriteInstance = Favorite.withCriteria(uniqueResult:true){
					eq("user.id", currentUserId)
					eq("productGoods.id", productId)
				}
				if(favoriteInstance){
					favoriteInstance.delete(flush:true)
					//取消收藏成功!
					result = "收藏"
				}else{
					favoriteInstance = new Favorite(
					  user : User.get(currentUserId)
					, productGoods : ProductGoods.get(productId)
					)
					//绑定
					/*
					BindDynamicMethod mybind = new BindDynamicMethod()
					def mymap = ['user.id' :currentUserId, 'productGoods.id':productId]
					def myargs =  [favoriteInstance, mymap]
					mybind.invoke(favoriteInstance, 'bind', (Object[]) myargs)
					*/
					log.info "*****************favoriteInstance=user:${favoriteInstance.user}, product:${favoriteInstance.productGoods}"
					//检查
					if(!favoriteInstance.validate()){
						def errorMsg = ""
						favoriteInstance.errors.allErrors.each{ errorInstance ->
							errorMsg = "${errorMsg};${messageSource.getMessage(errorInstance, Locale.ENGLISH)}"
						}
						errorMsg = errorMsg.encodeAsHTML()
						
						throw new RuntimeException("检查失败：${errorMsg}")
					}
					//保存
					favoriteInstance.save(flush:true)
					//收藏成功
					result = "取消收藏"
				}
			}catch(e){
				status.setRollbackOnly();
				throw new RuntimeException("失败：${e.getMessage()}")
			}
		}
		return result
    }
}
