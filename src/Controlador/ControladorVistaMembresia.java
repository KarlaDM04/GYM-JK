/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Vista.VistaMembresia;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KARLARASLIN
 */
public class ControladorVistaMembresia {
    private VistaMembresia vista;
    private MembresiaDAO modelo;
    
    public ControladorVistaMembresia(VistaMembresia vista, MembresiaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        cargarMembresias();
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

}
