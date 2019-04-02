package ThanosGame;

import javafx.geometry.Point2D;
import ThanosGame.terrain.TerrainMap;

public class Heal extends Item{
    private int vieRendue;

    public Heal(Point2D position, Point2D Size,int vieRendue){
        super(position, Size,null,new Point2D(0,0),100);
        this.vieRendue=vieRendue;
        mylife = 1;
    }

    public void runLogic(World myWorld,TerrainMap myTerrain){
        if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)) {
            mylife = 0;
            myWorld.thanos.addHp(vieRendue);
        }
    }
}
