/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Libro {

    private static Connection con = Conexion.conectar();

    private static int isbn;
    private static String titulo;
    private static String autor;
    private static String editorial;
    private static int paginas;
    private static int copias;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String query;

    public Libro() {

    }

    public static void aniadirLibro(int isbn, String titulo, String autor, String editorial, int paginas, int copias) throws SQLException {

        query = "insert into libros (isbn,titulo,autor,"
                + "editorial,paginas,copias) values (?,?,?,?,?,?);";
        ps = con.prepareStatement(query);

        ps.setInt(1, isbn);
        ps.setString(2, titulo);
        ps.setString(3, autor);
        ps.setString(4, editorial);
        ps.setInt(5, paginas);
        ps.setInt(6, copias);

        ps.executeUpdate();
        System.out.println("Datos insertados con exito" + ps.toString());

        ps.close();

        Conexion.cerrarConexion();
    }

    public static void borrarLibro(int isbn) throws SQLException {

        query = "delete from libros where isbn=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, isbn);
        ps.executeUpdate();
        System.out.println("Se ha  borrado el libro con isbn: " + isbn + ps.toString());

    }

    public static void verCatalogo() throws SQLException {

        query = "select * from libros order by titulo asc";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            isbn = rs.getInt("isbn");
            titulo = rs.getString("titulo");
            autor = rs.getString("autor");
            editorial = rs.getString("editorial");
            paginas = rs.getInt("paginas");
            copias = rs.getInt("copias");

            System.out.println(mostrarPorPantalla());

        }

    }

    public static void actualizarCopias(int isbn, int copias) throws SQLException {

        query = " update libros set copias=? where isbn=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, copias);
        ps.setInt(2, isbn);
        ps.executeUpdate();
        System.out.println("Actualizados con exito");
        verCatalogo();

    }

    public static String mostrarPorPantalla() {
        return "Libro{" + "isbn= " + isbn + ", titulo= " + titulo + ", autor= " + autor + ", editorial= " + editorial + ", paginas= " + paginas + ", copias= " + copias + '}';

    }

}
