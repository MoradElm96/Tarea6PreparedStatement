/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea6;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

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
       libro.verCatalogo();
       libro.actualizarCopias(12345, 89);
        
    }

}
