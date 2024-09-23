pipeline {
    agent any
    environment {
        COMPOSE_PATH = "docker-compose.yaml"
        IMAGE = "spring-image"
    }
    stages {
    
        stage('checkout') { // Changed 'state' to 'stage'
            steps {
                echo "Running..."
                echo "Running on node = $NODE_NAME"
                echo "Build number is $BUILD_NUMBER"
            }
        }

        
    }
}

