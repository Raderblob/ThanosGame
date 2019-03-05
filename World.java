import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class World {
    PlayerClass Thanos;
    byte [][] terrain;
    SimplexNoise geny;
    double counter =0;


    public World(int worldType,PlayerClass p){
        Thanos = p;
        geny= new SimplexNoise(   (int)System.currentTimeMillis(),8,0.1,0.5);
        switch (worldType){
            case 0 :

            break;
            default:

            break;
        }
    }

    public void runWorld(){

    }

    public void renderWorld(GraphicsContext gc){
        gc.clearRect(0,0,6000,6000);
        for(int x = 0 ;x <500;x++){
            for(int y = 0;y<500;y++){
                    double a =Math.abs(geny.getNoise(x*0.05,y*0.05))*(y/500d);
                    gc.setFill(new Color(a,a,a,1));
                   gc.fillRect(x,y,1,1);


            }
        }
    }
}
