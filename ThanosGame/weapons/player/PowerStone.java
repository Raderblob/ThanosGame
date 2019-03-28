package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class PowerStone extends Stone {
    public PowerStone(Thanos owner) {
        super(owner);
        stoneType = 3;
        stoneName="Power Stone";
        myPower=1000;
    }

    public int doAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        super.doAction(currentTerrain,currentWorld,destroyAt);
        return 1;
    }
}