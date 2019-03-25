package ThanosGame.menus;

import ThanosGame.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInventaire extends JFrame implements ActionListener {
    private MenuPrincipal myMenu;
    private JButton bouton;
    private JButton bouton2;
    private JButton bouton3;
    private JLabel logo;
    private JPanel conteneurMain;
    private JPanel cadre;
    private int longueurBouton = 50;
    private int hauteurBouton = 50;
    private String texteTitre = "Téthanos";
    private int nbPierres = 8; //ATTENTION MODIFIER POUR FAIRE EN SORTE D'AVOIR LE NBPIERRES CORRESPONDANT

    public MenuInventaire() {
        //Création de la fenêtre et son entête
        setTitle(texteTitre + ": DEBOTTE Adrien, LE GALL Louise, GILLES Killian, MARCHANT Richard");
        setLayout(null);//c null
        setSize(1500, 800);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Bouton start
        bouton = new JButton("Start");
        bouton.setBounds((getWidth() - longueurBouton) / 2, 500, longueurBouton, hauteurBouton);
        bouton.setBackground(Color.blue);
        bouton.setForeground(Color.white);
        bouton.addActionListener(this);

        //Bouton Resume
        bouton2 = new JButton("Resume");
        bouton2.setBounds((getWidth() - longueurBouton) / 2, 600, longueurBouton, hauteurBouton);
        bouton2.setBackground(Color.green);
        bouton2.setForeground(Color.white);
        bouton2.addActionListener(this);

        //Bouton Option
        bouton3 = new JButton("Options");
        bouton3.setBounds((getWidth() - longueurBouton) / 2, 700, longueurBouton, hauteurBouton);
        bouton3.setBackground(Color.red);
        bouton3.setForeground(Color.white);
        bouton3.addActionListener(this);

        conteneurMain = new JPanel();
        conteneurMain.setLayout(null);
        conteneurMain.add(bouton);
        conteneurMain.add(bouton2);
        conteneurMain.add(bouton3);
        conteneurMain.add(cadre);
        conteneurMain.add(logo);
        conteneurMain.setBounds(0, 0, getWidth(), getHeight());
        conteneurMain.setBackground(Color.pink);

        add(conteneurMain);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }


}

