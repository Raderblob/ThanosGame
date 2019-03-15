package ThanosGame;

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

    public void collisionThanos(Thanos thanos1){
        if(((thanos1.myPosition.getX()+thanos1.mySize.getX())>this.Position.getX())&&(thanos1.myPosition.getX()<(this.Position.getX()-this.mySize.getX()))&&((thanos1.myPosition.getY()+thanos1.mySize.getY())>this.Position.getY())&&(thanos1.myPosition.getY()<(this.Position.getY()-this.mySize.getY()))){
            thanos1.PV=thanos1.PV-this.degats;
            //Supprimer projectile ensuite
        }
    }
}
