package ThanosGame;

//import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Projectiles extends Item{
    private int vitesse;
    private int degats;
    private Point2D speed;

    public Projectiles(Point2D position, Point2D Size, int vitesse , int degats , Point2D speed){
        super(position,Size);
        this.vitesse=vitesse;
        this.degats=degats;
        this.speed=speed;
    }

    public void collisionThanos(Thanos Thanos1){
        if(((Thanos1.myPosition.getX()+Thanos1.mySize.getX())>this.position.getX())&&(Thanos1.myPosition.getX()<(this.position.getX()-this.mySize.getX()))&&((Thanos1.myPosition.getY()+Thanos1.mySize.getY())>this.position.getY())&&(Thanos1.myPosition.getY()<(this.position.getY()-this.mySize.getY()))){
            Thanos1.PV=Thanos1.PV-this.degats;
            //Supprimer projectile ensuite
        }
    }

    public void collisionTerrain(){ //variable terrain
    }
}
