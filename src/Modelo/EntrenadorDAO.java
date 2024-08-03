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
public class EntrenadorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "Itachiuchiha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarEntrenador(Entrenador entrenador) {
        String sql = "INSERT INTO entrenador (nombre, especialidad, id_Usuario, turno) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entrenador.getNombre());
            stmt.setString(2, entrenador.getEspecialidad());
            stmt.setInt(3, entrenador.getIdUsuario());
            stmt.setString(4, entrenador.getTurno());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entrenador> obtenerEntrenadores() {
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

    public void actualizarEntrenador(Entrenador entrenador) {
        String sql = "UPDATE entrenador SET nombre = ?, especialidad = ?, id_usuario = ?, turno = ? WHERE id_entrenador = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entrenador.getNombre());
            stmt.setString(2, entrenador.getEspecialidad());
            stmt.setInt(3, entrenador.getIdUsuario());
            stmt.setString(4, entrenador.getTurno());
            stmt.setInt(5, entrenador.getIdEntrenador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEntrenador(int idEntrenador) {
        String sql = "DELETE FROM entrenador WHERE id_entrenador = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEntrenador);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean esRolEntrenador(int idUsuario) {
        String sql = "SELECT rol FROM usuario WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "Entrenador".equals(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeEntrenador(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM entrenador WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
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

}
