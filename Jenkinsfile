pipeline {
    agent any
    environment {
        IMAGE = "mengsoklay/deops-backend"           // Docker image name
        VERSION = "${env.BUILD_ID}"                   // Version from Jenkins build ID
        DOCKER_CREDENTIALS_ID = 'dockerhub-token'     // Docker Hub credentials in Jenkins
        GIT_CREDENTIALS_ID = 'git-token'              // GitHub credentials for manifest repo
        GIT_REPO_URL = 'https://github.com/soklaymeng/argro-spring.git'  // Kubernetes manifest repo
        GIT_BRANCH = 'master'                         // GitHub branch
        DEPLOYMENT_FILE = 'manifest/deployment.yaml'   // Path to deployment.yaml file in the manifest repo
        SERVICE_FILE = 'manifest/service.yaml'         // Path to service.yaml file in the manifest repo
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker image..."
                    sh "docker build -t ${IMAGE}:${VERSION} ."
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"
                        sh "docker push ${IMAGE}:${VERSION}"
                    }
                }
            }
        }

        stage('Clone Manifest Repository') {
            steps {
                script {
                    echo "Cloning manifest repository..."
                    sh '''
                        if [ -d "argro-spring" ]; then
                            rm -rf argro-spring
                        fi
                    '''
                    git branch: GIT_BRANCH, credentialsId: GIT_CREDENTIALS_ID, url: GIT_REPO_URL
                }
            }
        }

        stage('Update Kubernetes Manifest') {
            steps {
                script {
                    echo "Updating image in deployment manifest..."
                    sh """
                    sed -i 's|image: .*|image: ${IMAGE}:${VERSION}|' argro-spring/${DEPLOYMENT_FILE}
                    """
                }
            }
        }

        stage('Commit and Push Manifest Changes') {
            steps {
                script {
                    dir('argro-spring') {
                        withCredentials([usernamePassword(credentialsId: GIT_CREDENTIALS_ID, usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
                            sh '''
                                git config --global user.email "mengsoklay2222@gmail.com"
                                git config --global user.name "soklaymeng"
                                git add ${DEPLOYMENT_FILE}
                                git commit -m "Update image to ${IMAGE}:${VERSION}"
                                git push https://${GIT_USER}:${GIT_PASS}@github.com/soklaymeng/argro-spring.git
                            '''
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo "Deploying to Kubernetes..."
                    sh '''
                        kubectl apply -f argro-spring/${DEPLOYMENT_FILE}
                        kubectl apply -f argro-spring/${SERVICE_FILE}
                    '''
                }
            }
        }
    }
}
