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
	}
}