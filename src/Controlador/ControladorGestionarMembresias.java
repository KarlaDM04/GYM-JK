package Controlador;

import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Vista.GestionarMembresia;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @autor KARLARASLIN
 */
public class ControladorGestionarMembresias {
    private GestionarMembresia vista;
    private MembresiaDAO modelo;

    public ControladorGestionarMembresias(GestionarMembresia vista, MembresiaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarMembresias();

        this.vista.getBtnEliminar().addActionListener(e -> eliminarMembresia());
        this.vista.getBtnActualizar().addActionListener(e -> actualizarMembresia());

        this.vista.getTablaMembresias().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer modificar los datos?", "Confirmar actualización", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        actualizarMembresia();
                    }
                }
            }
        });
    }

    private void cargarMembresias() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaMembresias().getModel();
        model.setRowCount(0);
        for (Membresia membresia : modelo.obtenerMembresias()) {
            model.addRow(new Object[]{
                membresia.getIdMembresia(),
                membresia.getTipo(),
                membresia.getPrecio(),
                membresia.getDuracion()
            });
        }
    }

    private void eliminarMembresia() {
        int selectedRow = vista.getTablaMembresias().getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer eliminar esta membresía?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    int idMembresia = (int) vista.getTablaMembresias().getValueAt(selectedRow, 0);
                    modelo.eliminarMembresia(idMembresia);
                    cargarMembresias();
                    JOptionPane.showMessageDialog(vista, "Membresía eliminada exitosamente.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la membresía: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una Membresía para eliminar.");
        }
    }

    private void actualizarMembresia() {
        int selectedRow = vista.getTablaMembresias().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int idMembresia = (int) vista.getTablaMembresias().getValueAt(selectedRow, 0);
                String tipo = (String) vista.getTablaMembresias().getValueAt(selectedRow, 1);
                double precio = (double) vista.getTablaMembresias().getValueAt(selectedRow, 2);
                int duracion = (int) vista.getTablaMembresias().getValueAt(selectedRow, 3);

                Membresia membresia = new Membresia(idMembresia, tipo, precio, duracion);
                modelo.actualizarMembresia(membresia);

                cargarMembresias();
                JOptionPane.showMessageDialog(vista, "Membresía actualizada exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar la membresía: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una Membresía para actualizar.");
        }
    }
}
