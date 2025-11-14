pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
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
                dir('userservice') {
                    sh 'docker build -t $DOCKERHUB_USER/backend .'
                }
            }
        }

        

        stage('Push Images to Docker Hub') {
            steps {
                sh """
                    echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_USER --password-stdin
                    docker push $DOCKERHUB_USER/backend
                   
                """
            }
        }

        stage('Deploy on GCP VM') {
            steps {
                sshagent(['gcp_vm_key']) {
                    sh """
                    ssh -o StrictHostKeyChecking=no dnumidu@34.72.48.177 '
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
