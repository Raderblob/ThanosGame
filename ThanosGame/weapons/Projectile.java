package ThanosGame.weapons;


import ThanosGame.Item;
import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Projectile extends Item {
    public int degats;
    protected Point2D speed;
    protected boolean canDamage;
    protected double maxLife;
    public boolean enemyOwned;
    public Projectile(Point2D position, Point2D size, int degats , Point2D speed,double mLife,int projectileId){
        super(position,size,ImagesSaves.projectiles[projectileId],new Point2D(20,10),100);
        this.degats=degats;
        this.speed=speed;
        canDamage =true;
        maxLife = mLife;
        enemyOwned = true;
        mylife = maxLife;
    }
    public Projectile(Point2D position, Point2D size, int degats , double speed,double angle){
        super(position,size,ImagesSaves.projectiles[0],new Point2D(20,10),100);
        this.degats=degats;
        double angleRad = angle/180*Math.PI;
        this.speed=new Point2D(Math.cos(angleRad),Math.sin(angleRad)).multiply(speed);
        canDamage =true;
        mylife =1000;
        enemyOwned=true;
    }

    public void runLogic(World myWorld,TerrainMap myTerrain,double nanoTime){
        if(runBasicPhysics(myTerrain,nanoTime)) {
            if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)) {
                mylife = 0;
                //create explosion (does not do the damage directly)
            }
            if (myTerrain.getTerrainValCollision(position.getX(), position.getY()) != 0) {
                mylife = 0;
                //create explosion
            }
        }

    }

    protected boolean runBasicPhysics(TerrainMap myTerrain,double nanoTime){
        mylife-=nanoTime;
        position = position.add(speed.multiply(nanoTime));
        Point2D clampedPos = myTerrain.clampPoint(position);
        if(clampedPos.getY()!= position.getY() || clampedPos.getX()!=position.getX()){
            mylife=0;
            return false;
        }else{
            return true;
        }
    }





    public void renderMe(GraphicsContext gc, Point2D camPos) {
        gc.save();
        Rotate nR = new Rotate(Math.atan2(speed.getY(),speed.getX())/Math.PI*180,position.getX()-camPos.getX(), position.getY()-camPos.getY());
        Main.applyMatrixTransform(gc, nR);
        super.renderMe(gc,camPos);
        gc.restore();
    }

}
