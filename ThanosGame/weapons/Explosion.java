package ThanosGame.weapons;

import ThanosGame.Item;
import ThanosGame.World;
import javafx.geometry.Point2D;

public class Explosion extends Item { //does the damage
    private double damageQuantity;
    public Explosion(Point2D maPosition, Point2D Size,int myL, int damageT) {
        super(maPosition, Size,null,new Point2D(0,0),100);
        mylife = myL;

    }

    public void runExplosion(World myWorld,double nanoTime){
        mylife -= nanoTime;
    }
}
