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
	}
}