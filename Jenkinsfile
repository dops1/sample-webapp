#!/usr/bin/env groovy

node('slave2') {


	docker.image('maven:latest').inside {

		stage 'compile'
		checkout scm
		sh 'mvn clean compile'

		stage 'test'
		sh 'mvn clean test'
		// archive junit results
                archiveArtifacts 'target/surefire-reports/*'

		stage 'assemble war'
		sh 'mvn clean package -DskipTests'

	}

	def myimage = docker.build 'webapp'

	stage 'integration tests'

	// create a test .properties file
	writeFile file: 'test.properties', text: 'foo=bar'

	// run the image
	myimage.withRun('-v $(pwd):/data -e APP_CONFIG_PATH=/data -p 8082:8080'){

        sleep 5

		httpRequest requestBody: 'foo=bar', url: 'http://10.209.20.77:8082/app/configview.jsp?name=test.properties'

	}

	// Deploy to prod using the deploy-tool
}
