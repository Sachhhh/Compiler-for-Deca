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

public class PagePrincipale  extends JFrame implements ActionListener {

    private DataBase dataBase ;
    private Utilisateur utilisateur;

    JButton bouttonMesTrajets ; 
    JButton bouttonAjoutTrajets;
    JButton bouttonChercherTrajets;
    JButton bouttonProfil;
    JButton boutonDeco ; 
    
    public PagePrincipale(DataBase dataBase, Utilisateur utilisateur) {

        this.dataBase=dataBase;
        this.utilisateur=utilisateur;

        JLabel labelBienvenue = new JLabel("Bienvenue sur VV !");
        labelBienvenue.setFont(new Font("Non Serif", 1 , 25));
        labelBienvenue.setBounds(120,50,350,50);
        labelBienvenue.setForeground(new Color(0x4169E1));



        bouttonMesTrajets = new JButton();
        bouttonMesTrajets.setBounds(125 , 320 , 250, 50 );
        bouttonMesTrajets.setText("Mes Trajets");
        bouttonMesTrajets.setFocusable(false);
        bouttonMesTrajets.addActionListener(this);

        bouttonAjoutTrajets = new JButton();
        bouttonAjoutTrajets.setBounds(125 , 240 , 250, 50 );
        bouttonAjoutTrajets.setText("Ajouter un Trajet");
        bouttonAjoutTrajets.setFocusable(false);
        bouttonAjoutTrajets.addActionListener(this);
        
        bouttonChercherTrajets = new JButton();
        bouttonChercherTrajets.setBounds(125 , 160 , 250, 50 );
        bouttonChercherTrajets.setText("Rechercher un covoiturage");
        bouttonChercherTrajets.setFocusable(false);
        bouttonChercherTrajets.addActionListener(this);

        bouttonProfil = new JButton();
        bouttonProfil.setBounds(10 , 432 , 100, 20 );
        bouttonProfil.setText("Profil");
        bouttonProfil.setFocusable(false);
        bouttonProfil.addActionListener(this);

        boutonDeco = new JButton();
        boutonDeco.setBounds(350 , 432 , 140, 20 );
        boutonDeco.setText("Deconnexion");
        boutonDeco.setFocusable(false);
        boutonDeco.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page Principale") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));

        this.add(bouttonAjoutTrajets);
        this.add(bouttonChercherTrajets);
        this.add(bouttonMesTrajets);
        this.add(bouttonProfil);
        this.add(boutonDeco);
        this.add(labelBienvenue);
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == bouttonChercherTrajets ) {
            this.dispose();
            PageRechercheTrajet pageRechercheTrajet = new PageRechercheTrajet(dataBase, utilisateur);
        }

        else if ( e.getSource() == bouttonAjoutTrajets ) {
            this.dispose();
            PageAjoutTrajet pageTrajet = new PageAjoutTrajet(dataBase, utilisateur); 
        }

        else if ( e.getSource() == bouttonMesTrajets )  {
            
            Trajet[] tab = utilisateur.returnTrajet(dataBase);
            int size = tab.length;
            if ( size <= 0 ) {
                JOptionPane.showMessageDialog(null, "Aucun Trajet","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            
            else {
                for ( int i = 0 ; i<size ; i++ ) {
                    PageMesTrajets pageMesTrajets = new PageMesTrajets(dataBase, utilisateur, tab[i]);
                }

            }
            
        }

        else if ( e.getSource() == bouttonProfil ) { 
            this.dispose();
            PageProfil pageProfil = new PageProfil(dataBase, utilisateur);
        }

        else if ( e.getSource() == boutonDeco ) {
            this.dispose();
            PageAccueil pageAccueil = new PageAccueil(dataBase);
        }
    }
}
