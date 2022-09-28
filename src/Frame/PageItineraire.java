package src.Frame;

import src.BaseDeDonnees.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PageItineraire extends JFrame implements ActionListener {

    private DataBase dataBase ;
    private Utilisateur utilisateur;
    private HashMap<String,Vertex> resultatRecherche;
    private Vertex destination;

    JButton choisirTrajet ; 
    JButton bouttonRetour ;

    
    private JComboBox<String> ZoneTrajet ;


    public PageItineraire(DataBase dataBase,Utilisateur utilisateur,HashMap<String,Vertex> resultatRecherche, Vertex destination) {

        this.dataBase = dataBase ; 
        this.utilisateur = utilisateur; 
        this.resultatRecherche = resultatRecherche ;
        this.destination=destination;
        
        LinkedList<Vertex> itineraire = destination.recomposerItineraire();


        bouttonRetour = new JButton();
        bouttonRetour.setBounds(400 , 430 , 100, 25 );
        bouttonRetour.setText("retour");
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);

        choisirTrajet = new JButton();
        choisirTrajet.setBounds(150 , 400 , 200, 50 );
        choisirTrajet.setText("Valider");
        choisirTrajet.setFocusable(false);
        choisirTrajet.addActionListener(this);


        String villeDep = itineraire.getFirst().getVille();
        String villeArrv = itineraire.getLast().getVille();
        int duree = itineraire.getLast().getDuree();
        int prix = itineraire.getLast().getPrix();
        int distance = itineraire.getLast().getDistance();
        int idTrajet = itineraire.getFirst().getIdTrajet();
        try{
        PreparedStatement stmt = dataBase.getConnection().prepareStatement("SELECT EMAIL,IMMATRICULATION FROM TRAJET WHERE IDTRAJ = ?");
        stmt.setInt(1, idTrajet);
        ResultSet rset=stmt.executeQuery();
        rset.next();
        String email = rset.getString(1);
        String immat = rset.getString(2); 
        rset.close();
        stmt.close();
        PreparedStatement stmt2 = dataBase.getConnection().prepareStatement("SELECT MODELE,MARQUE FROM VEHICULE WHERE IMMATRICULATION = ?");
        stmt2.setString(1, immat);
        ResultSet rset2=stmt2.executeQuery();
        rset2.next();
        String modele = rset2.getString(1);
        String marque = rset2.getString(2);
        rset2.close();
        stmt2.close();
        PreparedStatement stmt3 = dataBase.getConnection().prepareStatement("SELECT PRENOM FROM UTILISATEUR WHERE EMAIL = ?");
        stmt3.setString(1, email);
        ResultSet rset3=stmt3.executeQuery();
        rset3.next();
        String conducteur = rset3.getString(1);
        rset3.close();
        stmt3.close();
      
        if(itineraire.getLast().isChangement()){
            //String conducteur2 = itineraire.getLast()

        }

        JLabel VilleDep = new JLabel(villeDep) ;
        VilleDep.setFont(new Font("Non Serif", 0 , 12));
        VilleDep.setBounds(125, 125 , 250 , 25 ); 

        JLabel VilleArr = new JLabel(villeArrv) ;
        VilleArr.setFont(new Font("Non Serif", 0 , 12));
        VilleArr.setBounds(125, 175 , 250 , 25 );
    
    
        JLabel Zonedure = new JLabel(String.valueOf(duree));
        Zonedure.setFont(new Font("Non Serif", 0 , 12));
        Zonedure.setBounds(125,225,250,25);
    
    
        JLabel Zoneprix = new JLabel(String.valueOf(prix)) ; 
        Zoneprix.setFont(new Font("Non Serif", 0 , 12));
        Zoneprix.setBounds(125,275,250,25);


        JLabel ZoneImmat = new JLabel("Immatriculation  : "+ immat );
        ZoneImmat.setFont(new Font("Non Serif", 0 , 12));
        ZoneImmat.setBounds(125,325,550,25);


        JLabel labelTrajet = new JLabel("Votre trajet avec " + conducteur);
        labelTrajet.setFont(new Font("Non Serif", 1 , 20));
        labelTrajet.setBounds(125,25,350,50);

        JLabel labelVilleDep = new JLabel("Ville départ :");
        labelVilleDep.setBounds(125,100,150,25);

        JLabel labelVilleArr = new JLabel("Ville arrivé :");
        labelVilleArr.setBounds(125,150,200,25);

        JLabel labelDure = new JLabel("Durée :");
        labelDure.setBounds(125,200,200,25);

        JLabel labelprix = new JLabel("Prix :");
        labelprix.setBounds(125,250,300,25);
    
        JLabel labelImmat = new JLabel("Voiture :  " + marque + "  " + modele);
        labelImmat.setBounds(125,300,300,25);

        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Choix du Trajet") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true); 
        this.add(VilleDep);
        this.add(VilleArr);
        this.add(Zonedure);
        this.add(Zoneprix);
        this.add(labelTrajet);
        this.add(labelVilleArr);
        this.add(labelVilleDep);
        this.add(labelDure);
        this.add(labelprix);
        this.add(ZoneImmat);
        this.add(labelImmat);
        this.add(choisirTrajet);
        this.getContentPane().setBackground(new Color(255,160,122));
        this.add(bouttonRetour);

    }catch (SQLException e){
        e.printStackTrace();
    }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
    
        if ( e.getSource() == choisirTrajet ) {
            if(utilisateur.validerTrajet(destination, dataBase)){
                this.dispose();
                PagePrincipale p = new PagePrincipale(dataBase, utilisateur);
            }
            else{
                JOptionPane.showMessageDialog(null, "Votre solde est insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }

        else if ( e.getSource() == bouttonRetour ) {
            this.dispose();
            PageChoixTrajet p2 = new PageChoixTrajet(dataBase, utilisateur,resultatRecherche);
    }
}   
}