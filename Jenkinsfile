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
        docker build -t sandwich .
        docker run --name sandwichbarApp --network sandwichbar_network -p 8080:8080 sandwich
      }
    }
  }
}