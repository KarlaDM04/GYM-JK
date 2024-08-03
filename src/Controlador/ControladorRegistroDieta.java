/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Dieta;
import Modelo.DietaDAO;
import Modelo.Entrenador;
import Modelo.EntrenadorDAO;
import Modelo.Usuario;
import Vista.RegistroDieta;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author KARLARASLIN
 */
public class ControladorRegistroDieta implements ActionListener {
    private RegistroDieta vista;
    private DietaDAO modelo;
    private ClienteDAO model;
    private EntrenadorDAO mod;

    public ControladorRegistroDieta(RegistroDieta vista, DietaDAO modelo, ClienteDAO model, EntrenadorDAO mod) {
        this.vista = vista;
        this.modelo = modelo;
        this.model = model;
        this.mod = mod; 
        this.vista.getBtnRegistrar().addActionListener(this);
        
        cargarComboBoxClientes();
        cargarComboBoxEntrenadores();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrarDieta();
        }
    }

    private void registrarDieta() {
        try {
            int idCliente = Integer.parseInt(((String) vista.getComboBoxClientes().getSelectedItem()).split(" - ")[0]);
            String cliente = ((String) vista.getComboBoxClientes().getSelectedItem()).split(" - ")[1];
            
            int idEntrenador = Integer.parseInt(((String) vista.getComboBoxEntrenadores().getSelectedItem()).split(" - ")[0]);
            //String nombreE = ((String) vista.getComboBoxEntrenadores().getSelectedItem()).split(" - ")[1];
            
            String descripcion = vista.getTxtDieta().getText();
            Date fechaAsignacion = new Date(vista.getCalendario().getDate().getTime());
            Date fechaFinaliza = new Date(vista.getCalendario1().getDate().getTime());

            if (!modelo.existeCliente(idCliente)) {
                JOptionPane.showMessageDialog(vista, "El ID del cliente no existe.");
                return;
            }
            if (!modelo.existeEntrenador(idEntrenador)) {
                JOptionPane.showMessageDialog(vista, "El ID del entrenador no existe.");
                return;
            }
            
            if (modelo.clienteTieneDieta(idCliente)) {
                JOptionPane.showMessageDialog(vista, "El cliente ya tiene una dieta asignada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (fechaFinaliza.before(fechaAsignacion)) {
                JOptionPane.showMessageDialog(vista, "La fecha de fin no puede ser antes de la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Dieta dieta = new Dieta(0, descripcion, fechaAsignacion, fechaFinaliza, idCliente, cliente, idEntrenador);
            modelo.agregarDieta(dieta);

            JOptionPane.showMessageDialog(vista, "Dieta registrada con éxito.");
            vista.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Por favor, ingrese valores válidos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al registrar la dieta.");
            ex.printStackTrace();
        }
    }
    
    private void cargarComboBoxClientes() {
        try {
            List<Cliente> clientes = model.obtenerClientes();
            DefaultComboBoxModel<String> modeloo = new DefaultComboBoxModel<>();
            for (Cliente cliente : clientes) {
                String item = cliente.getIdCliente() + " - " + cliente.getNombre();
                modeloo.addElement(item); 
            }
            vista.getComboBoxClientes().setModel(modeloo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al cargar los clientes.");
        }
    }
    
    private void cargarComboBoxEntrenadores() {
        try {
            List<Entrenador> entrenadores = mod.obtenerEntrenadores();
            DefaultComboBoxModel<String> modeloo = new DefaultComboBoxModel<>();
            for (Entrenador entrenador : entrenadores) {
                String item = entrenador.getIdEntrenador() + " - " + entrenador.getNombre();
                modeloo.addElement(item); 
            }
            vista.getComboBoxEntrenadores().setModel(modeloo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al cargar los entrenadores.");
        }
    }
}