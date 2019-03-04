import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class MenuPrincipal extends JFrame implements ActionListener{
	private JButton bouton;
    private JButton bouton2;
    private JButton bouton3;
    private JLabel titre;
    private JLabel logo;
    private JPanel conteneurMain;
    private int longueurBouton = 200;
    private int hauteurBouton = 50;
    
	public MenuPrincipal(){
        super("Téthanos: DEBOTTE Adrien, LE GALL Louise, GILLES Killian, MARCHANT Richard");
        setLayout(null);
		setSize(1500,800);
        setLocation(0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        titre = new JLabel("Téthanos");
        titre.setSize(500,500);
        titre.setLocation(375,125);
        titre.setForeground(Color.white);
        titre.setBackground(Color.red);
        Font police = new Font(" Arial ",Font.BOLD,125);
        titre.setFont(police);
        
        
        bouton = new JButton("Afficher");
        bouton.setBounds(75,25,longueurBouton,hauteurBouton);
        bouton.setBackground(Color.green);
        bouton.addActionListener(this);
        
        bouton2 = new JButton("Effacer");
        bouton2.setBounds(275,25,longueurBouton,hauteurBouton);
        bouton2.setBackground(Color.red);
        bouton2.addActionListener(this);
        
        bouton3 = new JButton("Blablabla");
        bouton3.setBounds(475,25,longueurBouton,hauteurBouton);
        bouton3.setBackground(Color.blue);
        bouton3.addActionListener(this);

        logo = new JLabel();
        logo.setIcon(new ImageIcon("photo.png"));
        logo.setLocation(0,0);
        logo.setSize(getWidth(),getHeight());
        
        conteneurMain = new JPanel();
        conteneurMain.setLayout(null);
        conteneurMain.add(bouton);
        conteneurMain.add(bouton2);
        conteneurMain.add(bouton3);
        conteneurMain.add(titre);
        conteneurMain.add(logo);
        conteneurMain.setBounds(0,0,getWidth(),getHeight());
        conteneurMain.setBackground(Color.pink);
        
        add(conteneurMain);
        setVisible(true);
	}
    
    public void actionPerformed(ActionEvent e){
        
    }
    
    public static void main(String []args){
        MenuPrincipal mn = new MenuPrincipal();
    }
    
}
















