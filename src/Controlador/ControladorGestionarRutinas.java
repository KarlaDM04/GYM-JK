/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Rutina;
import Modelo.RutinaDAO;
import Vista.GestionarRutinas;
import java.util.Date;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @autor Jenny
 */
public class ControladorGestionarRutinas {
    private GestionarRutinas vista;
    private RutinaDAO modelo;

    public ControladorGestionarRutinas(GestionarRutinas vista, RutinaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarRutinas();

        this.vista.getBtnEliminar().addActionListener(e -> eliminarRutina());
        this.vista.getBtnActualizar().addActionListener(e -> actualizarRutina());

        this.vista.getTablaRutinas().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int selectedRow = vista.getTablaRutinas().getSelectedRow();
                    if (selectedRow != -1) {
                        int result = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea actualizar esta rutina?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            actualizarRutina();
                        }
                    }
                }
            }
        });
        
        this.vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarRutina();
            }
        });
    }

    private void cargarRutinas() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaRutinas().getModel();
        model.setRowCount(0);

        for (Rutina rutina : modelo.obtenerRutinas()) {
            model.addRow(new Object[]{
                rutina.getIdRutina(),
                rutina.getDescripcion(),
                rutina.getFechaAsignacion(),
                rutina.getFechaFinalizacion(),
                rutina.getIdCliente(),
                rutina.getNombre(),
                rutina.getIdEntrenador()
            });
        }
    }

    private void eliminarRutina() {
        try {
            int selectedRow = vista.getTablaRutinas().getSelectedRow();
            if (selectedRow != -1) {
                int idRutina = (int) vista.getTablaRutinas().getValueAt(selectedRow, 0);
                int result = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea eliminar esta rutina?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    modelo.eliminarRutina(idRutina);
                    cargarRutinas();
                    JOptionPane.showMessageDialog(vista, "Rutina eliminada exitosamente.");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione una Rutina para eliminar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al eliminar la rutina: " + ex.getMessage());
        }
    }

    private void actualizarRutina() {
        try {
            int selectedRow = vista.getTablaRutinas().getSelectedRow();
            if (selectedRow != -1) {
                int idRutina = (int) vista.getTablaRutinas().getValueAt(selectedRow, 0);
                String descripcion = (String) vista.getTablaRutinas().getValueAt(selectedRow, 1);
                Date fechaAsignacion = (Date) vista.getTablaRutinas().getValueAt(selectedRow, 2);
                Date fechaFinaliza = (Date) vista.getTablaRutinas().getValueAt(selectedRow, 3);
                int idCliente = (int) vista.getTablaRutinas().getValueAt(selectedRow, 4);
                String cliente=(String)vista.getTablaRutinas().getValueAt(selectedRow, 5);
                int idEntrenador = (int) vista.getTablaRutinas().getValueAt(selectedRow, 6);

                Rutina rutina = new Rutina(idRutina, descripcion, fechaAsignacion,fechaFinaliza, idCliente,cliente, idEntrenador);
                modelo.actualizarRutina(rutina);

                cargarRutinas();
                JOptionPane.showMessageDialog(vista, "Rutina actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione una Rutina para actualizar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al actualizar la rutina: " + ex.getMessage());
        }
    }
    private void buscarRutina() {
        try {
            int idBuscado = Integer.parseInt(vista.getTxtID().getText());
            Rutina rutinaEncontrada = modelo.obtenerRutinaPorId(idBuscado);

            if (rutinaEncontrada != null) {
                DefaultTableModel model = (DefaultTableModel) vista.getTablaRutinas().getModel();
                model.setRowCount(0);
                model.addRow(new Object[]{
                    rutinaEncontrada.getIdRutina(),
                    rutinaEncontrada.getDescripcion(),
                    rutinaEncontrada.getFechaAsignacion(),
                    rutinaEncontrada.getFechaFinalizacion(),
                    rutinaEncontrada.getIdCliente(),
                    rutinaEncontrada.getNombre(),
                    rutinaEncontrada.getIdEntrenador()
                });
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró una rutina con ese ID");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingresa un ID válido");
        }
    }
}
