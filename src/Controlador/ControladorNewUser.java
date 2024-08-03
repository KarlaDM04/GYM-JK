/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Utilerias.Validacion;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.NewUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author KARLARASLIN
 */
public class ControladorNewUser {
    private NewUser vista;
    private UsuarioDAO modelo;

    public ControladorNewUser(NewUser vista, UsuarioDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        initListeners();
    }

    private void initListeners() {
        vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vista.getTxtNomUsuario().getText().trim();
                String apellidoP = vista.getTxtApellidoP().getText().trim();
                String apellidoM = vista.getTxtApellidoM().getText().trim();
                String correo = vista.getTxtCorreo().getText().trim();
                String contrasenia = vista.getTxtNContrasenia().getText().trim();
                String telefono = vista.getTxtTelefono().getText().trim(); // Asegúrate de que el JTextField para teléfono esté correctamente nombrado
                String rol = (String) vista.getComboRol().getSelectedItem();
                if (modelo.existeCorreo(correo)) {
                    JOptionPane.showMessageDialog(vista, "El correo electrónico ya está registrado.");
                    return;
                }    
                if (validarDatos(nombre, apellidoP, apellidoM, correo, contrasenia, telefono)) {
                    Usuario usuario = new Usuario(0, nombre, apellidoP, apellidoM, correo, telefono, rol, contrasenia);
                    modelo.agregarUsuario(usuario);
                    JOptionPane.showMessageDialog(vista, "Usuario registrado exitosamente.");
                    vista.dispose();
                }
            }
        });
    }

    private boolean validarDatos(String nombre, String apellidoP, String apellidoM, String correo, String contrasenia, String telefono) {
        Validacion validacion = new Validacion();

        if (!validacion.validarLetras(nombre) || !validacion.validarLetras(apellidoP) || !validacion.validarLetras(apellidoM)) {
            return false;
        }

        if (!Validacion.validarCorreoElectronico(correo)) {
            return false;
        }

        if (!validacion.validarContrasenia(contrasenia)) {
            return false;
        }

        if (!validacion.validarLongitudNumero(telefono, 10)) {
            return false;
        }

        return true;
    }
}
