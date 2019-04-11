package ThanosGame.weapons;

import ThanosGame.enemies.Personnage;
import ThanosGame.weapons.projectiles.Projectile;
import ThanosGame.weapons.projectiles.ReboundProjectile;
import javafx.geometry.Point2D;
import resources.AudioSaves;

import java.util.LinkedList;

public class ReboundRifle extends Rifle {
    public ReboundRifle(LinkedList<Projectile> worldProjectiles, Personnage owner, double damage) {
        super(worldProjectiles, owner, damage);
    }

    @Override
    public Projectile createProjectile(Point2D direction) {
        if(owner.pointOnScreen(owner.myPosition)) {
            AudioSaves.gunSound.play(0.4);
        }
        return new ReboundProjectile(owner.myPosition,new Point2D(4,3),damage,direction.multiply(bulletSpeed));
    }
}
