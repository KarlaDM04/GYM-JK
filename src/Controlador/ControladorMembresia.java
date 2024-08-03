/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Vista.RegistroMembresia;
import Modelo.Membresia;
import Modelo.MembresiaDAO;
/**
 *
 * @author KARLARASLIN
 */
public class ControladorMembresia implements ActionListener {
    private RegistroMembresia vista;
    private MembresiaDAO modelo;

    public ControladorMembresia(RegistroMembresia vista, MembresiaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.getBtnRegistrar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrarMembresia();
        }
    }

   /* private void registrarMembresia() {
        String tipo = vista.getTxtTipoMembresia().getText();
        double precio = Double.parseDouble(vista.getTxtCosto().getText());
        int duracion = Integer.parseInt(vista.getTxtDuracion().getText());
        if (modelo.existeTipo(tipo)) {
            JOptionPane.showMessageDialog(vista, "La membresía con este tipo ya existe.");
            return;
        }
        Membresia membresia = new Membresia(0, tipo, precio, duracion);
        modelo.agregarMembresia(membresia);
        JOptionPane.showMessageDialog(vista, "Membresía registrada exitosamente.");
    }*/
    
    private void registrarMembresia() {
    String tipo = vista.getTxtTipoMembresia().getText();
    double precio;
    int duracion;

    try {
        precio = Double.parseDouble(vista.getTxtCosto().getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido.");
        return;
    }

    try {
        duracion = Integer.parseInt(vista.getTxtDuracion().getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(vista, "La duración debe ser un número entero válido.");
        return;
    }

    if (modelo.existeTipo(tipo)) {
        JOptionPane.showMessageDialog(vista, "La membresía con este tipo ya existe.");
        return;
    }

    Membresia membresia = new Membresia(0, tipo, precio, duracion);
    modelo.agregarMembresia(membresia);
    JOptionPane.showMessageDialog(vista, "Membresía registrada exitosamente.");
}

}

