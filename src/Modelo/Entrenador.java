/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.ControladorMembresia;
import java.beans.beancontext.BeanContextMembershipEvent;

/**
 *
 * @author KARLARASLIN
 */
public class Entrenador {
    private int idEntrenador;
    private String especialidad;
    private int idUsuario;
    private String turno;
    private String nombre ;

    public Entrenador(int idEntrenador,String nombre, String especialidad, int idUsuario, String turno) {
        this.idEntrenador = idEntrenador;
        this.especialidad = especialidad;
        this.idUsuario = idUsuario;
        this.turno=turno;
        this.nombre=nombre;
    }

    

    public Entrenador() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEntrenador() { 
	return idEntrenador; 
    }
    
    public void setIdEntrenador(int idEntrenador) { 
	this.idEntrenador = idEntrenador; }

    public String getEspecialidad() { 
	return especialidad; 
    }
    
    public void setEspecialidad(String especialidad) { 
	this.especialidad = especialidad; }

    public int getIdUsuario() { 
	return idUsuario; 
    }
    
    public void setIdUsuario(int idUsuario) { 
	this.idUsuario = idUsuario; 
    }
    
    public String getTurno() { 
	return turno; 
    }
    
    public void setTurno(String turno) { 
	this.turno = turno; }
    
    public String toString() {
        return idEntrenador+". "+nombre;
    }
}
