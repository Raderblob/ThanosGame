package ThanosGame;

import ThanosGame.graphics.images.ImagesSaves;
import javafx.geometry.Point2D;
import ThanosGame.terrain.TerrainMap;

public class Heal extends Item{
    private int vieRendue;

    public Heal(Point2D position, Point2D Size,int vieRendue){
        super(position, Size, ImagesSaves.healSprite,new Point2D(20,20),1000);
        this.vieRendue = vieRendue;
        mylife = 1;
    }

    public void runLogic(World myWorld,TerrainMap myTerrain){
        if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)) {
            mylife = 0;
            myWorld.thanos.addHp(vieRendue);
        }
    }
}
