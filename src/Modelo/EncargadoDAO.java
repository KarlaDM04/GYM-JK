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
    public class EncargadoDAO {
        private static final String URL = "jdbc:mysql://localhost:3306/base_de_datos";
        private static final String USER = "usuario";
        private static final String PASSWORD = "contraseña";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarEncargado(Encargado encargado) {
        String sql = "INSERT INTO Encargado (id_Encargado, id_Usuario) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, encargado.getIdEncargado());
            stmt.setInt(2, encargado.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Encargado> obtenerEncargados() {
        List<Encargado> encargados = new ArrayList<>();
        String sql = "SELECT * FROM Encargado";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Encargado encargado = new Encargado();
                encargado.setIdEncargado(rs.getInt("id_Encargado"));
                encargado.setIdUsuario(rs.getInt("id_Usuario"));
		encargados.add(encargado);          
		}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encargados;
    }

    public void actualizarEncargado(Encargado encargado) {
        String sql = "UPDATE Encargado SET id_Encargado = ?, id_Usuario = ? WHERE id_Encargado = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, encargado.getIdEncargado());
            stmt.setInt(2, encargado.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEncargado(int id_Encargado) {
        String sql = "DELETE FROM Encargado WHERE id_Encargado = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_Encargado);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}