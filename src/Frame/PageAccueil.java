package src.Frame ; 

import src.BaseDeDonnees.* ;


import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PageAccueil extends JFrame implements ActionListener {
    
    private DataBase dataBase ;
    private JButton bouttonInscription ;
    private JButton bouttonConnexion ; 

    public PageAccueil ( DataBase dataBase ) {
        this.dataBase = dataBase ; 
        ImageIcon imageIcon = new ImageIcon("ressource/logo.png");
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(200, 120,  java.awt.Image.SCALE_SMOOTH); 
        imageIcon = new ImageIcon(newimg);  
        
        
        
        JLabel label = new JLabel(); 
        label.setIcon(imageIcon);
        label.setBounds(150,50,200,200);


        bouttonInscription = new JButton() ; 
        
        bouttonInscription.setBounds(125 , 250 , 250, 50 );
        bouttonInscription.setText("S'inscrire");
        bouttonInscription.setFocusable(false);
        bouttonInscription.addActionListener(this);
          
        bouttonConnexion = new JButton() ; 
        bouttonConnexion.setBounds(125 , 320 , 250 , 50 );
        bouttonConnexion.addActionListener((ActionListener) this);
        bouttonConnexion.setFocusable(false);
        bouttonConnexion.setText("Se Connecter ");
        


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page de Connexion") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true); 

        this.getContentPane().setBackground(new Color(255,160,122));
        this.add(bouttonInscription);
        this.add(bouttonConnexion);
        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e  ) {
        if ( e.getSource() == bouttonConnexion) {
            this.dispose();
            PageConnexion pageConnexion = new PageConnexion(dataBase);
        }
        else if ( e.getSource() == bouttonInscription) {
            this.dispose();
            PageInscription pageInscription = new PageInscription(dataBase);
        }
    }
}


