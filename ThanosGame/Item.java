package ThanosGame;


import ThanosGame.graphics.AnimatedImage;
import ThanosGame.graphics.AnimatedPerson;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Item {
    public Point2D position;
    public Point2D mySize;
    protected AnimatedImage myAnimation;

    public Item(Point2D maPosition, Point2D Size,Image myImg,Point2D imgSize,int imgDelay) {
        this.position = maPosition;
        this.mySize = Size;
        myAnimation = new AnimatedPerson(myImg,imgSize, imgDelay);
    }

    protected boolean isInRectangle(Point2D rPos, Point2D rSize) {
        Point2D relPos = position.add(rPos.multiply(-1));
        if (relPos.getY() > 0 && relPos.getX() > 0 && relPos.getX() < rSize.getX() && relPos.getY() < rSize.getY()) {
            return true;
        } else {
            return false;
        }
    }
}
