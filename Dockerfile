# Usa la imagen de Java 17 como base
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo de construcción de Gradle y el código fuente
COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle ./build.gradle
COPY settings.gradle ./settings.gradle
COPY src ./src

# Da permisos de ejecución al script de Gradle
RUN chmod +x gradlew

# Construye el proyecto (incluye el test si funciona, o ignóralo si no es necesario)
RUN ./gradlew clean build -x test

# Verifica que el archivo JAR fue creado
RUN ls -l build/libs/

# Exponer el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "build/libs/parcial_prog3-0.0.1-SNAPSHOT.jar"]
