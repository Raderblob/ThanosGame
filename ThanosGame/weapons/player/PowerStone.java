package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.Projectile;
import javafx.geometry.Point2D;
import resources.AudioSaves;

public class PowerStone extends Stone {
    public PowerStone(Thanos owner) {
        super(owner);
        stoneType = 0;
        stoneName = "Power Stone";
        myPower = 1000;
        coolDown = 300;
        secondaryCoolDown=1000;
    }


    @Override
    protected int doSubSecondaryAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        AudioSaves.gunSound.play();
        currentWorld.worldProjectiles.add(new Projectile(owner.myPosition,new Point2D(4,3),(int)(myPower*0.01),destroyAt.add(owner.myPosition.multiply(-1)).normalize().multiply(2),1000,0,false));
        return 1;
    }
}
