package ThanosGame.weapons.player;

import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

import java.util.LinkedList;

public class RealityStone extends Stone {


    public RealityStone(Gant owner) {
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

}
