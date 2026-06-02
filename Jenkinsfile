//Contraseña: 3d43aaff7fac41a4abfaeb4ad395e92e
//Usuario: juanca_admin
//Contraseña: admin

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
                // Ejecuta la validación, los tests y genera los SDKs
                h 'chmod +x ./mvnw
                sh './mvnw clean verify'
            }
        }
    }

    post {
        always {
            // Guarda los SDKs y colecciones de Postman generados
            archiveArtifacts artifacts: 'generated/**/*', fingerprint: true
        }
        success {
            echo 'Pipeline completado con ÉXITO'
        }
        failure {
            echo 'Pipeline FALLIDO: Revisa los logs'
        }
    }
}