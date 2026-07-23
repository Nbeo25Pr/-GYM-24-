/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author demia
 */
public class usuario {
    private int idusuario;
    private String nombre;
    private String usuario;
    private String password;

    //Constructor para crear objetos
    public usuario(){};
    
    //Constructor para enviar a la bd
    public usuario(int idusuario, String nombre, String usuario, String password){
    this.idusuario = idusuario;
    this.nombre = nombre;
    this.usuario = usuario;
    this.password = password;
    
    
    
}

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
