/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import Vistas.InicioSesion;
import modelo.usuariodb;
import controlador.controller;


/**
 *
 * @author demia
 */
public class main {
     public static void main(String[] args) {
        InicioSesion ventana = new InicioSesion();
            usuariodb usuariodb = new usuariodb();
            
            // crear objeto del controlador
            controller controller = new controller(ventana, usuariodb);
        
            // Mostrar ventana en el centro de la pantalla 
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            
            
         
    }
   
}
