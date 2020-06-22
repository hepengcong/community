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
      agent {
        docker {
          image 'jenkins'
          args '-u root -v /var/jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock'
        }

      }
      steps {
        withCredentials(bindings: [usernamePassword(credentialsId: 'deliver', passwordVariable: 'password', usernameVariable: 'username')]) {
          sh 'ssh root@139.196.21.25 -u username -p password'
        }

      }
    }

  }
}