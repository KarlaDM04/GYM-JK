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
public class RutinaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "Itachiuchiha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarRutina(Rutina rutina) {
        if (!existeCliente(rutina.getIdCliente())) {
            System.out.println("Error: El ID del cliente no existe.");
            return;
        }
        if (!existeEntrenador(rutina.getIdEntrenador())) {
            System.out.println("Error: El ID del entrenador no existe.");
            return;
        }

        String sql = "INSERT INTO rutinas (descripcion, fecha_asignacion,fecha_finaliza, id_cliente,nombre_cliente, id_entrenador) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rutina.getDescripcion());
            stmt.setDate(2, new java.sql.Date(rutina.getFechaAsignacion().getTime()));
            stmt.setDate(3, new java.sql.Date(rutina.getFechaFinalizacion().getTime()));
            stmt.setInt(4, rutina.getIdCliente());
            stmt.setString(5, rutina.getNombre());
            stmt.setInt(6, rutina.getIdEntrenador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rutina> obtenerRutinas() {
        List<Rutina> rutinas = new ArrayList<>();
        String sql = "SELECT * FROM rutinas";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Rutina rutina = new Rutina(
                    rs.getInt("id_rutina"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_asignacion"),
                    rs.getDate("fecha_finaliza"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("id_entrenador")
                      
                );
                rutinas.add(rutina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rutinas;
    }

    public void actualizarRutina(Rutina rutina) {
        if (!existeCliente(rutina.getIdCliente())) {
            System.out.println("Error: El ID del cliente no existe.");
            return;
        }
        if (!existeEntrenador(rutina.getIdEntrenador())) {
            System.out.println("Error: El ID del entrenador no existe.");
            return;
        }

        String sql = "UPDATE rutinas SET descripcion = ?, fecha_asignacion = ?, fecha_finaliza = ?,id_cliente = ?,nombre_cliente = ?, id_entrenador = ? WHERE id_rutina = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rutina.getDescripcion());
            stmt.setDate(2, new java.sql.Date(rutina.getFechaAsignacion().getTime()));
            stmt.setDate(3, new java.sql.Date(rutina.getFechaFinalizacion().getTime()));
            stmt.setInt(4, rutina.getIdCliente());
            stmt.setString(5,rutina.getNombre());
            stmt.setInt(6, rutina.getIdEntrenador());
            stmt.setInt(7, rutina.getIdRutina());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarRutina(int idRutina) {
        String sql = "DELETE FROM rutinas WHERE id_rutina = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRutina);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    
    public boolean clienteTieneRutina(int idCliente) {
        String sql = "SELECT COUNT(*) FROM rutinas WHERE id_cliente = ?";
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
    
    
    public Rutina obtenerRutinaPorId(int idRutina) {
        String sql = "SELECT * FROM rutinas WHERE id_rutina = ?";
        try (Connection conn = getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRutina);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Rutina(
                        rs.getInt("id_rutina"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_asignacion"),
                        rs.getDate("fecha_finaliza"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getInt("id_entrenador")
                     
                    );
                } else {
                    return null; // No se encontró una rutina con ese ID
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la rutina con ID " + idRutina + ": " + e.getMessage());
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