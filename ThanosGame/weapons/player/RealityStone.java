package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class RealityStone extends Stone {


    public RealityStone(Thanos owner) {
        super(owner);
        stoneType=1;
        stoneName="Reality Stone";
        myPower = 10;
        coolDown = 1500;
    }

    @Override
    protected int doSubAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        doChanges( currentTerrain.getCircleOfPointsLinked(destroyAt,40),(byte)0,currentTerrain);
        return 1;
    }

}
