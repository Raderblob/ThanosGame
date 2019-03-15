package ThanosGame;

import javafx.geometry.Point2D;

public class Item {
    public Point2D Position ;
    public Point2D mySize;

    public Item(Point2D maPosition, Point2D Size){
        this.Position=maPosition;
        this.mySize=Size;
    }
}
