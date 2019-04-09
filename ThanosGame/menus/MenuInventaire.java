package ThanosGame.menus;


import ThanosGame.Thanos;
import resources.ImagesSaves;

import javax.swing.*;
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
        //nbPierres = 6;
        nbPierres = thanos.infinity.getNbPierres();



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
        {   //Violet
            {
                bViolet = new JButton("");
                bViolet.setBounds(x1Bouton, 100, longueurBouton, hauteurBouton);
                bViolet.setBackground(new Color(75, 15, 125));
                bViolet.setForeground(Color.white);
                bViolet.addActionListener(this);
            }
            //Rouge
            {
                bRouge = new JButton("");
                bRouge.setBounds(x1Bouton, 200, longueurBouton, hauteurBouton);
                bRouge.setBackground(Color.red);
                bRouge.setForeground(Color.white);
                bRouge.addActionListener(this);
            }
            //Bleu
            {
                bBleu = new JButton("");
                bBleu.setBounds(x1Bouton, 300, longueurBouton, hauteurBouton);
                bBleu.setBackground(Color.blue);
                bBleu.setForeground(Color.white);
                bBleu.addActionListener(this);
            }
            //Jaune
            {
                bJaune = new JButton("Esprit : Lvl max");
                bJaune.setBounds(x1Bouton, 400, longueurBouton, hauteurBouton);
                bJaune.setBackground(new Color(255, 255, 0));
                bJaune.setForeground(Color.white);
                bJaune.addActionListener(this);
            }
            //Vert
            {
                bVert = new JButton("Temps : Lvl max");
                bVert.setBounds(x1Bouton, 500, longueurBouton, hauteurBouton);
                bVert.setBackground(Color.green);
                bVert.setForeground(Color.white);
                bVert.addActionListener(this);
            }
            //Orange
            {
                bOrange = new JButton("");
                bOrange.setBounds(x1Bouton, 600, longueurBouton, hauteurBouton);
                bOrange.setBackground(new Color(255, 96, 0));
                bOrange.setForeground(Color.white);
                bOrange.addActionListener(this);
            }
        }

        //Bouton Pierres Cooldown
        {   //Violet
            {
                b2Violet = new JButton("");
                b2Violet.setBounds(x2Bouton, 100, longueurBouton, hauteurBouton);
                b2Violet.setBackground(new Color(75, 15, 125));
                b2Violet.setForeground(Color.white);
                b2Violet.addActionListener(this);
            }
            //Rouge
            {
                b2Rouge = new JButton("");
                b2Rouge.setBounds(x2Bouton, 200, longueurBouton, hauteurBouton);
                b2Rouge.setBackground(Color.red);
                b2Rouge.setForeground(Color.white);
                b2Rouge.addActionListener(this);
            }

            //Bleu
            {
                b2Bleu = new JButton("");
                b2Bleu.setBounds(x2Bouton, 300, longueurBouton, hauteurBouton);
                b2Bleu.setBackground(Color.blue);
                b2Bleu.setForeground(Color.white);
                b2Bleu.addActionListener(this);
            }
            //Jaune
            {
                b2Jaune = new JButton("");
                b2Jaune.setBounds(x2Bouton, 400, longueurBouton, hauteurBouton);
                b2Jaune.setBackground(new Color(255, 255, 0));
                b2Jaune.setForeground(Color.white);
                b2Jaune.addActionListener(this);
            }
            //Vert
            {
                b2Vert = new JButton("");
                b2Vert.setBounds(x2Bouton, 500, longueurBouton, hauteurBouton);
                b2Vert.setBackground(Color.green);
                b2Vert.setForeground(Color.white);
                b2Vert.addActionListener(this);
            }
            //Orange
            {
                b2Orange = new JButton("");
                b2Orange.setBounds(x2Bouton, 600, longueurBouton, hauteurBouton);
                b2Orange.setBackground(new Color(255, 96, 0));
                b2Orange.setForeground(Color.white);
                b2Orange.addActionListener(this);
            }

        }

        //Bouton More Life
        {
            moreLife = new JButton("Augmenter Vie : ");
            moreLife.setBounds(75,getHeight() / 2 + 200, 350, 50);
            moreLife.setBackground(Color.red);
            moreLife.setForeground(Color.white);
            moreLife.addActionListener(this);
        }

        //Photo du personnage
        {
            perso = new JLabel();
            perso.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/thanos.jpg")));
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
            fond.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/th2.jpg")));
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


        bViolet.setText("Pouvoir : "+this.thanos.infinity.prixPuissance[0]);
        bRouge.setText("Réalité : "+this.thanos.infinity.prixPuissance[1]);
        bBleu.setText("Espace : "+this.thanos.infinity.prixPuissance[2]);
        bJaune.setText("Esprit : "+this.thanos.infinity.prixPuissance[3]);
        bVert.setText("Temps : Lvl max");
        bOrange.setText("Âme : Lvl max");

        b2Violet.setText("Pouvoir : "+this.thanos.infinity.prixCooldown[0]);
        b2Rouge.setText("Réalité : "+this.thanos.infinity.prixCooldown[1]);
        b2Bleu.setText("Espace : "+this.thanos.infinity.prixCooldown[2]);
        b2Jaune.setText("Esprit : "+this.thanos.infinity.prixCooldown[3]);
        b2Vert.setText("Temps : Lvl max");
        b2Orange.setText("Âme : Lvl max");

        moreLife.setText("Augmenter Vie : "+thanos.prixVie);

        if(thanos.prixVie==0){
            moreLife.setText("Vie au max");
        }

        if(thanos.infinity.prixPuissance[0]==0){
            bViolet.setText("Lvl max");
        }
        if(thanos.infinity.prixPuissance[1]==0){
            bRouge.setText("Lvl max");
        }
        if(thanos.infinity.prixPuissance[2]==0){
            bBleu.setText("Lvl max");
        }
        if(thanos.infinity.prixPuissance[3]==0){
            bJaune.setText("Lvl max");
        }

        if(thanos.infinity.prixCooldown[0]==0){
            b2Violet.setText("Lvl max");
        }
        if(thanos.infinity.prixCooldown[1]==0){
            b2Rouge.setText("Lvl max");
        }
        if(thanos.infinity.prixCooldown[2]==0){
            b2Bleu.setText("Lvl max");
        }
        if(thanos.infinity.prixCooldown[3]==0){
            b2Jaune.setText("Lvl max");
        }

        if(!thanos.infinity.hasStone(0)){
            bViolet.setVisible(false);
            b2Violet.setVisible(false);
        }else{
            bViolet.setVisible(true);
            b2Violet.setVisible(true);
        }
        if(!thanos.infinity.hasStone(1)){
            bRouge.setVisible(false);
            b2Rouge.setVisible(false);
        }else{
            bRouge.setVisible(true);
            b2Rouge.setVisible(true);
        }
        if(!thanos.infinity.hasStone(2)){
            bBleu.setVisible(false);
            b2Bleu.setVisible(false);
        }
        else{
            bBleu.setVisible(true);
            b2Bleu.setVisible(true);
        }
        if(!thanos.infinity.hasStone(3)){
            bJaune.setVisible(false);
            b2Jaune.setVisible(false);
        }
        else{
            bJaune.setVisible(true);
            b2Jaune.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Puissance
        {
            if (e.getSource() == bViolet && thanos.infinity.nbAme>=thanos.infinity.prixPuissance[0]) {
                thanos.infinity.nbAme-=thanos.infinity.prixPuissance[0];
                thanos.infinity.niveauPuissance(0);
            }
            if (e.getSource() == bRouge && thanos.infinity.nbAme>=thanos.infinity.prixPuissance[1]) {
                thanos.infinity.nbAme-=thanos.infinity.prixPuissance[1];
                thanos.infinity.niveauPuissance(1);
            }
            if (e.getSource() == bBleu && thanos.infinity.nbAme>=thanos.infinity.prixPuissance[2]) {
                thanos.infinity.nbAme-=thanos.infinity.prixPuissance[2];
                thanos.infinity.niveauPuissance(2);
            }
            if (e.getSource() == bJaune && thanos.infinity.nbAme>=thanos.infinity.prixPuissance[3]) {
            thanos.infinity.nbAme-=thanos.infinity.prixPuissance[3];
            thanos.infinity.niveauPuissance(3);
            }
        }

        //Cooldown
        {
            if (e.getSource() == b2Violet && thanos.infinity.nbAme>=thanos.infinity.prixCooldown[0]) {
                thanos.infinity.nbAme-=thanos.infinity.prixCooldown[0];
                thanos.infinity.niveauCooldown(0);
            }
            if (e.getSource() == b2Rouge && thanos.infinity.nbAme>=thanos.infinity.prixCooldown[1]) {
                thanos.infinity.nbAme-=thanos.infinity.prixCooldown[1];
                thanos.infinity.niveauCooldown(1);
            }
            if (e.getSource() == b2Bleu && thanos.infinity.nbAme>=thanos.infinity.prixCooldown[2]) {
                thanos.infinity.nbAme-=thanos.infinity.prixCooldown[2];
                thanos.infinity.niveauCooldown(2);
            }
            if (e.getSource() == b2Jaune && thanos.infinity.nbAme>=thanos.infinity.prixCooldown[3]) {
                thanos.infinity.nbAme-=thanos.infinity.prixCooldown[3];
                thanos.infinity.niveauCooldown(3);
            }

        }

        //Vie
        {
            if (e.getSource() == moreLife && thanos.infinity.nbAme>=thanos.prixVie) {
                thanos.niveauVie();
            }

        }
        update();
    }


}

