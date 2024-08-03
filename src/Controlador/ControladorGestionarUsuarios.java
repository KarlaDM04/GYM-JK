/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.GestionarUsuarios;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author KARLARASLIN
 */
public class ControladorGestionarUsuarios {
    private GestionarUsuarios vista;
    private UsuarioDAO modelo;

    public ControladorGestionarUsuarios(GestionarUsuarios vista, UsuarioDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        cargarUsuarios();

        this.vista.getBtnEliminar().addActionListener(e -> eliminarUsuario());
        this.vista.getBtnActualizar().addActionListener(e -> actualizarUsuario());

        this.vista.getTablaUsuarios().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int selectedRow = vista.getTablaUsuarios().getSelectedRow();
                    if (selectedRow != -1) {
                        int result = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea actualizar este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            actualizarUsuario();
                        }
                    }
                }
            }
        });
    }

    private void cargarUsuarios() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaUsuarios().getModel();
        model.setRowCount(0);
        for (Usuario usuario : modelo.obtenerUsuarios()) {
            model.addRow(new Object[]{
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoP(),
                usuario.getApellidoM(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getRol(),
                usuario.getContrasenia()
            });
        }
    }

    private void eliminarUsuario() {
        try {
            int selectedRow = vista.getTablaUsuarios().getSelectedRow();
            if (selectedRow != -1) {
                int idUsuario = (int) vista.getTablaUsuarios().getValueAt(selectedRow, 0);
                int result = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea eliminar este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    modelo.eliminarUsuario(idUsuario);
                    cargarUsuarios();
                    JOptionPane.showMessageDialog(vista, "Usuario eliminado exitosamente.");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione un usuario para eliminar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al eliminar el usuario: " + ex.getMessage());
        }
    }

    private void actualizarUsuario() {
        try {
            int selectedRow = vista.getTablaUsuarios().getSelectedRow();
            if (selectedRow != -1) {
                int idUsuario = (int) vista.getTablaUsuarios().getValueAt(selectedRow, 0);
                String nombre = (String) vista.getTablaUsuarios().getValueAt(selectedRow, 1);
                String apeP = (String) vista.getTablaUsuarios().getValueAt(selectedRow, 2);
                String apeM = (String) vista.getTablaUsuarios().getValueAt(selectedRow, 3);
                String correo = (String) vista.getTablaUsuarios().getValueAt(selectedRow, 4);
                String telefono = (String) vista.getTablaUsuarios().getValueAt(selectedRow, 5);
                String rol = (String) vista.getTablaUsuarios().getValueAt(selectedRow, 6);
                String contrasenia = (String) vista.getTablaUsuarios().getValueAt(selectedRow, 7);

                Usuario usuario = new Usuario(idUsuario, nombre, apeP, apeM, correo, telefono, rol, contrasenia);
                modelo.actualizarUsuario(usuario);

                cargarUsuarios();
                JOptionPane.showMessageDialog(vista, "Usuario actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione un usuario para actualizar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al actualizar el usuario: " + ex.getMessage());
        }
    }
}
