/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author KARLARASLIN
 */
public class Encargado {
    private int idEncargado;
    private int idUsuario;

    public Encargado(int idEncargado, int idUsuario) {
        this.idEncargado = idEncargado;
        this.idUsuario = idUsuario;
    }

    Encargado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdEncargado() { 
	return idEncargado; 
    }
    
    public void setIdEncargado(int idEncargado) { 
	this.idEncargado = idEncargado; }

    public int getIdUsuario() { 
	return idUsuario; 
    }
    
    public void setIdUsuario(int idUsuario) { 
	this.idUsuario = idUsuario; 
    }
}
