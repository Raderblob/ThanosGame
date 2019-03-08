import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerClass {
    public int movingState;
    public int jumpingState;
    public Point2D myPosition;
    private Point2D mySpeed;
    private Point2D mySize;
    public PlayerClass(){
        myPosition = new Point2D(50,50);
        mySize = new Point2D(10,10);
        mySpeed = new Point2D(0,0);
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.PURPLE);
        gc.fillRect(myPosition.getX()-mySize.getX()-getCameraPosition().getX(),myPosition.getY()-mySize.getY()-getCameraPosition().getY(),mySize.getX()*2,mySize.getY()*2);
    }

    public Point2D getCameraPosition(){
        return new Point2D(Math.max(myPosition.getX()-200,0),0);
    }

    public void run(TerrainMap currentTerrain){
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
            myPosition = myPosition.add(0,-1);
        }
        if(terrainIsObstacleOverhead(currentTerrain)){
            mySpeed = new Point2D(mySpeed.getX(),0);
            myPosition = myPosition.add(0,1);
        }

        if(movingState>0){
            myPosition = myPosition.add(2*movingState*(terrainIsObstacleRight(currentTerrain)?0:1),0);
        }else if(movingState<0){
            myPosition = myPosition.add(2*movingState*(terrainIsObstacleLeft(currentTerrain)?0:1),0);
        }
        myPosition=   myPosition.add(mySpeed);
    }

    public void jump(){
        if(jumpingState ==0)
        {
            mySpeed = mySpeed.add(0,-10);
            jumpingState = 2;
        }
    }

    private boolean terrainUnderFoot(TerrainMap currentTerrain){
        for(int x =(int)( myPosition.getX()-mySize.getX());x<myPosition.getX()+mySize.getX();x++){
            if(currentTerrain.getTerrainVal(x,myPosition.getY()+mySize.getY())!=0){
                return true;
            }
        }
        return false;
    }
    private boolean terrainIsObstacleRight(TerrainMap currentTerrain){
        for(int y =(int)( myPosition.getY()-mySize.getY());y<myPosition.getY();y++){
            if(currentTerrain.getTerrainVal(myPosition.getX()+mySize.getX(),y)!=0){
                return true;
            }
        }
        return false;
    }
    private boolean terrainIsObstacleLeft(TerrainMap currentTerrain){
        for(int y =(int)( myPosition.getY()-mySize.getY());y<myPosition.getY();y++){
            if(currentTerrain.getTerrainVal(myPosition.getX()-mySize.getX(),y)!=0){
                return true;
            }
        }
        return false;
    }
    private boolean terrainIsObstacleOverhead(TerrainMap currentTerrain){
        for(int x =(int)( myPosition.getX()-mySize.getX());x<myPosition.getX()+mySize.getX();x++){
            if(currentTerrain.getTerrainVal(x,myPosition.getY()-mySize.getY())!=0){
                return true;
            }
        }
        return false;
    }

    private boolean playerTrimmingTerrain(TerrainMap currentTerrain){
        for(int x =(int)( myPosition.getX()-mySize.getX());x<myPosition.getX()+mySize.getX();x++){
            if(currentTerrain.getTerrainVal(x,myPosition.getY()+mySize.getY()-1)!=0){
                return true;
            }
        }
        return false;
    }
}
