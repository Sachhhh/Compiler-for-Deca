package src.BaseDeDonnees;

public class Edge {
    private Vertex depart;
    private Vertex arrivée;
    private int distance;
    private int duree;
    private int prix;
    private int idTroncon;

    public Edge(Vertex depart, Vertex arrivée, int distance, int duree, int prix, int idTroncon){
        this.depart=depart;
        this.arrivée=arrivée;
        this.distance=distance;
        this.prix=prix;
        this.duree=duree;
        this.idTroncon=idTroncon;
    }

    /**
     * @return Vertex return the depart
     */
    public Vertex getDepart() {
        return depart;
    }

    /**
     * @param depart the depart to set
     */
    public void setDepart(Vertex depart) {
        this.depart = depart;
    }

    /**
     * @return Vertex return the arrivée
     */
    public Vertex getArrivée() {
        return arrivée;
    }

    /**
     * @param arrivée the arrivée to set
     */
    public void setArrivée(Vertex arrivée) {
        this.arrivée = arrivée;
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
     * @return int return the idTroncon
     */
    public int getIdTroncon() {
        return idTroncon;
    }

    /**
     * @param idTroncon the idTroncon to set
     */
    public void setIdTroncon(int idTroncon) {
        this.idTroncon = idTroncon;
    }

}