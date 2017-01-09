#!/usr/bin/env groovy

def appName = "sample-webapp"
def appVersion = env.BUILD_NUMBER
def dockerTag = "${appName}-${appVersion}"

node {

    docker.image('maven:latest').inside {
        checkout scm

        // write the current docker image tag to the app sources
        writeFile file: "src/main/java/webapp/.version", text: dockerTag

        stage 'Compile'
        sh 'mvn compile'

        stage 'Unit Test'
        sh 'mvn test'

        //archive the junit reports
        junit "target/surefire-reports/*.xml"

        stage 'Assemble war'
        sh 'mvn package -Dmaven.test.skip=true'
    }

    stage 'Build Image'
    def myimage = docker.build(dockerTag)


    stage 'integration / performance tests'

    def tests = [
            int : {
                // create a test .properties file
                writeFile file: 'test.properties', text: 'foo=bar'

                // run the image
                myimage.withRun('-v $(pwd):/data -e APP_CONFIG_PATH=/data -p 8082:8080') {

                    sleep 5

                    httpRequest requestBody: 'foo=bar', url: 'http://localhost:8082/app/configview.jsp?name=test.properties'

                }
            },
            perf: {
                performanceTest(endpoint: "http://localhost:8082/app")
            }
    ]

    parallel tests

    // deploy to production

    stage "Deploying to prod"

    try {
        timeout(3) {
            input message: 'Deploy to prod ?'
        }
    } catch (all) {
        currentBuild.result = 'ABORTED'
    }

    docker.image('dops1/deploy-tool').inside('-w /tmp') {
        sh 'cd /usr/src/app && thor app:deploy'
    }


}