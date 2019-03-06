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
		clavier.setBounds ( 1200,50,200, 100);
		//clavier.addActionListener(this);
		
		ButtonGroup  bg = new ButtonGroup();
		JRadioButton azerty = new JRadioButton("AZERTY");
		azerty.addActionListener(this);
		JRadioButton qwerty = new JRadioButton("QWERTY");
		qwerty.addActionListener(this);
		// ajout des boutons radio dans le groupe bg
		bg.add(azerty);
		bg.add(qwerty);
		
		azerty.setVisible(false);
		qwerty.setVisible(false);
		
		option.add(azerty);
		option.add(qwerty);
		azerty.setBounds( 950,50,225,50);
		qwerty.setBounds( 950,100,225,50);
		
		

		
		// BOUTON SON 
		
		option.add(son);
		son.setBounds ( 1200,180,200, 100);
		son.addActionListener(this);
		
		option.add(volume);
		volume.setVisible(false);
		volume.setBounds(950,205, 225,50);
		
		//BOUTON NIVEAU 
		option.add(niveau);
		niveau.setBounds ( 1200,310,200, 100);
		niveau.addActionListener(this);
		
		ButtonGroup  niv= new ButtonGroup();
		JRadioButton easy = new JRadioButton("EASY !");
		easy.addActionListener(this);
		easy.setVisible(false);
		JRadioButton normal = new JRadioButton("NORMAL");
		normal.addActionListener(this);
		normal.setVisible(false);
		JRadioButton hard = new JRadioButton("HARD !!! ");
		hard.addActionListener(this);
		hard.setVisible(false);
		// ajout des boutons radio dans le groupe 
		niv.add(easy);
		niv.add(normal);
		niv.add(hard);
		
		option.add(easy);
		option.add(normal);
		option.add(hard);
		easy.setBounds( 950,310,225,33);
		normal.setBounds( 950,343,225,33);
		hard.setBounds( 950,376,225,34);
		
		// BOUTON CANCEL 
		option.add(cancel);
		cancel.setBounds ( 1350, 700 ,50, 50);
		
		cancel.addActionListener(this);
		
		// BOUTON VALID 
		option.add(valid);
		valid.setBounds ( 1250,700,50, 50);
		valid.addActionListener(this);
		
		// AJOUT
		option.add(Titan);
		add(option);
		setVisible(true);
		

}

	public void actionPerformed (ActionEvent e){
			if (e.getSource() == son) {
				//volume.setVisible(true);
			}else{
				myMenu.setVisible(true);
				this.setVisible(false);
			}
	}
	

	

		
}

		
		
