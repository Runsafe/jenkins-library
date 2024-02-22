#!/usr/bin/env groovy

/**
 * Stage plugin for installation to server
 */
def call(String archive)
{
	dir('artifacts')
	{
		unstash 'archive'
		sh 'mkdir -p ~/updates'
		sh "tar -xvf ${archive} -C ~/updates"
		sh "echo -e \"[\\033[0;32m$(date +%T)\\033[m] \\033[0;36m${currentBuild.fullProjectName} \\033[1;36m${currentBuild.displayName} \\033[1;32mstaged for deploy after \$((\$(date +%s%N)/1000000-${currentBuild.startTimeInMillis}))ms\\033[m.\" >> ~/builds.txt"
	}
}