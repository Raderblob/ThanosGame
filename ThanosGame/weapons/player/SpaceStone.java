package ThanosGame.weapons.player;

import ThanosGame.Main;
import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.projectiles.FXEffect;
import ThanosGame.weapons.projectiles.Tnt;
import javafx.geometry.Point2D;
import resources.AudioSaves;

class SpaceStone extends Stone {
    public SpaceStone() {
        super();
        stoneType = 2;
        stoneName="Space Stone";
        coolDown = 5000;
        secondaryCoolDown = 10000;
        myPower = 500;
    }

    @Override
    protected int doSubAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        Point2D destination = getDestination(destroyAt,currentWorld,currentTerrain);

        if(terrainClear(currentWorld.thanos,currentTerrain,destination)){
            currentWorld.worldExplosions.add(new FXEffect(currentWorld.thanos.myPosition,new Point2D(40,40),20,currentTerrain));
            currentWorld.worldExplosions.add(new FXEffect(destination,new Point2D(40,40),20,currentTerrain));
            currentWorld.thanos.myPosition = destination;
            AudioSaves.teleportSound.play();
            return 1;
        }

        return 0;
    }

    Point2D getDestination(Point2D destroyAt, World currentWorld, TerrainMap currentTerrain){
        Point2D destination = new Point2D(destroyAt.getX(),destroyAt.getY());
        Point2D teleportDistance = destination.add(currentWorld.thanos.myPosition.multiply(-1));
        double range = 500;
        if(Main.getMagnitudeSquared(teleportDistance)>Math.pow(range,2)){
            teleportDistance = teleportDistance.normalize().multiply(range);
            destination = teleportDistance.add(currentWorld.thanos.myPosition);
        }

        destination = currentTerrain.clampPoint(destination,currentWorld.thanos.mySize);
        return destination;
    }

    @Override
    protected int doSubSecondaryAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        if(currentWorld.thanos.infinity.nbAme>0) {
            Point2D destination = getDestination(destroyAt, currentWorld, currentTerrain);

            if (terrainClear(currentWorld.thanos, currentTerrain, destination)) {
                currentWorld.thanos.infinity.nbAme--;
                currentWorld.worldProjectiles.add(new Tnt(destination, myPower, 100));
                AudioSaves.teleportSound.play();
                return 1;
            }
        }
        return 0;
    }

    boolean terrainClear(Thanos thanos, TerrainMap currentTerrain, Point2D destination){
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
