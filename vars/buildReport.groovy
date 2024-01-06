#!/usr/bin/env groovy

/**
 * Send notifications based on build status string
 */
def call(String repository, String message) {

  // Send notification
	discordSend (
		description: "**Build:** [${env.BUILD_NUMBER}](${env.BUILD_URL})\n**Status:** ${currentBuild.currentResult}",
		enableArtifactsList: true,
		footer: message,
		image: '',
		link: currentBuild.absoluteUrl,
		result: currentBuild.currentResult,
		scmWebUrl: "https://github.com/Runsafe/${repository}/commit/%s",
		showChangeset: true,
		thumbnail: '',
		title: currentBuild.fullDisplayName,
		webhookURL: env.DISCORD_WEBHOOK
	)
}