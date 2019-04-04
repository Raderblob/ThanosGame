package ThanosGame;

import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.*;
import javafx.geometry.Point2D;
import resources.ImagesSaves;


public class Personnage extends PlayerClass {
    public boolean turned;
    private Weapon myGun;
    private Thanos player;
    private TerrainMap myTerrain;
    private final int TERRAINRANGE = 45;
    private final int SIGHTRANGE = 350;
    private AiState myState;
    private int patrolFlipper;
    private long timeKeeper;
    private long timeLapse;
    private boolean changeMind;
    private Point2D myTarget;

    public Personnage(Point2D pos, TerrainMap myTerrain, World myWorld) {
        turned=false;
        this.PV=100 * myWorld.getDifficulty() ;
        maxPv = PV;
        if(Main.numberGenerator.nextInt(10)<7){
            myState = AiState.STATIC;
        }else{
            patrolMe();
        }

        this.myTerrain = myTerrain;
        player = myWorld.thanos;
        myPosition = pos;
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(0, 0);
        myAnimation = new AnimatedPerson(ImagesSaves.wakandaisSprites, new Point2D(322, 400), 64);

        int rndgen = Main.numberGenerator.nextInt(100);
        if(rndgen<20){
            myGun = new ReboundRifle(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }else if(rndgen<50){
            myGun = new Rifle(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }else{
            myGun = new Spear(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }


    }

    private void moveRight() {
        moveDir(1);
    }

    private void moveLeft() {
        moveDir(-1);
    }

    private void moveDir(int dir) {
        movingState = dir;
        boolean shouldJump = (myState!=AiState.ATTACK||player.myPosition.getY()-myPosition.getY()<0);
        if (obsClear == 0 || (obstacleAtDistance(dir * TERRAINRANGE)&&shouldJump)) {
            jump();
        }
    }


    @Override
    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        myTarget=player.myPosition;
        for(FXEffect fx:currentWorld.worldExplosions){
            if(fx.myType()==1){
                if(Main.getMagnitudeSquared(fx.position.add(myPosition.multiply(-1)))<Math.pow(SIGHTRANGE,2)){
                    myTarget = fx.position;
                }
            }
        }

        Point2D pDir = myTarget.add(myPosition.multiply(-1));
        double playerDistance = pDir.distance(0,0);
        Point2D pDirN = pDir.multiply(1/playerDistance);

        if(myState!=AiState.STUNNED && myState != AiState.ATTACK) {
            if (playerDistance < SIGHTRANGE) {
                stunMe(10);
            }
            if (PV < maxPv) {
                stunMe(10);
            }
        }


        switch (myState) {
            case ATTACK:
                myGun.fire(pDirN);

                if(Main.numberGenerator.nextInt(1000)<80){
                    changeMind=true;
                }

                if(myTarget.getY()-myPosition.getY()<0) {
                    if (Main.numberGenerator.nextInt(1000) < 50) {
                        jump();
                    }
                }
                if(changeMind){
                    if(pDir.getX()>0){
                        movingState = Main.numberGenerator.nextInt(7)-1;
                    }else{
                        movingState = Main.numberGenerator.nextInt(7)-6;
                    }
                    if(movingState!=0){
                        movingState = movingState/Math.abs(movingState);
                    }
                    changeMind=false;
                }
                moveDir(movingState);

                if(playerDistance > SIGHTRANGE *10){
                    patrolMe();
                }
                break;
            case PATROL:
                if (patrolFlipper == 0) {
                    moveRight();
                    if (System.currentTimeMillis() - timeKeeper > timeLapse) {
                        patrolFlipper = 1;
                        timeKeeper = System.currentTimeMillis();
                    }
                } else {
                    moveLeft();
                    if (System.currentTimeMillis() - timeKeeper > timeLapse) {
                        patrolFlipper = 0;
                        timeKeeper = System.currentTimeMillis();
                    }
                }
                break;
            case STATIC:
                movingState = 0;
                break;
            case STUNNED:
                movingState=0;
                if(System.currentTimeMillis()-timeKeeper>timeLapse){
                    myState = AiState.ATTACK;
                }
                break;
        }
        super.run(currentTerrain, currentWorld, currentNanoTime);
    }

    public void patrolMe(){
        myState= AiState.PATROL;
        timeKeeper = System.currentTimeMillis();
        timeLapse = Main.numberGenerator.nextInt(1000)+500;
    }

    public void stunMe(int power){
        myState = AiState.STUNNED;
        timeKeeper = System.currentTimeMillis();
        timeLapse = Main.numberGenerator.nextInt(power*50)+500;
    }

    private Point2D playerDirection() {
        return player.myPosition.add(myPosition.multiply(-1)).normalize();
    }


    private boolean obstacleAtDistance(int atDistance) {
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            if (myTerrain.getTerrainValCollision(Math.max(myPosition.getX() + atDistance, 0), Math.max(y,0)) != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Point2D getCameraPosition() {
        return player.getCameraPosition();
    }



    enum AiState {
        STATIC, PATROL, ATTACK,STUNNED;
    }
}
