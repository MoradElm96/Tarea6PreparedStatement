Tarea 6 - BBDD y JAVA(Con sentencias Preparadas)
1.	Ejecutar libros.sql. Posteriormente, crear una clase llamada Libros que tenga los siguientes métodos de acceso a datos para la tabla libros de la BD librería:
•	anadirLibro recibe todos los datos necesarios
•	borraLibro recibe el ISBN
•	verCatalogo muestra todos los libros ordenador por título ascendente
•	actualizarCopias recibe el ISBN y el nuevo número de copias Todas estas operaciones se realizaran mediante sentencias preparadas.
a)	Añade un nuevo campo precio a la tabla Libros. Añade un nuevo método a la clase Libros que reciba un double indicando el precio por página de cada libro. Este método debe consultar las páginas de cada libro, multiplicar por el precio por página y rellenar la columna precio de cada libro.

b)	Añade un nuevo método a la clase Libros que reciba dos isbn y un double que indica el precio por página, y que realizará lo siguiente:
•	Consultará las páginas de los dos libros y calculará el precio para los dos.
•	Actualizará para ambos libros el precio con el precio máximo obtenido del cálculo anterior.
 
c)	Añade un nuevo método a la clase Libros que reciba un isbn, un número de páginas y un float que indica el precio por página, este método realizará lo siguiente:
•	Sumará el número de páginas a las páginas actuales que ya tiene el libro
•	Calcula el precio multiplicando el total de páginas por el precio por página
•	Actualiza las páginas y el precio del libro
d)	Añade un nuevo método a la clase Libros que reciba dos isbn y realice lo siguiente:
•	Consultará los datos del primer isbn
•	Insertará una nueva fila con el segundo isbn copiando el resto de datos de los obtenidos en la consulta anterior.
.
