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
                // Usamos el plugin Google Jib para construir la imagen Docker sin necesidad de tener Docker instalado.
                // Esto generará la imagen empaquetada en la ruta: target/jib-image.tar
                sh './mvnw compile com.google.cloud.tools:jib-maven-plugin:3.4.1:buildTar -Dimage=mi-api-spring:latest'
            }
        }
    }

    post {
        always {
            // Guardamos el SDK generado y TAMBIÉN la imagen de Docker exportada (.tar)
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