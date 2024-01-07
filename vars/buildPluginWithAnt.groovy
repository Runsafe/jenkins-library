#!/usr/bin/env groovy

/**
 * Download artefact dependenciess to the build
 */
def call(String plugin, String dependencies, String artifacts)
{
	checkout scm
	sh 'rm -rf plugins'
	def classPath = prepare dependencies
	sh "ant -f ant.xml ${classPath}"
	recordIssues enabledForFailure: true, tool: java(), unhealthy: 10
	archivePlugin '', artifacts, "${plugin}.tar"
}