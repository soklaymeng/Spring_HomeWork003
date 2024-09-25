pipeline {
    agent any
    environment {
        IMAGE = "mengsoklay/deops-backend"
        DOCKER_IMAGE = "${IMAGE}:${BUILD_NUMBER}"
        DOCKER_HUB_CREDENTIAL = "dockerhub_token"
    }
    stages {

        stage("cleanup") {
            steps {
                // sh " mvn clean install"
                sh " docker image prune -a "
            }
        }

        stage ("build") {
            steps {
                echo "Hello world !!"
                sh " docker build -t ${DOCKER_IMAGE} ."
                sh " docker images | grep -i ${IMAGE} "
            }
        }

        stage ("push image to docker hub") {
            steps {
                script {
                     withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIAL, passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                      sh 'echo "${DOCKER_PASS} ${DOCKER_USER}" '
                      sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    }
                    echo "🚀 Pushing the image to Docker hub"
                    sh 'docker push ${DOCKER_IMAGE}'
                }
               
            }
        }
        
        
    }
}
