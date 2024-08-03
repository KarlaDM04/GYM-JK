package Controlador;

import Modelo.Entrenador;
import Modelo.EntrenadorDAO;
import Vista.GestionarEntrenador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**.
 * 
 * @author KARLARASLIN
 */
public class ControladorGestionarEntrenador {
    private GestionarEntrenador vista;
    private EntrenadorDAO modelo;

    public ControladorGestionarEntrenador(GestionarEntrenador vista, EntrenadorDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarEntrenadores();

        this.vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEntrenador();
            }
        });

        this.vista.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEntrenador();
            }
        });

        this.vista.getTablaEntrenadores().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer modificar los datos?", "Confirmar actualización", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        actualizarEntrenador();
                    }
                }
            }
        });
    }

    private void cargarEntrenadores() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaEntrenadores().getModel();
        model.setRowCount(0);
        for (Entrenador entrenador : modelo.obtenerEntrenadores()) {
            model.addRow(new Object[]{
                entrenador.getIdEntrenador(),
                entrenador.getNombre(),
                entrenador.getEspecialidad(),
                entrenador.getIdUsuario(),
                entrenador.getTurno()
            });
        }
    }

    private void eliminarEntrenador() {
        int selectedRow = vista.getTablaEntrenadores().getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer eliminar este entrenador?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    int idEntrenador = (int) vista.getTablaEntrenadores().getValueAt(selectedRow, 0);
                    modelo.eliminarEntrenador(idEntrenador);
                    cargarEntrenadores();
                    JOptionPane.showMessageDialog(vista, "Entrenador eliminado exitosamente.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar el entrenador: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un Entrenador para eliminar.");
        }
    }

    private void actualizarEntrenador() {
        int selectedRow = vista.getTablaEntrenadores().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int idEntrenador = (int) vista.getTablaEntrenadores().getValueAt(selectedRow, 0);
                String nombre = (String) vista.getTablaEntrenadores().getValueAt(selectedRow, 1);
                String especialidad = (String) vista.getTablaEntrenadores().getValueAt(selectedRow, 2);
                int idUsuario = (int) vista.getTablaEntrenadores().getValueAt(selectedRow, 3);
                String turno = (String) vista.getTablaEntrenadores().getValueAt(selectedRow, 4);

                Entrenador entrenador = new Entrenador(idEntrenador,nombre, especialidad, idUsuario, turno);
                modelo.actualizarEntrenador(entrenador);

                cargarEntrenadores();
                JOptionPane.showMessageDialog(vista, "Entrenador actualizado exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar el entrenador: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un Entrenador para actualizar.");
        }
    }
}
