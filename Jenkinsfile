pipeline {
    agent any
    environment {
        COMPOSE_PATH = "docker-compose.yaml"
        IMAGE = "spring-image"
    }
    stages {
        stage('Cleanup') {
            steps {
                script {
                    echo "Stopping and removing container"
                    sh "docker compose -f ${COMPOSE_PATH} down"
                }
            }
        }
        stage('Deploy') { // Changed 'state'
            steps {
                script {
                    echo "Deploy Spring Boot"
                    sh "docker compose -f ${COMPOSE_PATH} up -d --build" // Adjusted 'docker compose' to 'docker-compose' for consistency
                }
            }
        }
    }
}
