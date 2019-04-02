package ThanosGame.weapons;

import ThanosGame.Personnage;

import java.util.LinkedList;

public class Rifle extends Weapon {
    public Rifle(LinkedList<Projectile> worldProjectiles, Personnage owner, int damage) {
        super(worldProjectiles, owner, damage, 5, 1500);
    }
}
