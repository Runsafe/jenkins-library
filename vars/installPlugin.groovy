#!/usr/bin/env groovy

/**
 * Install plugin to server
 */
def call(String archive, String job, String name)
{
	dir('artifacts')
	{
		unstash 'archive'
		sh 'mkdir -p ~/bukkit/plugins'
		sh "tar -xvf ${archive} -C ~/bukkit/plugins"
		sh "echo -e \"[\\033[0;32m$(date +%T)\\033[m] \\033[0;36m${job} \\033[1;36m${name} \\033[1;32mbuild completed\\033[m.\" >> ~/builds.txt"
	}
}