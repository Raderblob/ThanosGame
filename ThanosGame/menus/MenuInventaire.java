package ThanosGame.menus;


import javax.swing.*;
import ThanosGame.Thanos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInventaire extends JFrame implements ActionListener {
    private JButton bRouge, bVert, bBleu, bViolet, bOrange, bJaune;
    private JButton b2Rouge, b2Vert, b2Bleu, b2Violet, b2Orange, b2Jaune, moreLife;
    private JLabel fond,perso,PV,Ame, AmeTxt, Puissance, Cooldown;
    private JPanel conteneurMain, conteneurPerso,Pv1,Pv2, Ame1, Puissance1, Cooldown1;
    private int longueurBouton = 150;
    private int hauteurBouton = 50;
    private int x1Bouton = 500;
    private int x2Bouton = 700;
    private String texteTitre = "INVENTAIRE";
    private int nbPierres;
    private Thanos thanos;

    public MenuInventaire(Thanos thanos) {
        this.thanos = thanos;
        nbPierres = 6;
        //nbPierres = thanos.infinity.getNbPierres();

        //Création de la fenêtre et son en-tête
        {
            setTitle(texteTitre);
            setLayout(null);//c null
            setSize(960, 720);
            setLocation(0, 0);
            setResizable(false);
        }

        //Texte
        {
            Puissance = new JLabel("Puissance :");
            Puissance.setBounds(40,0,longueurBouton,hauteurBouton);
            Puissance.setForeground(Color.WHITE);
            Puissance1 = new JPanel();
            Puissance1.setLayout(null);
            Puissance1.setBounds(x1Bouton,15,longueurBouton,hauteurBouton);
            Puissance1.setBackground(Color.black);
            Puissance1.add(Puissance);

            Cooldown = new JLabel("Cooldown :");
            Cooldown.setBounds(40,0,longueurBouton,hauteurBouton);
            Cooldown.setForeground(Color.WHITE);
            Cooldown1 = new JPanel();
            Cooldown1.setLayout(null);
            Cooldown1.setBounds(x2Bouton,15,longueurBouton,hauteurBouton);
            Cooldown1.setBackground(Color.black);
            Cooldown1.add(Cooldown);

        }

        //Bouton Pierres Puissance
        {

            //Violet
            {
                bViolet = new JButton("");
                bViolet.setBounds(x1Bouton, 100, longueurBouton, hauteurBouton);
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
                bRouge.setBounds(x1Bouton, 200, longueurBouton, hauteurBouton);
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
                bBleu.setBounds(x1Bouton, 300, longueurBouton, hauteurBouton);
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
                bVert.setBounds(x1Bouton, 400, longueurBouton, hauteurBouton);
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
                bJaune.setBounds(x1Bouton, 500, longueurBouton, hauteurBouton);
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
                bOrange = new JButton("");
                bOrange.setBounds(x1Bouton, 600, longueurBouton, hauteurBouton);
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

        //Bouton Pierres Cooldown
        {
            //Violet
            {
                b2Violet = new JButton("");
                b2Violet.setBounds(x2Bouton, 100, longueurBouton, hauteurBouton);
                b2Violet.setBackground(new Color(75, 15, 125));
                b2Violet.setForeground(Color.white);
                b2Violet.addActionListener(this);
                if (nbPierres < 1) {
                    b2Violet.setVisible(false);
                } else {
                    b2Violet.setVisible(true);
                }
            }

            //Rouge
            {
                b2Rouge = new JButton("");
                b2Rouge.setBounds(x2Bouton, 200, longueurBouton, hauteurBouton);
                b2Rouge.setBackground(Color.red);
                b2Rouge.setForeground(Color.white);
                b2Rouge.addActionListener(this);
                if (nbPierres < 2) {
                    b2Rouge.setVisible(false);
                } else {
                    b2Rouge.setVisible(true);
                }
            }

            //Bleu
            {
                b2Bleu = new JButton("");
                b2Bleu.setBounds(x2Bouton, 300, longueurBouton, hauteurBouton);
                b2Bleu.setBackground(Color.blue);
                b2Bleu.setForeground(Color.white);
                b2Bleu.addActionListener(this);
                if (nbPierres < 3) {
                    b2Bleu.setVisible(false);
                } else {
                    b2Bleu.setVisible(true);
                }
            }

            //Vert
            {
                b2Vert = new JButton("");
                b2Vert.setBounds(x2Bouton, 400, longueurBouton, hauteurBouton);
                b2Vert.setBackground(Color.green);
                b2Vert.setForeground(Color.white);
                b2Vert.addActionListener(this);
                if (nbPierres < 4) {
                    b2Vert.setVisible(false);
                } else {
                    b2Vert.setVisible(true);
                }
            }

            //Jaune
            {
                b2Jaune = new JButton("");
                b2Jaune.setBounds(x2Bouton, 500, longueurBouton, hauteurBouton);
                b2Jaune.setBackground(new Color(255, 255, 0));
                b2Jaune.setForeground(Color.white);
                b2Jaune.addActionListener(this);
                if (nbPierres < 5) {
                    b2Jaune.setVisible(false);
                } else {
                    b2Jaune.setVisible(true);
                }
            }

            //Orange
            {
                b2Orange = new JButton("");
                b2Orange.setBounds(x2Bouton, 600, longueurBouton, hauteurBouton);
                b2Orange.setBackground(new Color(255, 96, 0));
                b2Orange.setForeground(Color.white);
                b2Orange.addActionListener(this);
                if (nbPierres < 6) {
                    b2Orange.setVisible(false);
                } else {
                    b2Orange.setVisible(true);
                }
            }
        }

        //Bouton More Life
        {
            moreLife = new JButton("Augmenter Vie : ");
            moreLife.setBounds(75,getHeight() / 2 + 200, 350, 50);
            moreLife.setBackground(Color.red);
            moreLife.setForeground(Color.white);
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
            PV.setBounds(300/2, 0, 350, 50);
            PV.setForeground(Color.WHITE);
            Pv2 = new JPanel();
            Pv2.setBounds(0,0,(int)(350*thanos.getHp()),50);
            Pv2.setBackground(Color.RED);
            Pv1 = new JPanel();
            Pv1.setLayout(null);
            Pv1.setBounds(75,getHeight() / 2 - 220,350,50);
            Pv1.setBackground(Color.BLACK);
            Pv1.add(PV);
            Pv1.add(Pv2);
        }

        //Ame
        {
            Ame = new JLabel(""+thanos.infinity.nbAme);
            Ame.setBounds(250,0,350,50);
            Ame.setForeground(Color.WHITE);
            AmeTxt = new JLabel("Âmes :");
            AmeTxt.setBounds(50,0,350,50);
            AmeTxt.setForeground(Color.WHITE);
            Ame1 = new JPanel();
            Ame1.setLayout(null);
            Ame1.setBounds(75,getHeight() / 2 - 270,350,50);
            Ame1.setBackground(Color.BLACK);
            Ame1.add(Ame);
            Ame1.add(AmeTxt);
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
            conteneurMain.add(b2Rouge);
            conteneurMain.add(b2Vert);
            conteneurMain.add(b2Bleu);
            conteneurMain.add(b2Violet);
            conteneurMain.add(b2Jaune);
            conteneurMain.add(b2Orange);
            conteneurMain.add(conteneurPerso);
            conteneurMain.add(Puissance1);
            conteneurMain.add(Cooldown1);
            conteneurMain.add(Pv1);
            conteneurMain.add(Ame1);
            conteneurMain.add(moreLife);
            conteneurMain.add(fond);
            conteneurMain.setBounds(0, 0, getWidth(), getHeight());
        }

        add(conteneurMain);
        setVisible(false);
    }

    public void update(){
       Pv2.setBounds(0,0,(int)(350*thanos.getHp()),50);
       PV.setText("" + thanos.getPV() + "/" + thanos.getMaxPv());
       Ame.setText(""+thanos.infinity.nbAme);
       bViolet.setText("");
       bVert.setText("");
       bJaune.setText("");
       bBleu.setText("");
       bRouge.setText("");
       bOrange.setText("");
       b2Violet.setText("");
       b2Vert.setText("");
       b2Orange.setText("");
       b2Rouge.setText("");
       b2Jaune.setText("");
       b2Bleu.setText("");
       //Mettre à jour prix pierres
    }

    public void actionPerformed(ActionEvent e) {

    }


}

