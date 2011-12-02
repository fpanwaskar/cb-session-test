package com.burberry.apps.test

import com.burberry.apps.test.HttpSessionStorage

class SessionController {
	
	static defaultAction = "show"
	
    def show = { 
		render(view:'show', model:[httpSessionStorage: new HttpSessionStorage(request)])
	}
	
	def addToSession = {
		def sessionStorage = new HttpSessionStorage(request)
		
		sessionStorage.storeMessage(params.id, params.message)
		render(view:'show', model:[httpSessionStorage: sessionStorage])
	}
}
