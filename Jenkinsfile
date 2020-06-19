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
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('image') {
      steps {
        sh 'docker build -t communitydemo .'
      }
    }

    stage('push') {
      steps {
        sh 'docker login registry.vena.network'
        input(message: 'Username', id: '1', submitterParameter: 'xbaas')
        input(message: 'Password', id: '2', submitterParameter: 'XBaaS@2020')
      }
    }

  }
}