
package conexion;

import com.mysql.jdbc.Connection;
import com.sun.istack.internal.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author LN710Q
 */
public class Conexion {
    private String user;
    private String pass;
    private String driver;
    private String url;
    
    private Connection cnx;
    public static Conexion instance;
    
    public synchronized static Conexion conectar(){
        if(instance == null){
            return new Conexion();
        }
        return instance;
        
    }
    private Conexion(){
        cargarCredenciales();
        try{
            Class.forName(this.driver);
            cnx = (Connection) DriverManager.getConnection(this.url,this.user,this.pass);
            
        } catch(ClassNotFoundException | SQLException ex){
         Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //credenciales de mi servidor de base de datos
    private void cargarCredenciales(){
        user = "root";
        pass = "";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/filtros";
    }
    public Connection getCnx(){
        return cnx;
    }
    public void cerrarConexion(){
       
        instance = null; 
             
    }
}
