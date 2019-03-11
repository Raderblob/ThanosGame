package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class PlayerClass {
    public int movingState;
    public int jumpingState;
    public Point2D myPosition;
    private Point2D mySpeed;
    private Point2D mySize;
    private Point2D destroyAt;
    public PlayerClass(){
        myPosition = new Point2D(50,50);
        mySize = new Point2D(10,10);
        mySpeed = new Point2D(0,0);
        destroyAt = new Point2D(-1,-1);
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.PURPLE);
        gc.fillRect(myPosition.getX()-mySize.getX()-getCameraPosition().getX(),myPosition.getY()-mySize.getY()-getCameraPosition().getY(),mySize.getX()*2,mySize.getY()*2);
       /* gc.drawImage(this.Thanos.getImgThanos()myPosition.getX()-mySize.getX()-getCameraPosition().getX(),myPosition.getY()-mySize.getY()-getCameraPosition().getY());*/
    }

    public void fireAt(double fX, double fY){
        destroyAt = new Point2D(fX,fY).add(getCameraPosition());
    }



    public Point2D getCameraPosition(){
        return new Point2D(Math.max(myPosition.getX()-200,0),0);
    }

    public void run(TerrainMap currentTerrain,World currentWorld){
       doPlayerMovement(currentTerrain,currentWorld);

        if(destroyAt.getX() !=-1){
            useTestStone(currentTerrain,currentWorld);
        }
    }

    private void doPlayerMovement(TerrainMap currentTerrain,World currentWorld){
        boolean tUnderFoot = terrainUnderFoot(currentTerrain);
        mySpeed = mySpeed.add(0, 0.5*(tUnderFoot ? 0 : 1));


        if(tUnderFoot){
            if(jumpingState==2) {
                jumpingState=1;
            }else{
                mySpeed = new Point2D(mySpeed.getX(),0);
                jumpingState=0;
            }
        }

        if(playerTrimmingTerrain(currentTerrain)){
            myPosition = myPosition.add(0,-4);
        }
        if(terrainIsObstacleOverhead(currentTerrain)){
            mySpeed = new Point2D(mySpeed.getX(),0);
            myPosition = myPosition.add(0,4);
        }

        if(movingState>0){
            myPosition = myPosition.add(2*movingState*(terrainIsObstacleRight(currentTerrain)?0:1),0);
        }else if(movingState<0){
            myPosition = myPosition.add(2*movingState*(terrainIsObstacleLeft(currentTerrain)?0:1),0);
        }
        myPosition=  currentTerrain.clampPoint( myPosition.add(mySpeed),mySize);
    }

    private void useTestStone(TerrainMap currentTerrain,World currentWorld){
        LinkedList<Point2D> pTD = new LinkedList<>();

        for(int x = -40;x<40;x++){
            for(int y = -40;y<+40;y++){
                if(Main.getMagnitudeSquared(x,y)<Math.pow(40,2)){
                    pTD.add(currentTerrain.clampPoint(new Point2D(x,y).add(destroyAt)));
                }
            }
        }
        byte bTD[] = new byte[pTD.size()];
        for (int i=0;i<bTD.length;i++){
            bTD[i]=0;
        }
        currentTerrain.changeTerrain( pTD.toArray(new Point2D[pTD.size()]),bTD,true);
        destroyAt = new Point2D(-1,-1);
    }

    public void jump(){
        if(jumpingState ==0)
        {
            mySpeed = mySpeed.add(0,-10);
            jumpingState = 2;
        }
    }

    private boolean terrainUnderFoot(TerrainMap currentTerrain){
        for(int x =(int)( myPosition.getX()-mySize.getX());x<myPosition.getX()+mySize.getX();x+=4){
            if(currentTerrain.getTerrainVal(x,myPosition.getY()+mySize.getY())!=0){
                return true;
            }
        }
        return false;
    }
    private boolean terrainIsObstacleRight(TerrainMap currentTerrain){
        for(int y =(int)( myPosition.getY()-mySize.getY());y<myPosition.getY();y+=4){
            if(currentTerrain.getTerrainVal(myPosition.getX()+mySize.getX(),y)!=0){
                return true;
            }
        }
        return false;
    }
    private boolean terrainIsObstacleLeft(TerrainMap currentTerrain){
        for(int y =(int)( myPosition.getY()-mySize.getY());y<myPosition.getY();y+=4){
            if(currentTerrain.getTerrainVal(myPosition.getX()-mySize.getX(),y)!=0){
                return true;
            }
        }
        return false;
    }
    private boolean terrainIsObstacleOverhead(TerrainMap currentTerrain){
        for(int x =(int)( myPosition.getX()-mySize.getX());x<myPosition.getX()+mySize.getX();x+=4){
            if(currentTerrain.getTerrainVal(x,myPosition.getY()-mySize.getY())!=0){
                return true;
            }
        }
        return false;
    }

    private boolean playerTrimmingTerrain(TerrainMap currentTerrain){
        for(int x =(int)( myPosition.getX()-mySize.getX());x<myPosition.getX()+mySize.getX();x+=4){
            if(currentTerrain.getTerrainVal(x,myPosition.getY()+mySize.getY()-1)!=0){
                return true;
            }
        }
        return false;
    }
}
