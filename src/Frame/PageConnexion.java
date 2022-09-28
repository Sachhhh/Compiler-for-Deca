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

public class PageConnexion extends JFrame implements ActionListener {
    
    private DataBase dataBase ;
    private Utilisateur utilisateur;

    JButton bouttonConnexion ; 
    JButton bouttonRetour ;
    JTextField ZoneEmail ; 
    JTextField ZonePasswd ; 

    public PageConnexion(DataBase dataBase) {

        this.dataBase=dataBase;


        bouttonRetour = new JButton();
        bouttonRetour.setBounds(350 , 430 , 140, 20 );
        bouttonRetour.setText("Retour");
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);

        bouttonConnexion = new JButton();
        bouttonConnexion.setBounds(125 , 330 , 250, 50 );
        bouttonConnexion.setText("Se Connecter");
        bouttonConnexion.setFocusable(false);
        bouttonConnexion.addActionListener(this);

        ZoneEmail = new JTextField() ;
        ZoneEmail.setBounds(125, 145 , 250 , 25 ); 

        ZonePasswd = new JTextField() ;
        ZonePasswd.setBounds(125, 245 , 250 , 25 ); 

        JLabel labelConnecteToi = new JLabel("Connecte Toi !");
        labelConnecteToi.setFont(new Font("Non Serif", 1 , 20));
        labelConnecteToi.setBounds(175,25,250,50);
 
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(125,120,150,25);

        JLabel labelPasswd = new JLabel("Mot de passe:");
        labelPasswd.setBounds(125,220,200,25);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page de Connexion") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));

        this.add(bouttonConnexion);
        this.add(bouttonRetour);
        this.add(ZoneEmail);
        this.add(ZonePasswd);
        this.add(labelConnecteToi);
        this.add(labelEmail);
        this.add(labelPasswd);

    }

    public void actionPerformed(ActionEvent e) {
        
        if ( e.getSource() == bouttonConnexion) {

            if( ZoneEmail.getText().equals("") || ZonePasswd.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Remplissez tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            
            else {

                String email = ZoneEmail.getText();
                String passwd = ZonePasswd.getText();


                utilisateur = new Utilisateur(email , passwd);
                

                if (utilisateur.connexion(dataBase)) {
                    this.dispose();
                    PagePrincipale pagePrincipale = new PagePrincipale(dataBase,utilisateur);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Mauvais identifiant / mot de passe","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
        } 

        else if ( e.getSource() == bouttonRetour) {
            this.dispose();
            PageAccueil pageAccueil = new PageAccueil(dataBase);
        }
    }
}
