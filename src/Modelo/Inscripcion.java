/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author KARLARASLIN
 */
public class Inscripcion {
    private int idInscripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private int idCliente;
    private String nombreCliente;
    private int idMembresia;

    public Inscripcion(int idInscripcion, Date fechaInicio, Date fechaFin, int idCliente,String nombre, int idMembresia) {
        this.idInscripcion = idInscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idCliente = idCliente;
        this.nombreCliente=nombre;
        this.idMembresia = idMembresia;
        
    }

    public int getIdInscripcion() { 
	return idInscripcion; 
    }
    
    public void setIdInscripcion(int idInscripcion) { 
	this.idInscripcion = idInscripcion; 
    }

    public Date getFechaInicio() { 
	return fechaInicio; 
    }
    
    public void setFechaInicio(Date fechaInicio) { 
	this.fechaInicio = fechaInicio; 
    }

    public Date getFechaFin() { 
	return fechaFin; 
    }
    
    public void setFechaFin(Date fechaFin) { 
	this.fechaFin = fechaFin; 
    }

    public int getIdCliente() { 
	return idCliente; 
    }
    public void setIdCliente(int idCliente) { 
	this.idCliente = idCliente; 
    }

    public int getIdMembresia() { 
	return idMembresia; 
    }
    
    public void setIdMembresia(int idMembresia) { 
	this.idMembresia = idMembresia; 
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    
}
