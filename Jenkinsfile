pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER}"
   sh "${mvnHome}/bin/mvn package"'''
      }
    }

  }
}