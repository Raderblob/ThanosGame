package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.DistractionFX;
import javafx.geometry.Point2D;

public class MindStone extends SpaceStone {
    public MindStone(Thanos owner) {
        super(owner);
        stoneType = 3;
        stoneName="Mind Stone";
        coolDown = 15000;
        secondaryCoolDown = 10000;
        myPower = 500;
    }


    protected int doSubSecondaryAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){

        return 1;
    }

    protected int doSubAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        Point2D destination = getDestination(destroyAt,currentWorld,currentTerrain);

        if(terrainClear(currentWorld.thanos,currentTerrain,destination)){
            currentWorld.worldExplosions.add(new DistractionFX(destination,new Point2D(10,10),200,currentTerrain));

            return 1;
        }

        return 0;
    }
}
