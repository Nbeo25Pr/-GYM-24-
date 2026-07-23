package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.usuariodb;
import modelo.clientedb;
import modelo.usuario;
import modelo.cliente;

import Vistas.InicioSesion;
import Vistas.RegistroCliente;
import Vistas.FRNGestionGym;

public class controller implements ActionListener {
    
    private InicioSesion ventana;
    private usuariodb usuariodb;
    private RegistroCliente vistaRegistro;
    private FRNGestionGym vistaGestion;
    private clientedb modeloCliente;
    private int idClienteSeleccionado = -1;
    
    public controller(InicioSesion ventana, usuariodb usuariodb){
        this.ventana = ventana;
        this.usuariodb = usuariodb;
        this.ventana.btnInicio.addActionListener(this);
    }
    
    public controller(RegistroCliente vistaRegistro, clientedb modeloCliente) {
        this.vistaRegistro = vistaRegistro;
        this.modeloCliente = modeloCliente;
        
        if (this.vistaRegistro.btnregistrar != null) {
            this.vistaRegistro.btnregistrar.addActionListener(this);
        }
        
        if (this.vistaRegistro.btnGestionClientes != null) {
            this.vistaRegistro.btnGestionClientes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    abrirGestionClientes();
                }
            });
        }
    }

    public controller(FRNGestionGym vistaGestion, clientedb modeloCliente) {
        this.vistaGestion = vistaGestion;
        this.modeloCliente = modeloCliente;
        
        if (this.vistaGestion.btnactuzalizar != null) this.vistaGestion.btnactuzalizar.addActionListener(this);
        if (this.vistaGestion.btneliminar != null) this.vistaGestion.btneliminar.addActionListener(this);
        if (this.vistaGestion.btnlimpiar != null) this.vistaGestion.btnlimpiar.addActionListener(this);
        
        if (this.vistaGestion.tablaClientes != null) {
            this.vistaGestion.tablaClientes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionarFilaTabla();
                }
            });
        }
        
        listarTablaGestion();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(ventana != null && e.getSource() == ventana.btnInicio){
            iniciarSesion();
        } else if (vistaRegistro != null && e.getSource() == vistaRegistro.btnregistrar) {
            registrarCliente();
        } else if (vistaGestion != null) {
            if (e.getSource() == vistaGestion.btnactuzalizar) {
                actualizarCliente();
            } else if (e.getSource() == vistaGestion.btneliminar) {
                eliminarCliente();
            } else if (e.getSource() == vistaGestion.btnlimpiar) {
                limpiarCamposGestion();
            }
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
            JOptionPane.showMessageDialog(ventana, "Bienvenido " + usuarioEncontrado.getNombre());
            iniciarSistema();
        }else{
            JOptionPane.showMessageDialog(ventana, "Credenciales incorrectas");
        }
    }
    
    private void registrarCliente(){
        String nombre = vistaRegistro.txtnombre.getText().trim();
        String fechaRegistro = vistaRegistro.fecharesgitro.getText().trim();
        String tipoSub = vistaRegistro.tipo.getSelectedItem().toString();
        String fechaTermino = vistaRegistro.termino.getText().trim();
        String clase = vistaRegistro.clase.getSelectedItem().toString();
        String fechaPago = vistaRegistro.pago.getText().trim();

        if (nombre.isEmpty() || fechaRegistro.isEmpty() || fechaTermino.isEmpty() || fechaPago.isEmpty()) {
            JOptionPane.showMessageDialog(vistaRegistro, "Por favor completa todos los campos requeridos.");
            return;
        }

        cliente nuevoCliente = new cliente(0, nombre, fechaRegistro, tipoSub, fechaPago, fechaTermino, clase);

        if (modeloCliente.insertar(nuevoCliente)) {
            JOptionPane.showMessageDialog(vistaRegistro, "Cliente registrado exitosamente");
            limpiarCamposRegistro();
        } else {
            JOptionPane.showMessageDialog(vistaRegistro, "Error al registrar cliente en la base de datos");
        }
    }

    private void listarTablaGestion(){
        if (vistaGestion == null || vistaGestion.tablaClientes == null) return;
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("F. Registro");
        modeloTabla.addColumn("Suscripción");
        modeloTabla.addColumn("F. Pago");
        modeloTabla.addColumn("F. Término");
        modeloTabla.addColumn("Clase");
        
        List<cliente> lista = modeloCliente.consultarclientes();
        for (cliente c : lista) {
            Object[] fila = new Object[7];
            fila[0] = c.getId();
            fila[1] = c.getNombre();
            fila[2] = c.getFecha_registro();
            fila[3] = c.getTipo_sub();
            fila[4] = c.getFecha_pago();
            fila[5] = c.getFecha_termino();
            fila[6] = c.getClase();
            modeloTabla.addRow(fila);
        }
        
        vistaGestion.tablaClientes.setModel(modeloTabla);
    }

    private void seleccionarFilaTabla(){
        int fila = vistaGestion.tablaClientes.getSelectedRow();
        if (fila >= 0) {
            idClienteSeleccionado = Integer.parseInt(vistaGestion.tablaClientes.getValueAt(fila, 0).toString());
            vistaGestion.txtnombre.setText(vistaGestion.tablaClientes.getValueAt(fila, 1).toString());
            vistaGestion.txtregistro.setText(vistaGestion.tablaClientes.getValueAt(fila, 2).toString());
            vistaGestion.cmbtipo.setSelectedItem(vistaGestion.tablaClientes.getValueAt(fila, 3).toString());
            vistaGestion.txtpago.setText(vistaGestion.tablaClientes.getValueAt(fila, 4).toString());
            vistaGestion.txtTermino.setText(vistaGestion.tablaClientes.getValueAt(fila, 5).toString());
            vistaGestion.cmbclase.setSelectedItem(vistaGestion.tablaClientes.getValueAt(fila, 6).toString());
        }
    }

    private void actualizarCliente(){
        if (idClienteSeleccionado == -1) {
            JOptionPane.showMessageDialog(vistaGestion, "Selecciona un cliente de la tabla para actualizar.");
            return;
        }

        String nombre = vistaGestion.txtnombre.getText().trim();
        String fechaRegistro = vistaGestion.txtregistro.getText().trim();
        String tipoSub = vistaGestion.cmbtipo.getSelectedItem().toString();
        String fechaPago = vistaGestion.txtpago.getText().trim();
        String fechaTermino = vistaGestion.txtTermino.getText().trim();
        String clase = vistaGestion.cmbclase.getSelectedItem().toString();

        cliente clienteEditado = new cliente(idClienteSeleccionado, nombre, fechaRegistro, tipoSub, fechaPago, fechaTermino, clase);

        if (modeloCliente.actualizar(clienteEditado)) {
            JOptionPane.showMessageDialog(vistaGestion, "Cliente actualizado correctamente");
            listarTablaGestion();
            limpiarCamposGestion();
        } else {
            JOptionPane.showMessageDialog(vistaGestion, "Error al actualizar cliente");
        }
    }

    private void eliminarCliente(){
        if (idClienteSeleccionado == -1) {
            JOptionPane.showMessageDialog(vistaGestion, "Selecciona un cliente de la tabla para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de eliminar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (modeloCliente.eliminar(idClienteSeleccionado)) {
                JOptionPane.showMessageDialog(vistaGestion, "Cliente eliminado correctamente");
                listarTablaGestion();
                limpiarCamposGestion();
            } else {
                JOptionPane.showMessageDialog(vistaGestion, "Error al eliminar cliente");
            }
        }
    }

    private void limpiarCamposRegistro(){
        vistaRegistro.txtnombre.setText("");
        vistaRegistro.fecharesgitro.setText("");
        vistaRegistro.termino.setText("");
        vistaRegistro.pago.setText("");
        vistaRegistro.tipo.setSelectedIndex(0);
        vistaRegistro.clase.setSelectedIndex(0);
    }

    private void limpiarCamposGestion(){
        idClienteSeleccionado = -1;
        vistaGestion.txtnombre.setText("");
        vistaGestion.txtregistro.setText("");
        vistaGestion.txtTermino.setText("");
        vistaGestion.txtpago.setText("");
        vistaGestion.cmbtipo.setSelectedIndex(0);
        vistaGestion.cmbclase.setSelectedIndex(0);
        vistaGestion.tablaClientes.clearSelection();
    }
    
    private void iniciarSistema(){
        RegistroCliente vistaRegistro = new RegistroCliente();
        clientedb modeloCliente = new clientedb();
        controller ctrlRegistro = new controller(vistaRegistro, modeloCliente);
        
        vistaRegistro.setLocationRelativeTo(null);
        vistaRegistro.setVisible(true);
                
        ventana.dispose();
    }

    private void abrirGestionClientes(){
        FRNGestionGym vistaGestion = new FRNGestionGym();
        clientedb modeloCliente = new clientedb();
        controller ctrlGestion = new controller(vistaGestion, modeloCliente);
        
        vistaGestion.setLocationRelativeTo(null);
        vistaGestion.setVisible(true);
        
        vistaRegistro.dispose();
    }
}