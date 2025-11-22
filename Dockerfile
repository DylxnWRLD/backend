# Dockerfile optimizado para Desarrollo (Ejecución Directa de Código Fuente)
# Usa Maven con JDK 17
FROM maven:3.9-eclipse-temurin-17
WORKDIR /app

# Copia solo el archivo POM. Esto se hace para descargar las dependencias y 
# almacenarlas en el caché del contenedor/host. El código fuente se monta por volumen.
COPY pom.xml .

# Descarga las dependencias para evitar hacerlo en cada ejecución del contenedor.
RUN mvn dependency:go-offline

# El código fuente (incluida la carpeta 'src') se montará a través de un volumen (./backend:/app).

EXPOSE 8080

# El comando ENTRYPOINT/CMD ejecuta la aplicación Spring Boot directamente desde la fuente.
# Esto es ideal para desarrollo con recarga en caliente (hot reload).
CMD ["mvn", "spring-boot:run"]