pipeline {
  agent {
    docker {
      image 'maven'
      args '-u root -v /root/.m2:/root/.m2'
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
        sh 'ssh root@139.196.21.25'
      }
    }

  }
}