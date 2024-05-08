pipeline {
  agent any
  stages {
    stage('Maven Build Sandwichbar Backend') {
      steps {
        sh 'mvn -B -DskipTests clean install'
      }
    }

    stage('Test Sandwichbar Backend') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Docker Build Sandwichbar Backend') {
      steps {
        sh 'docker build -t sandwich-backend .'
        sh 'docker rm -f sandwich-backend'
        sh 'docker run --name sandwich-backend --network sandwichbar_network -dp 8080:8080 sandwich-backend'
      }
    }

    stage('Docker Build Sandwichbar Frontend') {
          steps {
            sh 'docker build -t sandwich-frontend .'
            sh 'docker rm -f sandwich-frontend'
            sh 'docker run --name sandwich-frontend --network sandwichbar_network -dp 3030:3030 sandwich-frontend'
          }
        }
  }
}