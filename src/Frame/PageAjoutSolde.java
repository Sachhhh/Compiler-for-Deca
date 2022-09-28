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

public class PageAjoutSolde extends JFrame implements ActionListener{

    JButton bouttonAjoutSolde;
    
    JTextField ZoneMontant ; 

    DataBase dataBase1 ; 
    Utilisateur utilisateur1;
    

    public PageAjoutSolde( DataBase dataBase , Utilisateur utilisateur) {

        this.dataBase1 = dataBase ;
        this.utilisateur1 = utilisateur;

        bouttonAjoutSolde = new JButton();
        bouttonAjoutSolde.setBounds(165 , 250 , 150, 40 );
        bouttonAjoutSolde.setText("Ajouter");
        bouttonAjoutSolde.setFocusable(false);
        bouttonAjoutSolde.addActionListener(this);

        ZoneMontant = new JTextField();
        ZoneMontant.setBounds(125,175,250,25);

        JLabel labelAjoutArgent = new JLabel("Ajouter de l'argent ");
        labelAjoutArgent.setFont(new Font("Non Serif", 1 , 20));
        labelAjoutArgent.setBounds(145,25,250,50);
        
        JLabel labelMontant = new JLabel("Montant : ");
        labelMontant.setBounds(125,125,250,25);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page Profil") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500,400);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));

        this.add(bouttonAjoutSolde);
        this.add(ZoneMontant);
        this.add(labelAjoutArgent);
        this.add(labelMontant);
        System.out.println(utilisateur1.toString());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ( e.getSource() == bouttonAjoutSolde) {

            int montant ;


            try {
                
                int Montant = Integer.parseInt(ZoneMontant.getText());
                
                 
            
                if (this.utilisateur1.ajoutArgent(dataBase1, Montant)) {
                    utilisateur1.setSolde( utilisateur1.getSolde() + Montant);
                    JOptionPane.showMessageDialog(null,"Dépot Reussi","Confirmation",1);
                    this.dispose();
                    PageProfil pageProfil = new PageProfil(dataBase1, utilisateur1);
                }
        
                else {
                    JOptionPane.showMessageDialog(null, "Erreur lors du dépot","Erreur",JOptionPane.ERROR_MESSAGE);
                }

            }
            catch(Exception exep) {
                JOptionPane.showMessageDialog(null, "Veuillez rentrer un nombre","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }   
    }    
}
