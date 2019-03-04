import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class World {
    PlayerClass Thanos;
    byte [][] terrain;
    PerlinNoise geny;


    public World(int worldType,PlayerClass p){
        Thanos = p;
        geny= new PerlinNoise(   new Random().nextInt());
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
        gc.clearRect(0,0,600,600);
        for(int x = 0 ;x <500;x++){
            for(int y = 0;y<500;y++){
                if(Math.abs(geny.noise(x*0.05,y*0.05))>0.35){
                   gc.fillRect(x,y,1,1);
                }

            }
        }
    }
}
