pipeline {
    agent any

    // Aquí se define el parámetro de la versión
    parameters {
        string(
                name: 'RELEASE_VERSION',
                defaultValue: '1.0.0',
                description: 'Version to release (Semantic Versioning: MAJOR.MINOR.PATCH)'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Generate SDK') {
            steps {
                sh 'chmod +x ./mvnw'
                sh 'rm -rf generated/sdk-java generated/postman'
                sh './mvnw clean generate-resources -Dspring.profiles.active=ci'
            }
        }

        stage('Build Generated Java SDK') {
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
            echo 'Pipeline completado con ÉXITO.'
        }
        failure {
            echo 'Pipeline FALLIDO: Revisa los logs'
        }
    }
}