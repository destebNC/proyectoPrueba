# 1. Usar una imagen oficial de Java 21 como base
FROM eclipse-temurin:21-jdk-alpine

# 2. Información sobre el creador de la imagen (opcional pero recomendado)
LABEL maintainer="tu_correo@ejemplo.com"
LABEL version="1.0"
LABEL description="API de Proyecto Prueba"

# 3. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 4. Copiar el archivo .jar generado por Maven al contenedor
# Maven deja el archivo en la carpeta target/
COPY target/proyectoPrueba-0.0.1-SNAPSHOT.jar app.jar

# 5. Exponer el puerto donde escucha la aplicación (asumo que es el 8081 por tus perfiles)
EXPOSE 8081

# 6. Comando para arrancar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=ci"]