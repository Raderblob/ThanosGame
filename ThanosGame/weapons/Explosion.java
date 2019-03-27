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

        for(int i = 0;i<explosionPoints.length;i++){
            double damageD = (1-Main.getMagnitudeSquared(explosionPoints[i].add(position.multiply(-1)))/(Math.pow(explosionRadius,2)))*damagePerTick*nanoTime;
            if(canDamage(terrain.getTerrainVal(explosionPoints[i].getX(),explosionPoints[i].getY()),damageD)){
                terrain.changeTerrain(new Point2D[]{explosionPoints[i]},new byte[]{0});
            }
        }

    }

    private boolean canDamage(byte terrainByte, double damageDone){
        if(terrainByte == PixelBlockType.NOTHING.getMyVal()){
         return false;
        }else if(terrainByte == PixelBlockType.BEDROCK.getMyVal()){
            return false;
        }else if(terrainByte == PixelBlockType.GRASS.getMyVal() || terrainByte == PixelBlockType.DIRT.getMyVal()){
            if(damageDone>1){
                return true;
            }else{
                return false;
            }
        }else if(terrainByte == PixelBlockType.STONE.getMyVal()){
            if(damageDone>8){
                return true;
            }else{
                return false;
            }
        }else if(terrainByte == PixelBlockType.BRICK.getMyVal()){
            if(damageDone>12){
                return true;
            }else{
                return false;
            }
        }


        return false;
    }

}
