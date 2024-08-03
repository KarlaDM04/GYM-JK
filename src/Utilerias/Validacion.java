/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author KARLARASLIN
 */
public class Validacion {
    
    
    public static boolean validarCorreoElectronico(String email) {
        if (email.matches("^[a-zA-Z0-9._%+-]+@(gmail\\.com|hotmail\\.com|itoaxaca\\.edu\\.mx|outlook\\.com)$")){
            return true;
        } else {
            //JOptionPane.showMessageDialog(null, "Correo electrónico no válido. Debe ser @gmail.com, @hotmail.com, @itoaxaca.edu.mx o @outlook.com.");
            return false;
        }
    }

    public static boolean validarLetras(String cadena) {
        for (char c : cadena.toCharArray()) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' || c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú' || c == 'ñ' || c == 'Ñ')) {
                //JOptionPane.showMessageDialog(null, "El usuario puede contener únicamente letras");
                return false;
            }
        }
        return true;
    }

    
    
    public static boolean validarLongitudNumero(String numero, int longitudMax) {
        if (numero.matches("[0-9]+")) {
            if (numero.length() == longitudMax) {
                return true;
            } else {
                //JOptionPane.showMessageDialog(null, "El número no cuenta con " + longitudMax + " dígitos.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El valor ingresado no es un número válido.");
            return false;
        }
    }

    
    public static boolean validarContrasenia (String cadena){
        boolean mayuscula=false;
        boolean minuscula=false;
        boolean digito=false;
        boolean especial=false;
        
        for(char c: cadena.toCharArray()){
        if(Character.isUpperCase(c)){
            mayuscula=true;
        } else if(Character.isLowerCase(c)){
            minuscula=true;
        } else if (Character.isDigit(c)){
            digito=true;
        }
        else {especial=true;}
        }
        if (mayuscula && minuscula && digito && especial && cadena.length()==8){
            return true;
        }
        //JOptionPane.showMessageDialog(null, "Contraseña no válida. Debe contener al menos una mayúscula, una minúscula, un número, un carácter especial y tener al menos 8 caracteres.");
        return false;
    }
    
    public static int calcularEdad(Date fechaNacimiento) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(fechaNacimiento);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
     /*public static int calcularEdad(Date fechaNac) {
        LocalDate fechaNacimiento = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }*/
    
    
}