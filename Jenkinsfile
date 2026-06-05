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
                // 1. Damos permisos de ejecución a Maven
                sh 'chmod +x ./mvnw'

                // 2. Ejecutamos la validación COMPLETA real (usando el perfil de base de datos 'ci')
                // Como oasdiff está comentado en el pom.xml, Jenkins se lo saltará automáticamente.
                sh './mvnw clean verify -Dspring.profiles.active=ci'
            }
        }

        stage('Build Java SDK') {
            steps {
                // 3. Entramos en la carpeta del SDK generado y lo compilamos con Gradle
                sh 'cd generated/sdk-java && chmod +x ./gradlew && ./gradlew clean build'
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