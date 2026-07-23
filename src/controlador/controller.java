package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

// Importaciones de tu modelo
import modelo.usuariodb;
import modelo.clientedb;
import modelo.usuario;

// Importaciones de tus vistas
import Vistas.InicioSesion;
import Vistas.RegistroCliente;

/**
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
            JOptionPane.showMessageDialog(ventana, "Bienvenido " + usuarioEncontrado.getNombre());
            iniciarSistema();
        }else{
            JOptionPane.showMessageDialog(ventana, "Credenciales incorrectas");
        }
    }
    
    private void iniciarSistema(){
        RegistroCliente vistaRegistro = new RegistroCliente();
       
        clientedb modeloCliente = new clientedb();
        
        
        vistaRegistro.setLocationRelativeTo(null);
        vistaRegistro.setVisible(true);
                
        ventana.dispose();
    }
}