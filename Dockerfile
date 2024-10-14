FROM ubuntu:latest
LABEL authors="Silva"

ENTRYPOINT ["top", "-b"]

# Usar una imagen de Gradle para construir la aplicación
FROM gradle:8.2.1-jdk17 AS build
WORKDIR /app

# Copiar el código fuente al contenedor
COPY src .

# Construir la aplicación
RUN gradle build --no-daemon

# Usar una imagen de OpenJDK para ejecutar el JAR resultante
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR generado del paso de build
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
