/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jenny
 */
public class InscripcionDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "Itachiuchiha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        if (clienteTieneInscripcion(inscripcion.getIdCliente())) {
            System.out.println("Error: El cliente ya tiene una inscripción activa.");
            return;
        }

        String sql = "INSERT INTO inscripciones (fecha_inicio, fecha_fin, id_cliente, nombre_cliente, id_membresia) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(inscripcion.getFechaInicio().getTime()));
            stmt.setDate(2, new java.sql.Date(inscripcion.getFechaFin().getTime()));
            stmt.setInt(3, inscripcion.getIdCliente());
            stmt.setString(4,inscripcion.getNombreCliente());
            stmt.setInt(5, inscripcion.getIdMembresia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripciones";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion(
                    rs.getInt("id_inscripcion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("id_membresia")
                );
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    public void actualizarInscripcion(Inscripcion inscripcion) {
        String sql = "UPDATE inscripciones SET fecha_inicio = ?, fecha_fin = ?, nombre_cliente = ?, id_membresia = ? WHERE id_inscripcion = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(inscripcion.getFechaInicio().getTime()));
            stmt.setDate(2, new java.sql.Date(inscripcion.getFechaFin().getTime()));
            stmt.setString(3, inscripcion.getNombreCliente());
            stmt.setInt(4, inscripcion.getIdMembresia());
            stmt.setInt(5, inscripcion.getIdInscripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarInscripcion(int idInscripcion) {
        String sql = "DELETE FROM inscripciones WHERE id_inscripcion = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idInscripcion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeInscripcion(int idInscripcion) {
        String sql = "SELECT COUNT(*) FROM inscripciones WHERE id_inscripcion = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idInscripcion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean clienteTieneInscripcion(int idCliente) {
        String sql = "SELECT COUNT(*) FROM inscripciones WHERE id_cliente = ? AND fecha_fin >= CURDATE()";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public boolean existeMembresia(int idMembresia) {
        String sql = "SELECT COUNT(*) FROM Membresias WHERE id_membresia = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMembresia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     
    public List<Inscripcion> obtenerInscripcionesPorCliente(int idCliente) {
    List<Inscripcion> inscripciones = new ArrayList<>();
    String sql = "SELECT * FROM inscripciones WHERE id_cliente = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCliente);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion(
                    rs.getInt("id_inscripcion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("id_membresia")
                );
                inscripciones.add(inscripcion);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return inscripciones;
}
    public boolean existeCliente(int idCliente) {
    String sql = "SELECT COUNT(*) FROM cliente WHERE id_cliente = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCliente);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    public List<Membresia> obtenerTodasLasMembresias() {
        List<Membresia> membresias = new ArrayList<>();
        String sql = "SELECT * FROM membresias";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Membresia entrenador = new Membresia(
                    rs.getInt("id_Membresia"),
                    rs.getString("tipo"),
                    rs.getDouble("precio"),
                    rs.getInt("duracion")
                );
                membresias.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membresias;
    }


}
