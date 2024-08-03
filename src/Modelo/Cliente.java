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
public class Cliente {
    private int idCliente;
    private Date fechaNacimiento;
    private int edad;
    private double peso;
    private double estatura;
    private double imc;
    private int idUsuario;
    private String nombre;

    public Cliente(int idCliente, Date fechaNacimiento, int edad, double peso, double estatura, double imc, int idUsuario, String nombre) {
        this.idCliente = idCliente;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
        this.imc = imc;
        this.idUsuario = idUsuario;
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Cliente() {
    }

    public int getIdCliente() { 
	return idCliente; 
    }
    
    public void setIdCliente(int idCliente) { 
	this.idCliente = idCliente; 
    }

    public Date getFechaNacimiento() { 
	return fechaNacimiento; 
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) { 
	this.fechaNacimiento = fechaNacimiento; 
    }

    public int getEdad() { 
	return edad; 
    }
    
    public void setEdad(int edad) { 
	this.edad = edad; 
    }

    public double getPeso() { 
	return peso; 
    }
    
    public void setPeso(double peso) { 
	this.peso = peso; 
    }

    public double getEstatura() { 
	return estatura; 
    }
    
    public void setEstatura(double estatura) { 
	this.estatura = estatura; 
    }

    public double getImc() { 
	return imc; 
    }
    
    public void setImc(double imc) { 
	this.imc = imc; 
    }

    public int getIdUsuario() { 
	return idUsuario; 
    }
    
    public void setIdUsuario(int idUsuario) { 	
	this.idUsuario = idUsuario; 
    }
    
    public double calcularIMC(){
        return  peso/(estatura*estatura);
    }
    
    public String toString() {
        return idUsuario+". "+nombre;
    }
}
