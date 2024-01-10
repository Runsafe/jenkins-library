#!/usr/bin/env groovy

/**
 * Download artefact dependenciess to the build
 */
def call(String plugin, String dependencies, String artifacts, ArrayList<Map<String, String>> mappedArtifacts = [])
{
	// Using manual checkout to skip when not needed
	checkout scm
	
	// Download dependencies from jenkins
	def classPath = prepareDependencies dependencies
	
	// Build plugin
	sh "ant -f ant.xml ${classPath}"
	
	// Log java warnings
	recordIssues enabledForFailure: true, tool: java(), unhealthy: 10

  // Build plugin archive
	def archive = "${plugin}.tar"
	dir('artifacts')
	{
		sh "rm -rf tmp ${archive}"
		dir('tmp')
		{
			// Copy artifacts into root folder
			artifacts.split(',').each
			{
				sh "cp -a ../../${it} ."
			}

			// Copy artifacts into subdirectories
			mappedArtifacts.each
			{
				sh "mkdir -p ${it.dest}"
				sh "cp -a ../../${it.src} ${it.dest}/"
			}

			// Fingerprint artifacts
			fingerprint "**"
		}

		// Create tarball
		sh "tar -cvf ${archive} -C tmp ."
		
		// Fingerprint tarball
		fingerprint archive

		// Upload tarball to jenkins
		archiveArtifacts artifacts: archive

		// Stash tarball for deployment to test server
		stash includes: archive, name: 'archive'
	}
}