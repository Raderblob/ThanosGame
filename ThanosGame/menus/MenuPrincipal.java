package ThanosGame.Menus;

import ThanosGame.Game;
import ThanosGame.Keyboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame implements ActionListener{
    private JButton bouton;
    private JButton bouton2;
    private JButton bouton3;
    private JLabel titre;
    private JLabel logo;
    private JPanel conteneurMain;
    private JPanel cadre;
    private int longueurBouton = 200;
    private int hauteurBouton = 50;
    private int longueurTitre = 800;
    private int hauteurTitre = 125;
    private String texteTitre = "Téthanos";
    private int nbPierres = 8; //ATTENTION MODIFIER POUR FAIRE EN SORTE D'AVOIR LE NBPIERRES CORRESPONDANT
    private MenuOption mOption = new MenuOption(this);

    public MenuPrincipal(){

        //Création de la fenêtre et son entête
        setTitle(texteTitre +": DEBOTTE Adrien, LE GALL Louise, GILLES Killian, MARCHANT Richard");
        setLayout(null);//c null
        setSize(1500,800);
        setLocation(0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Titre
        titre = new JLabel(texteTitre,SwingConstants.CENTER);
        titre.setSize(longueurTitre,hauteurTitre);
        titre.setLocation(0,0);
        titre.setForeground(Color.white);
        Font police = new Font(" Arial ",Font.BOLD,125);
        titre.setFont(police);

        //JPanel pour centrer texte
        cadre = new JPanel();
        cadre.setLayout(null);
        cadre.setBounds((getWidth()-longueurTitre)/2,50,longueurTitre,hauteurTitre);
        cadre.setBackground(new Color(10,25,130));
        cadre.add(titre);


        //Bouton start
        bouton = new JButton("Start");
        bouton.setBounds((getWidth()-longueurBouton)/2,500,longueurBouton,hauteurBouton);
        bouton.setBackground(Color.blue);
        bouton.setForeground(Color.white);
        bouton.addActionListener(this);

        //Bouton Resume
        bouton2 = new JButton("Resume");
        bouton2.setBounds((getWidth()-longueurBouton)/2,600,longueurBouton,hauteurBouton);
        bouton2.setBackground(Color.green);
        bouton2.setForeground(Color.white);
        bouton2.addActionListener(this);

        //Bouton Option
        bouton3 = new JButton("Options");
        bouton3.setBounds((getWidth()-longueurBouton)/2,700,longueurBouton,hauteurBouton);
        bouton3.setBackground(Color.red);
        bouton3.setForeground(Color.white);
        bouton3.addActionListener(this);

        //Fond d'écran (avec choix en fonction de l'avancé du jeu
        logo = new JLabel();
        switch (nbPierres) {
            case 0 :
                logo.setIcon(new ImageIcon(getClass().getResource("images\\menuBackground0.jpg")));
                break;
            case 1 :
                logo.setIcon(new ImageIcon(getClass().getResource(".images\\menuBackground1.jpg")));
                break;
            case 2 :
                logo.setIcon(new ImageIcon(getClass().getResource("images\\menuBackground2.jpg")));
                break;
            case 3 :
                logo.setIcon(new ImageIcon(getClass().getResource("images\\menuBackground3.jpg")));
                break;
            case 4 :
                logo.setIcon(new ImageIcon(getClass().getResource("images\\menuBackground4.jpg")));
                break;
            case 5 :
                logo.setIcon(new ImageIcon(getClass().getResource("images\\menuBackground5.jpg")));
                break;
            case 6 :
                logo.setIcon(new ImageIcon(getClass().getResource("images\\menuBackground6.jpg")));
                break;
            default:
                logo.setIcon(new ImageIcon(getClass().getResource("images\\menuBackground6.jpg")));
                break;
        }
        logo.setLocation(0,0);
        logo.setSize(getWidth(),getHeight());

        conteneurMain = new JPanel();
        conteneurMain.setLayout(null);
        conteneurMain.add(bouton);
        conteneurMain.add(bouton2);
        conteneurMain.add(bouton3);
        conteneurMain.add(cadre);
        conteneurMain.add(logo);
        conteneurMain.setBounds(0,0,getWidth(),getHeight());
        conteneurMain.setBackground(Color.pink);

        add(conteneurMain);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == bouton){
            setVisible(false);
            Game.launchGame();
        }
        if(e.getSource() == bouton2){

        }
        if(e.getSource() == bouton3){
            setVisible(false);
            mOption.setVisible(true);
        }
    }



}