package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Utilerias.Validacion;
import javax.swing.JOptionPane;
/**
 *
 * @author KARLARASLIN
 */
public class ClienteDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "Itachiuchiha123";
    private UsuarioDAO usuarioDAO = new UsuarioDAO(); // Instancia para verificar el rol del usuario
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void agregarCliente(Cliente cliente) throws SQLException {
        String rol = usuarioDAO.obtenerRolUsuario(cliente.getIdUsuario());
        if (rol == null || !rol.equals("Cliente")) {
            throw new SQLException("El usuario no tiene el rol adecuado para ser registrado como cliente.");
        }

        if (clienteYaRegistrado(cliente.getIdUsuario())) {
            throw new SQLException("El cliente ya está registrado.");
        }

        String sql = "INSERT INTO Cliente (fecha_Nacimiento, edad, peso, estatura, imc, id_Usuario, nombre) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            stmt.setInt(2, cliente.getEdad());
            stmt.setDouble(3, cliente.getPeso());
            stmt.setDouble(4, cliente.getEstatura());
            stmt.setDouble(5, cliente.getImc());
            stmt.setInt(6, cliente.getIdUsuario());
            stmt.setString(7, cliente.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_Cliente"));
                cliente.setFechaNacimiento(rs.getDate("fecha_Nacimiento"));
                cliente.setEdad(rs.getInt("edad"));
                cliente.setPeso(rs.getDouble("peso"));
                cliente.setEstatura(rs.getDouble("estatura"));
                cliente.setImc(rs.getDouble("imc"));
                cliente.setIdUsuario(rs.getInt("id_Usuario"));
                cliente.setNombre(rs.getString("nombre"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET fecha_Nacimiento = ?, edad = ?, peso = ?, estatura = ?, imc = ?, id_Usuario = ?, nombre = ?  WHERE id_Cliente = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            stmt.setInt(2, Validacion.calcularEdad(new java.sql.Date(cliente.getFechaNacimiento().getTime())));
            stmt.setDouble(3, cliente.getPeso());
            stmt.setDouble(4, cliente.getEstatura());
            stmt.setDouble(5, cliente.getImc());
            stmt.setInt(6, cliente.getIdUsuario());
            stmt.setString(7, cliente.getNombre());
            stmt.setInt(8, cliente.getIdCliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCliente(int idCliente) {
        String sql = "DELETE FROM Cliente WHERE id_Cliente = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean clienteYaRegistrado(int idUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cliente WHERE id_Usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> entrenadores = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario entrenador = new Usuario(
                    rs.getInt("id_Usuario"),
                    rs.getString("nombre"),
                    rs.getString("apeP"),
                    rs.getString("apeM"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("rol"),
                    rs.getString("contrasenia")
                );
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }
    
}
