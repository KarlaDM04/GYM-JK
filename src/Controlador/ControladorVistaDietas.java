/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Dieta;
import Modelo.DietaDAO;
import Modelo.Entrenador;
import Modelo.EntrenadorDAO;
import Vista.GestionarDietaCliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;

public class ControladorVistaDietas {
    private GestionarDietaCliente vista;
    private DietaDAO modelo;
   // private EntrenadorDAO entrenadorDAO;  // Asegúrate de importar esta clase

    public ControladorVistaDietas(GestionarDietaCliente vista, DietaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarDietas();
        cargarComboBoxEntrenadores();

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
    }

    private void cargarDietas() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaDietas().getModel();
        model.setRowCount(0);

        for (Dieta dieta : modelo.obtenerDietas()) {
            model.addRow(new Object[]{
                dieta.getIdDieta(),
                dieta.getDescripcion(),
                dieta.getFechaAsignacion(),
                dieta.getFechaFinaliza(),
                dieta.getIdCliente(),
                dieta.getNombreCliente(),
                dieta.getIdEntrenador()
           });
        }
    }

    private void cargarComboBoxEntrenadores() {
        List<Entrenador> entrenadores = modelo.obtenerTodosLosEntrenadores();
        JComboBox<Entrenador> comboBoxEntrenadores = vista.getComboBoxEntrenadores();
        comboBoxEntrenadores.removeAllItems();
        for (Entrenador entrenador : entrenadores) {
            comboBoxEntrenadores.addItem(entrenador);
        }
    }

    private void actualizarDieta() {
        try {
        int selectedRow = vista.getTablaDietas().getSelectedRow();
        if (selectedRow != -1) {
            int idDieta = (int) vista.getTablaDietas().getValueAt(selectedRow, 0);
            String descripcion = (String) vista.getTablaDietas().getValueAt(selectedRow, 1);
            Date fechaAsignacion = (Date) vista.getTablaDietas().getValueAt(selectedRow, 2);
            Date fechaFinaliza = (Date) vista.getTablaDietas().getValueAt(selectedRow, 3);
            int idCliente = (int) vista.getTablaDietas().getValueAt(selectedRow, 4);
            String cliente=(String)vista.getTablaDietas().getValueAt(selectedRow, 5);
            Entrenador entrenadorSeleccionado = (Entrenador) vista.getTablaDietas().getValueAt(selectedRow, 6);
            int idEntrenador=entrenadorSeleccionado.getIdEntrenador();

            Dieta dieta = new Dieta(idDieta, descripcion, fechaAsignacion, fechaFinaliza, idCliente,cliente, idEntrenador);
            modelo.actualizarDieta(dieta);

            cargarDietas();
            JOptionPane.showMessageDialog(vista, "Dieta actualizada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una Dieta para actualizar.");
        }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al actualizar la rutina: " + ex.getMessage());
        }
    }
}
