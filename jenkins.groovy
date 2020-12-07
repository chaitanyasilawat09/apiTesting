def branchName = env.BRANCH_NAME
def Ip4_0Address = "172.18.1.77"
def branchIpAddress = "172.18.1.153"
def Ip4_1Address = "172.18.1.65"

def notify(status) {
     slackSend channel: "#jenkinsbuilds",
             color: '#2eb886',
             message: "${status}",
             tokenCredentialId: 'umkdE5giXctXeuyJD0c4PQao'
}

def trigg(String branchName) {
    if (branchName.equals('main')) {
        return '* 5 * * *'
    }
    if (branchName.equals('master')) {
        return '* 10 * * *'
    }
}

pipeline {
    agent any

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
                    if (branchName.equals("master0")) {
//                         sh "chmod +x ./shFile/setups77.sh"
//                         sh "./shFile/setups77.sh"
                    } else {
                        if (branchName.equals("masterTest")) {
//                             sh "chmod +x ./shFile/setups65restore.sh"
//                             sh "./shFile/setups65restore.sh"
                        } else {
//                             sh "chmod +x ./shFile/setups182.sh"
                            //sh "./shFile/setups182.sh"
                        }
                    }
                }
            }
        }
        stage('Build') {  // Compile and do unit testing
            steps {
                script {
                    if (branchName.equals("master")) {
                         notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started")
//                         sh "gradle clean runTestsParallel -PbaseUrl=\"${Ip4_1Address}\""
                        sh "gradle clean test"

                    } else {

                        if (branchName.equals("masterTest")) {
                                notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started")
//                            sh "gradle clean runTestsParallel -PbaseUrl=\"${Ip4_0Address}\""
                            sh "gradle clean runTestsParallel"
                        } else {
//                             sh "gradle clean runTestsParallel -PbaseUrl=\"${branchIpAddress}\""
                            sh "gradle clean runTestsParallel"
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            step([$class: 'Publisher', reportFilenamePattern: 'build/reports/tests/runTestsParallel/testng-results.xml'])
            script {
                if (branchName.equals("master") || branchName.equals("main")) {
                    publishHTML([allowMissing         : false,
                                 alwaysLinkToLastBuild: true,
                                 keepAll              : false,
                                 reportDir            : 'build/reports/tests/runTestsParallel/',
                                 reportFiles          : 'index.html',
                                 reportName           : 'HTML Report',
                                 reportTitles         : ''])

                      slackSend color: "#FF0000", message: " Build completed and result:-"
//                      notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build " + currentBuild.result)
                }
            }
            cleanWs notFailBuild: true
        }
    }
}