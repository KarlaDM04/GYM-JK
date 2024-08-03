/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author KARLARASLIN
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String telefono;
    private String rol;
    private String contrasenia;

    // Constructor con parámetros
    public Usuario(int idUsuario, String nombre, String apellidoP, String apellidoM, String correo, String telefono, String rol, String contrasenia) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.contrasenia = contrasenia;
    }

    // Constructor vacío
    public Usuario() {
        // Inicializa con valores por defecto si es necesario
    }

    // Getters y setters
    public int getIdUsuario() { 
        return idUsuario; 
    }
    
    public void setIdUsuario(int idUsuario) { 
        this.idUsuario = idUsuario; 
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    
    public String getApellidoP() { 
        return apellidoP; 
    }
    
    public void setApellidoP(String apellidoP) { 
        this.apellidoP = apellidoP; 
    }
    
    public String getApellidoM() { 
        return apellidoM; 
    }
    
    public void setApellidoM(String apellidoM) { 
        this.apellidoM = apellidoM; 
    }

    public String getCorreo() { 
        return correo; 
    }
    
    public void setCorreo(String correo) { 
        this.correo = correo; 
    }

    public String getTelefono() { 
        return telefono; 
    }
    
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }

    public String getRol() { 
        return rol; 
    }
    
    public void setRol(String rol) { 
        this.rol = rol; 
    }
    
    public String getContrasenia() { 
        return contrasenia; 
    }
    
    public void setContrasenia(String contrasenia) { 
        this.contrasenia = contrasenia; 
    }
    
    public String toString(){
        return idUsuario+". "+nombre;
    }
}
