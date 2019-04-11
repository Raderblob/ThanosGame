package ThanosGame.graphics;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimatedImage {
    private int imageIndex;
    private final int maxImageIndex;
    private final Image globalImage;
    private final Point2D subImageSize;
    int myMode;
    private final long delay;
    private long lastClock;

    public AnimatedImage(Image img, Point2D sISize, int delayToUse) {
        globalImage =img;
        Point2D globalSize = new Point2D(globalImage.getWidth(), globalImage.getHeight());
        subImageSize = sISize;
        myMode = 0;
        imageIndex = 0;
        maxImageIndex = (int) (globalSize.getX() / subImageSize.getX());
        delay = delayToUse;
        lastClock = System.currentTimeMillis();
    }
    public void setMyMode(int myMode){
        this.myMode=myMode;
    }

    public void draw(GraphicsContext gc, Point2D drawingCoords, Point2D imageSize) {
        gc.drawImage(globalImage,imageIndex * subImageSize.getX(), myMode * subImageSize.getY(), subImageSize.getX(), subImageSize.getY(), drawingCoords.getX(), drawingCoords.getY(), imageSize.getX(), imageSize.getY());
        if(System.currentTimeMillis()-lastClock > delay){
            lastClock = System.currentTimeMillis();
            imageIndex +=1;
            if(imageIndex >= maxImageIndex){
                imageIndex=0;
            }
        }
    }
}
