package src.Frame;

import src.BaseDeDonnees.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class PageChoixTrajet extends JFrame implements ActionListener {

    private DataBase dataBase ;
    private Utilisateur utilisateur;
    HashMap<String,Vertex> resultatRecherche;    

    JButton buttonVoirTrajet ; 
    JButton bouttonRetour ;


    private JComboBox<String> ZoneTrajet ;


    public PageChoixTrajet(DataBase dataBase,Utilisateur utilisateur,HashMap<String,Vertex> resultatRecherche) {

        this.dataBase = dataBase ; 
        this.utilisateur = utilisateur; 
        this.resultatRecherche = resultatRecherche ;

        bouttonRetour = new JButton();
        bouttonRetour.setBounds(350 , 435 , 140, 20 );
        bouttonRetour.setText("Retour");
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);
        this.getContentPane().setBackground(new Color(255,160,122));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Choix du Trajet") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true); 
        this.add(bouttonRetour);


        if(resultatRecherche.size()==0){
            JLabel labelNoResult = new JLabel("Désolé, aucune déstination");
            labelNoResult.setFont(new Font("Non Serif", 1 , 20));
            labelNoResult.setBounds(100,150,350,50);
            this.add(labelNoResult);
        }
        else{
            buttonVoirTrajet = new JButton();
            buttonVoirTrajet.setBounds(125 , 360 , 250, 50 );
            buttonVoirTrajet.setText("Voir ce Trajet");
            buttonVoirTrajet.setFocusable(false);
            buttonVoirTrajet.addActionListener(this);

            // Affiche le choix du trajet
            Object[] tableau = resultatRecherche.keySet().toArray();
            String [] tableauVilles = new String[tableau.length];
            for(int i = 0; i<tableau.length; i++) {
                tableauVilles[i]= (String) tableau[i];
            }
            ZoneTrajet = new JComboBox<String>(tableauVilles);
            ZoneTrajet.setFocusable(false);
            ZoneTrajet.setBounds(125,180,250,20);
            JLabel labelConnecteToi = new JLabel("Choisissez votre destination");
            labelConnecteToi.setFont(new Font("Non Serif", 1 , 20));
            labelConnecteToi.setBounds(125,25,350,50);
            this.add(buttonVoirTrajet);
            this.add(labelConnecteToi);
            this.add(ZoneTrajet);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
    
        if ( e.getSource() == buttonVoirTrajet ) {
            String ville = ZoneTrajet.getSelectedItem().toString();
            if(ville.equals("Destination")){
                JOptionPane.showMessageDialog(null, "Veuillez choisir une destination","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{
                Vertex vertex = resultatRecherche.get(ville); 
                this.dispose();
                PageItineraire p = new PageItineraire(dataBase,utilisateur,resultatRecherche,vertex); 
            }

        }

        else if ( e.getSource() == bouttonRetour ) {
            this.dispose();
            PageRechercheTrajet p = new PageRechercheTrajet(dataBase, utilisateur);
    }
}
}