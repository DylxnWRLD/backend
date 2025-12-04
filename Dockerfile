# ----------------------------------------------------
# ETAPA 1: BUILDER (Compilación del JAR)
# ----------------------------------------------------
# Usa una imagen completa (Maven y JDK 17) para la compilación.
FROM maven:3.9-eclipse-temurin-17 AS builder

# Directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copia solo el archivo POM. Se hace primero para aprovechar el caché de Docker 
# si solo cambian las dependencias, no el código fuente.
COPY pom.xml .

# Descarga todas las dependencias.
RUN mvn dependency:go-offline

# Copia el código fuente.
COPY src /app/src

# Construye la aplicación. El resultado es el archivo JAR en /app/target/
RUN mvn clean package -DskipTests

# ----------------------------------------------------
# ETAPA 2: RUNNER (Ejecución Final)
# ----------------------------------------------------
# Usa una imagen base más ligera (solo JRE 17) para el entorno de ejecución.
FROM eclipse-temurin:17-jre-focal

# Define la ubicación del JAR compilado.
ARG JAR_FILE=target/Restaurante-0.0.1-SNAPSHOT.jar 

# Copia el JAR compilado desde la etapa 'builder' a la imagen final.
# Lo renombramos a app.jar para simplificar el comando de ejecución.
COPY --from=builder /app/${JAR_FILE} /app/app.jar

EXPOSE 8080

# El punto de entrada para la aplicación: ejecuta el JAR.
ENTRYPOINT ["java", "-jar", "/app/app.jar"]