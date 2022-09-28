package src.Frame;


import src.BaseDeDonnees.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class PageAjoutTrajet extends JFrame implements ActionListener  {

    private DataBase dataBase ;
    private Utilisateur utilisateur;
    
    private JButton Ajout = new JButton("Ajouter ce trajet");
    private JButton AjoutVoiture = new JButton("Ajouter une voiture");
    private JButton bouttonRetour = new JButton("Retour");

    JTextField ZoneNbPlace = new JTextField();
    JTextField ZoneNbTroncon = new JTextField();
    JTextField ZoneVilleDepart = new JTextField() ;
    JTextField ZoneVilleArr = new JTextField() ;

    private JComboBox<String> ZoneMois ;
    private JComboBox<String> ZoneJour ;
    private JComboBox<String> ZoneAnnee ;
    private JComboBox<String> ZoneHeure ; 
    private JComboBox<String> ZoneMinute ; 
    private JComboBox<String> ZoneVoiture ;

    public PageAjoutTrajet( DataBase dataBase, Utilisateur utilisateur){
        
        this.dataBase = dataBase ; 
        this.utilisateur = utilisateur;

        Ajout.setBounds(120,400,250,50);
        Ajout.setFocusable(false);
        Ajout.addActionListener(this);

        
        bouttonRetour.setBounds(350 , 465 , 140, 20 );
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);

        AjoutVoiture.setBounds(260,210,200,20);
        AjoutVoiture.setFocusable(false);
        AjoutVoiture.addActionListener(this);



        ZoneNbTroncon.setBounds(50,260,80,20);

        // Affiche le choix de la minute 
        String[] TableauMinutes = new String[61];
        TableauMinutes[0]="Min";
        for ( int i = 1 ; i<61 ; i++ ) {
            if ( i  <= 10)
            {
                TableauMinutes[i] = "0" + String.valueOf(i-1);
            }
            else 
            {
                TableauMinutes[i] = String.valueOf(i-1);
            }
        }
        ZoneMinute = new JComboBox<String>(TableauMinutes);
        ZoneMinute.setFocusable(false);
        ZoneMinute.setBounds(375,110,70,20);

        // Affiche le choix de l'heure
        String[] TableauHeure = new String[25] ;
        TableauHeure[0] = "Heure" ;
        for ( int i = 1 ; i<25 ; i++ ){
            
            if ( i <= 10 ) 
            {
                TableauHeure[i] = "0"+String.valueOf(i-1);
            }
            else 
            {
                TableauHeure[i]=String.valueOf(i-1);
            }
        }
        ZoneHeure = new JComboBox<String>(TableauHeure);
        ZoneHeure.setBounds(295,110,70  ,20);
        ZoneHeure.setFocusable(false);

        // Affiche le choix du jour 
        String[] TableauJour = new String[32];
        TableauJour[0]= "Jour";
        for ( int i = 1 ; i < 32 ; i++ ) {

            if ( i <= 9 ) {
                TableauJour[i]= "0"+String.valueOf(i);
            }
            else{
                TableauJour[i]= String.valueOf(i);
            }
        }
        ZoneJour = new JComboBox<String>(TableauJour) ; 
        ZoneJour.setBounds(50,110,60,20);
        ZoneJour.setFocusable(false);

        // Affiche le choix du mois 

        String[] TableauMois = {"Mois","01","02","03","04","05","06","07","08","09","10","11","12"};
        ZoneMois = new JComboBox<String>(TableauMois);
        ZoneMois.setBounds(120,110,70,20);
        ZoneMois.setFocusable(false);

        // Affiche le choix de l'année 
        String[] TableauAnnee = {"Année","2022","2023","2024"};
        ZoneAnnee =  new JComboBox<String>(TableauAnnee);      
        ZoneAnnee.setBounds(200,110,80,20);
        ZoneAnnee.setFocusable(false);


        // Choix de la voiture 
        String[] TableauVoiture = utilisateur.selectionnerVoitures(dataBase);
        ZoneVoiture = new JComboBox<String>(TableauVoiture); 
        ZoneVoiture.setBounds(50,210,200,20);

        // Label De la page 
        JLabel labelAJoutTrajet = new JLabel("Ajouter un trajet");
        labelAJoutTrajet.setFont(new Font("Non Serif", 1 , 20));
        labelAJoutTrajet.setBounds(155,25,200,50);

        // label choix date 
        JLabel labelDate = new JLabel("Date du trajet ");
        labelDate.setBounds(50,70,250,50);
        
        // Label nb de place 
        JLabel labelNbPlace = new JLabel("Nombre de place disponible :"); 
        labelNbPlace.setBounds(50,135,250,20);

        // Label choisir voiture 

        JLabel labelVoiture = new JLabel("Choix de la voiture :"); 
        labelVoiture.setBounds(50,185,250,20);

        // Label troncon 

        JLabel labelTroncon = new JLabel("Nombre de tronçon :");
        labelTroncon.setBounds(50,235,250,20);

        JLabel labelVilledep = new JLabel("Ville de départ : ");
        labelVilledep.setBounds(50,285,250,20);
        
        JLabel labelVillArr = new JLabel("Ville d'arrivée : ");
        labelVillArr.setBounds(50,335,250,20);



        ZoneVilleDepart.setBounds(50,310,250,20);
        ZoneVilleArr.setBounds(50,360,250,20);


        // Textfield nb place 
        ZoneNbPlace.setBounds(50,160,200,20);

        // Paramètre la fenetre globale 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page Ajout Trajet") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.pack();
        this.setSize(500,530);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));
        

        // Ajout des différents elements 
        this.add(Ajout);
        this.add(AjoutVoiture);
        this.add(bouttonRetour);
        this.add(ZoneJour);
        this.add(ZoneAnnee);
        this.add(ZoneMois);
        this.add(ZoneHeure);
        this.add(ZoneMinute);
        this.add(ZoneNbPlace);
        this.add(ZoneVoiture);
        this.add(ZoneNbTroncon);
        this.add(ZoneVilleDepart);
        this.add(ZoneVilleArr);
        this.add(labelAJoutTrajet);
        this.add(labelDate);
        this.add(labelNbPlace);
        this.add(labelVoiture);
        this.add(labelTroncon);
        this.add(labelVilledep);
        this.add(labelVillArr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int nbPlaceDispo;
        
        String jour = ZoneJour.getSelectedItem().toString();
        String mois = ZoneMois.getSelectedItem().toString();
        String annee = ZoneAnnee.getSelectedItem().toString();
        String heure = ZoneHeure.getSelectedItem().toString();
        String min = ZoneMinute.getSelectedItem().toString();
        String villeDep = ZoneVilleDepart.getText();
        String villeArr = ZoneVilleArr.getText();
        String immat = ZoneVoiture.getSelectedItem().toString();

        String date = heure +"-"+ min +"-"+ jour +"-"+ mois +"-"+ annee;
        //String voiture = ZoneVoiture.getSelectedItem().toString();

        if ( e.getSource() == Ajout) {
            
            int nbTroncon ;  
            
            try {
                nbTroncon = Integer.parseInt(ZoneNbTroncon.getText());
                nbPlaceDispo = Integer.parseInt(ZoneNbPlace.getText());
            
                if ( villeDep.equals("") || villeArr.equals("") ) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer une ville de départ de d'arrivée","Erreur",JOptionPane.ERROR_MESSAGE);
                }

                else if ( jour.equals("Jour") || mois.equals("Mois") || annee.equals("Année") || heure.equals("Heure") || min.equals("Min") )
                {
                    JOptionPane.showMessageDialog(null, "Choissisez une Date","Erreur",JOptionPane.ERROR_MESSAGE);
                }

                else if ( nbTroncon <= 0 ) { 
                    JOptionPane.showMessageDialog(null, "Nombre de troncon invalide","Erreur",JOptionPane.ERROR_MESSAGE);
                }

                else if ( nbPlaceDispo <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Nombre de place disponible invalide","Erreur",JOptionPane.ERROR_MESSAGE);
                }

                // Check voiture 
                
                else {
                    Trajet trajet = new Trajet(date, utilisateur.getEmail(), immat, nbPlaceDispo, 0, villeDep, villeArr);
                    
                    int idtrajet = trajet.ajoutTrajet(dataBase);
                    this.dispose();

                    for ( int i = 0 ; i<nbTroncon ; i++ ) {
                        PageAjoutTroncon pageAjoutTroncon = new PageAjoutTroncon(dataBase, utilisateur, i, nbTroncon, idtrajet, nbPlaceDispo);
                    }
                    
                }
            }

            catch ( Exception exp ) {
                JOptionPane.showMessageDialog(null, "Veuillez vérifier les champs","Erreur",JOptionPane.ERROR_MESSAGE);
            }

        }

        else if ( e.getSource() == AjoutVoiture ) {
            this.dispose();
            PageAjoutVoiture pageAjoutVoiture = new PageAjoutVoiture(dataBase,utilisateur); 
        }

        

        else if ( e.getSource() == bouttonRetour) {
            this.dispose();
            PagePrincipale pagePrincipale = new PagePrincipale(dataBase, utilisateur);
        }        
	}
}
