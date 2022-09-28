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


public class PageInscription extends JFrame implements ActionListener {
    
    private DataBase dataBase ;
    
    JButton bouttonInscription ; 
    JButton bouttonRetour ; 
    JTextField ZoneEmail ; 
    JTextField ZonePasswd ; 
    JTextField ZonePrenom ; 
    JTextField ZoneNom ; 
    JTextField ZoneVille ;

    public PageInscription(DataBase dataBase) {
        
        this.dataBase=dataBase;
        bouttonInscription = new JButton();
        bouttonInscription.setBounds(125 , 380 , 250, 50 );
        bouttonInscription.setText("S'inscrire");
        bouttonInscription.setFocusable(false);
        bouttonInscription.addActionListener(this);

        bouttonRetour = new JButton();
        bouttonRetour.setBounds(350 , 437 , 140, 20 );
        bouttonRetour.setText("Retour");
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);

        ZoneEmail = new JTextField() ;
        ZoneEmail.setBounds(125, 125 , 250 , 25 ); 

        ZonePasswd = new JTextField() ;
        ZonePasswd.setBounds(125, 175 , 250 , 25 );
        
        ZonePrenom = new JTextField() ; 
        ZonePrenom.setBounds(125,275,250,25);

        ZoneNom = new JTextField() ;
        ZoneNom.setBounds(125,225,250,25);

        ZoneVille = new JTextField() ; 
        ZoneVille.setBounds(125,325,250,25);


        JLabel labelInscritToi = new JLabel("Inscris Toi !");
        labelInscritToi.setFont(new Font("Non Serif", 1 , 20));
        labelInscritToi.setBounds(195,25,150,50);
 
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(125,100,150,25);

        JLabel labelPasswd = new JLabel("Mot de passe:");
        labelPasswd.setBounds(125,150,200,25);

        JLabel labelNom = new JLabel("Votre Nom");
        labelNom.setBounds(125,200,200,25);

        JLabel labelPrenom = new JLabel("Votre prénom");
        labelPrenom.setBounds(125,250,200,25);
        
        JLabel labelVille = new JLabel("Ville de résidence");
        labelVille.setBounds(125,300,200,25);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page d'Inscription") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));

        this.add(bouttonInscription);
        this.add(bouttonRetour);
        this.add(ZoneEmail);
        this.add(ZonePasswd);
        this.add(ZoneNom);
        this.add(ZonePrenom);
        this.add(ZoneVille);
        this.add(labelInscritToi);
        this.add(labelEmail);
        this.add(labelPasswd);
        this.add(labelPrenom);
        this.add(labelNom);
        this.add(labelVille);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ( e.getSource() == bouttonInscription) {

            if ( ZoneEmail.getText().equals("") || ZonePasswd.getText().equals("") || ZoneNom.getText().equals("") || ZonePrenom.getText().equals("") || ZoneVille.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Remplissez tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else {
                String email = ZoneEmail.getText();
                String passwd = ZonePasswd.getText();
                String nom = ZoneNom.getText();
                String prenom = ZonePrenom.getText();
                String ville = ZoneVille.getText();

                // Ici on réalise l'inscription avec les champs au dessus
               Utilisateur utilisateur =  new Utilisateur(email, nom, prenom, ville, passwd, 0);
                // ConnexionReussi est a defenir en fonction de la réponse reussi a la requete de l'inscription  
                if ( utilisateur.inscription(dataBase) ) {
                    JOptionPane.showMessageDialog(null,"Inscription réussi, vous pouvez maintenant vous connecter","Confirmation",1);
                    this.dispose();
                    PageConnexion pageConnexion = new PageConnexion(dataBase) ; 
                }

                else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription","Erreur",JOptionPane.ERROR_MESSAGE); 
                }
            }
        }

        else if ( e.getSource() == bouttonRetour ) {
            this.dispose();
            PageAccueil pageAccueil = new PageAccueil(dataBase);
        }
    }
}
