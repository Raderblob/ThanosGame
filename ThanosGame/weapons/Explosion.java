package ThanosGame.weapons;

import ThanosGame.Item;
import ThanosGame.World;
import javafx.geometry.Point2D;

public class Explosion extends Item { //does the damage
    public Explosion(Point2D maPosition, Point2D Size) {
        super(maPosition, Size,null,new Point2D(0,0),100);
    }

    public void runExplosion(World myWorld){

    }
}
