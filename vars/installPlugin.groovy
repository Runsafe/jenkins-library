#!/usr/bin/env groovy

/**
 * Install plugin to server
 */
def call(String archive)
{
	dir('artifacts')
	{
		unstash 'archive'
		sh 'mkdir -p ~/bukkit/plugins'
		sh "tar -xvf ${archive} -C ~/bukkit/plugins"
		sh "echo -e \"[\\033[0;32m\$(date +%T)\\033[m] \\033[0;36m${currentBuild.fullProjectName} \\033[1;36m${currentBuild.displayName} \\033[1;32mbuild completed after \$((\$(date +%s%N)/1000000-${currentBuild.startTimeInMillis}))ms\\033[m.\" >> ~/builds.txt"
	}
}