pipeline {
    agent any
    environment {
        IMAGE = "mengsoklay/deops-backend"
        DOCKER_IMAGE = "${IMAGE}:${BUILD_NUMBER}"
        DOCKER_HUB_CREDENTIAL = "dockerhub_token"
    }
    stages {

        // stage("cleanup") {
        //     steps {
        //         sh " mvn clean install"
        //     }
        // }

        stage ("build") {
            steps {
                sh " docker build -t ${DOCKER_IMAGE} ."
                sh " docker ps | grep -t ${IMAGE} "
            }
        }
        
    }
}
