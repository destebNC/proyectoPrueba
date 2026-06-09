pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Generate SDK') {
            steps {
                sh 'chmod +x ./mvnw'

                // 1. Limpiamos las carpetas para que Jenkins genere los SDKs siempre desde cero
                sh 'rm -rf generated/sdk-java generated/postman'

                // 2. Ejecutamos build, tests, validaciones OpenAPI y generación de SDKs
                sh './mvnw clean verify -Dspring.profiles.active=ci'
            }
        }

        stage('Build Generated Java SDK') {
            steps {
                // 3. Compilamos el SDK Java generado USANDO MAVEN (esquivando el bug de Gradle con Java 21)
                sh 'cd generated/sdk-java && ../../mvnw clean package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                // 4. Inyectamos las credenciales y subimos la imagen usando Jib
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
            // Guardamos los artefactos por si queremos descargarlos manualmente desde Jenkins
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