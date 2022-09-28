package src.BaseDeDonnees;
import java.sql.*;
 import oracle.jdbc.OracleDriver;

public class DataBase {
   static final String URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
   static final String USERNAME = "rahouim";                            
   static final String PASSWD = "rahouim"; 
   private Connection connection;
 
    public void connection() throws SQLException{
        // Chargement du driver Oracle
        System.out.print("Loading Oracle driver... "); 
        DriverManager.registerDriver(new OracleDriver());
        System.out.println("loaded");

        // Etablissement de la connection
        System.out.print("Connecting to the database... "); 
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
        System.out.println("connected");
        this.connection=conn;
        }

    public void endConnection() throws SQLException{
        this.connection.close();
        }


    public Connection getConnection() {
        return connection;
    }

}