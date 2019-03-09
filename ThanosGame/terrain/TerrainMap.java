package ThanosGame.terrain;

import ThanosGame.Game;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TerrainMap{
    private SimplexNoise geny;
    private TerrainChunck chunk[];
    private int numChunks;
    public boolean terrainRendered;
    public TerrainMap(int numC) {
        numChunks = numC;
        terrainRendered = false;
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


    }



    public void createRender(){
        if(terrainRendered == false) {
            genTerrainImage();
            terrainRendered = true;
        }
    }
    public void disposeRender(){
        if(terrainRendered == true){
            for(int i = 0;i<chunk.length;i++){
                chunk[i].disposeTerrainImage();
            }
        }
    }

    public void draw(GraphicsContext gc, Point2D pos){
        for(int i = 0;i<chunk.length;i++)
        {
           double drawPos = -Math.min(pos.getX(),numChunks * TerrainChunck.chunkParam.getX()*4 - 1000)+i*TerrainChunck.chunkParam.getX()*4;
           if(drawPos+TerrainChunck.chunkParam.getX()*4 >=0 && drawPos<Game.winParam.getX())
           gc.drawImage( chunk[i].imageToDraw,drawPos,0);
        }
    }

    private void setTerrainVal(int x, int y, byte val){
        chunk[x/(int)TerrainChunck.chunkParam.getX()].setVal(x,y,val);
    }
    public byte getTerrainVal(double x, double y){
        int nX,nY;
        nX = (int)(x/4);
        nY= (int)(y/4);
        return chunk[nX/(int)TerrainChunck.chunkParam.getX()].getVal(nX,nY);
    }

    public void changeTerrain(Point2D pointToChange[],byte futurVals[]){
        Point2D pTC[] = new Point2D[pointToChange.length];
         for(int i=0;i<pointToChange.length;i++){
            pTC[i] = pointToChange[i].multiply(0.25);
            setTerrainVal((int)pTC[i].getX(),(int)pTC[i].getY(),futurVals[i]);
         }
         int indice = (int)pTC[0].getX()/(int)TerrainChunck.chunkParam.getX();
         chunk[indice].genTerrainImage();
         if(indice -1 >-1){
             chunk[indice-1].genTerrainImage();
         }
         if(indice+1<chunk.length){
             chunk[indice+1].genTerrainImage();
         }
        System.out.println(pTC[0].toString());
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
        BufferedImage terrainImage;
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

    public void disposeTerrainImage(){
        imageToDraw = null;
    }
    public byte getTVal(double x, double y){
        return terrain[(int)(x/4)][(int)(y/4)];
    }

}