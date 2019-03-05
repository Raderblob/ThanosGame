import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class World {
    PlayerClass Thanos;
    byte [][] terrain;
    SimplexNoise geny;
    SimplexNoise genlim;
    double counter =0;


    public World(int worldType,PlayerClass p){
        Thanos = p;

        switch (worldType){
            case 0 :
                geny= new SimplexNoise(   (int)System.currentTimeMillis(),8,0.0015,0.5);
                genlim = new SimplexNoise(   (int)System.currentTimeMillis()+1,8,0.005,0.4);
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
                if((y/500d)>(genlim.getNoise(x,0)+1)*0.5*0.6+0.5) {
                    double a = Math.abs(geny.getNoise(x , y )) ;
                    gc.setFill(new Color(a, 0, 0, 1));
                    gc.fillRect(x, y, 1, 1);

                }
            }
        }
    }
}
