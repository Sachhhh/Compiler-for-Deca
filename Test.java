//package BaseDeDonnees;

import java.sql.*;

public class Test {
  static final String URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
  static final String USERNAME = "rahouim";                            // A adapter pour votre compte Oracle
  static final String PASSWD = "rahouim";                             // A adapter pour votre compte Oracle
  static final String STMT = "select * from emp";

  public Test() {
    try {
      // Chargement du driver Oracle
      System.out.print("Loading Oracle driver... "); 
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      System.out.println("loaded");
      
       // Etablissement de la connection
      System.out.print("Connecting to the database... "); 
      Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
      System.out.println("connected");
      
      // Creation de la requete
      Statement stmt = conn.createStatement();
      
      // Execution de la requete
      ResultSet rset = stmt.executeQuery(STMT);
      
      // Affichage du resultat
      System.out.println("Results:");
      dumpResultSet(rset);
      System.out.println();
      
      // Fermeture
      rset.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      System.err.println("failed");
      e.printStackTrace(System.err);
    }
  }
  
  private void dumpResultSet(ResultSet rset) throws SQLException {
    ResultSetMetaData rsetmd = rset.getMetaData();
    int i = rsetmd.getColumnCount();
    while (rset.next()) {
      for (int j = 1; j <= i; j++) {
        System.out.print(rset.getString(j) + "\t");
    }
      System.out.println();
    }
  }

  public static void main(String args[]) {
    new Test();
  }
}