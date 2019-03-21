package ThanosGame.weapons;

import ThanosGame.Item;
import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.graphics.images.PixelBlockType;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

public class Explosion extends Item { //does the damage
    private double damagePerTick;
    private double explosionRadius;
    private Point2D[] explosionPoints;
    private double maxLife;
    public Explosion(Point2D maPosition, Point2D Size,double myL, double damageT,TerrainMap mTerrain) {
        super(maPosition, Size,ImagesSaves.explosions[0],new Point2D(15,13),100);
        mylife = myL;
        maxLife = myL;
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

    public void renderMe(GraphicsContext gc, Point2D camPos) {
        gc.save();
        double myScale =1- (mylife/maxLife);
        Scale nS = new Scale(myScale,myScale,position.getX()-camPos.getX(), position.getY()-camPos.getY());
        Rotate nR = new Rotate(Math.random()*360,position.getX()-camPos.getX(),position.getY()-camPos.getY());
        Affine nA = new Affine(nS);
        nA.append(nR);
        Main.applyMatrixTransform(gc, nA);
        super.renderMe(gc,camPos);
        gc.restore();
    }
}
