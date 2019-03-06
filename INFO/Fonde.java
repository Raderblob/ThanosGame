Dans Attributs 
	private int x;

 Dans le constructeur : 
	Thread chronoFond = new Thread(new Chrono());
	chronoFond.start();
	this.x=0;
	this.addKeyListener(new Deplacement);
	this.setFocusable(true);
	this.requestFocusInWindow();
	
	public void DeplacementFond(){
		this.xFond1 = this.xFond1-this.dx;
		
	// Méthodes getters 
		public int getx()0{return x;}
		
	
	// Méthodes Setter 
		public int setx(int x){this.x=x;}
Dans méthode paint
	this.deplacementFond();
		
