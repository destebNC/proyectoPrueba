pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Verify') {
            steps {
                sh 'chmod +x ./mvnw'
                sh './mvnw clean verify -Dspring.profiles.active=ci'
            }
        }

        stage('Build Java SDK') {
            steps {
                sh 'cd generated/sdk-java && ../../mvnw clean package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh './mvnw compile com.google.cloud.tools:jib-maven-plugin:3.4.1:build \
                        -Dimage=docker.io/$DOCKER_USER/mi-api-spring:latest \
                        -Djib.from.image=eclipse-temurin:21-jre-alpine \
                        -Djib.container.mainClass=com.example.proyectoPrueba.ProyectoPruebaApplication \
                        -Djib.to.auth.username=$DOCKER_USER \
                        -Djib.to.auth.password=$DOCKER_PASSWORD'
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'generated/**/*', fingerprint: true, allowEmptyArchive: true
        }
        success {
            echo '✅ Pipeline completado con ÉXITO. Imagen subida a Docker Hub.'
        }
        failure {
            echo '❌ Pipeline FALLIDO: Revisa los logs'
        }
    }
}