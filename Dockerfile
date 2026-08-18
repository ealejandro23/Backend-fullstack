# Etapa 1: Construir la aplicación, haciendo uso de Maven y Eclipse Temurin 21
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY demo/pom.xml .
COPY demo/src ./src
RUN mvn clean package -DskipTests


# Etapa 2: Crear la imagen final con la aplicación construida, usando Eclipse Temurin 21 JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]