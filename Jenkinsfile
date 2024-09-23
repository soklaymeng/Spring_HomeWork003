pipeline {
    agent any
    environment {
        IMAGE = "mengsoklay/deops-backend"
        DOCKER_IMAGE = "${IMAGE}:${BUILD_NUMBER}"
    }
    stages {
    
        stage('checkout') { // Changed 'state' to 'stage'
            steps {
                echo "Running..."
                echo "Running on node = $NODE_NAME"
                echo "Build number is $BUILD_NUMBER"
            }
        }

        stage("build docker image") {
            step {
                echo "Build new docker image"
                sh "docker build -t ${DOCKER_IMAGE} ."
                sh "docker images"
            }
        }

        
    }
}

