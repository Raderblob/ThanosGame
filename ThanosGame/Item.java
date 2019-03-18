package ThanosGame;


import ThanosGame.graphics.AnimatedImage;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Item {
    public Point2D position;
    public Point2D mySize;
    public double mylife;
    protected AnimatedImage myAnimation;

    public Item(Point2D maPosition, Point2D Size,Image myImg,Point2D imgSize,int imgDelay) {
        this.position = maPosition;
        this.mySize = Size;
       // myAnimation = new AnimatedPerson(myImg,imgSize, imgDelay);
    }

    protected boolean isInRectangle(Point2D rPos, Point2D rSize) {
        Point2D relPos = position.add(rPos.multiply(-1));
        if (Math.abs(relPos.getX()) < rSize.getX() && Math.abs(relPos.getY()) < rSize.getY()) {
            return true;
        } else {
            return false;
        }
    }
    public void renderMe(GraphicsContext gc,Point2D camPos){
        gc.setFill(Color.RED);
        gc.fillRect(position.getX()-mySize.getX()-camPos.getX(),position.getY()-mySize.getY()-camPos.getY(),mySize.getX()*2,mySize.getY()*2);
    //    myAnimation.draw(gc,new Point2D(position.getX()-mySize.getX(),position.getY()-mySize.getY()),mySize.multiply(2));
    }
}
