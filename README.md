## Documentación del proyecto

Este proyecto es la práctica de warm-up con java desarrollada de forma colaborativa. Se parte de una versión core, ya implementada, provista por la catedra que se pretende ampliar con un conjunto de mejoras.
El producto a costruir es una sistema informático que permita a un administrador gestionar sus contactos mediante interfaces gráficas de usuario. También el sistema permitirá a su operador solicitar la generación de reportes en formato pdf de los contactos gestionados.

## Arquitectura
Responsabilidades de las capas lógicas del sistema.   

* presentation:
  * views: Son interfaces gráficas de usuario.
	  - Deben presentar los modelos.
	  - Deben interactuar con el usuario.
    - Deben delegar en los presentadores el tratamiento de las iniciativas del usuario.
	
  * presenters: Son controladores de lógica de presentación.   
    - Deben gestionar la lógica de presentación y el estado de las vistas.   
    - Deben delegar en los controladores de lógica de negocio la ejecución de la operación, a travez de los dtos de entrada.   
    - Deben delegar en los dto's la validaciónes de lo campos.   
    - Deben delegar en las vistas la presentación de los dtos de salida.
  
  * business_controllers: Son controladores de lógica de negocio.    
    - Deben desarrollar las operaciones de negocio que conllevan a ejecución de una operación.    
    - Construyen las entidades a partir de los dtos de entrada o pueden delegar en los dtos de entrada la contrucción de las entidades.
	  - Delega en los dtos la contruccion de los dtos de salida a partir de las entidades.
	  - Delega en los repositorios el acceso y procesamiento de operaciones de persistencia en base de datos.
	  - Delega en los data_services la ejecución de operaciones avanzadas/complejas en base de datos.
	  - Delega en los business_services la ejecución de procesos avanzados/complejos de caracter genérico de la capa de negocio.
	  
    * dtos: Son objetos de transeferencia de datos entre la capa de presentación y la capa de negocio.
		  - deben poder construirse a partir de entidades de entrada.
		  - deben poder construir entidades a partir de su propio estado.
		  - deben definir las restricciones en la construcción de su estado.
	
    * Exceptions: Clases que epecializan excepciones comunes ocurrentes en la capa de negocio.
	  * business_services: Clases avanzadas de apoyo, fachadas de librerias de terceros para dar soporte a los procesos de negocio.
	
  * repositories: Módulos de acceso a base de datos mediante la implementación del patrón DAO.
    - Deben desarrollar operaciones CRUD sobre base de datos.
	  - Deben desarrollar operaciones de consulta sobre base de datos.
	  * data_services: Clases avanzadas de apoyo a la capa de negocio para la gestion de operaciones complejas en la base de datos.

## Tratamiento de errores
- El tratamiento de errores no se ejerce de manera centralizada. 
- Si una petición de ejecución de alguna lógica de negocio no puede ser llevada a cabo por algún motivo bloqueante como la violación de alguna regla de negocio, el controlador debe lanzar una excepción delegando su tratamiento a la clase que invoca la ejecución de la operación.

## Persistencia

- La gestión de la persistencia se realizará utilizando JPA. Para este proyecto se optó por la utilización de Hibernate como implementador del standard por sobre eclipselink.
- La capa de persistencia se implementará con la utilización del patrón DAO.
- Se dispone de un servicio para poblar la base de datos con datos cargados desde un fichero db.yml ("YAML") con datos de prueba para depuración, desarrollo y pruebas.

## Perfiles
- Se manejaran dos contextos de persistencia basado en perfiles.
- Perfil de desarrollo: base de datos relacional en memoria H2 para pruebas y depuración.
- Perfil de producción: base de datos relacional MariaDb

