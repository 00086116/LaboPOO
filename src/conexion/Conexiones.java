package conexion;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Conexiones {
    private String user;
    private String pass;
    private String driver;
    private String url;
    private Connection cnx;
    public static Conexiones instance;
    
    public synchronized static Conexiones conectar(){
        if(instance==null){
            return new Conexiones();
        }
        return instance;
    }
    private Conexiones(){
        cargarCredenciales();
        try{
            Class.forName(this.driver);
            cnx=(Connection) DriverManager.getConnection(this.url,this.user,this.pass);
        }catch(ClassNotFoundException|SQLException ex){
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void cargarCredenciales(){
        user="root";
        pass="";
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost/filtros";
    }
    public Connection getCnx(){
        return cnx;
    }
    public void cerrarConexion(){
        instance=null;
    }
}