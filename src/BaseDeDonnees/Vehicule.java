package src.BaseDeDonnees;

import java.sql.*;

public class Vehicule {
    private String immatriculation;
    private String marque;
    private String modele;
    private int puissance;
    private Energie energie;
    private int nbPlaces;

public Vehicule(String immatriculation, String marque, String modele, int puissance, Energie energie, int nbPlaces){
    this.immatriculation=immatriculation;
    this.marque=marque;
    this.modele=modele;
    this.puissance=puissance;
    this.energie=energie;
    this.nbPlaces=nbPlaces;
}


//Fonctionne
public Boolean ajout(DataBase dataBase, String email){
    try {
        
        //Voiture obligatoirement associée à au moins un user:
        PreparedStatement stmt3 = dataBase.getConnection().prepareStatement("SELECT * FROM \"UTILISATEUR\" WHERE \"EMAIL\" = ? ");
        stmt3.setString(1, email);
        ResultSet rset2=stmt3.executeQuery();
        if(!rset2.next()){
            System.out.println("Utilisateur introuvable");
            rset2.close();
            stmt3.close();
            return false;
        }

        //On vérifie que le couple voiture/proprio n'est pas déjà là:
        //Voiture obligatoirement associée à au moins un user:
        PreparedStatement stmt4 = dataBase.getConnection().prepareStatement("SELECT * FROM \"PROPRIOVOITURE\" WHERE \"EMAIL\" = ? ");
        stmt4.setString(1, email);
        ResultSet rset3=stmt4.executeQuery();
        while(rset3.next()){
            if (rset3.getString(2).equals(getImmatriculation())){
                System.out.println("Couple véhicule/proprio déjà reconnu");
                rset3.close();
                stmt4.close();
                return false;
            }
        }

        //Si c'est bon on insère le lien proprio voiture
        PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("INSERT INTO \"PROPRIOVOITURE\" VALUES(?,?)");
        stmt2.setString(1,email);
        stmt2.setString(2,getImmatriculation());
        stmt2.executeUpdate();
        stmt2.close();

        //On ajoute la voiture:
        PreparedStatement stmt = dataBase.getConnection().prepareStatement("INSERT INTO \"VEHICULE\" VALUES(?,?,?,?,?,?)");
        stmt.setString(1, immatriculation);
        stmt.setString(2, marque);
        stmt.setString(3, modele);
        stmt.setInt(4, puissance);
        stmt.setInt(5, nbPlaces);
        stmt.setString(6, energie.toString());
        stmt.executeUpdate();
        stmt.close();

        return true;
         
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//On supprime la voiture et son lien avec le proprio:
public void supprimer(DataBase dataBase){ 
    try {
        PreparedStatement stmt = dataBase.getConnection().prepareStatement("DELETE FROM \"VEHICULE\" WHERE \"IMMATRICULATION\"= ? ");
        PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("DELETE FROM \"PROPRIOVOITURE\" WHERE \"IMMATRICULATION\"= ? ");
        
        stmt.setString(1, immatriculation);
        stmt2.setString(1, immatriculation);
        stmt.executeUpdate();
        stmt2.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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
     * @return String return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return String return the modele
     */
    public String getModele() {
        return modele;
    }

    /**
     * @param modele the modele to set
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * @return int return the puissance
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * @param puissance the puissance to set
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    /**
     * @return Energie return the energie
     */
    public Energie getEnergie() {
        return energie;
    }

    /**
     * @param energie the energie to set
     */
    public void setEnergie(Energie energie) {
        this.energie = energie;
    }

    /**
     * @return int return the nbPlaces
     */
    public int getNbPlaces() {
        return nbPlaces;
    }

    /**
     * @param nbPlaces the nbPlaces to set
     */
    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

}
