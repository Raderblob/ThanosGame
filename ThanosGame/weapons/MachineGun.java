package ThanosGame.weapons;

import ThanosGame.enemies.Personnage;
import ThanosGame.weapons.projectiles.Projectile;

import java.util.LinkedList;

public class MachineGun extends Rifle {
    public MachineGun(LinkedList<Projectile> worldProjectiles, Personnage owner, double damage) {
        super(worldProjectiles, owner, damage);
        reloadTime = 200;
    }
}
