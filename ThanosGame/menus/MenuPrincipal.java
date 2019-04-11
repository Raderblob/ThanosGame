package ThanosGame.menus;

import ThanosGame.Game;
import ThanosGame.SaveGame;
import resources.ImagesSaves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MenuPrincipal extends JFrame implements ActionListener {
    public final Game game;
    private final JButton bouton;
    private final JButton bouton2;
    private final JButton bouton3;
    private final MenuOption mOption = new MenuOption(this);

    public MenuPrincipal(Game game) {
        this.game = game;
        //Création de la fenêtre et son entête
        String texteTitre = "Téthanos";
        setTitle(texteTitre + ": DEBOTTE Adrien, LE GALL Louise, GILLES Killian, MARCHANT Richard");
        setLayout(null);//c null
        setSize(1500, 800);
        setLocation(0, 0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //Titre
        JLabel titre = new JLabel(texteTitre, SwingConstants.CENTER);
        int hauteurTitre = 125;
        int longueurTitre = 800;
        titre.setSize(longueurTitre, hauteurTitre);
        titre.setLocation(0, 0);
        titre.setForeground(Color.white);
        Font police = new Font(" Arial ", Font.BOLD, 125);
        titre.setFont(police);

        //JPanel pour centrer texte
        JPanel cadre = new JPanel();
        cadre.setLayout(null);
        cadre.setBounds((getWidth() - longueurTitre) / 2, 50, longueurTitre, hauteurTitre);
        cadre.setBackground(new Color(10, 25, 130));
        cadre.add(titre);


        //Bouton start
        bouton = new JButton("Start/Resume");
        int hauteurBouton = 50;
        int longueurBouton = 200;
        bouton.setBounds((getWidth() - longueurBouton) / 2, 500, longueurBouton, hauteurBouton);
        bouton.setBackground(Color.blue);
        bouton.setForeground(Color.white);
        bouton.addActionListener(this);

        //Bouton Resume
        bouton2 = new JButton("Load Save");
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

        //Fond d'écran (avec choix en fonction de l'avancé du jeu
        JLabel logo = new JLabel();
        //ATTENTION MODIFIER POUR FAIRE EN SORTE D'AVOIR LE NBPIERRES CORRESPONDANT
        int nbPierres = 8;
        switch (nbPierres) {
            case 0:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground0.jpg")));
                break;
            case 1:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground1.jpg")));
                break;
            case 2:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground2.jpg")));
                break;
            case 3:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground3.jpg")));
                break;
            case 4:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground4.jpg")));
                break;
            case 5:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground5.jpg")));
                break;
            case 6:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground6.jpg")));
                break;
            default:
                logo.setIcon(new ImageIcon(ImagesSaves.class.getResource("menus/menuBackground6.jpg")));
                break;
        }
        logo.setLocation(0, 0);
        logo.setSize(getWidth(), getHeight());

        JPanel conteneurMain = new JPanel();
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
        if (e.getSource() == bouton) {
            setVisible(false);
            game.showGame();

        }
        if (e.getSource() == bouton2) {

            try {
                FileInputStream fileIn = new FileInputStream("SaveGame");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                SaveGame ep = (SaveGame) in.readObject();
                in.close();
                fileIn.close();
                ep.loadGame(game.thanos);
                System.out.println("Loaded Game");
                setVisible(false);
                game.showGame();
            } catch (Exception el) {
                System.out.println("Failed to load game");
            }
        }
        if (e.getSource() == bouton3) {
            setVisible(false);
            mOption.setVisible(true);
        }
    }


}