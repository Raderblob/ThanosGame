package ThanosGame.weapons.player;

import ThanosGame.Main;
import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class SpaceStone extends Stone {
    private double range = 1000;
    public SpaceStone(Thanos owner) {
        super(owner);
    }

    @Override
    public int doAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        Point2D destination = new Point2D(destroyAt.getX(),destroyAt.getY());
        Point2D teleportDistance = destination.add(currentWorld.thanos.myPosition.multiply(-1));
        if(Main.getMagnitudeSquared(teleportDistance)>Math.pow(range,2)){
            teleportDistance = teleportDistance.normalize().multiply(range);
            destination = teleportDistance.add(currentWorld.thanos.myPosition);
        }

        if(terrainClear(currentWorld.thanos,currentTerrain,destination)){
            currentWorld.thanos.myPosition = destination;
        }

        return 1;
    }

    private boolean terrainClear(Thanos thanos, TerrainMap currentTerrain,Point2D destination){
        for(int x = -(int)thanos.mySize.getX();x<thanos.mySize.getX();x++){
            for(int y = -(int)thanos.mySize.getY();y<thanos.mySize.getY();y++){
                if(currentTerrain.getTerrainVal(x+ destination.getX(),y+destination.getY())!=0){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Space Stone";
    }

    @Override
    public boolean isReal() {
        return true;
    }
}
