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

localhost:8080/users/signup
<code>
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
</code>
