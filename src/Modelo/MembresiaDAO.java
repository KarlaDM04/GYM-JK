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
public class MembresiaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "Itachiuchiha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarMembresia(Membresia membresia) {
        String sql = "INSERT INTO membresias (tipo, precio, duracion) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membresia.getTipo());
            stmt.setDouble(2, membresia.getPrecio());
            stmt.setInt(3, membresia.getDuracion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Membresia> obtenerMembresias() {
        List<Membresia> membresias = new ArrayList<>();
        String sql = "SELECT * FROM membresias";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Membresia membresia = new Membresia(
                    rs.getInt("id_Membresia"),
                    rs.getString("tipo"),
                    rs.getDouble("precio"),
                    rs.getInt("duracion")
                );
                membresias.add(membresia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membresias;
    }

    public void actualizarMembresia(Membresia membresia) {
        String sql = "UPDATE membresias SET tipo = ?, precio = ?, duracion = ? WHERE id_membresia = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membresia.getTipo());
            stmt.setDouble(2, membresia.getPrecio());
            stmt.setInt(3, membresia.getDuracion());
            stmt.setInt(4, membresia.getIdMembresia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMembresia(int idMembresia) {
        String sql = "DELETE FROM membresias WHERE id_membresia = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMembresia);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeTipo(String tipo) {
        String sql = "SELECT COUNT(*) FROM membresias WHERE tipo = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
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

