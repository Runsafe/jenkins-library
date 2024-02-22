#!/usr/bin/env groovy

/**
 * Download artefact dependenciess to the build
 */
String call(String plugins)
{
	Set refs = []
	// Make sure we get fresh files
	sh 'rm -rf plugins'
	dir('plugins')
	{
		// Download the framework
		copyArtifacts(projectName: '/Runsafe/Framework/master', filter:'Framework.tar', optional: false)
		sh 'tar -xvf Framework.tar'
		refs.add('-Drunsafe.dir=plugins/runsafe')
		refs.add('-Dlib.dir=plugins/runsafe')
		if (plugins)
		{
			plugins.split(',').each
			{
				// Download the plugin
				copyArtifacts(projectName: "/Runsafe/${it}/master", filter:"${it}.tar", optional: false)
				sh "tar -xvf ${it}.tar"
				refs.add("-D${it}.dir=plugins")
			}
		}
		// Record fingerprints of used dependencies
		fingerprint '**/*'
	}
	return refs.join(' ')
}