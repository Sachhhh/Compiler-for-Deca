package src.Frame;

import src.BaseDeDonnees.*;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.BaseDeDonnees.Utilisateur;

public class PageMesTrajets extends JFrame implements ActionListener {

    private DataBase dataBase ;
    private Utilisateur utilisateur;
    private Trajet trajet ; 

    private JButton bouttonRetour ;

public PageMesTrajets(DataBase dataBase1,Utilisateur utilisateur1 , Trajet traj ){

    this.dataBase = dataBase1 ; 
    this.utilisateur = utilisateur1 ; 
    this.trajet = traj; 

    bouttonRetour = new JButton();
    bouttonRetour.setBounds(155 , 350 , 200, 50 );
    bouttonRetour.setText("Quitter");
    bouttonRetour.setFocusable(false);
    bouttonRetour.addActionListener(this);

    JLabel VilleDep = new JLabel(trajet.getLieuDep()) ;
    VilleDep.setFont(new Font("Non Serif", 0 , 12));
    VilleDep.setBounds(125, 125 , 250 , 25 ); 

    JLabel VilleArr = new JLabel(trajet.getLieuArr()) ;
    VilleArr.setFont(new Font("Non Serif", 0 , 12));
    VilleArr.setBounds(125, 175 , 250 , 25 );
    
    
    JLabel Zonedate = new JLabel(trajet.getDate()) ;
    Zonedate.setFont(new Font("Non Serif", 0 , 12));
    Zonedate.setBounds(125,225,250,25);
    
    
    JLabel ZoneVoiture = new JLabel(trajet.getImmatriculation()) ; 
    ZoneVoiture.setFont(new Font("Non Serif", 0 , 12));
    ZoneVoiture.setBounds(125,275,250,25);



    JLabel labelTrajet = new JLabel("Votre trajet : conducteur");
    labelTrajet.setFont(new Font("Non Serif", 1 , 20));
    labelTrajet.setBounds(125,25,350,50);

    JLabel labelVilleDep = new JLabel("Ville départ :");
    labelVilleDep.setBounds(125,100,150,25);

    JLabel labelVilleArr = new JLabel("Ville arrivé :");
    labelVilleArr.setBounds(125,150,200,25);

    JLabel labelDate = new JLabel("Date du trajet :");
    labelDate.setBounds(125,200,200,25);

    JLabel labelImm = new JLabel("Immatriculation du véhicule :");
    labelImm.setBounds(125,250,300,25);
    


    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    this.setTitle("Mes Trajets") ; 
    this.setLayout(null);
    this.setLocation(500,300);
    this.setResizable(false);
    this.setSize(500,450);
    this.setVisible(true); 
    this.getContentPane().setBackground(new Color(255,160,122));
    this.add(VilleDep);
    this.add(VilleArr);
    this.add(Zonedate);
    this.add(ZoneVoiture);
    this.add(labelTrajet);
    this.add(labelVilleDep);
    this.add(labelVilleArr);
    this.add(labelDate);
    this.add(labelImm);
    this.add(bouttonRetour);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ( e.getSource() == bouttonRetour) {
            this.dispose() ;
        }
        
    }
    
}
