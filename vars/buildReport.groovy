#!/usr/bin/env groovy

/**
 * Send notifications based on build status string
 */
def call(String repository) {

  // Send notification
	discordSend (
		description: "**Build:** ${env.BUILD_NUMBER}**
**Status:** ${currentBuild.currentResult}",
		enableArtifactsList: true,
		footer: '',
		image: '',
		link: env.BUILD_URL,
		result: currentBuild.currentResult,
		scmWebUrl: "https://github.com/Runsafe/${repository}/commit/%s",
		showChangeset: true,
		thumbnail: '',
		title: "${env.JOB_NAME} #${env.BUILD_NUMBER}",
		webhookURL: env.DISCORD_WEBHOOK
	)
}