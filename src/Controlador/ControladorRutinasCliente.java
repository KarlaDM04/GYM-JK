/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Entrenador;
import Modelo.Rutina;
import Modelo.RutinaDAO;
import Vista.GestionarRutinaCliente;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para gestionar rutinas del cliente.
 * 
 * @autor Jenny
 */
public class ControladorRutinasCliente {
    private GestionarRutinaCliente vista;
    private RutinaDAO modelo;

    public ControladorRutinasCliente(GestionarRutinaCliente vista, RutinaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarEntrenadores();
        cargarRutinas();

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
    }

    private void cargarEntrenadores() {
        List<Entrenador> entrenadores = modelo.obtenerTodosLosEntrenadores();
        JComboBox<Entrenador> comboBoxEntrenadores = vista.getComboBoxEntrenadores();
        comboBoxEntrenadores.removeAllItems();
        for (Entrenador entrenador : entrenadores) {
            comboBoxEntrenadores.addItem(entrenador);
        }
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

    private void actualizarRutina() {
        try {
            int selectedRow = vista.getTablaRutinas().getSelectedRow();
            if (selectedRow != -1) {
                int idRutina = (int) vista.getTablaRutinas().getValueAt(selectedRow, 0);
                String descripcion = (String) vista.getTablaRutinas().getValueAt(selectedRow, 1);
                Date fechaAsignacion = (Date) vista.getTablaRutinas().getValueAt(selectedRow, 2);
                Date fechaFinaliza = (Date) vista.getTablaRutinas().getValueAt(selectedRow, 3);
                int idCliente = (int) vista.getTablaRutinas().getValueAt(selectedRow, 4);
                String cliente=(String) vista.getTablaRutinas().getValueAt(selectedRow, 5);
                Entrenador entrenadorSeleccionado = (Entrenador) vista.getTablaRutinas().getValueAt(selectedRow, 6);
                int idEntrenador = entrenadorSeleccionado.getIdEntrenador();

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
}
