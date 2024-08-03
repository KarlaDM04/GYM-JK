package Controlador;

import Modelo.Dieta;
import Modelo.DietaDAO;
import Vista.GestionarDietas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KARLARASLIN
 */
public class ControladorGestionarDietas {
    private GestionarDietas vista;
    private DietaDAO modelo;

    public ControladorGestionarDietas(GestionarDietas vista, DietaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        cargarDietas();
        
        this.vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDieta();
            }
        });
        
        this.vista.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDieta();
            }
        });
        
        this.vista.getTablaDietas().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer modificar los datos?", "Confirmar actualización", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        actualizarDieta();
                    }
                }
            }
        });
        
        this.vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDieta();
            }
        });
    }

    private void cargarDietas() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaDietas().getModel();
        model.setRowCount(0);
        for (Dieta rutina : modelo.obtenerDietas()) {
            model.addRow(new Object[]{
                rutina.getIdDieta(),
                rutina.getDescripcion(),
                rutina.getFechaAsignacion(),
                rutina.getFechaFinaliza(),
                rutina.getIdCliente(),
                rutina.getNombreCliente(),
                rutina.getIdEntrenador()
            });
        }
    }

    private void eliminarDieta() {
        int selectedRow = vista.getTablaDietas().getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer eliminar esta dieta?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    int idDieta = (int) vista.getTablaDietas().getValueAt(selectedRow, 0);
                    modelo.eliminarDieta(idDieta);
                    cargarDietas();
                    JOptionPane.showMessageDialog(vista, "Dieta eliminada exitosamente.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la dieta: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una Dieta para eliminar.");
        }
    }

    private void actualizarDieta() {
        int selectedRow = vista.getTablaDietas().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int idDieta = (int) vista.getTablaDietas().getValueAt(selectedRow, 0);
                String descripcion = (String) vista.getTablaDietas().getValueAt(selectedRow, 1);

                Date fechaAsignacion = (Date) vista.getTablaDietas().getValueAt(selectedRow, 2);
                Date fechaFinaliza = (Date) vista.getTablaDietas().getValueAt(selectedRow, 3);
                int idCliente = (int) vista.getTablaDietas().getValueAt(selectedRow, 4);
                String cliente = (String) vista.getTablaDietas().getValueAt(selectedRow, 5);
                int idEntrenador = (int) vista.getTablaDietas().getValueAt(selectedRow, 6);

                Dieta dieta = new Dieta(idDieta, descripcion, fechaAsignacion, fechaFinaliza, idCliente,cliente, idEntrenador);
                modelo.actualizarDieta(dieta);

                cargarDietas();
                JOptionPane.showMessageDialog(vista, "Dieta actualizada exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar la dieta: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una Dieta para actualizar.");
        }
    }
    
    private void buscarDieta() {
        try {
            int idBuscado = Integer.parseInt(vista.getTxtID().getText());
            Dieta dietaEncontrada = modelo.obtenerDietaPorId(idBuscado);

            if (dietaEncontrada != null) {
                DefaultTableModel model = (DefaultTableModel) vista.getTablaDietas().getModel();
                model.setRowCount(0);
                model.addRow(new Object[]{
                    dietaEncontrada.getIdDieta(),
                    dietaEncontrada.getDescripcion(),
                    dietaEncontrada.getFechaAsignacion(),
                    dietaEncontrada.getFechaFinaliza(),
                    dietaEncontrada.getIdCliente(),
                    dietaEncontrada.getNombreCliente(),
                    dietaEncontrada.getIdEntrenador()
                });
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró una dieta con ese ID");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingresa un ID válido");
        }
    }
}
