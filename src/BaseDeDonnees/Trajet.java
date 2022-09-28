package src.BaseDeDonnees;


import java.sql.*;

import javax.naming.spi.ResolveResult;



public class Trajet {
    private int idTrajet;
    private String date;
    private String email;
    private String immatriculation;
    private int nbPlacesDispo;
    private int attenteMax;
    private String lieuDepart;
    private String lieuArrivee;

    

    public Trajet(String date, String email, String immatriculation,int nbPlacesDispo, int attenteMax, String lieuDepart , String lieuArrivee){
        this.date=date;
        this.email=email;
        this.immatriculation=immatriculation;
        this.nbPlacesDispo=nbPlacesDispo;
        this.attenteMax=attenteMax;
        this.lieuDepart=lieuDepart;
        this.lieuArrivee=lieuArrivee;
    }

    public String getLieuDep() {
        return lieuDepart ;
    }

    public String getLieuArr() {
        return lieuArrivee ;
    }

    public int ajoutTrajet(DataBase dataBase){ //Il faut aussi vérifier si le trajet n'est pas déja présent à la meme date dans la table ou si une personne a deux trajets différents au meme moment
        try {
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("INSERT INTO \"TRAJET\" VALUES(COMPTEUR_TRAJET.nextval,?,?,?,?,TO_DATE(?,'HH24-MI-DD-MM-RR'),?,?)");
            stmt.setString(1,email);
            stmt.setString(2, immatriculation);
            stmt.setInt(3, nbPlacesDispo);
            stmt.setInt(4, attenteMax);
            stmt.setString(5, date);
            stmt.setString(6, lieuDepart);
            stmt.setString(7, lieuArrivee);
            stmt.executeUpdate();
            stmt.close();
            stmt = dataBase.getConnection().prepareStatement("SELECT EMAIL, DATEETHEURE  FROM  TRAJET where EMAIL=? and DATEETHEURE = TO_DATE(?,'HH24-MI-DD-MM-RR') ");
            stmt.setString(1,email);
            stmt.setString(2,date);
            ResultSet rset=stmt.executeQuery();
            dumpResultSet(rset);
            System.out.print(rset);
            if (rset.next()==false){System.out.println("null");}
            stmt.close();
            PreparedStatement stmt1 = dataBase.getConnection().prepareStatement("SELECT MAX(\"IDTRAJ\") FROM \"TRAJET\"");
            ResultSet rset1 = stmt1.executeQuery();
            rset1.next();
            int idtraj = rset1.getInt(1);
            stmt1.close(); rset1.close();
            return (idtraj);
            }
            
            
            
            catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
    public Boolean supprimer(DataBase dataBase){ 
        try {
            
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("DELETE FROM \"TRAJET\" WHERE \"IDTRAJ\"= ? ");
            stmt.setInt(1, idTrajet);
            stmt.executeUpdate();
            stmt.close();

            PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("DELETE FROM \"TRONCON\" WHERE \"IDTRAJET\"= ? ");
            stmt2.setInt(1, getIdTrajet());
            stmt2.executeUpdate();
            stmt2.close();
            return true;

        }
            catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        
    public Boolean chercherTrajet(DataBase dataBase)
    {
        try 
        {
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("Select from \"Trajet\" WHERE \"lieudep\"= ? AND \" lieuarr\"= ?");
            stmt.setString(1, lieuDepart );
            stmt.setString(2, lieuArrivee );
            stmt.executeUpdate();
            stmt.close();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return int return the idTrajet
     */
    public int getIdTrajet() {
        return idTrajet;
    }

    /**
     * @param idTrajet the idTrajet to set
     */
    public void setIdTrajet(int idTrajet) {
        this.idTrajet = idTrajet;
    }

    /**
     * @return String return the Date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the immatriculation
     */
    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * @param immatriculation the immatriculation to set
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * @return int return the nbPlacesDispo
     */
    public int getNbPlacesDispo() {
        return nbPlacesDispo;
    }

    /**
     * @param nbPlacesDispo the nbPlacesDispo to set
     */
    public void setNbPlacesDispo(int nbPlacesDispo) {
        this.nbPlacesDispo = nbPlacesDispo;
    }

    /**
     * @return String return the attenteMax
     */
    public int getAttenteMax() {
        return attenteMax;
    }

    @Override
    public String toString() {
        return "Trajet [attenteMax=" + attenteMax + ", date=" + date + ", email=" + email + ", idTrajet=" + idTrajet
                + ", immatriculation=" + immatriculation + ", lieuArrivee=" + lieuArrivee + ", lieuDepart=" + lieuDepart
                + ", nbPlacesDispo=" + nbPlacesDispo + "]";
    }

    /**
     * @param attenteMax the attenteMax to set
     */
    public void setAttenteMax(int attenteMax) {
        this.attenteMax = attenteMax;
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
}
