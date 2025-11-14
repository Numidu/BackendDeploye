pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'numidu'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/Numidu/BackendDeploye.git'
            }
        }

        stage('Build Backend Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                                 usernameVariable: 'DOCKER_USER',
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                        cd userservice
                        docker build -t $DOCKER_USER/backend .
                    '''
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                                 usernameVariable: 'DOCKER_USER',
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push $DOCKER_USER/backend
                    '''
                }
            }
        }

        stage('Deploy on GCP VM') {
            steps {
                sshagent(['gcp_vm_key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no dnumidu@34.44.230.107 '
                            cd ~/app || git clone https://github.com/Numidu/BackendDeploye.git ~/app &&
                            cd ~/app &&
                            git pull &&
                            docker-compose down &&
                            docker-compose pull &&
                            docker-compose up -d --build
                        '
                    """
                }
            }
        }
    }
}
