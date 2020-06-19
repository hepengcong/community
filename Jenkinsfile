pipeline {
  agent any
  stages {
    stage('dev') {
      steps {
        withCredentials(bindings: [usernamePassword(credentialsId: 'deliver', passwordVariable: 'password', usernameVariable: 'username')]) {
          script {
            def remote = [:]
            remote.name="hel"
            remote.host="139.196.21.25"
            remote.allowAnyHosts = true
            remote.user=$username
            remote.password=$password
          }

          sshCommand(remote: hel, command: 'ls')
        }

      }
    }

  }
}