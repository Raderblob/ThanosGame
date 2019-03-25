package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class RealityStone extends Stone {


    public RealityStone(Thanos owner) {
        super(owner);
    }

    @Override
    public int doAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        Point2D[] pTD = currentTerrain.getCircleOfPoints(destroyAt,40);
        byte bTD[] = new byte[pTD.length];
        for (int i = 0; i < bTD.length; i++) {
            bTD[i] = 0;
        }
        currentTerrain.changeTerrain(pTD, bTD);
        return 1;
    }

    @Override
    public String toString() {
        return "Reality Stone";
    }
}
