pipeline {
    agent any
    environment {
        DOCKER_FILE = "Dockerfile"
        IMAGE = "mengsoklay/deops-backend"
        TAG = "0.0.0"
        VERSION = "${env.BUILD_ID}"
        DOCKER_CREDENTIALS_ID = "dockerhub-token" // Docker Hub credentials
        GIT_REPOSITORY = "https://github.com/soklaymeng/argro-spring.git" // Kubernetes manifest repository
        GIT_BRANCH = "master" // Branch to check out
    }
    stages {
        stage("Clone Spring Boot Project") {
            steps {
                script {
                    echo "Cloning Spring Boot project repository."
                    git url: 'https://github.com/soklaymeng/Spring_HomeWork003.git', branch: "${GIT_BRANCH}"
                }
            }
        }

        stage("Build Image") {
            steps {
                script {
                    echo "Building Spring Boot Docker image."
                    sh "docker build -t ${IMAGE}:${TAG}.${VERSION} -f ${DOCKER_FILE} ."
                }
            }
        }

        stage("Push Image to Registry (Docker Hub)") {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' } // Only proceed if the build was successful
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                        echo "Logging into Docker Hub."
                        sh "docker login -u \$USERNAME -p \$PASSWORD"
                        sh "docker push ${IMAGE}:${TAG}.${VERSION}"
                    }
                }
            }
        }

        stage("Clone Manifest Repository") {
            steps {
                script {
                    echo "Cloning Kubernetes manifest repository."
                    git url: "${GIT_REPOSITORY}", branch: "${GIT_BRANCH}"
                }
            }
        }

        stage("Update Kubernetes Manifest") {
            steps {
                script {
                    echo "Updating Kubernetes deployment manifest with new image tag."
                    // Update the deployment.yaml file with the new Docker image tag
                    sh "sed -i 's|image:.*|image: ${IMAGE}:${TAG}.${VERSION}|' argro-spring/mainifest/deployment.yaml"
                }
            }
        }

        stage("Deploy to Kubernetes") {
            steps {
                script {
                    echo "Deploying updated manifests to Kubernetes."
                    withCredentials([file(credentialsId: 'kubeconfig-credential-id', variable: 'KUBECONFIG')]) {
                        sh "kubectl apply -f argro-spring/mainifest/deployment.yaml"
                        sh "kubectl apply -f argro-spring/mainifest/service.yaml"
                    }
                }
            }
        }
    }
}

