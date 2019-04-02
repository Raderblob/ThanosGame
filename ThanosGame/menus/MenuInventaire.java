package ThanosGame.menus;


import javax.swing.*;
import ThanosGame.Thanos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInventaire extends JFrame implements ActionListener {
    private JButton bRouge, bVert, bBleu, bViolet, bOrange, bJaune;
    private JLabel fond,perso,PV,Ame;
    private JPanel conteneurMain, conteneurPerso,Pv1,Pv2;
    private int longueurBouton = 50;
    private int hauteurBouton = 50;
    private String texteTitre = "INVENTAIRE";
    private int nbPierres; //ATTENTION MODIFIER POUR FAIRE EN SORTE D'AVOIR LE NBPIERRES CORRESPONDANT
    private Thanos thanos;

    public MenuInventaire(Thanos thanos) {
        this.thanos = thanos;
        nbPierres = thanos.infinity.getNbPierres();

        //Création de la fenêtre et son en-tête
        {
            setTitle(texteTitre);
            setLayout(null);//c null
            setSize(960, 720);
            setLocation(0, 0);
            setResizable(false);
        }

        //Bouton Pierres
        {   //Violet
            {
                bViolet = new JButton("");
                bViolet.setBounds((getWidth() - longueurBouton) / 2, 100, longueurBouton, hauteurBouton);
                bViolet.setBackground(new Color(75, 15, 125));
                bViolet.setForeground(Color.white);
                bViolet.addActionListener(this);
                if (nbPierres < 1) {
                    bBleu.setVisible(false);
                } else {
                    bViolet.setVisible(true);
                }
            }

            //Rouge
            {
                bRouge = new JButton("");
                bRouge.setBounds((getWidth() - longueurBouton) / 2, 200, longueurBouton, hauteurBouton);
                bRouge.setBackground(Color.red);
                bRouge.setForeground(Color.white);
                bRouge.addActionListener(this);
                if (nbPierres < 2) {
                    bRouge.setVisible(false);
                } else {
                    bRouge.setVisible(true);
                }
            }

            //Bleu
            {
                bBleu = new JButton("");
                bBleu.setBounds((getWidth() - longueurBouton) / 2, 300, longueurBouton, hauteurBouton);
                bBleu.setBackground(Color.blue);
                bBleu.setForeground(Color.white);
                bBleu.addActionListener(this);
                if (nbPierres < 3) {
                    bBleu.setVisible(false);
                } else {
                    bBleu.setVisible(true);
                }
            }

            //Vert
            {
                bVert = new JButton("");
                bVert.setBounds((getWidth() - longueurBouton) / 2, 400, longueurBouton, hauteurBouton);
                bVert.setBackground(Color.green);
                bVert.setForeground(Color.white);
                bVert.addActionListener(this);
                if (nbPierres < 4) {
                    bVert.setVisible(false);
                } else {
                    bVert.setVisible(true);
                }
            }

            //Jaune
            {
                bJaune = new JButton("");
                bJaune.setBounds((getWidth() - longueurBouton) / 2, 500, longueurBouton, hauteurBouton);
                bJaune.setBackground(new Color(255, 255, 0));
                bJaune.setForeground(Color.white);
                bJaune.addActionListener(this);
                if (nbPierres < 5) {
                    bJaune.setVisible(false);
                } else {
                    bJaune.setVisible(true);
                }
            }

            //Orange
            {
                bOrange = new JButton("" + thanos.getPV());
                bOrange.setBounds((getWidth() - longueurBouton) / 2, 600, longueurBouton, hauteurBouton);
                bOrange.setBackground(new Color(255, 96, 0));
                bOrange.setForeground(Color.white);
                bOrange.addActionListener(this);
                if (nbPierres < 6) {
                    bOrange.setVisible(false);
                } else {
                    bOrange.setVisible(true);
                }
            }
        }

        //Photo du personnage
        {
            perso = new JLabel();
            perso.setIcon(new ImageIcon(getClass().getResource("images\\thanos.jpg")));
            perso.setLocation(0, 5);
            perso.setSize(350, 310);
        }

        //Cadre perso
        {
            int x1, y1;
            x1 = 350;
            y1 = 320;
            conteneurPerso = new JPanel();
            conteneurPerso.setLayout(null);
            conteneurPerso.add(perso);
            conteneurPerso.setBounds(75, getHeight() / 2 - y1 / 2, x1, y1);
            conteneurPerso.setBackground(Color.white);
        }

        //Fond
        {
            fond = new JLabel();
            fond.setIcon(new ImageIcon(getClass().getResource("images\\th2.jpg")));
            fond.setLocation(0, 0);
            fond.setSize(getWidth(), getHeight());
        }

        //Barre de vie
        {
            PV = new JLabel("" + thanos.getPV() + "/" + thanos.getMaxPv());
            PV.setBounds(0, 0, 200, 20);
            PV.setBackground(Color.RED);
            Pv1 = new JPanel();
            Pv1.setBounds(0,0,100,10);
            Pv1.setForeground(Color.BLACK);
        //conteneurMain.setBackground(Color.black);
        }

        //Ame
        {
            Ame = new JLabel(""+thanos.infinity.nbAme);
            Ame.setBounds(0,50,200,20);
        }

        //Conteneur Main
        {
            conteneurMain = new JPanel();
            conteneurMain.setLayout(null);
            conteneurMain.add(bRouge);
            conteneurMain.add(bVert);
            conteneurMain.add(bBleu);
            conteneurMain.add(bViolet);
            conteneurMain.add(bJaune);
            conteneurMain.add(bOrange);
            conteneurMain.add(conteneurPerso);
            conteneurMain.add(PV);
            conteneurMain.add(Ame);
            conteneurMain.add(fond);
            conteneurMain.setBounds(0, 0, getWidth(), getHeight());
        }

        add(conteneurMain);
        setVisible(false);
    }

    public void uptade(){
       bOrange.setText(""+thanos.infinity.nbAme);
       //Mettre à jour Pv, âme, prix pierres
    }

    public void actionPerformed(ActionEvent e) {

    }


}

