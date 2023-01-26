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
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Libro {

    Connection con = Conexion.conectar();

    private int isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int paginas;
    private int copias;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;
    private Double precio;

    public Libro() {

    }

    public void aniadirLibro(int isbn, String titulo, String autor, String editorial, int paginas, int copias) throws SQLException {

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

    public void borrarLibro(int isbn) throws SQLException {

        query = "delete from libros where isbn=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, isbn);
        ps.executeUpdate();
        System.out.println("Se ha  borrado el libro con isbn: " + isbn + ps.toString());
        ps.close();

    }

    public void verCatalogo2() throws SQLException {

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
            try {
                precio = rs.getDouble("precio");
                System.out.println(toString());
            } catch (SQLException e) {
                System.out.println("El campo precio no existe en la tabla libros");
                System.out.println(toString());
            }
        }
        ps.close();
        rs.close();

    }

    public void actualizarCopias(int isbn, int copias) throws SQLException {

        query = " update libros set copias=? where isbn=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, copias);
        ps.setInt(2, isbn);
        ps.execute();
        System.out.println("Actualizados con exito");
        verCatalogo2();
        ps.close();
    }

    public void aniadirTablaPrecio() throws SQLException {
        query = "IF NOT EXISTS (SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'libros' AND COLUMN_NAME = 'precio') THEN\n"
                + "    ALTER TABLE libros ADD precio DOUBLE;\n"
                + "END IF;";

        Statement st = con.createStatement();
        if (st.execute(query)) {
            System.out.println("Campo precio creado con exito");
        } else {
            System.out.println("ya existe el campo precio");
        }
        st.close();

    }

    public void actualizarPrecio(double precioPorPagina) throws SQLException {
        //añadimos la columna precio
        aniadirTablaPrecio();

        //consulta las paginas
        query = "select isbn,paginas from libros";
        Statement st = con.createStatement();
        rs = st.executeQuery(query);
        while (rs.next()) {

            isbn = rs.getInt("isbn");
            paginas = rs.getInt("paginas");

            precio = (double) paginas * precioPorPagina;

            String query = "update libros set precio = ? where isbn=?";
            ps = con.prepareStatement(query);

            ps.setDouble(1, precio);
            ps.setInt(2, isbn);
            ps.executeUpdate();

        }
        //despues de actualizar
        System.out.println("Despues de actualizar la tabla y el campo precio quedaria asi:");
        verCatalogo2();
        ps.close();

    }

    public void actualizar2Libros(int isbn1, int isbn2, double precioPorPagina) throws SQLException {
        //añadimos la columna precio
        aniadirTablaPrecio();

        int paginasLib1 = 0;
        int paginasLib2 = 0;
        Double precioLib1 = 0.0;
        Double precioLib2 = 0.0;

        //consulta las paginas del primer libro
        query = "select paginas from libros where isbn=?";
        ps = con.prepareStatement(query);
        ps.setInt(1, isbn1);
        rs = ps.executeQuery();
        if (rs.next()) {
            paginasLib1 = rs.getInt("paginas");
            precioLib1 = paginasLib1 * precioPorPagina;
        }
        //precio
        ps = con.prepareStatement(query);
        ps.setInt(1, isbn2);
        rs = ps.executeQuery();
        if (rs.next()) {
            paginasLib2 = rs.getInt("paginas");
            precioLib2 = paginasLib2 * precioPorPagina;
        }

        Double precioMaximo = 0.0;

        if (precioLib1 > paginasLib2) {
            precioMaximo = precioLib1;
        } else {
            precioMaximo = precioLib2;
        }

        //actualiamos el precio con ayuda del metodo anterior
        query = " update libros set precio=? where isbn=?";
        ps = con.prepareStatement(query);
        ps.setDouble(1, precioMaximo);
        ps.setInt(2, isbn1);
        ps.execute();

        ps.setInt(2, isbn2);
        ps.execute();

        //despues de actualizar
        System.out.println("Despues de actualizar la tabla y el campo precio quedaria asi:");
        verCatalogo2();
        ps.close();

    }



    public void sumarPaginas(int isbn, int paginas, double precioPorPagina) throws SQLException {

        query = "select paginas from libros where isbn = ?";
        int paginasActuales = 0;
        ps = con.prepareStatement(query);
        // Consultar páginas actuales del libro
        ps.setInt(1, isbn);

        rs = ps.executeQuery();
        if (rs.next()) {
            paginasActuales = rs.getInt("paginas");

        }

        //sumamos las paginas actuales con las nuevas paginas
        int paginasTotales = paginasActuales + paginas;
        precio = paginasTotales * precioPorPagina;

        query = "update libros set paginas = ?, precio = ? where isbn =?";
        ps = con.prepareStatement(query);
        ps.setInt(1, paginasTotales);
        ps.setDouble(2, precio);
        ps.setInt(3, isbn);
        ps.execute();
        ps.close();
        System.out.println("---");
        verCatalogo2();
    }

    public void copiarLibro(int isbn1, int isbn2) throws SQLException{
        query = "select * from libros where isbn = ?";   
        ps = con.prepareStatement(query);
        // Consultar páginas actuales del libro
        ps.setInt(1, isbn1);
        rs = ps.executeQuery();
        if (rs.next()) {
            
            isbn = rs.getInt("isbn");
            titulo = rs.getString("titulo");
            autor = rs.getString("autor");
            editorial = rs.getString("editorial");
            paginas = rs.getInt("paginas");
            copias = rs.getInt("copias");
            try {
                precio = rs.getDouble("precio");
                System.out.println(toString());
            } catch (SQLException e) {
                System.out.println("El campo precio no existe en la tabla libros");
                System.out.println(toString());
            }
            query = "insert into libros (isbn,titulo,autor,editorial,paginas,copias,precio) values (?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, isbn2);
            ps.setString(2,titulo);
            ps.setString(3, autor);
            ps.setString(4, editorial);
            ps.setInt(5, paginas);
            ps.setInt(6, copias);
            ps.setDouble(7, precio);
            ps.executeUpdate();
            System.out.println("----");
            verCatalogo2();
            

        }
    }
    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", paginas=" + paginas + ", copias=" + copias + ", precio=" + precio + '}';
    }
}
