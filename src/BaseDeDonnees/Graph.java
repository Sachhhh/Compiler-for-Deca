package src.BaseDeDonnees;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

import javax.xml.crypto.Data;

public class Graph {
   private HashMap<Vertex,LinkedList<Edge>> graph ;
   private PriorityQueue<Vertex> pq ;


   public void addVertex(Vertex v){
       graph.putIfAbsent(v,new LinkedList<Edge>());
   }


   public void addEdge(Edge e){
        graph.get(e.getDepart()).add(e);
   }

   public Vertex ajoutTroncons(ResultSet rset, boolean b) throws SQLException{
       if(b){
        System.out.println("ajout avec changement");
       }
       else{
        System.out.println("ajout sans changement");
       }
        Vertex dep = new Vertex(rset.getString(2), rset.getString(13), rset.getInt(9), rset.getDouble(4), rset.getDouble(5),b);
        Vertex arrv = new Vertex(rset.getString(3), rset.getString(14),rset.getInt(9),  rset.getDouble(11), rset.getDouble(12),b);
        addVertex(arrv);
        addVertex(dep);
        addEdge(new Edge(dep, arrv, rset.getInt(6),rset.getInt(7),rset.getInt(8),rset.getInt(1)));
        return dep;
    }

   public void ajoutTrajet(int id, int num, DataBase dataBase, boolean b) throws SQLException{
        PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT * FROM TRONCON WHERE IDTRAJET = ? AND NUMTRONC > ?");
        stmt.setInt(1, id);
        stmt.setInt(2, num);
        ResultSet rset=stmt.executeQuery();
        while(rset.next()){
            ajoutTroncons(rset,b);
        }
        rset.close();
        stmt.close();
      
    }

    public void afficherGraph(){
        System.out.println("Nombre de sommets : "+graph.size());
        Iterator<LinkedList<Edge>> it = graph.values().iterator();
        int nbArcs = 0;
        while(it.hasNext()){
            nbArcs = nbArcs + it.next().size();
        }
        System.out.println("Nombre d'arcs' : "+nbArcs);
        Iterator<LinkedList<Edge>> it2 = graph.values().iterator();
        while(it2.hasNext()){
            Iterator<Edge> it3 = it2.next().iterator();
            while(it3.hasNext()){
                Edge e = it3.next();
                System.out.println("  "+e.getDepart().getIdTrajet()+"   "+e.getDepart().getVille()+"-->"+e.getArrivée().getVille()+" duree : "+e.getDuree());
            }
        }
        Set<Vertex> vertex=graph.keySet();
        Iterator<Vertex> it4=vertex.iterator();
        while(it4.hasNext()){
            Vertex v=it4.next();
            System.out.println(v.getVille()+" "+v.getDuree());
        }
    }


   public Graph(String ville, String date,DataBase dataBase) throws SQLException{
        this.graph = new HashMap<Vertex,LinkedList<Edge>>();
        this.pq = new PriorityQueue<Vertex>();
        // 1. On ajoute les troncons dont le départ est situé à moins d'1 km des coordonnées passées en paramètre et dont la date est celle passée en paramètre ainsi que la suite du trajet
        PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT * FROM TRONCON WHERE TO_CHAR(DATEDEP,'DD-MM-YYYY') = ? AND VILLEDEP = ? AND NBPLACES > 0");
        stmt.setString(1, date);
        stmt.setString(2, ville);
        ResultSet rset=stmt.executeQuery();
        while(rset.next()){
            ajoutTroncons(rset,false);
            ajoutTrajet(rset.getInt(9), rset.getInt(1), dataBase,false);
        }
        rset.close();
        stmt.close();
        //2. Pour chaque troncons présent dans le graphe on ajoute les troncons accessibles en changeant de voiture (moins d'1 km entre les deux troncons et moins d'1h d'attente) ainsi que la suite du trajet
        PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("SELECT * FROM TRONCON WHERE IDTRAJET != ? AND  DATEDEP>= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND DATEDEP<= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') + interval '1' hour AND SQRT(POWER(COORDEPLAT-?,2) + POWER(COORDEPLON-?,2)) < 0.01 AND NBPLACES > 0");
        Set<Vertex> vertex=graph.keySet();
        Iterator<Vertex> it=vertex.iterator();
        try{
        while(it.hasNext()){
            Vertex v=it.next();
            stmt2.setInt(1,v.getIdTrajet());
            stmt2.setString(2, v.getDate());
            stmt2.setString(3, v.getDate());
            stmt2.setDouble(4, v.getLat());
            stmt2.setDouble(5, v.getLng());
            ResultSet rset2=stmt2.executeQuery();
            while(rset2.next()){
                Vertex arrv = ajoutTroncons(rset2,true);
                addEdge(new Edge(v,arrv, (int) Math.sqrt(Math.pow(v.getLat()-arrv.getLat(),2)+Math.pow(v.getLng()-arrv.getLng(),2)) , 60, 0,rset2.getInt(1)));
                ajoutTrajet(rset2.getInt(9), rset2.getInt(1), dataBase,false);
            }
            rset2.close();
        }
        stmt2.close();
        }catch(Exception e){ 
        }
    }


    public void Dikjstra(Vertex depart){
        depart.setDuree(0);
        depart.setDistance(0);;
        pq.add(depart);
        while(!pq.isEmpty()){
            Vertex v1=pq.poll();
            LinkedList<Edge> edgeList = graph.get(v1);
            for(Edge e : edgeList){
                Vertex v2 = e.getArrivée();
                if(v2.getDuree()>v1.getDuree()+e.getDuree()){
                    v2.setDuree(v1.getDuree()+e.getDuree());
                    v2.setDistance(v1.getDistance()+e.getDistance());
                    v2.setPrix(v1.getPrix()+e.getPrix());
                    pq.add(v2);
                    v2.setPredecesseur(v1);
                }
            }
        }
    }
    
    public void initDikjstra(){
        Set<Vertex> vertex=graph.keySet();
        Iterator<Vertex> it=vertex.iterator();
        while(it.hasNext()){
            Vertex v=it.next();
            v.setDuree(Integer.MAX_VALUE);
            v.setDistance(Integer.MAX_VALUE);
        }
    }


    /**
     * @return HashMap<Vertex,LinkedList<Edge>> return the graph
     */
    public HashMap<Vertex,LinkedList<Edge>> getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(HashMap<Vertex,LinkedList<Edge>> graph) {
        this.graph = graph;
    }

    /**
     * @return PriorityQueue<Vertex> return the pq
     */
    public PriorityQueue<Vertex> getPq() {
        return pq;
    }

    /**
     * @param pq the pq to set
     */
    public void setPq(PriorityQueue<Vertex> pq) {
        this.pq = pq;
    }

    public static void main(String[] args){
        DataBase dataBase = new DataBase();
        try {
            dataBase.connection();
            Graph g = new Graph("Grenoble", "01-01-2022", dataBase);
            g.afficherGraph();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
    }

}
