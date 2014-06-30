/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class SecurityFilters {
    def filters = {
		/*登录，退出*/
		auth(controller: "auth", action: "*"){
			before={
				accessControl{true}
			}
		}
		
		/*其它*/
		all(controller: "*", action: "*"){
			before={
				if (!(controllerName == 'index' || controllerName == 'about' || controllerName == 'search')){//主页
					accessControl{
						permission("${controllerName}:${actionName}:${params.id}")
					}
				}
			}
		}
    }
}
