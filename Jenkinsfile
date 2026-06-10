pipeline {
    agent any

    // Aquí se define el parámetro de la versión
    parameters {
        string(
                name: 'RELEASE_VERSION',
                defaultValue: '1.0.0',
                description: 'Version to release (Semantic Versioning: MAJOR.MINOR.PATCH)'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Generate SDK') {
            steps {
                sh 'chmod +x ./mvnw'
                // Limpiamos también las nuevas carpetas de los SDKs para que se generen limpias
                sh 'rm -rf generated/sdk-java generated/postman generated/sdk-typescript generated/sdk-csharp generated/sdk-php'
                sh './mvnw clean generate-resources -Dspring.profiles.active=ci'
            }
        }

        stage('Build Generated Java SDK') {
            steps {
                sh 'cd generated/sdk-java && ../../mvnw clean package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
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

        // Preparación de artefactos
        stage('Prepare Release Artifacts') {
            steps {
                sh '''
                    mkdir -p release-artifacts
                    
                    # Copiamos y renombramos OpenAPI
                    cp src/main/resources/static/openapi.yaml release-artifacts/openapi-v${RELEASE_VERSION}.yaml
                    
                    # Copiamos y renombramos Postman
                    cp generated/postman/postman.json release-artifacts/postman-v${RELEASE_VERSION}.json
                    
                    # Copiamos y renombramos el SDK de Java
                    cp generated/sdk-java/target/openapi-java-client-1.0.0.jar release-artifacts/sdk-java-v${RELEASE_VERSION}.jar
                    
                    # Comprimimos el SDK de TypeScript (listo para npm install)
                    tar -czvf release-artifacts/sdk-typescript-v${RELEASE_VERSION}.tgz -C generated/sdk-typescript .
                    
                    # Comprimimos el SDK de C#
                    tar -czvf release-artifacts/sdk-csharp-v${RELEASE_VERSION}.tgz -C generated/sdk-csharp .
                    
                    # Comprimimos el SDK de PHP
                    tar -czvf release-artifacts/sdk-php-v${RELEASE_VERSION}.tgz -C generated/sdk-php .
                '''
            }
        }

        // Publicar el SDK de TypeScript en NPM de forma automática
        stage('Publish to NPM') {
            steps {
                withCredentials([string(credentialsId: 'npm-token', variable: 'NPM_TOKEN')]) {
                    sh '''
                        cd generated/sdk-typescript
                        
                        # Actualizamos la versión del package.json con la del parámetro de Jenkins
                        npm version ${RELEASE_VERSION} --no-git-tag-version
                        
                        # Inyectamos el Token de autenticación de forma segura
                        echo "//registry.npmjs.org/:_authToken=${NPM_TOKEN}" > .npmrc
                        
                        # Publicamos en el repositorio oficial de NPM (sin flag de acceso por ser paquete global)
                        npm publish
                    '''
                }
            }
        }

        // Generación de Release Notes
        stage('Generate Release Notes') {
            steps {
                sh '''
cat > release-artifacts/release-notes-v${RELEASE_VERSION}.md <<EOF
# Release v${RELEASE_VERSION}

## Added

- TODO

## Changed

- TODO

## Deprecated

- TODO

## Fixed

- TODO
EOF
                '''
            }
        }
    }

    post {
        always {
            // Archivar artefactos de la release en lugar del código suelto
            archiveArtifacts artifacts: 'release-artifacts/**/*', fingerprint: true, allowEmptyArchive: true
        }
        success {
            echo 'Pipeline completado con ÉXITO.'
        }
        failure {
            echo 'Pipeline FALLIDO: Revisa los logs'
        }
    }
}