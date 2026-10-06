# ---------- 1. Compilacion (no hace falta tener Java ni Maven en tu equipo) ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build

# Primero solo el pom para cachear las dependencias entre builds
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q package -DskipTests

# ---------- 2. Imagen final, solo con el JRE ----------
FROM eclipse-temurin:21-jre-alpine

LABEL description="API REST de inventarios de supermercado (Spring Boot)"

RUN addgroup -S app && adduser -S app -G app
USER app
WORKDIR /app

COPY --from=build /build/target/supermercado-api-*.jar app.jar

EXPOSE 8080

# Perfil por defecto: dev (H2). docker-compose.yml lo cambia a mysql.
ENTRYPOINT ["java", "-jar", "app.jar"]
