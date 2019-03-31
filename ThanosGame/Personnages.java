package ThanosGame;

import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;


public class Personnages extends PlayerClass {
    private Thanos player;
    private TerrainMap myTerrain;
    private final int TERRAINRANGE = 50;
    private AiState myState;
    private int patrolFlipper;
    private long timeKeeper;
    private long timeLapse;
    public Personnages(Point2D pos,Thanos thanos,TerrainMap myTerrain) {
        myState=AiState.PATROL;
        this.myTerrain=myTerrain;
        player = thanos;
        myPosition = pos;
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(0, 0);
        myAnimation = new AnimatedPerson(ImagesSaves.wakandaisSprites, new Point2D(335, 430), 64);


        timeKeeper = System.currentTimeMillis();
        timeLapse = 10000;
    }

    private void moveRight(){
        movingState = 1;
        if(obsClear ==0 || obstacleAtDistance(TERRAINRANGE)){
            jump();
        }
    }
    private void moveLeft(){
        movingState = -1;
        if(obsClear ==0 || obstacleAtDistance(-TERRAINRANGE)){
            jump();
        }
    }

    @Override
    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        switch (myState){
            case ATTACK:

                break;
            case PATROL:
                if(patrolFlipper ==0){
                    moveRight();
                    if(System.currentTimeMillis() - timeKeeper>timeLapse){
                        patrolFlipper =1;
                        timeKeeper  =System.currentTimeMillis();
                    }
                }else{
                    moveLeft();
                    if(System.currentTimeMillis() - timeKeeper>timeLapse){
                        patrolFlipper =0;
                        timeKeeper  =System.currentTimeMillis();
                    }
                }
                break;
            case STATIC:
                movingState=0;
                break;
        }
        super.run(currentTerrain, currentWorld, currentNanoTime);
    }


    private boolean obstacleAtDistance(int atDistance){
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            if (myTerrain.getTerrainValCollision(Math.max(myPosition.getX() + atDistance,0), y) != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Point2D getCameraPosition() {
        return player.getCameraPosition();
    }

    enum AiState{
        STATIC,PATROL,ATTACK;
    }
}
