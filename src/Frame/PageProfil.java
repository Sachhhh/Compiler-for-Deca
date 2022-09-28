package src.Frame;

import src.BaseDeDonnees.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import src.BaseDeDonnees.DataBase;


public class PageProfil extends JFrame implements ActionListener {
    
    private DataBase dataBase1 ;
    private Utilisateur utilisateur1 ;


    
    JButton bouttonRetour ; 
    JButton bouttonAjoutSolde ; 
    JLabel ZoneEmail ; 
    JLabel ZonePasswd ; 
    JLabel ZonePrenom ; 
    JLabel ZoneNom ; 
    JLabel ZoneVille ;
    JLabel ZoneSolde ;

    public PageProfil(DataBase dataBase, Utilisateur utilisateur) {


        this.dataBase1=dataBase;
        this.utilisateur1 = utilisateur;
        bouttonRetour = new JButton();
        bouttonRetour.setBounds(155 , 450 , 200, 50 );
        bouttonRetour.setText("Retour");
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);


        bouttonAjoutSolde = new JButton();
        bouttonAjoutSolde.setBounds(220 , 375 , 200, 25 );
        bouttonAjoutSolde.setText("Ajouter de l'argent");
        bouttonAjoutSolde.setFocusable(false);
        bouttonAjoutSolde.addActionListener(this);


        ZoneEmail = new JLabel(utilisateur.getEmail()) ;
        ZoneEmail.setFont(new Font("Non Serif", 0 , 12));
        ZoneEmail.setBounds(125, 125 , 250 , 25 ); 

        ZonePasswd = new JLabel(utilisateur.getMotDePasse()) ;
        ZonePasswd.setFont(new Font("Non Serif", 0 , 12));
        ZonePasswd.setBounds(125, 175 , 250 , 25 );
        
        ZonePrenom = new JLabel(utilisateur.getPrenom()) ; 
        ZonePrenom.setFont(new Font("Non Serif", 0 , 12));
        ZonePrenom.setBounds(125,275,250,25);

        ZoneNom = new JLabel(utilisateur.getNom()) ;
        ZoneNom.setFont(new Font("Non Serif", 0 , 12));
        ZoneNom.setBounds(125,225,250,25);

        ZoneVille = new JLabel(utilisateur.getVille()) ; 
        ZoneVille.setFont(new Font("Non Serif", 0 , 12));
        ZoneVille.setBounds(125,325,250,25);

        ZoneSolde = new JLabel(String.valueOf(utilisateur.getSolde()));
        ZoneSolde.setFont(new Font("Non Serif", 0 , 12));
        ZoneSolde.setBounds(125,375,250,25);


        JLabel labelProfil = new JLabel("Votre profil");
        labelProfil.setFont(new Font("Non Serif", 1 , 20));
        labelProfil.setBounds(190,25,150,50);
 
        JLabel labelEmail = new JLabel("Email :");
        labelEmail.setBounds(125,100,150,25);

        JLabel labelPasswd = new JLabel("Mot de passe :");
        labelPasswd.setBounds(125,150,200,25);

        JLabel labelNom = new JLabel("Votre Nom :");
        labelNom.setBounds(125,200,200,25);

        JLabel labelPrenom = new JLabel("Votre prénom :");
        labelPrenom.setBounds(125,250,200,25);
        
        JLabel labelVille = new JLabel("Ville de résidence :");
        labelVille.setBounds(125,300,200,25);

        JLabel labelSolde = new JLabel("Solde : ");
        labelSolde.setBounds(125,350,200,25);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page Profil") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,600);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));

        this.add(bouttonRetour);
        this.add(bouttonAjoutSolde);
        this.add(ZoneEmail);
        this.add(ZonePasswd);
        this.add(ZoneNom);
        this.add(ZonePrenom);
        this.add(ZoneVille);
        this.add(ZoneSolde);
        this.add(labelProfil);
        this.add(labelEmail);
        this.add(labelPasswd);
        this.add(labelPrenom);
        this.add(labelNom);
        this.add(labelVille);
        this.add(labelSolde);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ( e.getSource() == bouttonRetour) {
            this.dispose();
            PagePrincipale pagePrincipale = new PagePrincipale(dataBase1, utilisateur1);
            
        }

        if ( e.getSource() == bouttonAjoutSolde ) {
            this.dispose();
            System.out.println(utilisateur1.toString());
            PageAjoutSolde pageAjoutSolde = new PageAjoutSolde(dataBase1, utilisateur1);
        }
    }
}
