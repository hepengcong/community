pipeline {
  agent none
  stages {
    stage('built') {
      agent {
        docker {
          image 'maven:3.6.3'
          args '-v /root/.m2:/root/.m2'
        }

      }
      steps {
        sh 'mvn clean package'
      }
    }

    stage('test') {
      agent {
        docker {
          image 'maven:3.6.3'
          args '-v /root/.m2:/root/.m2'
        }

      }
      steps {
        sh 'mvn test'
      }
    }

    stage('image') {
      agent {
        dockerfile {
          filename 'Dockerfile'
        }

      }
      steps {
        sh '''cd /var/jenkins_home/workspace/community_master/target
docker build -t communitydemo .'''
      }
    }

    stage('push') {
      agent {
        docker {
          image '50c5ba2ce935'
        }

      }
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
        sshagent(credentials: ['dev_host']) {
          sh 'ssh root@139.196.21.25'
        }

      }
    }

  }
}