package ThanosGame.graphics;

import ThanosGame.Main;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class AnimatedPerson extends AnimatedImage {
    private double lastDir;
    public AnimatedPerson(Image img, Point2D sISize, int delayToUse) {
        super(img, sISize, delayToUse);
        lastDir = Main.numberGenerator.nextInt(2)-1;
    }

    public void setWalkingMode(double xDir){
        System.out.println(xDir);
        if(xDir<0){
            this.myMode =1;
            lastDir = xDir;
        }else if(xDir >0){
            this.myMode =0;
            lastDir = xDir;
        }else{
            if(lastDir<0){
                myMode = 3;
            }else{
                myMode = 2;
            }
        }
    }
}
