#CV

Aplicación web, realizada como Proyecto Fin de Ciclo (para el **Ciclo Superior de Desarrollo de Aplicaciones Web**), durante los meses de Marzo a Junio de 2016.

Enlace a la aplicación web desplegada en un vps: [ **CV** ](http://92.222.86.6:8080/befp/)

Existe unos usuarios ya dados de alta en la aplicación, y con su Currículum Vítae ya creado; con los que se puede acceder para probar la aplicación, si así se desea. Algunos de ellos son los siguientes:

| Usuario                         |  Contraseña |
| --------------------------------|:-----------:|
| daniel.rivas@outlook.com        | qqqqqq      |
| mariamercedes.ruz@hotmail.com   | qqqqqq      |
| claudia.fuster@outlook.com      | qqqqqq      |

## Funcionalidades

- Permitir el registro de nuevos usuarios e iniciar sesión a usuarios previamente registrados.
- Los usuarios registrados, podrán elaborar su Currículum Vítae, mediante un sencillo interface gráfico.
- El Currículum Vítae, esta dividido en secciones, cada una de las cuales se puede rellenar / editar de forma independiente.
- El Currículum Vítae del usuario, se persiste en la Base de Datos, para posteriores modificaciones del mismo si, así lo desea el usuario.

## Consideraciones sobre algunos de los diálogos

**Datos Personales:**

El diálogo para los datos personales, ofrece la posibilidad de añadir una foto al Currículum Vítae, pero con las siguientes limitaciones (de momento):
* Tamaño máximo de la imagen: 10.000 bytes
* Extensión del fichero de imagen: .gif, .jpg y .png
* Los mejores resultados se obtendrán con imágenes que tengan un tamaño cercano a 100x100 píxeles.

El nombre, apellidos y email del usuario, no se pueden cambiar en el dialogo para los datos personales, pues se obtienen de los datos que el usuario cumplimente en el proceso de registro en la aplicación.
Los campos Provincia y Municipio no son directamente modificables, si no que se rellenan automáticamente al introducir o modificar el campo Código Postal.

**Formación Académica / Experiencia Laboral:**

El checkbox 'Cursando actualmente' / 'Actualmente' esta desactivado, pues su funcionalidad no esta del todo correctamente implementada por el momento.

**Idiomas:**

El usuario no puede introducir datos para un mismo idioma más de una vez, si se desea modificar los datos para un idioma determinado, usar la funcionalidad 'Editar' correspondiente a los datos del idioma cuyos datos se quieren modificar.

## Tecnologías utilizadas
Se han usado varios frameworks y librerías disponibles dentro del ecosistema Java EE.
- JavaServer Faces 
- PrimeFaces (para Vistas / UI)
- Hibernate / MySQL (para la capa de persistencia)
- OmniFaces
- GitHub
- Eclipse Mars (como IDE)


