# Microservicio de empleados

Este microservicio permite **crear, actualizar, consultar y elmiminar empleados**. 

En el archivo Documentation.md se encuentran los contratos de cada recurso del microservicio 

Tecnologías principales:

- Java 17 + Spring Boot 2.7.18 (Se recomienda migrar a una version mas actualizada)
- Spring Data JPA / Hibernate
- Mysql    
- Pruebas unitarias con JUnit 5 y Mockito
- Configuración de logs via YAML 
- Docker / Docker Compose  
- OpenAPI / Swagger UI  
- Gitflow
- CI/CD / githubActions

---

## 📦 Requisitos

- Docker Compose v2  
- Maven (si quieres compilar localmente)  

---

## 🚀 Levantar el servicio con Docker compose 

Para ejecutar se requiere crear un archivo .env con las credenciales de bd 

```bash
# .env
DB_USER=myuser
DB_PASS=mypass
DOCKERHUB_USER=mydockeruser
DOCKERHUB_PASS=mydockertoken
```

En la raíz del proyecto:

```bash
docker compose up --build -d 
```

Para ejcutar pruebas unitarias se realiza con el siguiente comando 

```bash
mvn clean test -Dspring.datasource.username=myuser -Dspring.datasource.password=mypass
```
