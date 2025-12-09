CÓMO CORRER EN LOCAL
-
1. Crear una carpeta raíz con el nombre de Proyecto.
2. Dentro crear la carpeta de backend.
3. Clonar los repositorios en la respectiva carpeta:
	- Para backend: git clone https://github.com/DylxnWRLD/backend

En la carpeta del back:
-
	./mvnw clean package -DskipTests

Estando en la carpeta de raíz para que pueda encontrar el Dockerfile:
-
	docker build -t app-backend ./backend

Vamos a la carpeta de backend con:
- 
    cd backend
    
Abrimos nuestro editor de código, en este caso fue visual
-
    code .

Dentro navegamos a /src/main/resources/application.properties. Ponemos el siguiente contenido:
-
    spring.application.name=Restaurante
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    spring.datasource.url=jdbc:mariadb://database:3306/restaurante_db
    spring.datasource.username=listi
    spring.datasource.password=123
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.datasource.continue-on-error=true
    spring.datasource.hikari.connection-timeout=60000

Luego desde bash iniciamos los contenedores
-
BASE DE DATOS:
-
    docker run -d \
      --name mariadb_db \
      --restart always \
      -p 3307:3306 \
      -e MARIADB_ROOT_PASSWORD=123 \
      -e MARIADB_DATABASE=restaurante_db \
      -e MARIADB_USER=listi \
      -e MARIADB_PASSWORD=123 \
      -v db_data:/var/lib/mysql \
      mariadb:10.6

PARA EL BACKEND:
-
    docker run -it --rm \
      --name spring_backend_dev \
      -p 8080:8080 \
      -e SPRING_DATASOURCE_URL=jdbc:mariadb://host.docker.internal:3307/restaurante_db \
      -e SPRING_DATASOURCE_USERNAME=listi \
      -e SPRING_DATASOURCE_PASSWORD=123 \
      app-backend

Probar en el navegador con:
- 
    http://localhost:8080/api/prueba/estado


  EJEMPLOS DE CURL
  -

  Para los usuarios
  -
    curl -X POST \
    http://localhost:8080/api/usuarios/registro \
    -H "Content-Type: application/json" \
    -d '{
    "nombre": "Pedro",
    "email": "pedro@gmail.com",
    "password": "123"
    }'

    curl -X POST \
    http://localhost:8080/api/usuarios/registro \
    -H "Content-Type: application/json" \
    -d '{
    "nombre": "Juan",
    "email": "juan@gmail.com",
    "password": "123"
    }'

  Para los platos del restaurante:
  -
    curl -X POST \
    http://localhost:8080/api/platos \
    -H "Content-Type: application/json" \
    -d '{
      "nombre": "Huevo con Jamón",
      "descripcion": "Delicioso desayuno de huevo y jamón",
      "precioBase": 50.00,
      "disponible": true
    }'

    curl -X POST \
    http://localhost:8080/api/platos \
    -H "Content-Type: application/json" \
    -d '{
      "nombre": "Chilaquiles",
      "descripcion": "Delicioso desayuno",
      "precioBase": 100.00,
      "disponible": true
    }'

    curl -X GET \
    http://localhost:8080/api/platos/disponibles



CÓMO DESPLEGARLO:
-
1. Primero debemos de ir a https://freedb.tech/#google_vignette. Aquí será en donde obtendremos los datos que posteriormente se utilizarán para el application.properties.
2. Tendremos que cambiar el contenido del Application Properties, debería quedar como el siguiente:
   -
        spring.application.name=Restaurante
        
        spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
        spring.datasource.url=jdbc:mariadb://(url que se obtiene desde freedb)
        spring.datasource.username=usuario (se obtiene desde freedb)
        spring.datasource.password=contraseña (se obtiene desde freedb)
        
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect

        spring.datasource.continue-on-error=true
        spring.datasource.hikari.connection-timeout=60000
4. Vamos a https://railway.com/ y damos a la opción de "Deploy a new project".
5. Daremos clic en la opción de Github Repository.
6. Elegimos el repositorio que contiene nuestro backend.
7. Damos clic en el recuadro del repositorio.
8. Vamos a "Settings" y buscamos y damos clic en la opción de "Generate Domain".
9. La URL que nos da, es la que se usará en los archivos js del front en /src/lib/api/ en lugar de la del localhost.
10. Ahora hacemos lo siguiente desde bash:
    -
        git add .
        git commit -m "Despliegue del back"
        git push
11. Esperamos a que Railway lo construya y lo despliegue.
12. Deberíamos poder acceder a través de la URL y los endpoints correspondientes.
    
