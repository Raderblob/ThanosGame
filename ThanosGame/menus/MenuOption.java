package ThanosGame.menus;

import ThanosGame.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuOption extends JFrame implements ActionListener  {
	// FENETRE 

	private MenuPrincipal myMenu;
	private JLabel Titan;
	private JPanel option;
	private JButton clavier, son, niveau, cancel, valid;
	private JSlider volume;
	private JRadioButton azerty, qwerty, up;
	private ButtonGroup bg, bg2;
	private JRadioButton easy, hard, normal, space;
	private Keyboard touches = new Keyboard();

	public MenuOption(MenuPrincipal mMenu){

		myMenu =mMenu;
		Font f=new Font("Arial", Font.BOLD, 28);
		Font g=new Font("Arial", Font.BOLD, 20);
		Titan = new JLabel();
		option = new JPanel();
		clavier = new JButton("KEYBOARD" );
		son = new JButton("SOUND");
		niveau = new JButton("LEVEL");
		cancel = new JButton( "X");
		valid = new JButton("O");
		volume = new JSlider(JSlider.HORIZONTAL,0,100,50);
		volume.setOpaque(false);

		// FENETRE

		setTitle("MenuOption");
		setSize (1500,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		//CONTENEUR
		option.setBounds(0,0,getWidth(),getHeight());
		option.setLayout(null);
		//FOND
		Titan.setIcon (new ImageIcon(getClass().getResource("images\\OptionsMenu\\Titan.png")));
		Titan.setSize(1500,800);
		Titan.setLocation(0,0);


		// BOUTON CLAVIER

		option.add(clavier);
		clavier.setBounds ( 1225,50,200, 100);
		clavier.addActionListener(this);
		clavier.setBorderPainted(false);
		clavier.setContentAreaFilled(false);
		clavier.setFocusPainted(false);
		clavier.setForeground(new Color(191,48,48));
		ButtonGroup  bg = new ButtonGroup(); ButtonGroup  bg2 = new ButtonGroup(); clavier.setFont(f);

		azerty = new JRadioButton("AZERTY");
		azerty.addActionListener(this);
		bg.add(azerty);
		azerty.setVisible(false);
		azerty.setBounds( 725,50,225,50);
		azerty.setBorderPainted(false); azerty.setContentAreaFilled(false); azerty.setFocusPainted(false);
		azerty.setForeground(new Color(233,56,63)); azerty.setFont(g);

		qwerty = new JRadioButton("QWERTY");
		qwerty.setSelected(true);
		qwerty.addActionListener(this);
		bg.add(qwerty);
		qwerty.setVisible(false);
		qwerty.setBounds( 725,100,225,50);
		qwerty.setBorderPainted(false); qwerty.setContentAreaFilled(false);qwerty.setFocusPainted(false);
		qwerty.setForeground(new Color(233,56,63)); qwerty.setFont(g);

		up = new JRadioButton("Up");
		up.addActionListener(this);
		bg2.add(up);
		up.setVisible(false);
		up.setBounds( 950,50,225,50);
		up.setBorderPainted(false); up.setContentAreaFilled(false); up.setFocusPainted(false);
		up.setForeground(new Color(233,56,63)); up.setFont(g);

		space = new JRadioButton("Space");
		space.setSelected(true);
		space.addActionListener(this);
		bg2.add(space);
		space.setVisible(false);
		space.setBounds( 950,100,225,50);
		space.setBorderPainted(false); space.setContentAreaFilled(false); space.setFocusPainted(false);
		space.setForeground(new Color(233,56,63)); up.setFont(g);

		option.add(azerty);
		option.add(qwerty);
		option.add(up);
		option.add(space);




		// BOUTON SON

		option.add(son);
		son.setBounds ( 1200,180,200, 100);
		son.addActionListener(this);
		son.setBorderPainted(false); son.setContentAreaFilled(false); son.setFocusPainted(false);
		son.setForeground(new Color(191,48,48));	son.setFont(f);

		option.add(volume);
		volume.setVisible(false);
		volume.setBounds(950,205, 225,50);
		/*volume.setBorderPainted(false); 	volume.setContentAreaFilled(false);	volume.setFocusPainted(false);*/


		//BOUTON NIVEAU
		option.add(niveau);
		niveau.setBounds ( 1200,310,200, 100);
		niveau.addActionListener(this);
		niveau.setBorderPainted(false); 	niveau.setContentAreaFilled(false);	niveau.setFocusPainted(false);
		niveau.setForeground(new Color(191,48,48)); niveau.setFont(f);

		ButtonGroup  niv= new ButtonGroup();

		easy = new JRadioButton("EASY !");
		easy.addActionListener(this);
		easy.setVisible(false);
		niv.add(easy);
		option.add(easy);
		easy.setBounds( 950,300,225,33);
		easy.setBorderPainted(false); 	easy.setContentAreaFilled(false);	easy.setFocusPainted(false);
		easy.setForeground(new Color(233,56,63)); easy.setFont(g);

		normal = new JRadioButton("NORMAL");
		normal.setSelected(true);
		normal.addActionListener(this);
		normal.setVisible(false);
		niv.add(normal);
		option.add(normal);
		normal.setBounds( 950,343,225,33);
		normal.setBorderPainted(false); normal.setContentAreaFilled(false);normal.setFocusPainted(false);
		normal.setForeground(new Color(233,56,63)); normal.setFont(g);

		hard = new JRadioButton("HARD !!! ");
		hard.addActionListener(this);
		hard.setVisible(false);
		niv.add(hard);
		option.add(hard);
		hard.setBounds( 950,386,225,34);
		hard.setBorderPainted(false); hard.setContentAreaFilled(false);hard.setFocusPainted(false);
		hard.setForeground(new Color(233,56,63)); hard.setFont(g);


		// BOUTON CANCEL
		option.add(cancel);
		cancel.setBounds ( 1400, 680 ,70, 70);
		cancel.addActionListener(this);
		cancel.setBorderPainted(false); cancel.setContentAreaFilled(false); cancel.setFocusPainted(false);
		cancel.setForeground(new Color(207,10,29)); cancel.setFont(f);

		cancel.addActionListener(this);

		// BOUTON VALID
		option.add(valid);
		valid.setBounds ( 1350,680,70, 70);
		valid.addActionListener(this);
		valid.setBorderPainted(false); valid.setContentAreaFilled(false);  valid.setFocusPainted(false);
		valid.setForeground(new Color(22,184,78)); valid.setFont(f);

		// AJOUT
		option.add(Titan);
		add(option);
		setVisible(false);


	}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == son){
			volume.setVisible(true);
			easy.setVisible(false);
			normal.setVisible(false);
			hard.setVisible(false);
			azerty.setVisible(false);
			qwerty.setVisible(false);
			space.setVisible(false);
			up.setVisible(false);
		}
		if (e.getSource() == clavier){
			azerty.setVisible(true);
			qwerty.setVisible(true);
			space.setVisible(true);
			up.setVisible(true);
			volume.setVisible(false);
			easy.setVisible(false);
			normal.setVisible(false);
			hard.setVisible(false);
		}
		if (e.getSource() == niveau){
			easy.setVisible(true);
			normal.setVisible(true);
			hard.setVisible(true);
			azerty.setVisible(false);
			qwerty.setVisible(false);
			volume.setVisible(false);
			space.setVisible(false);
			up.setVisible(false);
		}
		if (e.getSource() == valid){
            // Phase 2 CLAVIER
            if (azerty.isSelected()){
                touches.setAZERTY();
            }
            if (qwerty.isSelected()){
                touches.setQWERTY();
            }
            if (up.isSelected()){
                touches.setUpJump();
            }
            if (space.isSelected()){
                touches.setSpaceJump();
            }

            // Phase 2 NIVEAU
            if (easy.isSelected()){}
            if (easy.isSelected()){}
            if (hard.isSelected()){}

			myMenu.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource() == cancel){
			myMenu.setVisible(true);
			this.setVisible(false);
			hard.setSelected(true);
			qwerty.setSelected(true);
			space.setSelected(true);
			volume.setValue(10);

		}




		// Phase 2 SON
	}





}