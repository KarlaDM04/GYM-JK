/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Inscripcion;
import Modelo.InscripcionDAO;
import Modelo.Membresia;
import Vista.GestionInscipcionCliente;
import Vista.GestionInscripcionCliente;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @autor KARLARASLIN
 */
public class ControladorVistaInscripcion {
    private GestionInscripcionCliente vista;
    private InscripcionDAO modelo;

    public ControladorVistaInscripcion(GestionInscripcionCliente vista, InscripcionDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarInscripciones();
        cargarComboBoxMembresias();
        this.vista.getBtnActualizar().addActionListener(e -> actualizarInscripcion());

        this.vista.getTablaInscripciones().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    int selectedRow = vista.getTablaInscripciones().getSelectedRow();
                    if (selectedRow != -1) {
                        int result = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea actualizar esta inscripción?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            actualizarInscripcion();
                        }
                    }
                }
            }
        });
    }

    private void cargarInscripciones() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaInscripciones().getModel();
        model.setRowCount(0);

        for (Inscripcion inscripcion : modelo.obtenerInscripciones()) {
            model.addRow(new Object[]{
                inscripcion.getIdInscripcion(),
                inscripcion.getFechaInicio(),
                inscripcion.getFechaFin(),
                inscripcion.getIdCliente(),
                inscripcion.getNombreCliente(),
                inscripcion.getIdMembresia()
            });
        }
    }
    
    private void cargarComboBoxMembresias() {
        List<Membresia> membresias = modelo.obtenerTodasLasMembresias();
        JComboBox<Membresia> comboBoxM = vista.getComboBoxMembresias();
        comboBoxM.removeAllItems();
        for (Membresia membresia : membresias) {
            comboBoxM.addItem(membresia);
        }
    }

    private void actualizarInscripcion() {
        try {
            int selectedRow = vista.getTablaInscripciones().getSelectedRow();
            if (selectedRow != -1) {
                int idInscripcion = (int) vista.getTablaInscripciones().getValueAt(selectedRow, 0);
                Date fechaInicio = (Date) vista.getTablaInscripciones().getValueAt(selectedRow, 1);
                Date fechaFin = (Date) vista.getTablaInscripciones().getValueAt(selectedRow, 2);
                int idCliente = (int) vista.getTablaInscripciones().getValueAt(selectedRow, 3);
                String nonmbre=(String)vista.getTablaInscripciones().getValueAt(selectedRow, 4);
                Membresia membresia=(Membresia)vista.getTablaInscripciones().getValueAt(selectedRow, 5);
                int idMembresiaN = membresia.getIdMembresia();

                if (modelo.existeMembresia(idMembresiaN)) {
                    Inscripcion inscripcion = new Inscripcion(idInscripcion, fechaInicio, fechaFin, idCliente, nonmbre,idMembresiaN);
                    modelo.actualizarInscripcion(inscripcion);

                    cargarInscripciones();
                    JOptionPane.showMessageDialog(vista, "Inscripción actualizada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(vista, "La membresía no existe. Consulte las membresías existentes.");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione una Inscripción para actualizar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al actualizar la inscripción: " + ex.getMessage());
        }
    }

    /*public void solicitarIdCliente() {
        String idClienteStr = JOptionPane.showInputDialog(null, "Ingrese el ID del cliente:");
        if (idClienteStr != null && !idClienteStr.isEmpty()) {
            try {
                int idCliente = Integer.parseInt(idClienteStr);
                mostrarInscripcionesPorCliente(idCliente);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El ID ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }*/

    public void mostrarInscripcionesPorCliente(int idCliente) {
        List<Inscripcion> inscripciones = modelo.obtenerInscripcionesPorCliente(idCliente);
        DefaultTableModel model = (DefaultTableModel) vista.getTablaInscripciones().getModel();
        model.setRowCount(0);

        for (Inscripcion inscripcion : inscripciones) {
            model.addRow(new Object[]{
                inscripcion.getIdInscripcion(),
                inscripcion.getFechaInicio(),
                inscripcion.getFechaFin(),
                inscripcion.getIdCliente(),
                inscripcion.getNombreCliente(),
                inscripcion.getIdMembresia()
            });
        }
        
        vista.setVisible(true);
    }
}
