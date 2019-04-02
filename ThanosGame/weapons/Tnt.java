package ThanosGame.weapons;

import javafx.geometry.Point2D;

public class Tnt extends Projectile {
    public Tnt(Point2D position, int degats, double mLife) {
        super(position, new Point2D(10,10), degats, new Point2D(0,0), mLife, 3);
        enemyOwned = false;
    }
}
