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

        stage('Build Docker Image') {
            steps {
                // Añadimos el parámetro jib.from.image para que use Java 21 y no falle
                sh './mvnw compile com.google.cloud.tools:jib-maven-plugin:3.4.1:buildTar -Dimage=mi-api-spring:latest -Djib.from.image=eclipse-temurin:21-jre-alpine'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'generated/**/*, target/jib-image.tar', fingerprint: true, allowEmptyArchive: true
        }
        success {
            echo '✅ Pipeline completado con ÉXITO'
        }
        failure {
            echo '❌ Pipeline FALLIDO: Revisa los logs'
        }
    }
}