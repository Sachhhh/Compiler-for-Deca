package src;


import src.Frame.*; 
import src.BaseDeDonnees.*;
import java.sql.SQLException;

public class Main {

    public static void main(String args[]) {
        DataBase dataBase = new DataBase();
        try {
            dataBase.connection();
            PageAccueil pageAccueil = new PageAccueil(dataBase);
            //dataBase.endConnection();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            }  
        }
    }
    