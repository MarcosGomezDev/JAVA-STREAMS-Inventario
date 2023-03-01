package InterfazDeGestion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author MGM39434796P
 */
public class funcion {
    
    private static String fichero = "datos.dat";
    private ArrayList <producto> datosArray = new ArrayList <producto>();
    
    /**
     * Con este constructor lo primero que hara el programa es leer el arrary que
     * hay en el fichero y asi poder trabajr con ella.
     * @throws IOException 
     */
    public funcion() throws IOException {
        try {
            
            File ficheroDatos = new File (fichero);
            if (!ficheroDatos.exists()){
                ficheroDatos.createNewFile();
            }
            
            DataInputStream data = new DataInputStream (new FileInputStream (fichero));
            
            producto aux = new producto();
            aux.setCodigo(data.readUTF());
            
            while (aux.getCodigo() != null){
                aux.setNombre(data.readUTF());
                aux.setCantidad(data.readUTF());
                aux.setDescripcion(data.readUTF());
                datosArray.add(aux);
                aux = new producto();
                aux.setCodigo(data.readUTF());
            }
            data.close();
        }catch (EOFException e1){
        }
    }
    
    /**
     * Esta clase la utilizaremos en los metodos para ir guardando los cambios
     * dentro del array.
     * @throws IOException 
     */
    public void guardarArray () throws IOException{
        try {
            File ficheroDatos = new File (fichero);
            DataOutputStream dat = new DataOutputStream(new FileOutputStream(ficheroDatos));
            for (int i=0; i<datosArray.size(); i++){
                dat.writeUTF(datosArray.get(i).getCodigo());
                dat.writeUTF(datosArray.get(i).getNombre());
                dat.writeUTF(datosArray.get(i).getCantidad());
                dat.writeUTF(datosArray.get(i).getDescripcion());
            }
            dat.close();
        }catch (FileNotFoundException ex){
        }
    }
    
    /**
     * Este metodo nos va ha permitir añadir un nuevo producto al fichero.
     * @param codigo, es el codigo que tiene asignado el producto.
     * @param nombre, nombre del producto.
     * @param cantidad, cantidad que hay de ese producto en stock.
     * @param descripcion, una breve descripcion de que es el producto.
     * @throws IOException 
     */
    public void altas (String codigo, String nombre, String cantidad, String descripcion) throws IOException{
        producto aux = new producto ();
        aux.setCodigo(codigo);
        aux.setNombre(nombre);
        aux.setCantidad(cantidad);
        aux.setDescripcion(descripcion);
        datosArray.add(aux);
        guardarArray();
    }
    
    /**
     * Este metodo nos permite mostrar en la interfaz todos los productos dados de alta.
     * @return nos devuelve en formato de texto los datos del producto.
     */
    public String listado (){
        String texto="";
        
        for (int i =0; i< datosArray.size(); i++){
            texto = texto + "Codigo............:" + datosArray.get(i).getCodigo() + "\n";
            texto = texto + "Nombre............:" + datosArray.get(i).getNombre() + "\n";
            texto = texto + "Cantidad..........:" + datosArray.get(i).getCantidad() + "\n";
            texto = texto + "Descripcion.......:" + datosArray.get(i).getDescripcion() + "\n";
            
            texto = texto +"------------------" + "\n";
        }
        return texto;
    }    
    
    /**
     * Metodo que nos permitira modificar los datos de los productos que tengamos.
     * @param codigo, es el codigo que tiene asignado el producto.
     * @param nombre, nombre del producto.
     * @param cantidad, cantidad que hay de ese producto en stock.
     * @param descripcion, una breve descripcion de que es el producto.
     * @return, devuelve la modificacion realizada.
     * @throws IOException 
     */
    public int modificar (String codigo, String nombre, String cantidad, String descripcion) throws IOException{
        int modificado = 0;
        
        for (int i=0; i<datosArray.size(); i++){
            if (datosArray.get(i).getCodigo().equals(codigo)){
                modificado = i;
                break;                 
            }
        }
        if (modificado >= 0){
            datosArray.get(modificado).setNombre(nombre);
            datosArray.get(modificado).setCantidad(cantidad);
            datosArray.get(modificado).setDescripcion(descripcion);
            guardarArray();
            return modificado;
        }else {
            return modificado;
        }
    }
    
    /**
     * Metodo borrar nos permite buscar un producto y en caso de existir eliminarlo.
     * @param codigo, Codgio del producto que le enviamos al metodo para comprobar
     * si existe y eliminarlo.
     * @return
     * @throws IOException
     */
    public int borrar (String codigo) throws IOException{
        int borrar=-1;
           
        for (int i=0; i<datosArray.size();i++){
            if (datosArray.get(i).getCodigo().equals(codigo)){
                borrar=i;
                break;
            }
        }
        if (borrar>=0) {
            datosArray.remove(borrar);
            guardarArray();
            return borrar;
        }
        else{
            return borrar;
       } 
    }
    
    /**
     * Metodo para buscar productos en un fichero, si lo encuentra nos lo devuelve
     * y lo imprimos por pantalla.
     * @param codigo, codigo del producto que le enviamos al metodo para comprobar
     * si existe.
     * @return 
     */
    public String buscar (String codigo){
        String texto = "";
        Iterator it = datosArray.iterator();
        producto p = null;
        
        while (it.hasNext()){
            p = (producto)it.next();
            while (p.getCodigo().equals(codigo)){
            texto = texto + "Codigo............:" + p.getCodigo() + "\n";
            texto = texto + "Nombre............:" + p.getNombre() + "\n";
            texto = texto + "Cantidad..........:" + p.getCantidad() + "\n";
            texto = texto + "Descripcion.......:" + p.getDescripcion() + "\n";
            
            texto = texto +"------------------" + "\n";
            break;
            }
        }
        return texto;
    }
}