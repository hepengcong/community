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
        sh 'mvn clean package'
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
        withCredentials(bindings: [usernamePassword(credentialsId: 'harbor', passwordVariable: 'pass', usernameVariable: 'user')]) {
          sh 'docker login registry.vena.network -u $user -p $pass'
          sh 'docker tag communitydemo registry.vena.network/xbaas/communitydemo '
          sh 'docker push registry.vena.network/xbaas/communitydemo'
        }

      }
    }

 stage('deliver') {
        agent any
        environment {
          PATH = '/usr/bin'
        }
        steps {
          sshagent(credentials: ['dev_host']) {
            withCredentials(bindings: [usernamePassword(credentialsId: 'deliver', passwordVariable: 'password', usernameVariable: 'username')]) {
             def remote = [:]
                      remote.name = "dev_serve"
                      remote.host = "139.196.21.25"
                      remote.allowAnyHosts = true
                      remote.user = "$username"
                      remote.password = "$password"

            sshCommand remote: remote, command: "ls"
               withCredentials(bindings: [usernamePassword(credentialsId: 'harbor', passwordVariable: 'pass', usernameVariable: 'user')]) {
               sh 'docker login registry.vena.network -u $user -p $pass'
               }
            }

          }

        }
      }


  }
}