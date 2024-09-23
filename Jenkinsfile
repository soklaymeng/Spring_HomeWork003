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
                // This is where you might want to add your checkout scm step
                // checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building new Docker image"
                sh "docker build -t ${DOCKER_IMAGE} ."
                sh "docker images"
            }
        }
    }
}

