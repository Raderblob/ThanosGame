package ThanosGame.weapons;

import ThanosGame.enemies.Personnage;
import javafx.geometry.Point2D;
import resources.AudioSaves;

import java.util.LinkedList;

public class Rifle extends Weapon {
    public Rifle(LinkedList<Projectile> worldProjectiles, Personnage owner, int damage) {
        super(worldProjectiles, owner, damage, 5, 1500);
    }

    @Override
    public Projectile createProjectile(Point2D direction) {
        if(owner.pointOnScreen(owner.myPosition)) {
            AudioSaves.gunSound.play(0.4);
        }
        return super.createProjectile(direction);
    }
}
