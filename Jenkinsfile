node{
    def app
    def name = "tk-be-profile-service"
    def port = "9091"
    def network = "tk-be-network"
    def eurekaServer = "http://tk-be-discovery-service:8761/eureka/"
    def build = "${env.BUILD_NUMBER}"
    def version = "1"
    def imageName = "chippermitrais/$name"
    def image =  imageName +':'+ version +'.'+ build
    def mvnHome = tool name: 'maven-default', type: 'maven'
    def mvnCMD = "${mvnHome}/bin/mvn"
    def remote = [:]
    remote.name = 'chippermitrais'
    remote.host = 'chippermitrais.ddns.net'
    remote.allowAnyHosts = true

    stage('SCM Checkout') {
        checkout scm
    }
    stage('Build Source Code') {
        withCredentials([
        usernamePassword(credentialsId: 'team6-dbAuth', passwordVariable: 'dbAuthPassword', usernameVariable: 'dbAuthUser')]) {
            sh "${mvnCMD} -B -DskipTests clean verify -Deureka.client.serviceUrl.defaultZone=http://chippermitrais.ddns.net:8761/eureka/ -Dspring.datasource.url=jdbc:postgresql://chippermitrais.ddns.net:5432/tk_db_profile_service -Dspring.datasource.username=$dbAuthUser -Dspring.datasource.password=$dbAuthPassword"
        }
    }
    stage('Build Docker Image') {
        app = docker.build(image)
    }
    stage('Push Image') {
        withCredentials([usernamePassword(credentialsId: 'team6-dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
            sh "docker login -u $dockerHubUser -p $dockerHubPassword"
            sh "docker push $image"
        }
    }
    stage('Remove Existing Docker Image & Run Application') {
        withCredentials([
            usernamePassword(credentialsId: 'team6-dbAuth', passwordVariable: 'dbAuthPassword', usernameVariable: 'dbAuthUser'),
            sshUserPrivateKey(credentialsId: 'team6-chippermitrais', keyFileVariable: 'sshkey', usernameVariable: 'sshuname'),
            string(credentialsId: 'team6-token-secret', variable: 'tokenSecret')
            ]) {
                remote.user = env.sshuname
                remote.identityFile = env.sshkey
                def cmd = "docker ps -aqf name=$name"
                def container = sshCommand remote: remote, command: cmd

                if (container) {
                    echo 'Existing container found!!! Deleting...'
                    sshCommand remote: remote, command: "docker stop \$($cmd)"
                    sshCommand remote: remote, command: "docker rm \$($cmd)"
                    echo 'Done.'
                }

                sshCommand remote: remote, command: "docker images $imageName -q | xargs --no-run-if-empty docker rmi -f"
				
                sshCommand remote: remote, command: "docker run --name $name -p $port:$port --network $network -e EUREKA_SERVER_URL=$eurekaServer -e DB_URL=jdbc:postgresql://chipper-db:5432/tk_db_profile_service -e DB_USERNAME=$dbAuthUser -e DB_PASSWORD=$dbAuthPassword -e TOKEN_SECRET=$tokenSecret --restart always -d $image"
        }
    }
}