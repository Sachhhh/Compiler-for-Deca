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
import javax.swing.JTextField;

public class PageRechercheTrajet extends JFrame implements ActionListener {

    private DataBase dataBase ;
    private Utilisateur utilisateur;

    JButton buttonRechercheTrajet ; 
    JButton bouttonRetour ;
    JTextField ZoneVilleDepart ; 
    JTextField ZoneVilleArr ;

    private JComboBox<String> ZoneMois ;
    private JComboBox<String> ZoneJour ;
    private JComboBox<String> ZoneAnnee ;

    

    public PageRechercheTrajet(DataBase dataBase,Utilisateur utilisateur) {
        
        this.dataBase = dataBase ; 
        this.utilisateur = utilisateur; 
        
        buttonRechercheTrajet = new JButton();
        buttonRechercheTrajet.setBounds(125 , 360 , 250, 50 );
        buttonRechercheTrajet.setText("Rechercher un trajet");
        buttonRechercheTrajet.setFocusable(false);
        buttonRechercheTrajet.addActionListener(this);

        bouttonRetour = new JButton();
        bouttonRetour.setBounds(350 , 435 , 140, 20 );
        bouttonRetour.setText("Retour");
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);


        ZoneVilleDepart = new JTextField() ;
        ZoneVilleDepart.setBounds(125, 185 , 250 , 25 ); 

        ZoneVilleArr = new JTextField() ;
        ZoneVilleArr.setBounds(125, 285 , 250 , 25 ); 

     


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
        ZoneJour.setBounds(125,120,80,40);
        ZoneJour.setFocusable(false);

        // Affiche le choix du mois 

        String[] TableauMois = {"Mois","01","02","03","04","05","06","07","08","09","10","11","12"};
        ZoneMois = new JComboBox<String>(TableauMois);
        ZoneMois.setBounds(205,120,80,40);
        ZoneMois.setFocusable(false);

        // Affiche le choix de l'année 
        String[] TableauAnnee = {"Année","2022","2023","2024"};
        ZoneAnnee =  new JComboBox<String>(TableauAnnee);      
        ZoneAnnee.setBounds(295,120,80,40);
        ZoneAnnee.setFocusable(false);

        JLabel labelConnecteToi = new JLabel("Entrer votre itinéraire");
        labelConnecteToi.setFont(new Font("Non Serif", 1 , 20));
        labelConnecteToi.setBounds(125,25,350,50);
 
        JLabel labelDate = new JLabel("Date du trajet : ");
        labelDate.setBounds(125,80,150,25);

        JLabel labelDep = new JLabel("Ville de départ : ");
        labelDep.setBounds(125,160,150,25);

        JLabel labelArr = new JLabel("Ville d'arrivée : ");
        labelArr.setBounds(125,260,200,25);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Recherche de Trajet") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true); 

        this.getContentPane().setBackground(new Color(255,160,122));

        this.add(buttonRechercheTrajet);
        this.add(bouttonRetour);
        this.add(ZoneVilleDepart);
        this.add(ZoneVilleArr);
        this.add(labelConnecteToi);
        this.add(labelDep);
        this.add(labelArr);
        this.add(labelDate);

        this.add(ZoneJour);
        this.add(ZoneAnnee);
        this.add(ZoneMois);
        

    }


    @Override
    public void actionPerformed(ActionEvent e) {
    
        String jour = ZoneJour.getSelectedItem().toString();
        String mois = ZoneMois.getSelectedItem().toString();
        String annee = ZoneAnnee.getSelectedItem().toString();
        String villeDep = ZoneVilleDepart.getText();
        String villeArr = ZoneVilleArr.getText();
    
        if ( e.getSource() == buttonRechercheTrajet ) {

            if(villeDep.equals("")){
                JOptionPane.showMessageDialog(null, "Veuillez saisir une ville de départ","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else if(jour.equals("Jour") || mois.equals("Mois") || annee.equals("Année")){
                if(villeArr.equals("")){
                    JOptionPane.showMessageDialog(null, "Choissisez une Date ou une ville d'arrivée","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    //utilisateur.rechercheVilleAVilleB(villeDep, villeArr);
                }
            }
            else{
                String date  = jour+"-"+mois+"-"+annee ; 
                System.out.println(date);
                if(villeArr.equals("")){
                    HashMap<String,Vertex> resultatRecherche=utilisateur.rechercheVilleDate(villeDep, date, dataBase);
                    this.dispose();
                    PageChoixTrajet p = new PageChoixTrajet(dataBase, utilisateur, resultatRecherche) ;              
                }
                // else{
                //     utilisateur.rechercheTrajet();
                // }
            } 

        }

        else if ( e.getSource() == bouttonRetour ) {
            this.dispose();
            PagePrincipale pagePrincipale = new PagePrincipale(dataBase, utilisateur);
        }
    }
    
}
