pipeline {
    agent any
    environment {
        IMAGE = "mengsoklay/deops-backend"
        DOCKER_IMAGE = "${IMAGE}:${BUILD_NUMBER}"
        DOCKER_HUB_CREDENTIAL = "dockerhub-token"
        MANIFEST_REPO = "manifest-repo"
        GIT_MANIFEST_REPO = "https://github.com/soklaymeng/argro-spring.git"
    }
    stages {

        stage("cleanup") {
            steps {
                // sh " mvn clean install"
                sh " docker image prune -a "
            }
        }

        stage ("build") {
            steps {
                echo "Hello world !!"
                sh " docker build -t ${DOCKER_IMAGE} ."
                sh " docker images | grep -i ${IMAGE} "
            }
        }

        stage ("push image to docker hub") {
            steps {
                script {
                     withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIAL, passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                      sh 'echo "${DOCKER_PASS} ${DOCKER_USER}" '
                      sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    }
                    echo "🚀 Pushing the image to Docker hub"
                    sh 'docker push ${DOCKER_IMAGE}'
                }
               
            }
        }

        stage ("clone manifest file") {
             steps {
                    sh "pwd"
                    sh "ls -l"
                    sh '''
                    if [ -d "${MANIFEST_REPO}" ]; then
                        echo "🚀 ${MANIFEST_REPO} exists, removing it..."
                        rm -rf ${MANIFEST_REPO}
                    fi
                    '''
                    echo "🚀 Updating the image of the Manifest file..."
                    sh "git clone -b main ${GIT_MANIFEST_REPO} ${MANIFEST_REPO}"
                    sh "ls -l"
             }
        }
        
    }
}
