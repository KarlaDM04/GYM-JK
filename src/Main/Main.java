/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author KARLARASLIN
 */

import Controlador.ControladorLogin;
import Modelo.UsuarioDAO;
import Vista.Login;

public class Main {
    public static void main(String[] args) {
        Login vista = new Login();
        UsuarioDAO modelo = new UsuarioDAO();
        new ControladorLogin(vista, modelo);
    }
}

