package conexión;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Axel
 */
public class Conexion {
    public static Connection conectar(){
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://");
        }catch(SQLException e){
            System.out.println("Error en la conexión local "+ e);
        }
    }
}
