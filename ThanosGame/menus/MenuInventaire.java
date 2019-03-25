package ThanosGame.menus;

import ThanosGame.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInventaire extends JFrame implements ActionListener {
    private JButton bRouge, bVert, bBleu, bViolet, bOrange, bJaune;
    private JLabel fond;
    private JPanel conteneurMain;
    private int longueurBouton = 50;
    private int hauteurBouton = 50;
    private String texteTitre = "INVENTAIRE";
    private int nbPierres = 8; //ATTENTION MODIFIER POUR FAIRE EN SORTE D'AVOIR LE NBPIERRES CORRESPONDANT

    public MenuInventaire() {
        //Création de la fenêtre et son entête
        setTitle(texteTitre);
        setLayout(null);//c null
        setSize(960, 720);
        setLocation(0, 0);
        setResizable(false);

        //Rouge
        bRouge = new JButton("");
        bRouge.setBounds((getWidth() - longueurBouton) / 2, 100, longueurBouton, hauteurBouton);
        bRouge.setBackground(Color.red);
        bRouge.setForeground(Color.white);
        bRouge.addActionListener(this);

        //Vert
        bVert = new JButton("");
        bVert.setBounds((getWidth() - longueurBouton) / 2, 200, longueurBouton, hauteurBouton);
        bVert.setBackground(Color.green);
        bVert.setForeground(Color.white);
        bVert.addActionListener(this);

        //Bleu
        bBleu = new JButton("");
        bBleu.setBounds((getWidth() - longueurBouton) / 2, 300, longueurBouton, hauteurBouton);
        bBleu.setBackground(Color.blue);
        bBleu.setForeground(Color.white);
        bBleu.addActionListener(this);

        //Violet
        bViolet = new JButton("");
        bViolet.setBounds((getWidth() - longueurBouton) / 2, 400, longueurBouton, hauteurBouton);
        bViolet.setBackground(new Color(75,15,125));
        bViolet.setForeground(Color.white);
        bViolet.addActionListener(this);

        //Jaune
        bJaune = new JButton("");
        bJaune.setBounds((getWidth() - longueurBouton) / 2, 500, longueurBouton, hauteurBouton);
        bJaune.setBackground(new Color(255,255,0));
        bJaune.setForeground(Color.white);
        bJaune.addActionListener(this);

        //Orange
        bOrange = new JButton("");
        bOrange.setBounds((getWidth() - longueurBouton) / 2, 600, longueurBouton, hauteurBouton);
        bOrange.setBackground(new Color(255,96,0));
        bOrange.setForeground(Color.white);
        bOrange.addActionListener(this);

        fond = new JLabel();
        fond.setIcon(new ImageIcon(getClass().getResource("images\\th2.jpg")));
        fond.setLocation(0, 0);
        fond.setSize(getWidth(), getHeight());

        conteneurMain = new JPanel();
        conteneurMain.setLayout(null);
        conteneurMain.add(bRouge);
        conteneurMain.add(bVert);
        conteneurMain.add(bBleu);
        conteneurMain.add(bViolet);
        conteneurMain.add(bJaune);
        conteneurMain.add(bOrange);
        conteneurMain.add(fond);
        conteneurMain.setBounds(0, 0, getWidth(), getHeight());
        //conteneurMain.setBackground(Color.black);

        add(conteneurMain);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {

    }


}

