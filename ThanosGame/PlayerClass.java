package ThanosGame;

import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class PlayerClass {
    protected double PV ;
    protected double maxPv;
    public double myShield;

    public int movingState;
    private int jumpingState;
    private boolean doubleJumped;
    public Point2D myPosition;
    public Point2D mySize;
    public Point2D mySpeed;
    protected Point2D destroyAt;
    protected AnimatedPerson myAnimation;
    protected int obsClear ;

    public PlayerClass() {


        myPosition = new Point2D(50, 50);
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(0, 0);
        destroyAt = new Point2D(-1, -1);


        myAnimation = new AnimatedPerson(ImagesSaves.thanosSprites, new Point2D(400, 330), 64);
    }
    public void removeHp(double amount){
        double toRemove = amount;
        if(myShield>0){
            toRemove-=myShield;
            myShield-=amount;
        }
        PV -= Math.max(0,toRemove);
    }

    public void draw(GraphicsContext gc) {
        myAnimation.draw(gc, new Point2D(myPosition.getX() - mySize.getX() - getCameraPosition().getX(), myPosition.getY() - mySize.getY() - getCameraPosition().getY()), new Point2D(mySize.getX() * 2, mySize.getY() * 2));
    }

    public void fireAt(double fX, double fY) {
        destroyAt = new Point2D(fX, fY).add(getCameraPosition());
    }


    public Point2D getCameraPosition() {
        return new Point2D(Math.max(myPosition.getX() - 200, 0), 0);
    }

    public void run(TerrainMap currentTerrain, World currentWorld,double currentNanoTime) {
        if(myShield>0){
            myShield-=currentNanoTime*0.04;
        }
        myShield= Math.min(Math.max(myShield,0),maxPv);
        doPlayerMovement(currentTerrain, currentWorld,currentNanoTime);
        myAnimation.setWalkingMode(movingState);
    }

    private void doPlayerMovement(TerrainMap currentTerrain, World currentWorld,double currentNanoTime) {
        boolean tUnderFoot = terrainUnderFoot(currentTerrain);
        obsClear = 1;
        mySpeed = mySpeed.add(0, currentNanoTime * 0.5 * (tUnderFoot ? 0 : 1));


        if (tUnderFoot) {
            if (jumpingState == 2) {
                jumpingState -= 1;
            } else {
                mySpeed = new Point2D(mySpeed.getX(), 0);
                jumpingState = 0;
                doubleJumped = false;
            }
        }

        if (playerTrimmingTerrain(currentTerrain)) {
            myPosition = myPosition.add(0, -4);
        }
        if (terrainIsObstacleOverhead(currentTerrain) || myPosition.getY() - mySize.getY() == 0) {
            mySpeed = new Point2D(mySpeed.getX(), 0);
            myPosition = myPosition.add(0, 4);
        }

        if (movingState > 0) {
            obsClear = terrainIsObstacleRight(currentTerrain) ? 0 : 1;
            myPosition = myPosition.add( currentNanoTime*2 * movingState * (obsClear), 0);
        } else if (movingState < 0) {
            obsClear = terrainIsObstacleLeft(currentTerrain) ? 0 : 1;
            myPosition = myPosition.add( currentNanoTime*2 * movingState * (obsClear), 0);
        }
        myPosition = currentTerrain.clampPoint(myPosition.add(mySpeed.multiply(currentNanoTime)), mySize);
    }



    public void jump() {
        if (jumpingState == 0) {
            mySpeed = new Point2D(mySpeed.getX(), -10);
            jumpingState = 2;
        } else if (!doubleJumped) {
            mySpeed = new Point2D(mySpeed.getX(), -10);
            doubleJumped = true;
        }
    }

    private boolean terrainUnderFoot(TerrainMap currentTerrain) {
        for (int x = (int) (myPosition.getX() - mySize.getX()); x < myPosition.getX() + mySize.getX(); x += 4) {
            if (currentTerrain.getTerrainValCollision(x, myPosition.getY() + mySize.getY()) != 0) {
                return true;
            }
            if (currentTerrain.getTerrainValCollision(x , myPosition.getY() + mySize.getY()-3) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean terrainIsObstacleRight(TerrainMap currentTerrain) {
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            if (currentTerrain.getTerrainValCollision(myPosition.getX() + mySize.getX()+3, y) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean terrainIsObstacleLeft(TerrainMap currentTerrain) {
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            if (currentTerrain.getTerrainValCollision(myPosition.getX() - mySize.getX()-3, y) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean terrainIsObstacleOverhead(TerrainMap currentTerrain) {
        for (int x = (int) (myPosition.getX() - mySize.getX()); x < myPosition.getX() + mySize.getX(); x += 4) {
            if (currentTerrain.getTerrainValCollision(x, myPosition.getY() - mySize.getY()) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean playerTrimmingTerrain(TerrainMap currentTerrain) {
        for (int x = (int) (myPosition.getX() - mySize.getX()); x < myPosition.getX() + mySize.getX(); x += 4) {
            if (currentTerrain.getTerrainValCollision(x, myPosition.getY() + mySize.getY() - 1) != 0) {
                return true;
            }
        }
        return false;
    }

    public double getHp(){
        return PV/maxPv;
    }
    public double getPV(){
        return PV;
    }
    public double getMaxPv(){
        return maxPv;
    }
}