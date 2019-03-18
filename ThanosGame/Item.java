package ThanosGame;

//import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Item {
    public Point2D position ;
    public Point2D mySize;

    public Item(Point2D maPosition, Point2D Size){
        this.position=maPosition;
        this.mySize=Size;
    }
}
