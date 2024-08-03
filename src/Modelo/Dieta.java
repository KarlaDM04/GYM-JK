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
public class Dieta {
    private int idDieta;
    private String descripcion;
    private Date fechaAsignacion;
    private Date fechaFinaliza;
    private int idCliente;
    private String nombreCliente;
    private int idEntrenador;

    public Dieta(int idDieta, String descripcion, Date fechaAsignacion,Date fechaFinaliza, int idCliente,String nombre, int idEntrenador) {
        this.idDieta = idDieta;
        this.descripcion = descripcion;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaFinaliza=fechaFinaliza;
        this.idCliente = idCliente;
        this.nombreCliente=nombre;
        this.idEntrenador = idEntrenador;
    }

    public int getIdDieta() { 
	return idDieta; 
    }
    
    public void setIdDieta(int idDieta) { 
	this.idDieta = idDieta; 
    }

    public String getDescripcion() { 
	return descripcion; 
    }
    
    public void setDescripcion(String descripcion) { 
	this.descripcion = descripcion; 
    }

    public Date getFechaAsignacion() { 
	return fechaAsignacion; 
    }
    
    public void setFechaAsignacion(Date fechaAsignacion) { 
	this.fechaAsignacion = fechaAsignacion; 
    }
    
    public Date getFechaFinaliza() { 
	return fechaFinaliza; 
    }
    
    public void setFechaFinaliza(Date fechaFinaliza) { 
	this.fechaFinaliza = fechaFinaliza; 
    }

    public int getIdCliente() { 
	return idCliente; 
    }
    
    public void setIdCliente(int idCliente) { 
	this.idCliente = idCliente; 
    }

    public int getIdEntrenador() { 
	return idEntrenador; 
    }
    public void setIdEntrenador(int idEntrenador) { 
	this.idEntrenador = idEntrenador; 
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    
}
