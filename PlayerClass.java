import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerClass {
    public int movingState;
    public int jumpingState;
    public Point2D myPosition;
    public PlayerClass(){
        myPosition = new Point2D(50,50);
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.PURPLE);
        gc.fillRect(myPosition.getX()-10-getCameraPosition().getX(),myPosition.getY()-10-getCameraPosition().getY(),20,20);
    }

    public Point2D getCameraPosition(){
        return new Point2D(Math.max(myPosition.getX()-50,0),0);
    }

    public void run(){
     myPosition=   myPosition.add(movingState,0);
    }
}
