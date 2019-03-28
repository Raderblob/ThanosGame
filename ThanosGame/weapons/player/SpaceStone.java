package ThanosGame.weapons.player;

import ThanosGame.Main;
import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.FXEffect;
import javafx.geometry.Point2D;

public class SpaceStone extends Stone {
    private double range = 500;
    public SpaceStone(Thanos owner) {
        super(owner);
        stoneType = 2;
        stoneName="Space Stone";
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
            currentWorld.worldExplosions.add(new FXEffect(currentWorld.thanos.myPosition,new Point2D(40,40),20,currentTerrain));
            currentWorld.worldExplosions.add(new FXEffect(destination,new Point2D(40,40),20,currentTerrain));
            currentWorld.thanos.myPosition = destination;
        }

        return 1;
    }

    private boolean terrainClear(Thanos thanos, TerrainMap currentTerrain,Point2D destination){
        for(int x = -(int)thanos.mySize.getX();x<thanos.mySize.getX();x++){
            for(int y = -(int)thanos.mySize.getY();y<thanos.mySize.getY();y++){
                if(currentTerrain.getTerrainValCollision(x+ destination.getX(),y+destination.getY())!=0){
                    return false;
                }
            }
        }
        return true;
    }

}
