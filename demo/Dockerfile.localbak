# Etapa 1: Construir la aplicación con Maven y Temurin 21
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Crear imagen final con Temurin 21 JRE
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar jar generado
COPY --from=builder /app/target/*.jar app.jar

# Render asigna el puerto en la variable de entorno PORT
ENV PORT=8080

EXPOSE 8080

# Importante: esta es la forma correcta en Render
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
