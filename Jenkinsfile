pipeline {
    agent any
    environment {
        IMAGE = "mengsoklay/deops-backend"
        DOCKER_IMAGE = "${IMAGE}:${BUILD_NUMBER}"
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Running..."
                echo "Running on node = ${env.NODE_NAME}"
                echo "Build number is ${env.BUILD_NUMBER}"
                checkout scm  // Added the checkout step
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building new Docker image"
                sh "docker build -t ${DOCKER_IMAGE} ."
                sh "docker images"
            }
        }

        stage('Push to Registry') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub_credential', usernameVariable: 'REGISTRY_USERNAME', passwordVariable: 'REGISTRY_PASSWORD')]) {
                    sh "echo ${REGISTRY_PASSWORD} | docker login -u ${REGISTRY_USERNAME} --password-stdin"
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
    }
}
