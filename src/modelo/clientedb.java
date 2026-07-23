/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author rousc
 */
import conexion.Conectiondb;
//import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// clase que permite manejar los objetos del sistema y enviarlos a la DB
public class clientedb {
    // metodo para registrar en la base de datos
    public boolean insertar(cliente cliente){
        // crear sentencia SQL
        String sql_query = "INSERT INTO cliente(nombre, fecha_registro, tipo_sub, fecha_pago, fecha_termino, clase) VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            
            // conexion a la base de datos 
            Connection conn = Conectiondb.conexion();
            
            // Crear e preparedStatement para mandarlo a la DB 
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            
            // Enviar los datos del modelo
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getFecha_registro());
            stmt.setString(3, cliente.getTipo_sub());
            stmt.setString(4, cliente.getFecha_pago());
            stmt.setString(5, cliente.getFecha_termino());
            stmt.setString(6, cliente.getClase());
            
              
              
              // Ejecutar el query en la base de datos
              stmt.executeUpdate();
              
              // Cerrar Statement y conexion a la BD
              stmt.close();
              conn.close();
              
              return true;
              
        } catch(SQLException e){
            // en caso de error notificar al usuario
            System.out.println("ERROR AL INSERTAR DATOS: " + e.getMessage());
            return false;
            
        }
    }
    
   //Metodo para consultar Estudiantes
    public List<cliente> consultarclientes(){
        List<cliente> listaclientes = new ArrayList<>();
        
        String query_sql = "SELECT * FROM estudiantes";
        try{
            Connection conn = Conectiondb.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            ResultSet result = stmt.executeQuery();
            
            // Ciclo para Obtemer todos los registros
            while(result.next()){
                
                int id = result.getInt("id_cliente");
                String nombre  = result.getString("nombre");
                String fecha_registro  = result.getString("fecha_registro");
                String tipo_sub = result.getString("tipo_sub");
                String fecha_pago = result.getString("fecha_pago");
                String fecha_termino  = result.getString("fecha_termino");
                String clase = result.getString("clase");
                
                // Metodo para hcer el guardado en la lista 
                // Crear Objeto de estudiante y guardarlo en la lista 
                cliente Cliente = new cliente(id,nombre,fecha_registro, tipo_sub, fecha_pago, fecha_termino, clase);
                
                // Guardar objeto en la lista 
                listaclientes.add(Cliente);
                
                
                
            }
            
        }catch(SQLException e){
            System.out.println("Error en consulta:" + e.getMessage());
        }
        
        return listaclientes;
        
    }
    
    // METODO PARA ACTUALIZAR ESTUDIAMTES
    
    public boolean actualizar(cliente cliente){
        
        String query_sql = "UPDATE estudiantes SET nombre = ?, fecha_registro = ?, tipo_sub = ?, fecha_pago = ?, fecha_termino = ?, clase = ? WHERE id_estudiante = ?";
        
        try{
        
            // Conectar con la BD
                // conexion a la base de datos 
            Connection conn = Conectiondb.conexion();
            
            // Crear e preparedStatement para mandarlo a la DB 
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            // Enviar los datos del modelo
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getFecha_registro());
            stmt.setString(3, cliente.getTipo_sub());
            stmt.setString(4, cliente.getFecha_pago());
            stmt.setString(5, cliente.getFecha_termino());
            stmt.setString(6, cliente.getClase());
              
              // Verificar el numero de filas que cambiaron 
              int filas_cambiadas = stmt.executeUpdate();
              
              stmt.close();
              conn.close();
              
              
              // Cuando es booean no es necesario aplicar un if
              return filas_cambiadas > 0;
              
            
            
        }catch(SQLException e){
            System.out.println("ERROR AL ACTUALIZAR EN LA BD" + e.getMessage());
            return false;
            
        }
        
        
    }
    // METODO PARA ELIMINAR UN REGISTRO
    public boolean eliminar(int id_cliente){
        // SE PONE ? PARA EVITAR UNA INYECCCIÓN SQL 
        String query_sql = "DELETE FROM cliente WHERE id_cliente = ? ";
        
        try{
                    // Conectar con la BD
                // conexion a la base de datos 
            Connection conn = Conectiondb.conexion();
            
            // Crear e preparedStatement para mandarlo a la DB 
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            stmt.setInt(1, id_cliente);
            
            // VALOR DE LAS FILAS AFECTADAD
            int filas_cambiadas = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
              
            
            return filas_cambiadas > 0;
            
        
    }catch(SQLException e){
            System.out.println("ERROR EN LA EMILINACION DE DATOS" + e.getMessage());
            return false;
    }
    
}
}
