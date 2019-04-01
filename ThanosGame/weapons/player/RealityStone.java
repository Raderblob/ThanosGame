package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.FXEffect;
import javafx.geometry.Point2D;

import java.util.LinkedList;

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
        LinkedList<Point2D> pointsToChange =  currentTerrain.getCircleOfPointsLinked(destroyAt,40);
        doChanges(pointsToChange,(byte)0,currentTerrain);
        doDamage(currentWorld,destroyAt,pointsToChange);
        currentWorld.worldExplosions.add(new FXEffect(destroyAt,new Point2D(60,60),25,currentTerrain));
        return 1;
    }

}
