#!groovy
import hudson.tasks.test.AbstractTestResultAction


def branchName = env.BRANCH_NAME
def Ip4_0Address = "172.18.1.77"
def branchIpAddress = "172.18.1.153"
def Ip4_1Address = "172.18.1.65"

@NonCPS
def testStatuses() {
    def testStatus = ""
    AbstractTestResultAction testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
    if (testResultAction != null) {
        def total = testResultAction.totalCount
        def failed = testResultAction.failCount
        def skipped = testResultAction.skipCount
        def passed = total - failed - skipped
        testStatus = "Test Status:\n  Passed: ${passed}, Failed: ${failed} ${testResultAction.failureDiffString}, Skipped: ${skipped}"
println testStatus+"....println"
//slackSend color: "#FF0000",testStatus
        if (failed == 0) {
            currentBuild.result = 'SUCCESS'
        }
    }
    else {
        slackSend color: "#FF0000", message: " AbstractTestResultAction result is empty "+ testStatus.isEmpty()+"......."
    }
    return testStatus
}
def test = testStatuses()
def notify(status) {
    slackSend channel: "#jenkinsbuilds",
            color: '#2eb886',
            message: "${status}",
            mess: testStatuses(),
            tokenCredentialId: 'umkdE5giXctXeuyJD0c4PQao',
            token: 'umkdE5giXctXeuyJD0c4PQao'
}

def trigg(String branchName) {
    if (branchName.equals('main')) {
        return '5 5 * * *'
    }
    if (branchName.equals('master')) {
        return '4 10 * * *'
    }
}

pipeline {
    agent any

//    environment {
//        AWS_ACCESS_KEY_ID     = credentials('aws-key')
//    }

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(daysToKeepStr: '20', artifactNumToKeepStr: '20'))
    }
    tools {
        gradle 'default'
    }

    triggers {
        cron(trigg(branchName))
    }
    stages {
        stage('Configure') {  // build the box
            steps {
                script {
                    if (branchName.equals("master")) {
//                         sh "chmod +x ./shFile/setups77.sh"
//                         sh "./shFile/setups77.sh"
                    }
                }
            }
        }
        stage('Build') {  // Compile and do unit testing
            steps {
                script {

                 //   if (branchName.equals("master")) {

                       // withCredentials([string(credentialsId: 'aws-key', variable: 'AWS_ACCESS_KEY_ID')]) {
                    withCredentials([usernamePassword(credentialsId: 'a1de3395-403d-4e5a-95d7-ea6647d17a9e', passwordVariable: 'password', usernameVariable: 'username'), string(credentialsId: 'aws-key', variable: 'slacktocken')])
                            {// some block
                    echo "${slacktocken}"+"....1....slacktocken........."
                    echo "${username}"+"....1....AWS_ACCESS_KEY_ID"
                    echo "${password}"+"...1.....AWS_ACCESS_KEY_ID"
                        // notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL}" )
                      //  slackSend color: "#FF0000", message: " Build Started...:- "
//                         sh "gradle clean runTestsParallel -PbaseUrl=\"${Ip4_1Address}\""

                        sh 'gradle clean test -Dawskey=\"${username}\" -Dslacktocken=\"${slacktocken}\"'
                                println("${slacktocken}"+"....2...slacktocken.........")
                        echo "${username}"+"....2....AWS_ACCESS_KEY_ID"
                        echo "${password}"+"...2.....AWS_ACCESS_KEY_ID"
                    }
//                    else {
//
//                        if (branchName.equals("main")) {
//                                notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL} ")
//                            slackSend color: "#FF0000", message: " Build Started...:- "
//
////                            sh "gradle clean runTestsParallel -PbaseUrl=\"${Ip4_0Address}\""
//                            set +x
//                            sh "gradle clean runTests -Pawskey=\"${AWS_ACCESS_KEY_ID}\""
//                            echo "${AWS_ACCESS_KEY_ID}"+"........AWS_ACCESS_KEY_ID"
//                        } else {
////                             sh "gradle clean runTestsParallel -PbaseUrl=\"${branchIpAddress}\""
//                            notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL} ")
//                            slackSend color: "#FF0000", message: " Build Started...:- "
//                            echo "${AWS_ACCESS_KEY_ID}"+"........AWS_ACCESS_KEY_ID"
//                            set +x
//                            sh "gradle clean runTests -Pawskey=\"${AWS_ACCESS_KEY_ID}\""
//                        }
//                    }
                }
            }
        }
    }
//    post {
//        always {
//            step([$class: 'Publisher', reportFilenamePattern: 'build/reports/tests/runTests/testng-results.xml'])
//            script {
//              //  slackSend  message: "${test}"
//               // slackSend testStatuses()
//                if (branchName.equals("master") || branchName.equals("main")) {
////                    publishHTML([allowMissing         : false,
////                                 alwaysLinkToLastBuild: true,
////                                 keepAll              : false,
////                                 reportDir            : 'build/reports/tests/runTests/',
////                                 reportFiles          : 'index.html',
////                                 reportName           : 'HTML Report',
////                                 reportTitles         : ''])
//
//
////                    AbstractTestResultAction testResult1 =  currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
////                    if (testResult1 != null) {
////                        echo "Tests1234: ${testResult1.failCount} / ${testResult1.failureDiffString} failures of ${testResult1.totalCount}.\n\n"
////                    }
//
//               //     slackSend color: "#FF0000", message: " AbstractTestResultAction result  in post is 12343empty ,,,test.....  "+ test.isEmpty()"......."
////                    slackSend color: "#FF0000", message: " post is 12343empty ,,,test.....  "+ testStatuses().toString()
////                    slackSend  message: "${test}"
////                      slackSend color: "#FF0000", message: " Build completed and  result:- ${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL} ......${currentBuild.result}.==============${env.currentResult}"
////                      notify("${env.JOB_NAME}/${env.BUILD_NUMBER} ...build...  + ${currentBuild.result}.................."+test)
//                }
//
//
//            }
//            cleanWs notFailBuild: true
//        }
//    }
}
