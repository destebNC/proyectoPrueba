pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Descarga el código de tu repositorio
                checkout scm
            }
        }

        stage('Build & Verify') {
            steps {
                // 1. Damos permisos de ejecución a Maven
                sh 'chmod +x ./mvnw'

                // 2. Ejecutamos la validación
                sh './mvnw clean verify -Dspring.profiles.active=ci'
            }
        }

        stage('Build Java SDK') {
            steps {
                // 3. ¡EL CAMBIO ESTÁ AQUÍ!
                // Usamos Maven en lugar de Gradle para evitar el error de Java 21
                sh 'cd generated/sdk-java && ../../mvnw clean package -DskipTests'
            }
        }
    }

    post {
        always {
            // Guarda los SDKs y colecciones de Postman generados
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