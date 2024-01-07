#!/usr/bin/env groovy

/**
 * Download artefact dependenciess to the build
 */
String call(String plugins = '')
{
	Set refs = []
	dir('plugins')
	{
		copyArtifacts(projectName: '/Runsafe/Framework/master', filter:'framework.tar', optional: false);
		sh 'tar -xvf framework.tar'
		refs.add('-Drunsafe.dir=plugins/runsafe')
		refs.add('-Dlib.dir=plugins/runsafe')
		if (dependencies)
		{
			dependencies.split(',').each
			{
				copyArtifacts(projectName: "/Runsafe/${it}/master", filter:"${it}.tar", optional: false);
				sh "tar -xvf ${it}.tar"
				refs.add("-D${it}.dir=plugins")
			}
		}
		fingerprint '**/*'
	}
	return refs.join(' ')
}