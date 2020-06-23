pipeline {
  agent {
    docker {
      image 'maven'
      args '-u root -v /root/.m2:/root/.m2'
    }

  }
  stages {

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
      steps {
       script  {
                  def remote = [:]
                  remote.name = 'test'
                  remote.host = '139.196.21.25'
                  remote.user = 'root'
                  remote.password = 'Hpchpc123'
                  remote.allowAnyHosts = true
             withCredentials(bindings: [usernamePassword(credentialsId: 'harbor', passwordVariable: 'pass', usernameVariable: 'user')]) {
                      sshCommand remote: remote, command:"docker login registry.vena.network -u $user -p $pass"
                      sshCommand remote: remote, command:"docker rm -f testdemo"
                      sshCommand remote: remote, command:"docker run -d -p 12300:8080 --name testdemo registry.vena.network/xbaas/communitydemo:latest"

                    }

              }

      }
    }

  }
}