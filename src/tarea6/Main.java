/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea6;

import java.sql.SQLException;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        // Connection con = Conexion.conectar();

        Libro libro = new Libro();
       // Scanner sc = new Scanner(System.in);

       // libro.aniadirLibro(123456789, "El principito", "makiavelo", "salvat", 158, 2);
       // libro.borrarLibro(123456789);
       libro.verCatalogo2();
      // libro.aniadirTablaPrecio();
     //  libro.actualizarCopias(12453, 65);
     //a)
     libro.actualizarPrecio(1.5);
     //b)
     
     libro.actualizar2Libros(1725, 1325, 1.8);
        
     libro.sumarPaginas(1725, 300,1.8 );
     libro.copiarLibro(1725, 12);
    }

}
