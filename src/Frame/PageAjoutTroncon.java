package src.Frame;

import src.BaseDeDonnees.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PageAjoutTroncon extends JFrame implements ActionListener  {

    private DataBase dataBase ; 
    private Utilisateur utilisateur; 
    private int num ;
    private int idtraj;
    private int nbPlaceDispo;
    private int numMax;

    private JButton Ajout = new JButton("Ajouter ce trajet");


    JTextField ZoneVilleDepart = new JTextField() ;
    JTextField ZoneVilleArr = new JTextField() ;

    JTextField ZoneLatDep = new JTextField() ; 
    JTextField ZoneLongDep = new JTextField() ; 
    JTextField ZoneLatArr = new JTextField() ; 
    JTextField ZoneLongArr = new JTextField() ; 

    private JComboBox<String> ZoneMois ;
    private JComboBox<String> ZoneJour ;
    private JComboBox<String> ZoneAnnee ;
    private JComboBox<String> ZoneHeure ; 
    private JComboBox<String> ZoneMinute ; 
    private JComboBox<String> ZoneVoiture ;

    public PageAjoutTroncon( DataBase dataBase , Utilisateur utilisateur , int numero, int numeroMax, int idtrajet, int nbPlace) {

        this.dataBase = dataBase ; 
        this.utilisateur = utilisateur ; 
        this.num = numero ; 
        this.idtraj = idtrajet;
        this.nbPlaceDispo = nbPlace;
        this.numMax = numeroMax;

        this.dataBase = dataBase ; 
        this.utilisateur = utilisateur;

        Ajout.setBounds(120,465,250,50);
        Ajout.setFocusable(false);
        Ajout.addActionListener(this);


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
    
     
       // Label De la page 
        JLabel labelAJoutTrajet = new JLabel("Tronçon numéro : "+num);
        labelAJoutTrajet.setFont(new Font("Non Serif", 1 , 20));
        labelAJoutTrajet.setBounds(140,25,300,50);

        JLabel heurePrevu = new JLabel("Date prevu de départ : ");
        heurePrevu.setBounds(50,85,250,20);
          
        JLabel labelVilledep = new JLabel("Ville de départ : ");
        labelVilledep.setBounds(50,135,250,20);
        ZoneVilleDepart.setBounds(50,160,250,20);
        
        JLabel labelVillArr = new JLabel("Ville d'arrivée : ");
        labelVillArr.setBounds(50,185,250,20);
        ZoneVilleArr.setBounds(50,210,250,20);

        JLabel labelLongDep = new JLabel("Longitude de départ : ");
        labelLongDep.setBounds(50,235,250,20);
        ZoneLongDep.setBounds(50,260,250,20);

        JLabel labelLatDep = new JLabel("Latitude de départ : ");
        labelLatDep.setBounds(50,285,250,20);
        ZoneLatDep.setBounds(50, 310, 250, 20);


        JLabel labelLongArr = new JLabel("Longitude d'arrivée : ");
        labelLongArr.setBounds(50,335,250,20);
        ZoneLongArr.setBounds(50,360,250,20);

        JLabel labelLatArr = new JLabel("Latitude d'arrivée : ");
        labelLatArr.setBounds(50,385,250,20);
        ZoneLatArr.setBounds(50, 410, 250, 20);




        // Paramètre la fenetre globale 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page Ajout Trajet") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.pack();
        this.setSize(500,580);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));
        

        // Ajout des différents elements 
        this.add(Ajout);
        this.add(ZoneVilleDepart);
        this.add(ZoneVilleArr);
        this.add(ZoneLatDep);
        this.add(ZoneLongDep);
        this.add(ZoneLatArr);
        this.add(ZoneLongArr);
        this.add(labelAJoutTrajet);
        this.add(labelVilledep);
        this.add(labelVillArr);
        this.add(labelLatDep);
        this.add(labelLongDep);
        this.add(labelLatArr);
        this.add(labelLongArr);
        this.add(heurePrevu);
        this.add(ZoneJour);
        this.add(ZoneAnnee);
        this.add(ZoneMois);
        this.add(ZoneHeure);
        this.add(ZoneMinute);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if ( e.getSource() == Ajout ) {
            try {

                String jour = ZoneJour.getSelectedItem().toString();
                String mois = ZoneMois.getSelectedItem().toString();
                String annee = ZoneAnnee.getSelectedItem().toString();
                String heure = ZoneHeure.getSelectedItem().toString();
                String min = ZoneMinute.getSelectedItem().toString();
        
                String date = heure +"-"+ min +"-"+ jour +"-"+ mois +"-"+ annee;


                String villeDep = ZoneVilleDepart.getText()  ; 
                String villeArr = ZoneVilleArr.getText() ; 
                Float latDep = Float.parseFloat(ZoneLatDep.getText());
                Float longDep = Float.parseFloat(ZoneLongDep.getText());
                Float latArr = Float.parseFloat(ZoneLatArr.getText());
                Float longArr = Float.parseFloat(ZoneLongArr.getText());
                Troncon troncon = new Troncon(villeDep, villeArr, longDep, latDep, date, longArr, latArr, idtraj, nbPlaceDispo);
                if (troncon.ajoutTroncon(dataBase)){
                    if (num == (numMax - 1)){
                        PagePrincipale pagePrincipale = new PagePrincipale(dataBase, utilisateur);
                        this.dispose();
                    }
                    else{
                        this.dispose();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Erreur ajout","Erreur",JOptionPane.ERROR_MESSAGE); 
                }
                
            }
            catch ( Exception exp ) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer des coordonnées valides","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
