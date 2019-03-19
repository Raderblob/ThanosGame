package ThanosGame.weapons;

//import ThanosGame.terrain.TerrainMap;

import ThanosGame.Item;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Projectile extends Item {
    private int degats;
    private Point2D speed;
    protected boolean canDamage;

    public Projectile(Point2D position, Point2D Size, int degats , Point2D speed){
        super(position,Size,null,new Point2D(0,0),100);
        this.degats=degats;
        this.speed=speed;
        canDamage =true;
        mylife =1000;
    }

    public void runLogic(World myWorld,TerrainMap myTerrain,double nanoTime){
        mylife-=1;
        position = position.add(speed.multiply(nanoTime));
        if(isInRectangle(myWorld.thanos.myPosition,myWorld.thanos.mySize)){
            mylife=0;
            //create explosion (does not do the damage directly)
        }
        if(myTerrain.getTerrainVal(position.getX(),position.getY())!=0){
            mylife=0;
            //create explosion
            Point2D[] ptT = new Point2D[1];
            ptT[0] = new Point2D(position.getX(),position.getY());
            myTerrain.changeTerrain(ptT,new byte[]{0});
        }

    }

  /*  public void collisionThanos(Thanos Thanos1) {
        if (((Thanos1.myPosition.getX() + Thanos1.mySize.getX()) > this.position.getX()) && (Thanos1.myPosition.getX() < (this.position.getX() - this.mySize.getX())) && ((Thanos1.myPosition.getY() + Thanos1.mySize.getY()) > this.position.getY()) && (Thanos1.myPosition.getY() < (this.position.getY() - this.mySize.getY()))) {
            Thanos1.PV = Thanos1.PV - this.degats;
        }
    }



    public void collisionTerrain(TerrainMap currentTerrain){
    }*/
}
