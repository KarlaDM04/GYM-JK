/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.Login;
import Vista.NewUser;
import Vista.VistaCliente;
import Vista.VistaEntrenador;
import Vista.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author KARLARASLIN
 */
public class ControladorLogin {
    private Login vista;
    private UsuarioDAO modelo;
    private NewUser ventana;
    private int idUsuario;

    public ControladorLogin(Login vista, UsuarioDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setVisible(true);
        ventana=new NewUser();
        //this.ventana.setVisible(true);
        this.vista.getBtnIngresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
        
        this.vista.getBtnNuevaCuenta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(true);
                new ControladorNewUser(ventana,modelo);
            }
        });
    }

    public void autenticarUsuario() {
        String correo = vista.getTxtCorreoLogin().getText();
        String contrasenia = new String(vista.getTxtContraseniaLogin().getPassword());
        Usuario usuario = buscarUsuario(correo, contrasenia);
        if (usuario != null) {
            idUsuario = usuario.getIdUsuario();
            switch (usuario.getRol().toLowerCase()) {
                case "encargado":
                    mostrarVentanaEncargado();
                    VistaMenu encargado = new VistaMenu();
                    encargado.setVisible(true);
                    break;
                case "entrenador":
                    mostrarVentanaEntrenador();
                    VistaEntrenador entrenador=new VistaEntrenador();
                    entrenador.setVisible(true);
                    break;
                case "cliente":
                    mostrarVentanaCliente();
                    VistaCliente cliente=new VistaCliente();
                    cliente.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(vista, "Usuario no registrado.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.");
        }
    }

    public Usuario buscarUsuario(String correo, String contrasenia) {
        for (Usuario usuario : modelo.obtenerUsuarios()) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    public void mostrarVentanaEncargado() {
        JOptionPane.showMessageDialog(vista, "Bienvenido, Encargado.");
    }

    public void mostrarVentanaEntrenador() {
        JOptionPane.showMessageDialog(vista, "Bienvenido, Entrenador.");
    }

    public void mostrarVentanaCliente() {
        JOptionPane.showMessageDialog(vista, "Bienvenido, Cliente.");
    }
}
