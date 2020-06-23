pipeline {
  agent {
    docker {
      image 'maven'
      args '-u root -v /root/.m2:/root/.m2'
    }
    node {
      def remote = [:]
      remote.name = 'hel'
      remote.host = '139.196.21.25'
      remote.user = 'root'
      remote.password = 'Hpchpc123'
      remote.allowAnyHosts = true
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

      steps {
            sshCommand remote: hel, command: "ls"
        }

      }


  }
}