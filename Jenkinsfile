pipeline {
  agent {
    docker {
      args '-v /root/.m2:/root/.m2'
      image 'maven:3.6.3'
    }

  }
  stages {
    stage('built') {
      steps {
        sh 'sh \'mvn -B -DskipTests clean package\''
      }
    }

  }
}