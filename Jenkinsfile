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
      post{
        failure {
          script{
            sh "exit 1"
            error "Stage 1 Failed, exiting now..."
          }
        }
      }
    }

    stage('Docker Build Sandwichbar Backend') {
      steps {
        sh 'docker rm -f sandwich_db'
        sh 'docker rm -f sandwich_backend'
        sh 'docker compose up -d'
      }
      post{
        failure {
          script{
            sh "exit 1"
            error "Stage 2 Failed, exiting now..."
          }
        }
      }
    }

  }
}