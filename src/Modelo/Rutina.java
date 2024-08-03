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
public class Rutina {
    private int idRutina;
    private String descripcion;
    private Date fechaAsignacion;
    private Date fechaFinalizacion;
    private int idCliente;
    private int idEntrenador;
    private String nombre;

    public Rutina(int idRutina, String descripcion, Date fechaAsignacion,Date fechaFinalizacion, int idCliente,String nombre, int idEntrenador) {
        this.idRutina = idRutina;
        this.descripcion = descripcion;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaFinalizacion=fechaFinalizacion;
        this.idCliente = idCliente;
        this.idEntrenador = idEntrenador;
        this.nombre=nombre;
    }

    
    public Rutina(){
    
    }
    public int getIdRutina() { 
	return idRutina; }
    public void setIdRutina(int idRutina) { 
	this.idRutina = idRutina; }

    public String getDescripcion() { 
	return descripcion; }
    public void setDescripcion(String descripcion) { 
	this.descripcion = descripcion; }

    public Date getFechaAsignacion() { 
	return fechaAsignacion; }
    public void setFechaAsignacion(Date fechaAsignacion) { 
	this.fechaAsignacion = fechaAsignacion; }

    public Date getFechaFinalizacion() { 
	return fechaFinalizacion; }
    public void setFechaFinalizacion(Date fechaFinal) { 
	this.fechaFinalizacion = fechaFinal; }

    public int getIdCliente() { 
	return idCliente; }
    public void setIdCliente(int idCliente) { 
	this.idCliente = idCliente; }

    public int getIdEntrenador() { 
	return idEntrenador; }
    public void setIdEntrenador(int idEntrenador) { 
	this.idEntrenador = idEntrenador; }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
