pipeline {
    agent any
    environment {
        COMPOSE_PATH = "docker-compose.yaml"
        IMAGE = "spring-image"
    }
    stages {
        stage('Deploy') {
            steps {
                script {
                    echo "Deploy Spring.js"
                    sh "docker-compose -f ${COMPOSE_PATH} up -d"
                }
            }
        }
    }
}
