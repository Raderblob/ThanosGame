package ThanosGame.weapons;

import ThanosGame.enemies.Personnage;
import ThanosGame.weapons.projectiles.Projectile;

import java.util.LinkedList;

public class MachineReboundGun extends ReboundRifle {
    public MachineReboundGun(LinkedList<Projectile> worldProjectiles, Personnage owner, double damage) {
        super(worldProjectiles, owner, damage);
        reloadTime=200;
    }
}
