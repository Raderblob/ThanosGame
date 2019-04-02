package ThanosGame;

import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Pierre extends Item{
    public int typePierre;

    public Pierre(Point2D position, Point2D Size, int type){
        super(position, Size, ImagesSaves.stoneSprite, new Point2D(20,20),1000);
        typePierre = type;
        mylife = 1;
    }

    public void runLogic(World myWorld,TerrainMap myTerrain){
        if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)) {
            mylife = 0;

        }
    }
}
