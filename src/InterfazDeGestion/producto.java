package InterfazDeGestion;
/**
 * @author MGM39434796P
 */
public class producto {
    
    private String codigo;  
    private String nombre;
    private String cantidad;
    private String descripcion;

    public producto() {
        codigo = "";
        nombre = "";
        cantidad = "";
        descripcion = "";
    }
    
    public producto (String codigo, String nombre, String cantidad, String descripcion){
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }    

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setCodigo (String codigo){
            this.codigo = codigo;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public void setCantidad (String cantidad){
        this.cantidad = cantidad;
    }
    
    public String getCantidad (){
        return cantidad;
    }
}
