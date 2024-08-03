package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Vista.GestionarClientes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KARLARASLIN
 */
public class ControladorGestionarClientes {
    private GestionarClientes vista;
    private ClienteDAO modelo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ControladorGestionarClientes(GestionarClientes vista, ClienteDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        cargarClientes();

        this.vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        this.vista.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });

        this.vista.getTablaClientes().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer modificar los datos?", "Confirmar actualización", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        actualizarCliente();
                    }
                }
            }
        });
    }

    private void cargarClientes() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaClientes().getModel();
        model.setRowCount(0);

        for (Cliente cliente : modelo.obtenerClientes()) {
            model.addRow(new Object[]{
                cliente.getIdCliente(),
                dateFormat.format(cliente.getFechaNacimiento()),
                cliente.getEdad(),
                cliente.getPeso(),
                cliente.getEstatura(),
                cliente.getImc(),
                cliente.getIdUsuario(),
                cliente.getNombre()
            });
        }
    }

    private void eliminarCliente() {
        int selectedRow = vista.getTablaClientes().getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(vista, "¿Está seguro de querer eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    int idCliente = (int) vista.getTablaClientes().getValueAt(selectedRow, 0);
                    modelo.eliminarCliente(idCliente);
                    cargarClientes();
                    JOptionPane.showMessageDialog(vista, "Cliente eliminado exitosamente.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar el cliente: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un Cliente para eliminar.");
        }
    }

    private void actualizarCliente() {
        int selectedRow = vista.getTablaClientes().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int idCliente = (int) vista.getTablaClientes().getValueAt(selectedRow, 0);

                // Convertir fecha de String a Date
                String fechaStr = (String) vista.getTablaClientes().getValueAt(selectedRow, 1);
                Date fechaNacimiento = dateFormat.parse(fechaStr);

                int edad = (int) vista.getTablaClientes().getValueAt(selectedRow, 2);
                double peso = (double) vista.getTablaClientes().getValueAt(selectedRow, 3);
                double estatura = (double) vista.getTablaClientes().getValueAt(selectedRow, 4);
                double imc = calcularIMC(peso, estatura);
                int idUsuario = (int) vista.getTablaClientes().getValueAt(selectedRow, 6);
                String nombre=(String)vista.getTablaClientes().getValueAt(selectedRow, 7);

                Cliente cliente = new Cliente(idCliente, fechaNacimiento, edad, peso, estatura, imc, idUsuario, nombre);
                modelo.actualizarCliente(cliente);

                cargarClientes();
                JOptionPane.showMessageDialog(vista, "Cliente actualizado exitosamente.");
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(vista, "Formato de fecha incorrecto.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar el cliente: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un Cliente para actualizar.");
        }
    }

    private double calcularIMC(double peso, double estatura) {
        return peso / (estatura * estatura);
    }
}
