package ThanosGame.weapons;

//import ThanosGame.terrain.TerrainMap;
import ThanosGame.Item;
import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Projectile extends Item {
    private int vitesse;
    private int degats;
    private Point2D speed;
    private double mylife;
    protected boolean canDamage;

    public Projectile(Point2D position, Point2D Size, int vitesse , int degats , Point2D speed){
        super(position,Size,null,new Point2D(0,0),100);
        this.vitesse=vitesse;
        this.degats=degats;
        this.speed=speed;
        canDamage =true;
    }

    public void collisionWorld(World myWorld,TerrainMap myTerrain){
        if(isInRectangle(myWorld.thanos.myPosition,myWorld.thanos.mySize)){
            mylife=0;
            //create explosion (does not do the damage directly)
        }
        if(myTerrain.getTerrainVal(position.getX(),position.getY())!=0){
            mylife=0;
            //create explosion
        }

    }

    public void collisionThanos(Thanos Thanos1) {
        if (((Thanos1.myPosition.getX() + Thanos1.mySize.getX()) > this.position.getX()) && (Thanos1.myPosition.getX() < (this.position.getX() - this.mySize.getX())) && ((Thanos1.myPosition.getY() + Thanos1.mySize.getY()) > this.position.getY()) && (Thanos1.myPosition.getY() < (this.position.getY() - this.mySize.getY()))) {
            Thanos1.PV = Thanos1.PV - this.degats;
        }
    }


    public void collisionTerrain(TerrainMap currentTerrain){
    }
}
