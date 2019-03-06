import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class World {
    PlayerClass Thanos;
    TerrainMap terrain;
    double counter = 0;
    public World(int worldType,PlayerClass p){
        Thanos = p;

        switch (worldType){
            case 0 :
                terrain = new TerrainMap();

            break;
            default:

            break;
        }
    }

    public void runWorld(){

    }

    public void renderWorld(GraphicsContext gc){
        double lastTime = System.currentTimeMillis();
        gc.drawImage(terrain.imageToDraw,-Math.min(counter,7000),0);

        System.out.println(System.currentTimeMillis()-lastTime + " " + counter);
        counter +=0.5;
    }
}

class TerrainMap{
    private byte [][] terrain;
    private SimplexNoise geny;
    private BufferedImage terrainImage;
    private Graphics terrainDrawer;
    public javafx.scene.image.Image imageToDraw;
    public TerrainMap(){
        terrainImage = new BufferedImage(2000*4,125*4,BufferedImage.TYPE_3BYTE_BGR);
        terrainDrawer = terrainImage.getGraphics();
        geny= new SimplexNoise(   (int)System.currentTimeMillis(),8,0.0035,0.5);
        terrain = new byte[2000][125];
        for(int x = 0 ;x <2000;x++){
            for(int y = 0;y<125;y++){
                double a =Math.min(1, Math.abs(geny.getNoise(x , y ))*Math.pow(y/125d+0.6,10));//*Math.pow(y/125+0.5,5)) ;
                if(a>0.25){
                    terrain[x][y] = 1;

                }else{
                    terrain[x][y] = 0;
                }
            }
        }
        genTerrainImage();
    }

    private void genTerrainImage(){
        for(int x = 0 ;x <terrainImage.getWidth();x+=4){
            for(int y = 0;y<terrainImage.getHeight();y+=4){
                byte a =getVal(x,y);
                if(a==1){
                    terrainDrawer.setColor(new java.awt.Color((int)(Math.random()*255),0,0));
                }else{
                    terrainDrawer.setColor(java.awt.Color.blue);
                }

                terrainDrawer.fillRect(x, y, 4, 4);

            }
        }
        imageToDraw = SwingFXUtils.toFXImage(terrainImage,null);
    }

    public byte getVal(double x, double y){
        return terrain[(int)(x/4)][(int)(y/4)];
    }
}


