package ThanosGame;


import ThanosGame.graphics.AnimatedImage;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Item {
    public Point2D position;
    public Point2D mySize;
    public double mylife;
    protected AnimatedImage myAnimation;

    public Item(Point2D maPosition, Point2D size, Image myImg, Point2D imgSize, int imgDelay) {
        position = new Point2D(maPosition.getX(), maPosition.getY());
        mySize = size;
        myAnimation = new AnimatedImage(myImg, imgSize, imgDelay);
    }

    protected boolean isInRectangle(Point2D rPos, Point2D rSize) {
        Point2D relPos = position.add(rPos.multiply(-1));
        return Math.abs(relPos.getX()) < rSize.getX() + mySize.getX() && Math.abs(relPos.getY()) < rSize.getY() + mySize.getY();
    }

    public void renderMe(GraphicsContext gc, Point2D camPos) {
        myAnimation.draw(gc, new Point2D(position.getX() - mySize.getX() - camPos.getX(), position.getY() - mySize.getY() - camPos.getY()), mySize.multiply(2));
    }
}
