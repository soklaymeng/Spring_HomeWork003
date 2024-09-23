pipeline {
    agent any
    environment {
        DOCKER_FILE = "Dockerfile"
        IMAGE = "mengsoklay/deops-backend"
        TAG = "0.0.0"
        VERSION = "${env.BUILD_ID}" // Corrected environment variable name
    }
    stages {
        stage("Build Image") {
            steps {
                script {
                    echo "Building Next.js Docker image."
                    sh """
                        docker build -f ${DOCKER_FILE} \
                            -t ${IMAGE}:${TAG}.${VERSION} \
                            .
                    """
                }
            }
        }
        
        stage("Push Image to Docker Hub") {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-token', 
                        passwordVariable: 'DOCKER_PASSWORD', 
                        usernameVariable: 'DOCKER_USERNAME'
                    )]) {
                        echo "Logging into Docker Hub"
                        sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                        
                        echo "Pushing Docker image to Docker Hub"
                        sh "docker push ${IMAGE}:${TAG}.${VERSION}"
                    }
                }
            }
        }
        
        stage("Update Kubernetes Manifest in Git") {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'git-token', 
                        passwordVariable: 'GIT_PASSWORD', 
                        usernameVariable: 'GIT_USERNAME'
                    )]) {
                        // Clone the repository
                        git branch: 'main', 
                            credentialsId: 'git-token', 
                            url: 'https://github.com/soklaymeng/Spring_HomeWork003.git'
                        
                        // Diagnostic: Print current directory and list files
                        sh """
                            echo "Current directory: \$(pwd)"
                            echo "Listing files:"
                            ls -la
                        """
                        
                        // Define the path to values.yaml
                        def valuesFilePath = 'values.yaml' // Update if located elsewhere, e.g., 'k8s/values.yaml'
                        
                        // Check if values.yaml exists
                        if (fileExists(valuesFilePath)) {
                            echo "Updating image tag in ${valuesFilePath}"
                            sh "sed -i 's/tag:.*/tag: ${TAG}.${VERSION}/' ${valuesFilePath}"
                        } else {
                            error "File ${valuesFilePath} does not exist."
                        }
                        
                        // Configure Git user
                        sh """
                            git config user.email "mengsoklay2222@gmail.com"
                            git config user.name "soklaymeng"
                        """
                        
                        // Commit and push changes
                        sh """
                            git add ${valuesFilePath}
                            git commit -m "Update image tag to ${TAG}.${VERSION}"
                            git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/soklaymeng/Spring_HomeWork003.git HEAD:main
                        """
                    }
                }
            }
        }
        
        stage("Trigger ArgoCD Sync") {
            steps {
                script {
                    withCredentials([string(
                        credentialsId: 'argocd-token', 
                        variable: 'ARGOCD_TOKEN'
                    )]) {
                        echo "Triggering ArgoCD to synchronize the application"
                        sh """
                            curl -X POST \
                                -H "Authorization: Bearer \$ARGOCD_TOKEN" \
                                -H "Content-Type: application/json" \
                                https://argocd.example.com/api/v1/applications/<app-name>/sync
                        """
                    }
                }
            }
        }
    }
    
    post {
        failure {
            echo "Build or deployment failed."
            // Optional: Add notifications (e.g., Slack, email) here
        }
        success {
            echo "Pipeline completed successfully."
            // Optional: Add success notifications here
        }
    }
}
