package src.BaseDeDonnees;
import java.lang.Object;
import java.lang.Math;

import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

import java.lang.Math;


public class Troncon{
    //Données:

    // Clé primaire: idTroncon.
    static int idTroncon;
    private String villeDpt;
    private String villeArr;
    private float coordDptLon;
    private float coordDptLat;
    private String dateDpt;
    private String dateArr;
    private float coordArrLon;
    private float coordArrLat;
    private float dist;
    private float duree;
    private float montant;
    private int idTrajet;

    public int getIdTroncon() {
      return idTroncon;
    }


    public void setIdTroncon(int idTroncon) {
      this.idTroncon = idTroncon;
    }


    public String getVilleDpt() {
      return villeDpt;
    }


    public void setVilleDpt(String villeDpt) {
      this.villeDpt = villeDpt;
    }


    public String getVilleArr() {
      return villeArr;
    }


    public void setVilleArr(String villeArr) {
      this.villeArr = villeArr;
    }


    public float getCoordDptLon() {
      return coordDptLon;
    }


    public void setCoordDptLon(float coordDptLon) {
      this.coordDptLon = coordDptLon;
    }


    public float getCoordDptLat() {
      return coordDptLat;
    }


    public void setCoordDptLat(float coordDptLat) {
      this.coordDptLat = coordDptLat;
    }


    public float getCoordArrLon() {
      return coordArrLon;
    }


    public void setCoordArrLon(float coordArrLon) {
      this.coordArrLon = coordArrLon;
    }


    public float getCoordArrLat() {
      return coordArrLat;
    }


    public void setCoordArrLat(float coordArrLat) {
      this.coordArrLat = coordArrLat;
    }


    public float getDist() {
      return dist;
    }


    public void setDist(float dist) {
      this.dist = dist;
    }


    public float getDuree() {
      return duree;
    }


    public void setDuree(float duree) {
      this.duree = duree;
    }


    public float getMontant() {
      return montant;
    }


    public void setMontant(float montant) {
      this.montant = montant;
    }


    public int getIdTrajet() {
      return idTrajet;
    }


    public void setIdTrajet(int idTrajet) {
      this.idTrajet = idTrajet;
    }


    public int getNbPlacesDispo() {
      return nbPlacesDispo;
    }


    public void setNbPlacesDispo(int nbPlacesDispo) {
      this.nbPlacesDispo = nbPlacesDispo;
    }



    //idTronçon sera généré automatiquement en incrémentant la table tronçon
    public Troncon(String villeDpt, String villeArr, float coordDptLon, float coordDptLat, float coordArrLon,
            float coordArrLAt, float dist, float duree, float montant, int idTrajet, int nbPlacesDispo,
             String dateDep, String dateArr) {
        this.villeDpt = villeDpt;
        this.villeArr = villeArr;
        this.coordDptLon = coordDptLon;
        this.coordDptLat = coordDptLat;
        this.coordArrLon = coordArrLon;
        this.coordArrLat = coordArrLAt;
        this.dist = dist;
        this.duree = duree;
        this.montant = montant;
        this.idTrajet = idTrajet;
        this.nbPlacesDispo = nbPlacesDispo;
        this.dateArr = dateArr;
        this.dateDpt = dateDep;
    }

    


    public Troncon(String villeDpt, String villeArr, float coordDptLon, float coordDptLat, String dateDpt,
        float coordArrLon, float coordArrLat, int idTrajet, int nbPlacesDispo) {
      this.villeDpt = villeDpt;
      this.villeArr = villeArr;
      this.coordDptLon = coordDptLon;
      this.coordDptLat = coordDptLat;
      this.dateDpt = dateDpt;
      this.coordArrLon = coordArrLon;
      this.coordArrLat = coordArrLat;
      this.idTrajet = idTrajet;
      this.nbPlacesDispo = nbPlacesDispo;
    }




    private int nbPlacesDispo;

  


    //Fonctionne
    public Boolean ajoutTroncon(DataBase dataBase){ 
        
        try {


          this.dist = calculDistance(coordDptLat, coordDptLon, coordArrLat, coordArrLon);
          calculPrix(dataBase, dist);
          calculDuree();
          int idtronc;
          PreparedStatement stmt0 = dataBase.getConnection().prepareStatement("SELECT MAX(NUMTRONC) FROM \"TRONCON\"");
          ResultSet rset = stmt0.executeQuery();
          if (!rset.next()){
            idtronc = 1;
          }
          else{
            idtronc = rset.getInt(1)+1;
          }

            PreparedStatement stmt = dataBase.getConnection().prepareStatement("INSERT INTO \"TRONCON\" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'HH24-MI-DD-MM-RR'),TO_DATE(?,'HH24-MI-DD-MM-RR'))");
            
            stmt.setInt(1, idtronc);
            stmt.setString(2, villeDpt);
            stmt.setString(3, villeArr);

            stmt.setFloat(4, coordDptLon);
            stmt.setFloat(5, coordDptLat);
            
            stmt.setFloat(6, dist);
            stmt.setFloat(7, duree);
            stmt.setFloat(8, montant);
            stmt.setInt(9, idTrajet);
            stmt.setInt(10, nbPlacesDispo);
            //Les coordonnées d'arrivées sont en dernier dans la table
            stmt.setFloat(11, coordArrLon);
            stmt.setFloat(12, coordArrLat);
            stmt.setString(13, dateDpt);
            stmt.setString(14, dateArr);
            stmt.executeUpdate();
            stmt.close();

            return true;




            }
            catch (SQLException e) {
                e.printStackTrace();
                return false;
            }


        }

        //Méthode supprimer inutile (on supprime directement les trajets)

        //Calcul des distances en km
        public float calculDistance(float latA, float lonA, float latB, float lonB){
          //float result = (float)Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lat1 - lon2))*6371;
          //System.out.println(result);
          //return result;
          float RADIUS = (float)6371.01; 
          float temp = (float) (RADIUS*Math.sqrt((lonA-lonB)*(lonA-lonB) + (latA-latB)*(latA-latB)));
          System.out.println(temp);
          return temp*(float)Math.PI/180;
        }

        public Boolean calculPrix (DataBase dataBase, float dist){
          try {
            PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT \"IMMATRICULATION\" FROM \"TRAJET\" WHERE \"IDTRAJET\" = ? ");
            stmt.setInt(1, getIdTrajet());
            ResultSet rset = stmt.executeQuery();
            rset.next();
            String immat = rset.getString(1);
            stmt.close(); rset.close();
            PreparedStatement stmt1 = dataBase.getConnection().prepareStatement("SELECT * FROM \"VEHICULE\" WHERE \"IMMATRICULATION\" = ? ");
            stmt1.setString(1, immat);
            ResultSet rset1 = stmt1.executeQuery();
            rset1.next();
            String nrj = rset1.getString(6);
            int pfisc = rset1.getInt(4);
            double cout;
            if (nrj == "Electrique"){
              cout = 0.5;
            }
            else if (nrj == "Hybride"){
              cout = 1;
            }
            else{
              cout = 1.5;
            }

            cout = cout * pfisc * 0.1;
            cout = cout * dist;
            this.setMontant((float)cout);
            return true;

          } catch (Exception e) {
            e.printStackTrace();
            return false;
          }
        }

        public Boolean calculDuree (){
          //Une moyenne de 90 km/h
          this.duree = dist*2/3;
          return true;

        }
    

    

}
