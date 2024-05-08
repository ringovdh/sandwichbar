pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean install'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Docker Build') {
      steps {
        sh 'docker build -t sandwich .'
        sh 'docker rm -f sandwichbarApp'
        sh 'docker run --name sandwichbarApp --network sandwichbar_network -dp 8080:8080 sandwich'
      }
    }
  }
}