node {

	stage 'compile'

	// pull code
	// ?

	checkout scm

	docker.image('maven:latest').inside {
		sh 'mvn clean compile'

		stage 'test'

		sh 'mvn clean test'


		stage 'docker build'

		sh 'mvn clean package'

	}



	// Produces: target/*.war


	def myimage = docker.build 'webapp'


	stage 'integration tests'

	// create a test .properties file
	writeFile file: 'test.properties', text: 'foo=bar'

	// run the image

	myimage.withRun('-v $(pwd):/data -e APP_CONFIG_PATH=/data -p 8082:8080'){

		httpRequest requestBody: 'foo=bar', url: 'http://localhost:8082/app/configview.jsp?name=test.properties'

	}


	// generate test.properties file

	// somehow test that http://localhost:PORT/app/...... returns the contents of the file


	// done
}