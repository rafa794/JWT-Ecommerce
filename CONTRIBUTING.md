# Proyecto Spring Cloud con Autenticación JWT
Proyecto Spring Cloud con Autenticación JWT y base de datos PostgreSQL

# Guía de uso
2. ``mvn clean install`` para instalar las dependencias del proyecto.
3. ``docker-compose up --build`` para levantar Rabbitmq y Zipkin.
4. Arrancar desde el IDE el servicio Config Server.
5. Arrancar desde el IDE el servicio Eureka Server.
6. Arrancar desde el IDE el servicio Gateway Server.
7. Arrancar desde el IDE el servicio Usuarios, Prendas, Precios y Tiendas


# Documentación
- Config server: http://localhost:8888/<nombre_servicio>/default
- Eureka server: http://localhost:8761/
- Gateway: http://localhost:9000/
- Usuarios: http://localhost:8080/swagger-ui.html
- Prendas: http://localhost:8081/swagger-ui.html
- Precios: http://localhost:8082/swagger-ui.html
- Tiendas: http://localhost:8083/swagger-ui.html

# Autor
- Enrique Fernández Manzano
- Hugo Gutiérrez Tomás 
- Rafael Mendoza Márquez