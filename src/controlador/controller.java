/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import modelo.usuariodb;
import Vistas.InicioSesion;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.clientedb;
import modelo.usuario;
import Vistas.FRNGestionGym;


/**
 *
 * @author demia
 */
public class controller implements ActionListener {
    
    private InicioSesion ventana;
    private usuariodb usuariodb;
    
    public controller(InicioSesion ventana, usuariodb usuariodb){
        this.ventana = ventana;
        this.usuariodb = usuariodb;
        this.ventana.btn_ingresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ventana.btn_ingresar){
            iniciarSesion();
        }
        
    }
    
    private void iniciarSesion(){
        String usuario = ventana.txt_usuario.getText().trim();
        String password = new String(ventana.txt_password.getPassword());
        
        if(usuario.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(ventana, "Ingresa todos los campos");
            return;
        }
        
        usuario usuarioEncontrado = usuariodb.validarLogin(usuario, password);
        
        if(usuarioEncontrado != null){
            JOptionPane.showMessageDialog(ventana, "Bienvenido " + usuarioEncontrado.getUsuario());
            iniciarSistema();
            
        }else{
            JOptionPane.showMessageDialog(ventana, "Credenciales incorrectas");
        }
    }
    
    private void iniciarSistema(){
        FRNGestionGym vistaMenu = new FRNGestionGym();
        clientedb clientedb = new clientedb();
        
        controller controladorRegistro = new controller(vistaMenu, clientedb);
        
                
        vistaMenu.setLocationRelativeTo(null);
        vistaMenu.setVisible(true);
                
        ventana.dispose();
    }
}
