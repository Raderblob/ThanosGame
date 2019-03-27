package ThanosGame.weapons;

import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.graphics.images.PixelBlockType;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Explosion extends FXEffect { //does the damage
    private double damagePerTick;

    public Explosion(Point2D maPosition, Point2D Size, double myL, double damageT, TerrainMap mTerrain) {
        super(maPosition, Size, myL, mTerrain);
        damagePerTick=damageT;
    }


    public void runExplosion(World myWorld, TerrainMap terrain, double nanoTime){
        super.runExplosion(myWorld,terrain,nanoTime);
        if(Main.getMagnitudeSquared(position.add(myWorld.thanos.myPosition.multiply(-1)))<Math.pow(explosionRadius,2)){
            //reduce hp of player
        }

        for (Point2D explosionPoint : explosionPoints) {
            double damageD = (1 - Main.getMagnitudeSquared(explosionPoint.add(position.multiply(-1))) / (Math.pow(explosionRadius, 2))) * damagePerTick * nanoTime;
            if (canDamage(terrain.getTerrainVal(explosionPoint.getX(), explosionPoint.getY()), damageD)) {
                terrain.changeTerrain(new Point2D[]{explosionPoint}, new byte[]{0});
            }
        }

    }

    private boolean canDamage(byte terrainByte, double damageDone){
        if(terrainByte == PixelBlockType.NOTHING.getMyVal()){
         return false;
        }else if(terrainByte == PixelBlockType.BEDROCK.getMyVal()){
            return false;
        }else if(terrainByte == PixelBlockType.GRASS.getMyVal() || terrainByte == PixelBlockType.DIRT.getMyVal()){
            return damageDone > 1;
        }else if(terrainByte == PixelBlockType.STONE.getMyVal()){
            return damageDone > 8;
        }else if(terrainByte == PixelBlockType.BRICK.getMyVal()){
            return damageDone > 12;
        }


        return false;
    }

}
