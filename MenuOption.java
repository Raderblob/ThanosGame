import javax.swing.*; 
import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;


public class MenuOption extends JFrame implements ActionListener  {
	// FENETRE 
	private JLabel Titan;
	private JPanel option;
	private JButton clavier;
	private JButton son;
	private JButton niveau; 
	private JButton cancel;
	private JButton valid;
	private JSlider barreson; 
	private JRadioButton azerty;
	private JRadioButton qwerty;
	private ButtonGroup bg; 
	private MenuPrincipal myMenu;
	
	
	MenuOption(MenuPrincipal mMenu){
		myMenu =mMenu;
			JLabel Titan = new JLabel(); 
			JPanel option = new JPanel(); 
			JButton clavier = new JButton("KEYBOARD" ); 
			JButton son = new JButton("SOUND"); 
			JButton niveau = new JButton("LEVEL"); 
			JButton cancel = new JButton( new ImageIcon(getClass().getResource("images\\OptionsMenu\\Cancel.jpg")));
			JButton valid = new JButton("VALID"); 
			JSlider volume = new JSlider(JSlider.HORIZONTAL,0,100,50); 

			
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
		ButtonGroup  bg = new ButtonGroup(); clavier.setFont(f);
		
		azerty = new JRadioButton("AZERTY");
		azerty.addActionListener(this);
		bg.add(azerty);
		azerty.setVisible(false);
		azerty.setBounds( 950,50,225,50);
		azerty.setBorderPainted(false); azerty.setContentAreaFilled(false); azerty.setFocusPainted(false);
		azerty.setForeground(new Color(233,56,63)); azerty.setFont(g);
		
		qwerty = new JRadioButton("QWERTY");
		qwerty.addActionListener(this);
		bg.add(qwerty);
		qwerty.setVisible(false);
		qwerty.setBounds( 950,100,225,50); 
		qwerty.setBorderPainted(false); qwerty.setContentAreaFilled(false);qwerty.setFocusPainted(false);
		qwerty.setForeground(new Color(233,56,63)); qwerty.setFont(g);
		
		option.add(azerty);
		option.add(qwerty);
		
		

		
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
		setVisible(true);
		

}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == son){
			volume.setVisible(true);
		}
		if (e.getSource() == clavier){
			azerty.setVisible(true);
			qwerty.setVisible(true);
		}
		if (e.getSource() == niveau){
			easy.setVisible(true);
			normal.setVisible(true);
			hard.setVisible(true);
				
		}
		if (e.getSource() == valid){
				myMenu.setVisible(true);
				this.setVisible(false);
		}
	}
	

	

		
}

		
		
