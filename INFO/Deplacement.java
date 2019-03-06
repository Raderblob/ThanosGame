
 public class Deplacement  implements KeyListener {
	 public void keyPressed(){
		 if ( e.getKeyCode()= =KeyEvent.VK_RIGHT){
			 Menu.Fond.setx(1);
		else if(( e.getKeyCode()= =KeyEvent.VK_LEFT){
			 Menu.Fond.setx(-1);
			
		 }
	 public void keyReleased(){ Menu.Fond.setx(0);}
	 public void keyTyped(){}
	
}
