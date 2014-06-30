def dev = grails.util.GrailsUtil.isDevelopmentEnv()
def purePluginDefaultBundle = 'pure'

modules = {
    'pure-all' {
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-all', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'pure.css' : 'pure-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-all-nr' {
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-all-nr', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'pure-nr.css' : 'pure-nr-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-base' {
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-base', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'base.css' : 'base-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-buttons' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-buttons', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'buttons.css' : 'buttons-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-forms' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-forms', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'forms.css' : 'forms-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-forms-nr' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-forms-nr', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'forms-nr.css' : 'forms-nr-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-grids' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-grids', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'grids.css' : 'grids-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-grids-nr' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-grids-nr', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'grids-nr.css' : 'grids-nr-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-menus' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-menus', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'menus.css' : 'menus-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-menus-nr' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-menus-nr', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'menus-nr.css' : 'menus-nr-min.css') ], exclude: 'minify', disposition: 'head'
    }

    'pure-tables' {
    	dependsOn 'pure-base'
    	defaultBundle purePluginDefaultBundle

        resource id: 'pure-tables', url: [ plugin: 'pure-css', dir: 'css', file: (dev ? 'tables.css' : 'tables-min.css') ], exclude: 'minify', disposition: 'head'
    }
}