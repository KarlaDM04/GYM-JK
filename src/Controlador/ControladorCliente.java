/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
/**
 *
 * @author KARLARASLIN
 */
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Utilerias.Validacion;
import Vista.RegistroCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class ControladorCliente implements ActionListener {
    private RegistroCliente vista;
    private ClienteDAO modelo;
    private UsuarioDAO model;

    public ControladorCliente(RegistroCliente vista, ClienteDAO modelo, UsuarioDAO model) {
        this.vista = vista;
        this.modelo = modelo;
        this.model = model;
        this.vista.getBtnRegistrar().addActionListener(this);
        
        cargarComboBoxClientes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrarCliente();
        }
    }

    private void registrarCliente() {
        try {
            int idUsuario = Integer.parseInt(((String) vista.getComboBoxClientes().getSelectedItem()).split(" - ")[0]);
            String nombre = ((String) vista.getComboBoxClientes().getSelectedItem()).split(" - ")[1];
            java.util.Date fechaNacimiento = vista.getCalendarioFechaNacimiento().getDate();
            int edad = Validacion.calcularEdad(fechaNacimiento);
            double peso = (double) vista.getSpinnerPeso().getValue();
            double estatura = (double) vista.getSpinnerEstatura().getValue();
            double imc = calcularIMC(peso, estatura);

            Cliente cliente = new Cliente();
            cliente.setIdUsuario(idUsuario);
            cliente.setNombre(nombre); 
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setEdad(edad);
            cliente.setPeso(peso);
            cliente.setEstatura(estatura);
            cliente.setImc(imc);

            modelo.agregarCliente(cliente);
            JOptionPane.showMessageDialog(vista, "Cliente registrado exitosamente.");
            vista.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al registrar el cliente. El rol del usuario que eligió no corresponde a cliente ó el cliente ya se encuentra registrado ");
        }
    }

    private double calcularIMC(double peso, double estatura) {
        return peso / (estatura * estatura);
    }
    
    private void cargarComboBoxClientes() {
        try {
            List<Usuario> usuarios = model.obtenerUsuarios();
            DefaultComboBoxModel<String> modeloo = new DefaultComboBoxModel<>();
            for (Usuario usuario : usuarios) {
                String item = usuario.getIdUsuario() + " - " + usuario.getNombre();
                modeloo.addElement(item); 
            }
            vista.getComboBoxClientes().setModel(modeloo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error al cargar los usuarios.");
        }
    }
}