pipeline {
    agent any
    environment {
        DOCKER_FILE = "Dockerfile"
        IMAGE = "mengsoklay/deops-backend"
        TAG = "0.0.0"
        VERSION = "${env.BUILD_ID}"
        KUBECONFIG = '/path/to/your/kubeconfig'  // Path to the Kubeconfig file on Jenkins server
    }
    stages {
        stage("Build Image") {
            steps {
                script {
                    echo "Building Spring Boot Docker image."
                    sh "docker build -t ${IMAGE}:${TAG}.${VERSION} ."
                }
            }
        }

        stage("Push image to registry (Docker Hub)") {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' } // Only proceed if the build was successful
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-token', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                        echo "Login to Docker Hub"
                        sh "docker login -u $USERNAME -p $PASSWORD"
                        sh "docker push ${IMAGE}:${TAG}.${VERSION}"
                    }
                }
            }
        }

        stage("Update Kubernetes Manifest") {
            steps {
                script {
                    echo "Updating Kubernetes deployment manifest with new image tag."
                    // Update the deployment.yaml file with the new Docker image tag
                    sh "sed -i 's|image:.*|image: ${IMAGE}:${TAG}.${VERSION}|' kubernetes/deployment.yaml"
                }
            }
        }

        stage("Deploy to Kubernetes") {
            steps {
                script {
                    echo "Deploying updated manifests to Kubernetes."
                    // Apply the updated deployment.yaml and service.yaml to the Kubernetes cluster
                    withCredentials([file(credentialsId: 'kubeconfig-credential-id', variable: 'KUBECONFIG')]) {
                        sh "kubectl apply -f kubernetes/deployment.yaml"
                        sh "kubectl apply -f kubernetes/service.yaml"
                    }
                }
            }
        }
    }
}
