

puclic class Chrono implements Runnable {
	private final int Stop = 4;  // Temps de boucle
	// Cette méthode repeint le fond toutes les 4ms
	public void run() {
		while ( true){ 
			Menu.Fond.repaint(); 
				try{ 
					Thread.sleep(Stop); 
				}catch{} // Obligatoire en cas d'erreur
				
		
