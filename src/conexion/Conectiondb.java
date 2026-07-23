/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author demia
 */
import java.sql.*;

public class Conectiondb{
    
    // Manejo de las constantes de datos de la conexion
    private static final String URL = "jdbc:mysql://localhost:3306/gym_24";
    private static final String USER = "root";
    private static final String PASS = "";
    
    // Metodo para realizar la conexion 
    public static Connection conexion(){
        Connection conn = null;
        
        // Manejar el error con la conexion de bases de datos
        try{
            // guardar conexion en el oj¿bjeto Conection
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("LA CONEXIÓN FUE EXITOSA");
        }catch(SQLException e){
            // Manejar el error de SQL y mostar al usuario el error 
            System.out.println("ERROR EN LA CONEXIÓN: " + e.getMessage());
            }
        return conn;
        }
    }
