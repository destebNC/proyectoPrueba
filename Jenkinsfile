pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Verify Main Project') { // Renombramos el stage para mayor claridad
            tools {
                nodejs 'NodeJS_18' // <--- ¡Asegúrate de que este nombre coincida con el que configuraste en Jenkins!
            }
            steps {
                sh 'chmod +x ./mvnw'
                // Ejecutamos el comando completo que incluye build, tests, validaciones OpenAPI, lint, breaking changes y generación de SDKs/Postman
                sh './mvnw clean verify -Dspring.profiles.active=ci'
            }
        }

        stage('Build Generated Java SDK') { // Stage para compilar el SDK Java generado
            steps {
                sh 'cd generated/sdk-java && chmod +x ./gradlew && ./gradlew clean build'
            }
        }

        stage('Build & Push Docker Image') { // Stage para construir y subir la imagen Docker
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