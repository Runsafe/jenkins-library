#!/usr/bin/env groovy

/**
 * Archive artifacts from build
 */
def call(String targetFolder, String artifacts, String archive)
{
	dir('artifacts')
	{
		sh 'rm -rf tmp'
		sh "mkdir -p tmp/${targetFolder}"
		targetFolder="tmp/${targetFolder}"
		artifacts.split(',').each
		{
			sh "cp -a ${it} ${targetFolder}"
		}
		sh "tar -cvf ${archive} -C tmp"
		archiveArtifacts artifacts: archive
		stash includes: archive, name: 'archive'
	}
}