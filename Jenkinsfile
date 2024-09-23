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
                
                // Ensure checkout from SCM is defined properly
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/soklaymeng/YourRepo.git']]])
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
                    echo "Logging in to DockerHub..."
                    sh "echo ${REGISTRY_PASSWORD} | docker login -u ${REGISTRY_USERNAME} --password-stdin"
                    
                    echo "Pushing Docker image to the registry..."
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
    }
}
