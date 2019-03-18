package ThanosGame;

public class Shield {
    package ThanosGame;
import java.awt.Image;
import javax.swing.ImageIcon;
    public class Shield extends Personnage {
        private Image imgShield;
        private ImageIcon icoShield;

        private final int STOP = 12;
        private int dxShield;

        public Shield(int x, int y){
            super(x,y,209,259);
            super.setRight(true);
            super.setWalk(true);
            this.dxShield=1;
            this.icoShield = new ImageIcon(getClass().getRessource("Shielddeboutdroite.png"));
            this.imgShield = this.icoShield.getImage();
            Thread chronoShield = new Thread (this);
            chronoShield.start();
        }
        // GETTER
        public Image getImgShield(){ return imgShield;}
        //SETTER
        //METHOD
        public void move();{//déplacement de l'agent
            if(super.isRight()==true){this.dxShield=1;}
            else{this.dxShield=-1;}
            super.setX (super.getX() + this.dxShield);

        }
        public void run() {
            try {Thread.sleep(15);}
            catch (InterruptedException e){}
            while (true){
                this.move();
                try{Thread.sleep(STOP);}
                catch (InterruptedException e){}
            }
        }

    }

}
