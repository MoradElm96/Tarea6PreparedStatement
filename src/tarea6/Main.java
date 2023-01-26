/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea6;

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
        Scanner sc = new Scanner(System.in);
        int opcion;
      
       do {
        
       
           
            
        System.out.println("Tarea 6: Menu base datos ");
        System.out.println("1. Añadir libro");
        System.out.println("2. Borrar libro");
        System.out.println("3. Ver Catalogo");
        System.out.println("4. Actualizar copias ");
        System.out.println("5. ");
        System.out.println("6. Borrar libro");
        System.out.println("7. Ver Catalogo");
        System.out.println("8. Actualizar copias ");
        
        System.out.println("0. Salir");
        System.out.print("Introduce la opción: ");
            
            opcion = sc.nextInt();
            

            switch (opcion) {

                case 1:
                  

                    break;
                case 2:
                
                   

                    break;

                case 3:
                  

                    

                    break;

                case 4:
                  
                    
                    break;

                default:
                    System.out.println("Opcion no valida ");
                    break;
            }

        } while (opcion != 0);
       sc.close();
        

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
