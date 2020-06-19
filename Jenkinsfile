pipeline {
  agent any
  stages {
    stage('deliver') {
                    withCredentials(bindings: [usernamePassword(credentialsId: 'deliver', passwordVariable: 'password', usernameVariable: 'username')]) {
              script {
              def remote = [:]
              remote.name = "server"
              remote.host = "x.x.x.x"
              remote.allowAnyHosts = true
              remote.user = "$username"
              remote.password = "$password"

              sshCommand remote: remote, command: "cd /www && git fetch"
            }}
      }
  }
}