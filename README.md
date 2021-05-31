# clients-death-prediction

# Challenge

* Microservicio desarrollado en JAVA Spring boot

* API Rest documentada en Swagger

* Deployado en AWS o algún CLOUD + código subido en GITHUB

* Endpoint de Entrada POST /creacliente

* Endpoint de salida GET [/kpideclientes](https://safe-woodland-49765.herokuapp.com/api/v1/kpideclientes)

* Endpoint de salida GET [/listclientes](https://safe-woodland-49765.herokuapp.com/api/v1/listclientes)

# Resolucion

* Microservicio en Java 11 con Spring Boot y arquitectura hexagonal
* Base de datos utilizada Postgresql
* Test unitarios con Junit
* Documentacion generada en [Swagger](https://safe-woodland-49765.herokuapp.com/swagger-ui.html)
* Desplegado en [Heroku](https://safe-woodland-49765.herokuapp.com/)

### Postman collection

https://www.getpostman.com/collections/a30e8e8c57e92dc3ba83

Creacion de DB: 

```sql
CREATE TABLE client
(
    id         SERIAL NOT NULL
        CONSTRAINT client_pk
            PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INTEGER,
    birthdate  DATE
);
```