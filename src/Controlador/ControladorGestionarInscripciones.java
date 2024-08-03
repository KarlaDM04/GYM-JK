package Controlador;

import Modelo.Inscripcion;
import Modelo.InscripcionDAO;
import Vista.GestionarInscripciones;
import java.util.Date;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para gestionar inscripciones.
 * 
 * @autor KARLARASLIN
 */
public class ControladorGestionarInscripciones {
    private GestionarInscripciones vista;
    private InscripcionDAO modelo;

    public ControladorGestionarInscripciones(GestionarInscripciones vista, InscripcionDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarInscripciones();

        this.vista.getBtnEliminar().addActionListener(e -> eliminarInscripcion());
        this.vista.getBtnActualizar().addActionListener(e -> actualizarInscripcion());

        this.vista.getTablaInscripciones().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer modificar los datos?", "Confirmar actualización", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        actualizarInscripcion();
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

    private void eliminarInscripcion() {
        int selectedRow = vista.getTablaInscripciones().getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer eliminar esta inscripción?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    int idInscripcion = (int) vista.getTablaInscripciones().getValueAt(selectedRow, 0);
                    modelo.eliminarInscripcion(idInscripcion);
                    cargarInscripciones();
                    JOptionPane.showMessageDialog(vista, "Inscripción eliminada exitosamente.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la inscripción: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una Inscripción para eliminar.");
        }
    }

    private void actualizarInscripcion() {
        int selectedRow = vista.getTablaInscripciones().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int idInscripcion = (int) vista.getTablaInscripciones().getValueAt(selectedRow, 0);
                Date fechaInicio = (Date) vista.getTablaInscripciones().getValueAt(selectedRow, 1);
                Date fechaFin = (Date) vista.getTablaInscripciones().getValueAt(selectedRow, 2);
                int idCliente = (int) vista.getTablaInscripciones().getValueAt(selectedRow, 3);
                String nombre=(String)vista.getTablaInscripciones().getValueAt(selectedRow, 4);
                int idMembresia = (int) vista.getTablaInscripciones().getValueAt(selectedRow, 5);

                Inscripcion inscripcion = new Inscripcion(idInscripcion, fechaInicio, fechaFin, idCliente,nombre, idMembresia);
                modelo.actualizarInscripcion(inscripcion);

                cargarInscripciones();
                JOptionPane.showMessageDialog(vista, "Inscripción actualizada exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar la inscripción: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una Inscripción para actualizar.");
        }
    }
}
