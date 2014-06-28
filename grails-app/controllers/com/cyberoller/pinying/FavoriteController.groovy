package com.cyberoller.pinying

class FavoriteController {

	def favoriteService

    def toggleFavorite(Integer productId) { 
		def result=""
		try{
			result = favoriteService.toggleFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"
	}

	def isFavorite (Integer productId) {
		def result=""
		try{
			result = favoriteService.isFavorite(productId)
		}catch(e){
			result = e.getMessage()
		}
		render "${result}"	    
	}
}