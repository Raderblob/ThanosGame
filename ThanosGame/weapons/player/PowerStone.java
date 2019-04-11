package ThanosGame.weapons.player;

import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.projectiles.Projectile;
import javafx.geometry.Point2D;
import resources.AudioSaves;

class PowerStone extends Stone {
    public PowerStone() {
        super();
        stoneType = 0;
        stoneName = "Power Stone";
        myPower = 1000;
        coolDown = 300;
        secondaryCoolDown=1000;
    }


    @Override
    protected int doSubSecondaryAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        AudioSaves.gunSound.play();
        currentWorld.worldProjectiles.add(new Projectile(currentWorld.thanos.myPosition,new Point2D(8,6),(int)(myPower*0.03),destroyAt.add(currentWorld.thanos.myPosition.multiply(-1)).normalize().multiply(4),1000,4,false));
        return 1;
    }
}
