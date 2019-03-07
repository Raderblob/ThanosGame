import javafx.geometry.Point2D;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class World {
    PlayerClass Thanos;
    TerrainMap terrain;
    double counter = 0;
    public World(int worldType,PlayerClass p){
        Thanos = p;

        switch (worldType){
            case 0 :
                terrain = new TerrainMap(30);

            break;
            default:

            break;
        }
    }

    public void runWorld(){

    }

    public void renderWorld(GraphicsContext gc){
        double lastTime = System.currentTimeMillis();
       terrain.draw(gc,new Point2D((float)counter,0f));

        System.out.println(System.currentTimeMillis()-lastTime + " " + counter);
        counter +=0.5;
    }
}

class TerrainMap{
    private SimplexNoise geny;
    private TerrainChunck chunk[];
    private int numChunks;
    public TerrainMap(int numC) {
        numChunks = numC;
        geny = new SimplexNoise((int) System.currentTimeMillis(), 8, 0.0035, 0.5);


        chunk = new TerrainChunck[numChunks];
        for (int i = 0; i < chunk.length; i++){
                chunk[i] = new TerrainChunck(i);
        }


        for(int x = 0;x<(int)TerrainChunck.chunkParam.getX()*chunk.length;x++){
            for(int y =0;y<(int)TerrainChunck.chunkParam.getY();y++){
                double a =Math.min(1, Math.abs(geny.getNoise(x , y ))*Math.pow(y/TerrainChunck.chunkParam.getY()+0.6,10));//*Math.pow(y/125+0.5,5)) ;
                if(a>0.25){
                    setTerrainVal(x,y,(byte)1);

                }else{
                    setTerrainVal(x,y,(byte)0);
                }
            }
        }

        System.out.println("check");
       genTerrainImage();
        System.out.println("check2");

    }

    public void draw(GraphicsContext gc,Point2D pos){
        for(int i = 0;i<chunk.length;i++)
        {
            gc.drawImage( chunk[i].imageToDraw,-Math.min(pos.getX(),numChunks * TerrainChunck.chunkParam.getX()*4 - 1000)+i*TerrainChunck.chunkParam.getX()*4 ,0);
        }
    }

    private void setTerrainVal(int x, int y, byte val){
        chunk[x/(int)TerrainChunck.chunkParam.getX()].setVal(x,y,val);
    }
    private byte getTerrainVal(int x, int y){
        return chunk[x/(int)TerrainChunck.chunkParam.getX()].getVal(x,y);
    }

    private void genTerrainImage(){
        for(int i = 0;i<chunk.length;i++)
        {
            chunk[i].genTerrainImage();
        }
    }

}

class TerrainChunck{
    final public static Point2D chunkParam = new Point2D(500,125);
    private int chunkNum;
    private byte terrain[][];
    private BufferedImage terrainImage;
    private Graphics terrainDrawer;
    public javafx.scene.image.Image imageToDraw;
    public TerrainChunck(int cN){
        chunkNum = cN;
        terrain = new byte[(int)chunkParam.getX()][(int)chunkParam.getY()];
    }

    public void setVal(int x, int y, byte val){
        terrain[x%(int)chunkParam.getX()][y] = val;
    }

    public byte getVal(int x,int y){
        return terrain[x%(int)chunkParam.getX()][y];
    }

    public void genTerrainImage() {
        terrainImage = new BufferedImage((int)chunkParam.getX()*4,(int)chunkParam.getY()*4,BufferedImage.TYPE_3BYTE_BGR);
        terrainDrawer = terrainImage.getGraphics();
        for(int x = 0 ;x <terrainImage.getWidth();x+=4){
            for(int y = 0;y<terrainImage.getHeight();y+=4){
                byte a =getTVal(x,y);
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
    public byte getTVal(double x, double y){
        return terrain[(int)(x/4)][(int)(y/4)];
    }

}


