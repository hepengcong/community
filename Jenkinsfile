pipeline {
  agent {
    docker {
      args '-v /root/.m2:/root/.m2'
      image 'maven:3.6.3'
    }

     stage('deliver') {
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

                }

              }

            }
          }
}
