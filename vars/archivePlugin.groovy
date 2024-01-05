#!/usr/bin/env groovy

/**
 * Archive artifacts from build
 */
def call(String targetFolder, List<String> artifacts, String archive)
{
	dir('artifacts')
	{
		sh 'rm -rf tmp'
		if (!targetFolder?.trim())
		{
			sh "mkdir -p tmp/$targetFolder"
			targetFolder="tmp/$targetFolder"
		}
		artifacts.each
		{
			sh "cp -a ${it} ${targetFolder}"
		}
		sh "tar -cvf ${archive} -C tmp"
		archiveArtifacts artifacts: archive
		stash includes: archive, name: 'archive'
	}
}