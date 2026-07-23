package modelo;

import conexion.Conectiondb;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clientedb {

    public boolean insertar(cliente cliente){
        String sql_query = "INSERT INTO cliente(nombre, fecha_registro, tipo_sub, fecha_pago, fecha_termino, clase) VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            Connection conn = Conectiondb.conexion();
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getFecha_registro());
            stmt.setString(3, cliente.getTipo_sub());
            stmt.setString(4, cliente.getFecha_pago());
            stmt.setString(5, cliente.getFecha_termino());
            stmt.setString(6, cliente.getClase());
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
            return true;
        } catch(SQLException e){
            System.out.println("ERROR AL INSERTAR DATOS: " + e.getMessage());
            return false;
        }
    }
    
    public List<cliente> consultarclientes(){
        List<cliente> listaclientes = new ArrayList<>();
        String query_sql = "SELECT * FROM cliente";
        
        try{
            Connection conn = Conectiondb.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                int id = result.getInt("id_cliente");
                String nombre = result.getString("nombre");
                String fecha_registro = result.getString("fecha_registro");
                String tipo_sub = result.getString("tipo_sub");
                String fecha_pago = result.getString("fecha_pago");
                String fecha_termino = result.getString("fecha_termino");
                String clase = result.getString("clase");
                
                cliente Cliente = new cliente(id, nombre, fecha_registro, tipo_sub, fecha_pago, fecha_termino, clase);
                listaclientes.add(Cliente);
            }
            
            stmt.close();
            conn.close();
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        
        return listaclientes;
    }
    
    public boolean actualizar(cliente cliente){
        String query_sql = "UPDATE cliente SET nombre = ?, fecha_registro = ?, tipo_sub = ?, fecha_pago = ?, fecha_termino = ?, clase = ? WHERE id_cliente = ?";
        
        try{
            Connection conn = Conectiondb.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getFecha_registro());
            stmt.setString(3, cliente.getTipo_sub());
            stmt.setString(4, cliente.getFecha_pago());
            stmt.setString(5, cliente.getFecha_termino());
            stmt.setString(6, cliente.getClase());
            stmt.setInt(7, cliente.getId());
            
            int filas_cambiadas = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
            return filas_cambiadas > 0;
        }catch(SQLException e){
            System.out.println("ERROR AL ACTUALIZAR EN LA BD: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id_cliente){
        String query_sql = "DELETE FROM cliente WHERE id_cliente = ?";
        
        try{
            Connection conn = Conectiondb.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            stmt.setInt(1, id_cliente);
            int filas_cambiadas = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
            return filas_cambiadas > 0;
        }catch(SQLException e){
            System.out.println("ERROR EN LA ELIMINACION DE DATOS: " + e.getMessage());
            return false;
        }
    }
}