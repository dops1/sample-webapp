#!/usr/bin/env groovy

def appName = "sample-webapp"
def appVersion = env.BUILD_NUMBER
def dockerTag = "${appName}-${appVersion}"

node {

    docker.image('maven:latest').inside {
        checkout scm

        writeFile file: "src/main/java/webapp/.version", text: dockerTag

        stage 'Unit Test'
        sh 'mvn surefire:test'

        stage 'Package'
        sh 'mvn package -Dmaven.test.skip=true'
    }

    stage 'Build Image'
    def appImage = docker.build(dockerTag)


    stage 'Test image'

    writeFile file: "testData.properties", text: """
prop1=value1
test=foo
"""
    appImage.withRun("-e 'APP_CONFIG_PATH=/data' -v ${pwd()}:/data -p 8082:8080") {
        sleep 3
        httpRequest url: 'http://localhost:8082/app/config.jsp', validResponseCodes: '200', validResponseContent: 'testData.properties'
    }

    // TODO
    // push the image
    // deploy it to a CI environment
    // test the deployment succeeded
    // send notification to Slack
    // capture the test report from the junit tests
}