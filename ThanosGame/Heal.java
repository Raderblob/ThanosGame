package ThanosGame;

//import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Heal extends Item{
    private int vieRendue;

    public Heal(Point2D position, Point2D Size,int vieRendue){
        super(position, Size,null,new Point2D(0,0),100);
        this.vieRendue=vieRendue;
    }

   /* public void Healing(Thanos Thanos1){
        if(((Thanos1.myPosition.getX()+Thanos1.mySize.getX())>this.position.getX())&&(Thanos1.myPosition.getX()<(this.position.getX()-this.mySize.getX()))&&((Thanos1.myPosition.getY()+Thanos1.mySize.getY())>this.position.getY())&&(Thanos1.myPosition.getY()<(this.position.getY()-this.mySize.getY()))){
            if(Thanos1.PV<=Thanos1.PVmax-this.vieRendue){
                Thanos1.PV=Thanos1.PV+this.vieRendue;
            }
            else {
                Thanos1.PV=Thanos1.PVmax;
            }
            //Supprimer Heal ensuite
        }
    }*/
}
