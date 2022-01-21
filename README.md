# nisum-app
Aplicación demo para registro de usuarios

## Requisitos de ejeución:

* git
* maven
* java 1.8
* postman

## Pasos para ejecutar:

* Previamente usted tuvo que haber configurado maven y java 1.8 en sus variables globales, y descargado el proyecto.
* Desde su terminal favorita ubicarse en la raiz del proyecto.
* Ejecutar el comando mvn spring-boot:run 

## Pruebas desde postman

* Crear un nuevo request de tipo POST a la url: localhost:8080/users/signup
* En la sección Body seleccionar el tipo raw y al final de las opciones el tipo JSON.
* En el campo de texto adjuntar la siguiente JSON:
 

```json
{
    "name": "Francisco",
    "email": "francisco.briceno@dominio.com",
    "password": "satelite",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```

### Generar error de validacion de email

* Enviar el mismo body 2 veces o modificar el body repitiendo el correo.

### Validacion de campos:

* Enviar el siguiente json, este no contiene el nombre 

```json
{
    "email": "francisco.briceno@dominio.com",
    "password": "satelite",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```
> respuesta esperada:

```json
{
    "mensaje": "campo name  no debe estar vacío"
}
```

## Escenario exitoso. 

> Para el caso del escenario exitoso la respuesta deberia ser:

```json
{
    "id": "12dc3001-c7b3-470d-b909-f463d9550f0c",
    "name": "Francisco",
    "email": "francisco.briceno2@dominio.com",
    "created": "2022-01-21T12:00:59.909181",
    "modified": null,
    "lastLogin": "2022-01-21T12:00:59.90167",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcmFuY2lzY28uYnJpY2VubzJAZG9taW5pby5jb20iLCJpYXQiOjE2NDI3ODgwNTksImV4cCI6MTY0Mjc4ODM1OX0.hoA5rSEHIxjV02iEbzJqhsfN0DNO5FKmj6ODOCzdLm8",
    "isActive": true
}
```

## Visualizar todos los usuarios:

Para ver todos los usuarios registrado usted debera copiar el token devuelto en la respuesta del escenario exitoso, crear un nuevo request de tipo get, apuntando a la url localhost:8080/users/

En el tab header, agregar en la columna key la palabra `Authorization` en la columna value agregar el valor:
`Bearer token` donde Token es el token generado por la aplicacion cuando se registro.

La respuesta esperada es: 

```json
[
    {
        "id": "12dc3001-c7b3-470d-b909-f463d9550f0c",
        "name": "Francisco",
        "email": "francisco.briceno2@dominio.com",
        "created": "2022-01-21T12:00:59.909181",
        "modified": "2022-01-21T12:07:25.859548",
        "lastLogin": "2022-01-21T12:07:25.859571",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcmFuY2lzY28uYnJpY2VubzJAZG9taW5pby5jb20iLCJpYXQiOjE2NDI3ODg0NDUsImV4cCI6MTY0Mjc4ODc0NX0.Cv1XYbDDYL5XnzgJwHSIaHzn-_UEHhI2mO6fr5RMy4A",
        "isActive": true
    }
]
```
El token expira a los 5 minutos.

## Generar nuevo token

para generar un nuevo token crear un nuevo request de tipo Post que apunte a localhost:8080/users/signin en el apartado Body asegurarse que sea de tipo JSON, en este debera ir el email y el password con el que se registro.

```json
{
    "email":"francisco.briceno2@dominio.com",
    "password":"satelite"
}
```

## Diagrama de secuencia
![Nisum Sequence Diagram](https://user-images.githubusercontent.com/3578372/150602157-2fe1866e-3568-4613-b41f-e2c9d46e7de4.png)

