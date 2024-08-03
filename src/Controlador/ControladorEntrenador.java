/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
/**
 *
 * @author KARLARASLIN
 */
import Modelo.Entrenador;
import Modelo.EntrenadorDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.RegistroEntrenador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ControladorEntrenador {
    private RegistroEntrenador vista;
    private EntrenadorDAO modelo;
    private UsuarioDAO model;

    public ControladorEntrenador(RegistroEntrenador vista, EntrenadorDAO modelo, UsuarioDAO model) {
        this.vista = vista;
        this.modelo = modelo;
        this.model = model; 
        this.vista.getRegistrarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEntrenador();
            }
        });
        cargarComboBoxEntrenadores();
    }

    private void registrarEntrenador() {
        try {
            //String nombre = vista.getNombre().getText();
            String especialidad = vista.getEspecialidadField().getText();
            
            int idUsuario = Integer.parseInt(((String) vista.getComboBoxEntrenadores().getSelectedItem()).split(" - ")[0]);
            String nombre = ((String) vista.getComboBoxEntrenadores().getSelectedItem()).split(" - ")[1];
            
            String turno = (String)vista.getTurnoField().getSelectedItem();
            if (!modelo.esRolEntrenador(idUsuario)) {
                javax.swing.JOptionPane.showMessageDialog(vista, "El usuario no tiene el rol de 'Entrenador'.");
                return;
            }
            if (modelo.existeEntrenador(idUsuario)) {
                javax.swing.JOptionPane.showMessageDialog(vista, "El entrenador ya está registrado.");
                return;
            }
            Entrenador entrenador = new Entrenador(0,nombre, especialidad, idUsuario, turno);
            modelo.agregarEntrenador(entrenador);
            javax.swing.JOptionPane.showMessageDialog(vista, "Entrenador registrado exitosamente!");
            vista.getEspecialidadField().setText("");
            vista.getComboBoxEntrenadores().getSelectedItem();
            //vista.getNombre().setText("");
            vista.dispose();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(vista, "ID Usuario debe ser un número válido.");
        }
    }
    
    private void cargarComboBoxEntrenadores() {
        try {
            List<Usuario> usuarios = model.obtenerUsuarios();
            DefaultComboBoxModel<String> modeloo = new DefaultComboBoxModel<>();
            for (Usuario usuario : usuarios) {
                String item = usuario.getIdUsuario() + " - " + usuario.getNombre();
                modeloo.addElement(item); 
            }
            vista.getComboBoxEntrenadores().setModel(modeloo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al cargar los usuarios.");
        }
    }
}