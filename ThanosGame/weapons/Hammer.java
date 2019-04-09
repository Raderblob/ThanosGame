package ThanosGame.weapons;

import ThanosGame.enemies.Personnage;
import ThanosGame.weapons.projectiles.Projectile;
import javafx.geometry.Point2D;
import resources.AudioSaves;

import java.util.LinkedList;

public class Hammer extends Weapon {
    public Hammer(LinkedList<Projectile> worldProjectiles, Personnage owner, double damage) {
        super(worldProjectiles, owner, damage, 5, 3000);
    }

    @Override
    public Projectile createProjectile(Point2D direction) {
        if(owner.pointOnScreen(owner.myPosition)) {
            AudioSaves.thunderSound.play();
        }
        return new Projectile(owner.myPosition,new Point2D(7,5),damage,direction.multiply(bulletSpeed),1000,5);
    }
}
