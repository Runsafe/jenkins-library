#!/usr/bin/env groovy

/**
 * Archive artifacts from build
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