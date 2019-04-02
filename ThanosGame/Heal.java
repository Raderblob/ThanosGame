package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import resources.ImagesSaves;

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
