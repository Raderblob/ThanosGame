package ThanosGame;

import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.ReboundRifle;
import ThanosGame.weapons.Rifle;
import ThanosGame.weapons.Spear;
import ThanosGame.weapons.Weapon;
import javafx.geometry.Point2D;


public class Personnage extends PlayerClass {
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

    public Personnage(Point2D pos, TerrainMap myTerrain, World myWorld) {
        this.PV=100 ;
        maxPv = PV;
        if(Main.numberGenerator.nextInt(10)<7){
            myState = AiState.STATIC;
        }else{
            myState = AiState.PATROL;
        }

        this.myTerrain = myTerrain;
        player = myWorld.thanos;
        myPosition = pos;
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(0, 0);
        myAnimation = new AnimatedPerson(ImagesSaves.wakandaisSprites, new Point2D(335, 430), 64);

        int rndgen = Main.numberGenerator.nextInt(100);
        if(rndgen<20){
            myGun = new ReboundRifle(myWorld.worldProjectiles, this, 10);
        }else if(rndgen<50){
            myGun = new Rifle(myWorld.worldProjectiles, this, 10);
        }else{
            myGun = new Spear(myWorld.worldProjectiles, this, 10);
        }


        timeKeeper = System.currentTimeMillis();
        timeLapse = Main.numberGenerator.nextInt(1000)+500;
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
        Point2D pDir = player.myPosition.add(myPosition.multiply(-1));
        double playerDistance = pDir.distance(0,0);
        Point2D pDirN = pDir.multiply(1/playerDistance);

        if(playerDistance <SIGHTRANGE){
            myState = AiState.ATTACK;
        }
        if(PV < maxPv){
            myState =AiState.ATTACK;
        }


        switch (myState) {
            case ATTACK:
                myGun.fire(pDirN);

                if(Main.numberGenerator.nextInt(1000)<80){
                    changeMind=true;
                }

                if(player.myPosition.getY()-myPosition.getY()<0) {
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
                    myState = AiState.PATROL;
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
        }
        super.run(currentTerrain, currentWorld, currentNanoTime);
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
        STATIC, PATROL, ATTACK;
    }
}
