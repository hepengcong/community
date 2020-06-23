pipeline {
  agent {
    docker {
      image 'maven'
      args '-u root -v /root/.m2:/root/.m2'
    }

  }
  stages {
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
      steps {
        script {
          def remote = [:]
          remote.name = 'test'
          remote.host = '139.196.21.25'
          remote.user = 'root'
          remote.password = 'Hpchpc123'
          remote.allowAnyHosts = true
        }

        sshCommand(remote: remote, command: 'ls')
        withCredentials(bindings: [usernamePassword(credentialsId: 'deliver', passwordVariable: 'password', usernameVariable: 'username')]) {
          sh '''pwd
whoami
ssh root@139.196.21.25 -u $username -p $password'''
          sh 'ip add'
        }

      }
    }

  }
}