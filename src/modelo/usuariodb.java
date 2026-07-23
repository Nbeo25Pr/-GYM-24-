/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
import conexion.Conectiondb;

/**
 *
 * @author demia
 */
public class usuariodb {
    
    public usuario validarLogin(String usuario, String pass){
        String query_sql = ("SELECT * FROM usuario WHERE usuario = ? AND pass = ?");
        
        try{
            
            Connection conn = Conectiondb.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            stmt.setString(1, usuario);
            stmt.setString(2, pass);
            
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
               
                usuario usuarioencontrado = new usuario(
                result.getInt("idusuario"),
                result.getString("nombre")
                result.getString("usuario"),
                result.getString("pass") 
                );
                return usuarioencontrado;
            }
            
        }catch(SQLException e){
            
            System.out.println("Error al consultar en la BD" + e.getMessage());
        }
        return null;
    }
}
