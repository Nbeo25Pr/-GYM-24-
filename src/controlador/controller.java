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
        this.ventana..addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ventana.btnInicio){
            iniciarSesion();
        }
        
    }
    
    private void iniciarSesion(){
        String usuario = ventana.txtUsuario.getText().trim();
        String password = new String(ventana.txtPass.getPassword());
        
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
        FrmMenu vistaMenu = new FrmMenu();
        EstudianteDB estudiantedb = new EstudianteDB();
        
        EstudianteController controladorRegistro = new EstudianteController(vistaMenu, estudiantedb );
        
                
        vistaMenu.setLocationRelativeTo(null);
        vistaMenu.setVisible(true);
                
        ventana.dispose();
    }
}
