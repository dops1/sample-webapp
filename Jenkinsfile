#!/usr/bin/env groovy

node {


	docker.image('maven:latest').inside {

		stage 'compile'
		checkout scm
		sh 'mvn clean compile'

		stage 'test'
		sh 'mvn clean test'
		// archive junit results

		stage 'assemble war'
		sh 'mvn clean package'

	}

	def myimage = docker.build 'webapp'

	stage 'integration tests'

	// create a test .properties file
	writeFile file: 'test.properties', text: 'foo=bar'

	// run the image
	myimage.withRun('-v $(pwd):/data -e APP_CONFIG_PATH=/data -p 8082:8080'){

		httpRequest requestBody: 'foo=bar', url: 'http://localhost:8082/app/configview.jsp?name=test.properties'

	}

	// Deploy to prod using the deploy-tool
}
