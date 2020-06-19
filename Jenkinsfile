pipeline {
  agent none
  stages {
    stage('built') {
      environment {
        maven = '-v /root/.m2:/root/.m2'
      }
      steps {
        sh 'mvn clean package'
      }
    }

    stage('test') {
      environment {
        maven = '-v /root/.m2:/root/.m2'
      }
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
        withCredentials(bindings: [usernamePassword(credentialsId: 'harbor', passwordVariable: 'pass', usernameVariable: 'user')]) {
          sh 'docker login registry.vena.network -u $user -p $pass'
          sh 'docker tag communitydemo registry.vena.network/xbaas/communitydemo '
          sh 'docker push registry.vena.network/xbaas/communitydemo'
        }

      }
    }

    stage('deliver') {
      steps {
        sshagent(credentials: ['dev_host']) {
          sh 'ssh root@139.196.21.25'
        }

      }
    }

  }
}