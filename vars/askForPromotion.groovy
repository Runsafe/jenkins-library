#!/usr/bin/env groovy

/**
 * Trigger deployment to production
 */
def call()
{
	// Trigger the deployment build with parameters pointing at the currently executing build
	build wait: false, job: '/Runsafe/Deployment/main', parameters: [string(name: 'project', value: currentBuild.fullProjectName), string(name: 'build', value: env.BUILD_NUMBER)]
}