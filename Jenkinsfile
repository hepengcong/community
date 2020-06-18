pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('built') {
      steps {
        sh 'sh \'clean package\''
      }
    }

  }
}