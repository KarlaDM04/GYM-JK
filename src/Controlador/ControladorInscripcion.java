/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Vista.RegistroInscricpion;
import Modelo.Inscripcion;
import Modelo.InscripcionDAO;
import Modelo.Membresia;
import Modelo.MembresiaDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author KARLARASLIN
 */
public class ControladorInscripcion {
    private RegistroInscricpion vista;
    private InscripcionDAO modelo;
    private ClienteDAO model;
    private MembresiaDAO mod;

    public ControladorInscripcion(RegistroInscricpion vista, InscripcionDAO modelo, ClienteDAO model, MembresiaDAO mod) {
        this.vista = vista;
        this.modelo = modelo;
        this.model = model; 
        this.mod = mod; 
        this.vista.getRegistrarButton().addActionListener(e -> registrarInscripcion());
        
        cargarComboBoxInscripciones();
        cargarComboBoxMembresias();
    }

    private void registrarInscripcion() {
        try {
            java.util.Date fechaInicio = vista.getCalendarioFechaInicio().getDate();
            java.util.Date fechaFin = vista.getCalendarioFechaFin().getDate();
            int idCliente = Integer.parseInt(((String) vista.getComboBoxInscripciones().getSelectedItem()).split(" - ")[0]);
            String nombre = ((String) vista.getComboBoxInscripciones().getSelectedItem()).split(" - ")[1];
            //String nombre=vista.getTxtName().getText();
            int idMembresia = Integer.parseInt(((String) vista.getComboBoxMembresias().getSelectedItem()).split(" - ")[0]);
            //String tipo = ((String) vista.getComboBoxMembresias().getSelectedItem()).split(" - ")[1];

            if (fechaFin.before(fechaInicio)) {
                JOptionPane.showMessageDialog(vista, "La fecha de fin no puede ser antes de la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!modelo.existeCliente(idCliente)) {
                JOptionPane.showMessageDialog(vista, "El ID del cliente no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!modelo.existeMembresia(idMembresia)) {
                JOptionPane.showMessageDialog(vista, "El ID de la membresía no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (modelo.clienteTieneInscripcion(idCliente)) {
                JOptionPane.showMessageDialog(vista, "El cliente ya tiene una inscripción activa.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Inscripcion inscripcion = new Inscripcion(0, fechaInicio, fechaFin, idCliente, nombre, idMembresia);

            modelo.agregarInscripcion(inscripcion);
            JOptionPane.showMessageDialog(vista, "Inscripción registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.getComboBoxInscripciones().getSelectedItem();
            vista.getComboBoxMembresias().getSelectedItem();
            vista.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al registrar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarComboBoxInscripciones() {
        try {
            List<Cliente> clientes = model.obtenerClientes();
            DefaultComboBoxModel<String> modeloo = new DefaultComboBoxModel<>();
            for (Cliente cliente : clientes) {
                String item = cliente.getIdCliente() + " - " + cliente.getNombre();
                modeloo.addElement(item); 
            }
            vista.getComboBoxInscripciones().setModel(modeloo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al cargar los clientes.");
        }
    }
    
    private void cargarComboBoxMembresias() {
        try {
            List<Membresia> membresias = mod.obtenerMembresias();
            DefaultComboBoxModel<String> modeloo = new DefaultComboBoxModel<>();
            for (Membresia membresia : membresias) {
                String item = membresia.getIdMembresia() + " - " + membresia.getTipo();
                modeloo.addElement(item); 
            }
            vista.getComboBoxMembresias().setModel(modeloo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al cargar las membresias.");
        }
    }
}