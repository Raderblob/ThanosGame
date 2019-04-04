package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.FXEffect;
import javafx.geometry.Point2D;
import resources.AudioSaves;

import java.util.LinkedList;

public class RealityStone extends Stone {


    public RealityStone(Thanos owner) {
        super(owner);
        stoneType = 1;
        stoneName = "Reality Stone";
        myPower = 10;
        coolDown = 1500;
        secondaryCoolDown = 1;
    }

    @Override
    protected int doSubAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        LinkedList<Point2D> pointsToChange = currentTerrain.getCircleOfPointsLinked(destroyAt, 40);
        doDamage(currentWorld, destroyAt, pointsToChange);
        doChanges(pointsToChange, (byte) 0, currentTerrain);
        currentWorld.worldExplosions.add(new FXEffect(destroyAt, new Point2D(60, 60), 25, currentTerrain));
        if (!AudioSaves.realityCreateSound.isPlaying()) {
            AudioSaves.realityCreateSound.play();
        }
        return 1;
    }

    @Override
    protected int doSubSecondaryAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        LinkedList<Point2D> pointsToChange = new LinkedList<>();
        pointsToChange.add(destroyAt);
        pointsToChange.add(currentTerrain.clampPoint(destroyAt.add(4, 0)));
        pointsToChange.add(currentTerrain.clampPoint(destroyAt.add(4, 4)));
        pointsToChange.add(currentTerrain.clampPoint(destroyAt.add(0, 4)));
        if (currentTerrain.getTerrainVal(destroyAt) != 1) {
            doChanges(pointsToChange, (byte) 1, currentTerrain);
            currentWorld.worldExplosions.add(new FXEffect(destroyAt, new Point2D(20, 20), 4, currentTerrain));
            AudioSaves.realityDestroySound.play();
            return 1;
        } else {
            return 0;
        }
    }
}
