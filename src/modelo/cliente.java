/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author demia
 */
public class cliente {
    private int id;
    private String nombre;
    private String fecha_registro;
    private String tipo_sub;
    private String fecha_pago;
    private String fecha_termino;
    private String clase;
    
    
    // 1- constructor para crear un objeto vacio
    // Estudiante estudiante = new Estudiante
   public cliente(){}
   
   //2- Consructor para crear un objeto dentro del sistema sin BD
   // Estudiante estudiante = new Estudiante(gabriel,tcgo251608,19,IID)
    public cliente(String nombre, String fecha_registro, String tipo_sub, String fecha_pago, String fecha_termino, String clase) {
        this.nombre = nombre;
        this.fecha_registro = fecha_registro;
        this.tipo_sub = tipo_sub;
        this.fecha_pago = fecha_pago;
        this.fecha_termino = fecha_termino;
        this.clase = clase;
    }
    
    //3- Constructor para crear un objeto que ocupe la BD

    public cliente(int id, String nombre, String fecha_registro, String tipo_sub, String fecha_pago, String fecha_termino, String clase) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_registro = fecha_registro;
        this.tipo_sub = tipo_sub;
        this.fecha_pago = fecha_pago;
        this.fecha_termino = fecha_termino;
        this.clase = clase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getTipo_sub() {
        return tipo_sub;
    }

    public void setTipo_sub(String tipo_sub) {
        this.tipo_sub = tipo_sub;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
    
}
