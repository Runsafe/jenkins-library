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
		sh "echo -e \"[\\033[0;32m$(date +%T)\\033[m] \\033[0;36m${job} \\033[1;36m${name} \\033[1;32mstaged for deploy\\033[m.\" >> ~/builds.txt"
	}
}