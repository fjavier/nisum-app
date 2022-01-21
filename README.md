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

### Documentacion del API
https://documenter.getpostman.com/view/3496702/UVXona7G#6c33bc35-4938-4079-b3f6-aa5c71d50a3c

## Importar la colección de request del API desde postman 

De esta manera usted podra probar el API ya con datos pre-cargados.

- Seleccionar la opción importar
- Seleccionar la opción link
- Pegar el link: https://www.postman.com/collections/3b5ac7455d1e312cd86b
- Click en Siguient
- Click en importar

### Resultado 
![image](https://user-images.githubusercontent.com/3578372/150606103-93d321f0-293d-4e05-a4cd-523d6ab94fd6.png)

## Consideraciones del API

- El metodo para obtener todos los usuarios requiere autenticación, para ello debera reemplazar el token que viene por defecto por el generado al momento de hacer el signup del usuario.
- Para autenticarse nuevamente con un usuario creado, usted tendra que generar un nuevo token. para esto vaya al request llamado Signin e ingresar las credenciales del usuario que creo previamente.
- Usted podra revisar la documentaciòn del swagger en el siguiente link: http://localhost:8080/swagger-ui.html
- Usted podra ingresar a la base de datos desde el siguiente link: http://localhost:8080/h2-console con las credenciales 
-  user: sa
-  password: 123qweasd


## Diagrama de secuencia
![Nisum Sequence Diagram](https://user-images.githubusercontent.com/3578372/150602157-2fe1866e-3568-4613-b41f-e2c9d46e7de4.png)

