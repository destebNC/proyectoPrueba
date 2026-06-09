pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Verify Main Project') {
            // ❌ ELIMINADO: el bloque "tools" que pedía NodeJS, ya que causa el error fatal.
            steps {
                sh 'chmod +x ./mvnw'

                // 1. Limpiamos las carpetas para que Jenkins genere los SDKs desde cero
                sh 'rm -rf generated/sdk-java generated/postman'

                // 2. Ejecutamos build, tests, validaciones OpenAPI, lint y generación de SDKs
                sh './mvnw clean verify -Dspring.profiles.active=ci'
            }
        }

        stage('Build Generated Java SDK') {
            steps {
                // Compilamos el SDK Java generado usando Gradle (como lo configuró tu compañero)
                sh 'cd generated/sdk-java && chmod +x ./gradlew && ./gradlew clean build'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                // Inyectamos las credenciales y subimos la imagen usando Jib
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