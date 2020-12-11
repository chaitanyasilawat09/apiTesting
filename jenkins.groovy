def branchName = env.BRANCH_NAME

def notify(status) {
     slackSend channel: "#jenkinsbuilds",
             color: '#2eb886',
             message: "${status}",
             tokenCredentialId: 'umkdE5giXctXeuyJD0c4PQao',
             token: 'umkdE5giXctXeuyJD0c4PQao'
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
                    if (branchName.equals("master")) {
//                         sh "chmod +x ./shFile/setups77.sh"
//                         sh "./shFile/setups77.sh"
                    } else {
                        if (branchName.equals("main")) {
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
                         notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL}" )
                        slackSend color: "#FF0000", message: " Build Started...:- "
//                         sh "gradle clean runTestsParallel -PbaseUrl=\"${Ip4_1Address}\""
                        sh "gradle clean runTests"

                    } else {

                        if (branchName.equals("main")) {
                                notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL} ")
                            slackSend color: "#FF0000", message: " Build Started...:- "
//                            sh "gradle clean runTestsParallel -PbaseUrl=\"${Ip4_0Address}\""
                            sh "gradle clean runTests"
                        } else {
//                             sh "gradle clean runTestsParallel -PbaseUrl=\"${branchIpAddress}\""
                            notify("${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL} ")
                            slackSend color: "#FF0000", message: " Build Started...:- "
                            sh "gradle clean runTests"
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
//                    publishHTML([allowMissing         : false,
//                                 alwaysLinkToLastBuild: true,
//                                 keepAll              : false,
//                                 reportDir            : 'build/reports/tests/runTestsParallel/',
//                                 reportFiles          : 'index.html',
//                                 reportName           : 'HTML Report',
//                                 reportTitles         : ''])


//                    AbstractTestResultAction testResult1 =  currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
//                    if (testResult1 != null) {
//                        echo "Tests1234: ${testResult1.failCount} / ${testResult1.failureDiffString} failures of ${testResult1.totalCount}.\n\n"
//                    }

                      slackSend color: "#FF0000", message: " Build completed and  result:- ${env.JOB_NAME}/${env.BUILD_NUMBER} build started /${env.Build_URL} ......${currentBuild.result}.==============${env.currentResult}"
                      notify("${env.JOB_NAME}/${env.BUILD_NUMBER} ...build...  + ${currentBuild.result}")
                }

            }
            cleanWs notFailBuild: true
        }
    }
}