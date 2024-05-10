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
        sh 'docker rm -f sandwich-db'
        sh 'docker rm -f sandwich-backend'
        sh 'docker compose up -d'
      }
    }

  }
}