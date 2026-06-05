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

        // 👇 NUEVA ETAPA: DOCKERIZAR 👇
        stage('Build Docker Image') {
            steps {
                // Construimos la imagen y le ponemos un nombre (tag)
                sh 'docker build -t mi-api-spring:latest .'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'generated/**/*', fingerprint: true
        }
        success {
            echo '✅ Pipeline completado con ÉXITO'
        }
        failure {
            echo '❌ Pipeline FALLIDO: Revisa los logs'
        }
    }
}