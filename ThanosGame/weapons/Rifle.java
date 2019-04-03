package ThanosGame.weapons;

import ThanosGame.Personnage;
import javafx.geometry.Point2D;
import resources.AudioSaves;

import java.util.LinkedList;

public class Rifle extends Weapon {
    public Rifle(LinkedList<Projectile> worldProjectiles, Personnage owner, int damage) {
        super(worldProjectiles, owner, damage, 5, 1500);
    }

    @Override
    public Projectile createProjectile(Point2D direction) {
        AudioSaves.gunSound.play(0.4);
        return super.createProjectile(direction);
    }
}
