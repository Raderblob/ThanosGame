package ThanosGame.weapons;

import ThanosGame.Item;
import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.graphics.images.PixelBlockType;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Explosion extends Item { //does the damage
    private double damagePerTick;
    private double explosionRadius;
    private Point2D[] explosionPoints;
    public Explosion(Point2D maPosition, Point2D Size,double myL, double damageT,TerrainMap mTerrain) {
        super(maPosition, Size,null,new Point2D(0,0),100);
        mylife = myL;
        damagePerTick = damageT;
        explosionRadius = mySize.getX();
        explosionPoints = mTerrain.getCircleOfPoints(position,explosionRadius);
    }

    public void runExplosion(World myWorld, TerrainMap terrain, double nanoTime){
        mylife -= nanoTime;
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
