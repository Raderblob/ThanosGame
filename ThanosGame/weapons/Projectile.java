package ThanosGame.weapons;

//import ThanosGame.terrain.TerrainMap;

import ThanosGame.Item;
import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Scale;

public class Projectile extends Item {
    private int degats;
    private Point2D speed;
    protected boolean canDamage;

    public Projectile(Point2D position, Point2D Size, int degats , Point2D speed){
        super(position,Size,ImagesSaves.projectiles[0],new Point2D(20,10),100);
        this.degats=degats;
        this.speed=speed;
        canDamage =true;
        mylife =1000;
    }

    public void runLogic(World myWorld,TerrainMap myTerrain,double nanoTime){
        mylife-=nanoTime;
        position = position.add(speed.multiply(nanoTime));
        if(isInRectangle(myWorld.thanos.myPosition,myWorld.thanos.mySize)){
            mylife=0;
            //create explosion (does not do the damage directly)
            myWorld.worldExplosions.add(new Explosion(position,new Point2D(10,10),10,1,myTerrain));
        }
        if(myTerrain.getTerrainVal(position.getX(),position.getY())!=0){
            mylife=0;
            //create explosion
            myWorld.worldExplosions.add(new Explosion(position,new Point2D(10,10),10,degats,myTerrain));
        }

    }







    public void renderMe(GraphicsContext gc, Point2D camPos) {
        gc.save();
        Scale nR = new Scale(1,1,position.getX(), position.getY());
        Main.applyMatrixTransform(gc, nR);
        super.renderMe(gc,camPos);
        gc.restore();
    }
  /*  public void collisionThanos(Thanos Thanos1) {
        if (((Thanos1.myPosition.getX() + Thanos1.mySize.getX()) > this.position.getX()) && (Thanos1.myPosition.getX() < (this.position.getX() - this.mySize.getX())) && ((Thanos1.myPosition.getY() + Thanos1.mySize.getY()) > this.position.getY()) && (Thanos1.myPosition.getY() < (this.position.getY() - this.mySize.getY()))) {
            Thanos1.PV = Thanos1.PV - this.degats;
        }
    }



    public void collisionTerrain(TerrainMap currentTerrain){
    }*/
}
