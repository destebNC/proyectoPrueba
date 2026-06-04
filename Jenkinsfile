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

                // 2. Descargamos oasdiff para Linux, lo extraemos y reemplazamos el .exe
                sh '''
                    curl -L -o oasdiff_linux.tar.gz https://github.com/Tufin/oasdiff/releases/download/v1.9.6/oasdiff_1.9.6_linux_amd64.tar.gz
                    tar -xzf oasdiff_linux.tar.gz
                    mv oasdiff oasdiff.exe
                    chmod +x oasdiff.exe
                '''

                // 3. Ejecutamos la validación COMPLETA real (usando el perfil de base de datos 'ci')
                sh './mvnw clean verify -Dspring.profiles.active=ci'
            }
        }
        stage('Build Java SDK') {
                    steps {
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