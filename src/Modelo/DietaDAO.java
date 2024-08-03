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
 * @author KARLARASLIN
 */
public class DietaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "Itachiuchiha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarDieta(Dieta dieta) {
        if (!existeCliente(dieta.getIdCliente())) {
            System.out.println("Error: El ID del cliente no existe.");
            return;
        }
        if (!existeEntrenador(dieta.getIdEntrenador())) {
            System.out.println("Error: El ID del entrenador no existe.");
            return;
        }

        String sql = "INSERT INTO dietas (descripcion, fecha_asignacion, fecha_finaliza, id_cliente,nombre_cliente, id_Entrenador) VALUES (?, ?, ?, ?,?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dieta.getDescripcion());
            stmt.setDate(2, new java.sql.Date(dieta.getFechaAsignacion().getTime()));
            stmt.setDate(3, new java.sql.Date(dieta.getFechaFinaliza().getTime()));
            stmt.setInt(4, dieta.getIdCliente());
            stmt.setString(5,dieta.getNombreCliente());
            stmt.setInt(6, dieta.getIdEntrenador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dieta> obtenerDietas() {
        List<Dieta> dietas = new ArrayList<>();
        String sql = "SELECT * FROM dietas";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dieta dieta = new Dieta(
                    rs.getInt("id_dieta"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_Asignacion"),
                    rs.getDate("fecha_finaliza"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("id_Entrenador")
                );
                dietas.add(dieta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dietas;
    }

    public void actualizarDieta(Dieta dieta) {
        if (!existeCliente(dieta.getIdCliente())) {
            System.out.println("Error: El ID del cliente no existe.");
            return;
        }
        if (!existeEntrenador(dieta.getIdEntrenador())) {
            System.out.println("Error: El ID del entrenador no existe.");
            return;
        }

        String sql = "UPDATE dietas SET descripcion = ?, fecha_Asignacion = ?, fecha_finaliza = ?,id_cliente = ?, nombre_cliente = ?, id_entrenador = ? WHERE id_dieta = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dieta.getDescripcion());
            stmt.setDate(2, new java.sql.Date(dieta.getFechaAsignacion().getTime())); 
            stmt.setDate(3, new java.sql.Date(dieta.getFechaFinaliza().getTime()));// Cambio de tipo de fecha
            stmt.setInt(4, dieta.getIdCliente());
            stmt.setString(5,dieta.getNombreCliente());
            stmt.setInt(6, dieta.getIdEntrenador());
            stmt.setInt(7, dieta.getIdDieta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDieta(int idDieta) {
        String sql = "DELETE FROM dietas WHERE id_dieta = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDieta);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   

    public boolean existeDieta(int idDieta) {
        String sql = "SELECT COUNT(*) FROM dietas WHERE id_dieta = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDieta);
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

    // Verifica si existe un entrenador
    public boolean existeEntrenador(int idEntrenador) {
        String sql = "SELECT COUNT(*) FROM entrenador WHERE id_entrenador = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEntrenador);
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
    
    public boolean clienteTieneDieta(int idCliente) {
        String sql = "SELECT COUNT(*) FROM dietas WHERE id_cliente = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Dieta> obtenerDietasPorCliente(int idCliente) {
    List<Dieta> dietas = new ArrayList<>();
    String sql = "SELECT * FROM dietas WHERE id_cliente = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCliente);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Dieta dieta = new Dieta(
                    rs.getInt("id_dieta"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_asignacion"),
                    rs.getDate("fecha_finaliza"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("id_entrenador")
                );
                dietas.add(dieta);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return dietas;
}

     public Dieta obtenerDietaPorId(int idDieta) {
        String sql = "SELECT * FROM dietas WHERE id_dieta = ?";
        try (Connection conn = getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDieta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Dieta(
                        rs.getInt("id_dieta"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_asignacion"),
                        rs.getDate("fecha_finaliza"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getInt("id_entrenador")
                    );
                } else {
                    return null; // No se encontró una dieta con ese ID
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la dieta con ID " + idDieta + ": " + e.getMessage());
            return null;
        }
    }   
     
     public List<Entrenador> obtenerTodosLosEntrenadores() {
        List<Entrenador> entrenadores = new ArrayList<>();
        String sql = "SELECT * FROM entrenador";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Entrenador entrenador = new Entrenador(
                    rs.getInt("id_entrenador"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getInt("id_usuario"),
                    rs.getString("turno")
                );
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }
}
