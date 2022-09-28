package src.BaseDeDonnees;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JSpinner.ListEditor;
import javax.xml.crypto.Data;

import src.Frame.PageAjoutSolde;

public class Utilisateur {
    private String email;
    private String nom;
    private String prenom;
    private String ville;
    private String motDePasse;
    private int solde;
    LinkedList<String> listeVoitures = new LinkedList<String>();
    LinkedList<Trajet> trajets = new LinkedList<Trajet>();
    
    public Utilisateur(String email, String nom, String prenom, String ville, String motDePasse,int solde){
        this.email=email;
        this.nom=nom;
        this.prenom=prenom;
        this.ville=ville;
        this.motDePasse=motDePasse;
        this.solde=solde;
    }

    public Utilisateur(String email,String motDePasse) {
        this.email=email;
        this.motDePasse=motDePasse;
        DataBase dataBase = new DataBase();
        try{
            dataBase.connection();
            this.connexion(dataBase);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Boolean inscription(DataBase dataBase) { 
        try {
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("INSERT INTO \"UTILISATEUR\" VALUES(?,?,?,?,?,?)");
            stmt.setString(1, email);
            stmt.setString(2, nom);
            stmt.setString(3, prenom);
            stmt.setString(4, ville);
            stmt.setString(5, motDePasse);
            stmt.setInt(6, solde);
            stmt.executeUpdate();
            stmt.close();
            return true;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    public LinkedList<String> getListeVoitures() {
        return listeVoitures;
    }

    

    public void setListeVoitures(String newCar) {
        this.listeVoitures.add(newCar);
    }

    public Boolean connexion(DataBase dataBase){ 
        try {
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT rtrim(\"MOTDEPASSE\",' ') FROM \"UTILISATEUR\" WHERE \"EMAIL\" = ? ");
            stmt.setString(1, email);
            ResultSet rset=stmt.executeQuery();
            if(!rset.next()){
                System.out.println("Utilisateur introuvable");
                return false;
            }
            String motDePasseAttendu=rset.getString(1);
            rset.close();
            stmt.close();
            if(motDePasseAttendu.equals(motDePasse)){
                System.out.println("Utilisateur connecté");
                //Recuperation des données de l'utilisateur
                PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("SELECT * FROM \"UTILISATEUR\" WHERE \"EMAIL\" = ? ");
                stmt2.setString(1, email);
                ResultSet rset2=stmt2.executeQuery();
                if(!rset2.next()){
                    System.out.println("Utilisateur introuvable");
                    rset2.close();
                    stmt2.close();
                    return false ;
                }
                setNom(rset2.getString(2));
                setPrenom(rset2.getString(3));
                setVille(rset2.getString(4));
                setSolde(rset2.getInt(6));
                rset2.close();
                stmt2.close();
                return true;
            }
            else{
                System.out.println("Mot de passe incorrect");
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // add 
    public HashMap<String,Vertex> rechercheVilleDate(String ville, String date, DataBase dataBase){
        Graph g=null;
        try {
            g = new Graph(ville, date, dataBase);
            g.initDikjstra();
            Set<Vertex> vertex=g.getGraph().keySet();
            Iterator<Vertex> it=vertex.iterator();
            while(it.hasNext()){
                Vertex v=it.next();
                if(v.getVille().compareToIgnoreCase(ville)==0){
                    g.Dikjstra(v);
                }
        } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HashMap<String,Vertex> hMap= new HashMap<String,Vertex>();
        Set<Vertex> vertex=g.getGraph().keySet();
        Iterator<Vertex> it=vertex.iterator();
        while(it.hasNext()){
            Vertex v=it.next();
            if(v.getDuree()!=Integer.MAX_VALUE){
                if(v.getVille().compareToIgnoreCase(ville)!=0){
                
                    if(hMap.get(v.getVille())==null){
                        hMap.put(v.getVille(),v);
                    }
                    else if(hMap.get(v.getVille()).isChangement() && !v.isChangement()){
                        hMap.put(v.getVille(),v);
                    }
                    else if(hMap.get(v.getVille()).getDuree()>v.getDuree() && v.isChangement() == hMap.get(v.getVille()).isChangement()){
                        hMap.put(v.getVille(),v);
                    }
                }
            }
        }
       return hMap;
    }

    public HashMap<String,Vertex> rechercheVilleAVilleB(String villeA, String villeB){
        HashMap<String,Vertex> res= new HashMap<String,Vertex>();
        return res;
    }


    public boolean validerTrajet(Vertex v, DataBase dataBase){  
        if(solde<v.getPrix()){
            return false;
        }
        Vertex arrv=v ; 
        while(arrv.getPredecesseur()!=null){
            Vertex dep=arrv.getPredecesseur();
            try {
                PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("SELECT MONTANT,IDTRAJET FROM TRONCON WHERE IDTRAJET = ? OR IDTRAJET = ? AND COORDEPLON = ? AND COORDEPLAT= ? AND COORARRLAT= ? AND COORARRLAT= ?");
                stmt2.setInt(1, arrv.getIdTrajet());
                stmt2.setInt(2, dep.getIdTrajet());
                stmt2.setDouble(3, dep.getLng());
                stmt2.setDouble(4, dep.getLat());
                stmt2.setDouble(5, arrv.getLng());
                stmt2.setDouble(6, arrv.getLat());
                ResultSet rset=stmt2.executeQuery();
                if(!rset.next()){
                    System.out.println("Troncon non trouvé");
                }
                else{
                    int prix = rset.getInt(1);
                    int idTrajet = rset.getInt(2);
                
                    rset.close();
                    stmt2.close();
                    PreparedStatement stmt3 = dataBase.getConnection().prepareStatement("SELECT EMAIL FROM TRAJET WHERE IDTRAJ = ?");
                    stmt3.setInt(1, idTrajet);
                    ResultSet rset2 = stmt3.executeQuery();
                    if(!rset2.next()){
                        System.out.println("Email non trouvé");
                    }
                    String email = rset2.getString(1);
                    rset2.close();
                    stmt3.close();
                    transfertArgent(dataBase,email,prix);
                    }
                PreparedStatement stmt = dataBase.getConnection().prepareStatement("UPDATE TRONCON SET NBPLACES = NBPLACES-1 WHERE IDTRAJET = ? AND COORDEPLON = ? AND COORDEPLAT= ? AND COORARRLAT= ? AND COORARRLAT= ?");
                stmt.setInt(1, arrv.getIdTrajet());
                stmt.setDouble(2, dep.getLng());
                stmt.setDouble(3, dep.getLat());
                stmt.setDouble(4, arrv.getLng());
                stmt.setDouble(5, arrv.getLat());
                stmt.executeUpdate();
                stmt.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            arrv=arrv.getPredecesseur();
        }
           return true;
        }



    public int consulterSolde (DataBase dataBase){
        try {
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT \"SOLDE\" FROM \"UTILISATEUR\" WHERE \"EMAIL\" = ?");
            stmt.setString(1, getEmail());
            ResultSet rset = stmt.executeQuery();
            //Calcul du total de l'argent à ajouter:
            rset.next();
            int solde = rset.getInt(1);
            stmt.close(); rset.close();
            return solde;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
    }

    /**
     * Ajoute l'argent argent à l'utilisateur
     * @param dataBase
     * @param argent
     */
    public Boolean ajoutArgent(DataBase dataBase, int argent){
        try{

            PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT \"SOLDE\" FROM \"UTILISATEUR\" WHERE \"EMAIL\" = ?");
            stmt.setString(1, getEmail());
            ResultSet rset = stmt.executeQuery();
            //Calcul du total de l'argent à ajouter:
            rset.next();
            argent = rset.getInt(1) + argent;
            rset.close();
            stmt.close();

            PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("UPDATE \"UTILISATEUR\" SET (\"SOLDE\") = ? WHERE \"EMAIL\" = ?");
            stmt2.setInt(1, argent);
            stmt2.setString(2, getEmail());
            stmt2.executeUpdate();
            stmt2.close();
            return true;
            
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
                

    }

    public Boolean ajouterVoiture(DataBase dataBase, Vehicule vehicule){
        vehicule.ajout(dataBase, getEmail());
        return true;
    }

    /**
     * Permet d'effectuer des virements
     * @param Email
     * @param argent
     */
    public void transfertArgent(DataBase dataBase, String email, int argent){

        if (getSolde()<argent){
            System.out.println("Solde insuffisant!");
            return;
        }
        try{

            //D'abord on enlève l'argent:
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT \"SOLDE\" FROM \"UTILISATEUR\" WHERE \"EMAIL\" = ?");
            stmt.setString(1, getEmail());
            ResultSet rset = stmt.executeQuery();
            rset.next();
            int expediteur = rset.getInt(1) - argent;
            rset.close();
            stmt.close();
            PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("UPDATE \"UTILISATEUR\" SET (\"SOLDE\") = ? WHERE \"EMAIL\" = ?");
            stmt2.setInt(1, expediteur);
            stmt2.setString(2, getEmail());
            stmt2.executeUpdate();
            stmt2.close();

            //On le rajoute sur l'autre email:
            PreparedStatement stmt3 = dataBase.getConnection().prepareStatement("SELECT \"SOLDE\" FROM \"UTILISATEUR\" WHERE \"EMAIL\" = ?");
            stmt3.setString(1, email);
            ResultSet rset1 = stmt3.executeQuery();
            rset1.next();
            int dest = rset1.getInt(1) + argent;
            rset1.close();
            stmt3.close();
            PreparedStatement stmt4 = dataBase.getConnection().prepareStatement("UPDATE \"UTILISATEUR\" SET (\"SOLDE\") = ? WHERE \"EMAIL\" = ?");
            stmt4.setInt(1, dest);
            stmt4.setString(2, email);
            stmt4.executeUpdate();
            stmt4.close();
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        setSolde(getSolde()-argent);
    }

    /**
     * Sélectionne les voitures que l'utilisateur peut conduire
     * @param dataBase
     */
    public String[] selectionnerVoitures(DataBase dataBase){

        try{
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT DISTINCT IMMATRICULATION FROM PROPRIOVOITURE WHERE EMAIL = ?");
            stmt.setString(1, getEmail());
            ResultSet rset = stmt.executeQuery();
            
            while(rset.next()){
                setListeVoitures(rset.getString(1));
            }

            int size = getListeVoitures().size();
            String[] tabVoitures = new String[size];
            int i = 0;
            for (String voiture: getListeVoitures()){
                tabVoitures[i] = voiture;
                i++;
            }
            return tabVoitures;
            
        }
        catch (SQLException e){
            e.printStackTrace();
            String[] tabvide = {""};
            return tabvide;
        }
    }
    
    public Boolean stockerTrajets( DataBase dataBase){
        try {
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT * FROM \"TRAJET\" WHERE \"EMAIL\" = ?");
            stmt.setString(1, getEmail());
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                Trajet traj = new Trajet(rset.getString(6) ,rset.getString(2), rset.getString(3), rset.getInt(4), rset.getInt(5), rset.getString(7), rset.getString(8));
                trajets.add(traj);
            }
            stmt.close(); rset.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**Renvoie la liste des trajets
     * 
     */
    public Trajet[] returnTrajet(DataBase dataBase){
        stockerTrajets(dataBase);
        Trajet[] trajetsUser = new Trajet[trajets.size()];
        int i = 0;
        for (Trajet t : trajets){
            trajetsUser[i] = t;
            trajetsUser[i].toString();
            i++;
        }
        return trajetsUser;
    }




    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Utilisateur [email=" + email + ", listeVoitures=" + listeVoitures + ", motDePasse=" + motDePasse
                + ", nom=" + nom + ", prenom=" + prenom + ", solde=" + solde + ", ville=" + ville + "]";
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return String return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return String return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return String return the motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * @param motDePasse the motDePasse to set
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    /**
     * @return float return the solde
     */
    public int getSolde() {
        return solde;
    }

    /**
     * @param solde the solde to set
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

}
