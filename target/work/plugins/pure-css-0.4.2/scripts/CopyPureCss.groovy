import grails.util.BuildSettings
import grails.util.BuildSettingsHolder

includeTargets << grailsScript("Init")

target(main: "Copies Pure CSS plugin files to an application") {
    def srcDir = "${pureCssPluginDir}/web-app/css/"
    def destDir = "${BuildSettingsHolder.settings.baseDir}/web-app/css/pure/"
    
    println 'Copying Pure CSS files...'

    ant.mkdir(dir: destDir)
    ant.copydir(src: srcDir, dest: destDir)

    println 'Done. Pure CSS files copied to web-app/css/pure/'
}

setDefaultTarget(main)