package src.BaseDeDonnees;

import java.util.HashMap;
import java.util.LinkedList;

public class Vertex implements Comparable<Vertex>{
    private String ville;
    private String date;
    private int IdTrajet;
    private double lat;
    private double lng;
    private int duree;
    private int prix;
    private int distance;
    private boolean changement;
    private Vertex predecesseur;

    public Vertex(String ville, String date,int idTrajet, double lat, double lng, boolean changement){
        this.ville=ville;
        this.date=date;
        this.IdTrajet=idTrajet;
        this.lat=lat;
        this.lng=lng;
        this.changement=changement;
    }

    public LinkedList<Vertex> recomposerItineraire(){
        Vertex p = this;
        LinkedList<Vertex> itineraire = new LinkedList<Vertex>(); 
        while(p!=null){
            itineraire.addFirst(p);
            p=p.predecesseur;
        }
        return itineraire;
    }

    // public int compare(Vertex v1, Vertex v2){
    //     if(v1.duree<v2.duree){
    //         return 1;
    //     }
    //     else{
    //         return -1;
    //     }
    // }
    
    @Override
    public int compareTo(Vertex v) {
        if(duree<v.duree){
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public int hashCode(){
        return ville.hashCode()+IdTrajet*2000000;
    }   

    @Override
    public boolean equals(Object o){
        Vertex v = (Vertex) o;
        return ville.compareTo(v.getVille())==0 && IdTrajet==v.getIdTrajet() && lat==v.getLat() && lng==v.getLng();
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
     * @return double return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return double return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
    }


    /**
     * @return String return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return Vertex return the predecesseur
     */
    public Vertex getPredecesseur() {
        return predecesseur;
    }

    /**
     * @param predecesseur the predecesseur to set
     */
    public void setPredecesseur(Vertex predecesseur) {
        this.predecesseur = predecesseur;
    }

    /**
     * @return int return the duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * @return int return the prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * @return int return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }


    /**
     * @return boolean return the changement
     */
    public boolean isChangement() {
        return changement;
    }

    /**
     * @param changement the changement to set
     */
    public void setChangement(boolean changement) {
        this.changement = changement;
    }


    /**
     * @return int return the IdTrajet
     */
    public int getIdTrajet() {
        return IdTrajet;
    }

    /**
     * @param IdTrajet the IdTrajet to set
     */
    public void setIdTrajet(int IdTrajet) {
        this.IdTrajet = IdTrajet;
    }

}



