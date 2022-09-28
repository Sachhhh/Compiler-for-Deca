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

public class PageAjoutVoiture extends JFrame implements ActionListener {
    
    private DataBase dataBase ;
    private Utilisateur utilisateur;

    JButton Ajout ; 
    JButton bouttonRetour ; 
    JTextField ZoneImmatriculation ; 
    JTextField ZoneMarque ;
    JTextField ZoneModèle ; 
    JTextField ZonePuissanceFiscal ; 
    JTextField ZoneNbPlaceDispo ; 
    JComboBox ZoneEnergie ;
    
    
    public PageAjoutVoiture( DataBase dataBase , Utilisateur utilisateur) {

        this.dataBase = dataBase ; 
        this.utilisateur = utilisateur ;

        bouttonRetour = new JButton();
        bouttonRetour.setBounds(350 , 485 , 140, 20 );
        bouttonRetour.setText("Retour");
        bouttonRetour.setFocusable(false);
        bouttonRetour.addActionListener(this);
        
        Ajout = new JButton();
        Ajout.setText("Ajouter cette voiture");
        Ajout.setBounds(125 , 420 , 250, 50 );
        Ajout.setFocusable(false);
        Ajout.addActionListener(this);

        ZoneImmatriculation = new JTextField() ;
        ZoneImmatriculation.setBounds(70, 120, 280, 20);
    
        ZoneMarque = new JTextField() ;
        ZoneMarque.setBounds(70,170,280,20); 
    
        ZoneModèle = new JTextField() ; 
        ZoneModèle.setBounds(70,220,280,20);

        String[] typeEnergie = {"essence","diesel","hybride","électrique"};
        ZoneEnergie = new JComboBox(typeEnergie);
        ZoneEnergie.setBounds(70,270,280,20);

        ZonePuissanceFiscal = new JTextField() ; 
        ZonePuissanceFiscal.setBounds(70,320,280,20);
    
        ZoneNbPlaceDispo = new JTextField() ; 
        ZoneNbPlaceDispo.setBounds(70,370,280,20);


        JLabel labelConnecteToi = new JLabel("Ajouter une voiture");
        labelConnecteToi.setFont(new Font("Non Serif", 1 , 20));
        labelConnecteToi.setBounds(135,25,280,50);
        
        JLabel labelImmatriculation = new JLabel("Immatriculation du véhicule");
        labelImmatriculation.setBounds(70,80,250,50);
        JLabel labelMarque = new JLabel("Marque du véhicule");
        labelMarque.setBounds(70,140,250,30);
        JLabel labelModèle = new JLabel("Modèle du véhicule");
        labelModèle.setBounds(70,190,250,30);
        JLabel labelEnergie = new JLabel("Type de véhicule");
        labelEnergie.setBounds(70,240,250,30);
        JLabel labelPuissanceFiscal = new JLabel("Puissance fiscale");
        labelPuissanceFiscal.setBounds(70,290,250,30);
        JLabel labelPlacedispo = new JLabel("Place disponible");
        labelPlacedispo.setBounds(70,340,250,30);


        this.add(Ajout);
        this.add(bouttonRetour);
        
        this.add(ZoneImmatriculation);
        this.add(ZoneMarque);
        this.add(ZoneModèle);
        this.add(ZonePuissanceFiscal);
        this.add(ZoneNbPlaceDispo);        
        this.add(ZoneEnergie);

        this.add(labelConnecteToi);
        this.add(labelImmatriculation);
        this.add(labelMarque);
        this.add(labelModèle);
        this.add(labelEnergie);
        this.add(labelPuissanceFiscal);
        this.add(labelPlacedispo);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Page Ajout Voiture ") ; 
        this.setLayout(null);
        this.setResizable(false);
        this.pack();
        this.setSize(500,550);
        this.setVisible(true); 
        this.getContentPane().setBackground(new Color(255,160,122));



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ( e.getSource() == Ajout) {
            
            String immatriculation = ZoneImmatriculation.getText() ; 
            String marque = ZoneMarque.getText();
            String modele = ZoneModèle.getText();
            String energie = ZoneEnergie.getSelectedItem().toString();
            String puissanceFiscal = ZonePuissanceFiscal.getText();
            String placedispo = ZoneNbPlaceDispo.getText();
            Energie nrj;
            if (energie == "essence"){
                nrj = Energie.Essence;
            }
            else if (energie == "diesel"){
                nrj = Energie.Diesel;
            }
            else if (energie == "hybride"){
                nrj = Energie.Hybride;
            }
            else{
                nrj = Energie.Electrique;
            }

            Vehicule v = new Vehicule(immatriculation, marque, modele, Integer.parseInt(puissanceFiscal), nrj, Integer.parseInt(placedispo));


            if ( immatriculation.equals("") || marque.equals("") || modele.equals("") || puissanceFiscal.equals("") || placedispo.equals("") ) 
            {
                JOptionPane.showMessageDialog(null, "Veuillez remplier les champs","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else 
            {
                
                utilisateur.ajouterVoiture(dataBase, v);
                this.dispose();
                PageAjoutTrajet pageAjoutTrajet = new PageAjoutTrajet(dataBase,utilisateur) ;
            }
        }

        else if ( e.getSource() == bouttonRetour ) {
            this.dispose();
            PageAjoutTrajet pageAjoutTrajet = new PageAjoutTrajet(dataBase, utilisateur);
        }
    }
}
