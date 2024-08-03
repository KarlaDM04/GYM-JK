/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Seguridad.Encriptador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author KARLARASLIN
 */
public class UsuarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "Itachiuchiha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarUsuario(Usuario usuario) {
        if (existeCorreo(usuario.getCorreo())) {
            System.out.println("El correo ya está registrado.");
            return;
        }

        String sql = "INSERT INTO usuario (nombre, apeP, apeM, correo, telefono, rol, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidoP());
            stmt.setString(3, usuario.getApellidoM());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getRol());
            //stmt.setString(7, usuario.getContrasenia());
            String contraseniaEncriptada = Encriptador.encriptarContrasenia(usuario.getContrasenia());
            stmt.setString(7, contraseniaEncriptada);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidoP(rs.getString("apeP"));
                usuario.setApellidoM(rs.getString("apeM"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setRol(rs.getString("rol"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, apeP = ?, apeM = ?, correo = ?, telefono = ?, rol = ?, contrasenia = ? WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidoP());
            stmt.setString(3, usuario.getApellidoM());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getRol());
           // stmt.setString(7, usuario.getContrasenia());
            String contraseniaEncriptada = Encriptador.encriptarContrasenia(usuario.getContrasenia());
            stmt.setString(7, contraseniaEncriptada);

            stmt.setInt(8, usuario.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarUsuario(int idUsuario) {
    String sqlEntrenador = "DELETE FROM entrenador WHERE id_Usuario = ?";
    String sqlUsuario = "DELETE FROM usuario WHERE id_usuario = ?";
    try (Connection conn = getConnection()) {
        conn.setAutoCommit(false); // Iniciar transacción

        try (PreparedStatement stmtEntrenador = conn.prepareStatement(sqlEntrenador)) {
            stmtEntrenador.setInt(1, idUsuario);
            stmtEntrenador.executeUpdate();
        }

        try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
            stmtUsuario.setInt(1, idUsuario);
            stmtUsuario.executeUpdate();
        }

        conn.commit(); // Confirmar transacción
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    public String obtenerRolUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT rol FROM usuario WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("rol");
                }
            }
        }
        return null;
    }
    
    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
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
