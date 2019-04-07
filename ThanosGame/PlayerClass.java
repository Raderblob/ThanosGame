package ThanosGame;

import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import resources.ImagesSaves;

import static ThanosGame.terrain.PixelBlockType.SPIKES;

public class PlayerClass {
    protected double PV;
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
    protected int obsClear;
    protected int recoverTime;
    protected long lastHit;

    public PlayerClass() {
        recoverTime = 0;

        myPosition = new Point2D(50, 50);
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(0, 0);
        destroyAt = new Point2D(-1, -1);


        myAnimation = new AnimatedPerson(ImagesSaves.thanosSprites, new Point2D(400, 330), 64);
    }

    public void removeHp(double amount) {

        double toRemove = amount;
        if (myShield > 0) {
            toRemove -= myShield;
            myShield -= amount;
        }
        if (!recovering() && toRemove > 0) {
            PV -= toRemove;
            lastHit = System.currentTimeMillis();
        }
    }

    public void addHp(int amount) {
        if (PV + amount >= maxPv) {
            PV = maxPv;
        } else {
            PV += amount;
        }
    }

    public void draw(GraphicsContext gc) {
        if (!recovering() || Main.numberGenerator.nextInt(10) < 5) {
            myAnimation.draw(gc, new Point2D(myPosition.getX() - mySize.getX() - getCameraPosition().getX(), myPosition.getY() - mySize.getY() - getCameraPosition().getY()), new Point2D(mySize.getX() * 2, mySize.getY() * 2));
        }
    }

    private boolean recovering() {
        return System.currentTimeMillis() - lastHit < recoverTime;
    }

    public void fireAt(double fX, double fY) {
        destroyAt = new Point2D(fX, fY).add(getCameraPosition());
    }


    public Point2D getCameraPosition() {
        return new Point2D(Math.max(myPosition.getX() - 200, 0), 0);
    }

    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        if (myShield > 0) {
            myShield -= currentNanoTime * 0.04;
        }
        myShield = Math.min(Math.max(myShield, 0), maxPv);
        doPlayerMovement(currentTerrain, currentWorld, currentNanoTime);
        myAnimation.setWalkingMode(movingState);
    }

    private void doPlayerMovement(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        boolean tUnderFoot = terrainUnderFoot(currentTerrain);
        boolean tooLow = playerTrimmingTerrain(currentTerrain);
        boolean tooHigh = terrainIsObstacleOverhead(currentTerrain);
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


        if (tooLow) {
            myPosition = myPosition.add(0, -4);
        } else if (tooHigh || myPosition.getY() - mySize.getY() <= 0) {
            mySpeed = new Point2D(mySpeed.getX(), 0);
            myPosition = myPosition.add(0, 4);
        }

        if (movingState > 0) {
            obsClear = terrainIsObstacleRight(currentTerrain) ? 0 : 1;
            myPosition = myPosition.add(currentNanoTime * 2 * movingState * (obsClear), 0);
        } else if (movingState < 0) {
            obsClear = terrainIsObstacleLeft(currentTerrain) ? 0 : 1;
            myPosition = myPosition.add(currentNanoTime * 2 * movingState * (obsClear), 0);
        }
        myPosition = currentTerrain.clampPoint(myPosition.add(mySpeed.multiply(currentNanoTime)), mySize.multiply(1.2));
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
        return whatTerrainUnderFoot(currentTerrain)!=0;
    }
    private byte whatTerrainUnderFoot(TerrainMap currentTerrain){
        byte terr;
        for (int x = (int) (myPosition.getX() - mySize.getX()); x < myPosition.getX() + mySize.getX(); x += 4) {
            terr = currentTerrain.getTerrainValCollision(x, myPosition.getY() + mySize.getY());
            if (terr != 0) {
                return terr;
            }
            terr = currentTerrain.getTerrainValCollision(x, myPosition.getY() + mySize.getY() - 3);
            if ( terr!= 0) {
                return terr;
            }
        }
        return 0;
    }

    protected boolean dangerBlockPresent(TerrainMap currentTerrain) {

       if(whatTerrainIsObstacleLeft(currentTerrain)==SPIKES.getMyVal()||whatTerrainIsObstacleRight(currentTerrain)==SPIKES.getMyVal()||whatTerrainUnderFoot(currentTerrain)==SPIKES.getMyVal()){
           return true;
       }


        return false;
    }

    private boolean terrainIsObstacleRight(TerrainMap currentTerrain) {
        return whatTerrainIsObstacleRight(currentTerrain)!=0;
    }

    private byte whatTerrainIsObstacleRight(TerrainMap currentTerrain){
        byte terr;
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            terr = currentTerrain.getTerrainValCollision(myPosition.getX() + mySize.getX() + 3, y);
            if (terr != 0) {
                return terr;
            }
        }
        return 0;
    }
    private byte whatTerrainIsObstacleLeft(TerrainMap currentTerrain){
        byte terr;
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            terr = currentTerrain.getTerrainValCollision(myPosition.getX() - mySize.getX() - 3, y);
            if (terr != 0) {
                return terr;
            }
        }
        return 0;
    }

    private boolean terrainIsObstacleLeft(TerrainMap currentTerrain) {
       return whatTerrainIsObstacleLeft(currentTerrain)!=0;
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

    public double getHp() {
        return PV / maxPv;
    }

    public double getPV() {
        return PV;
    }

    public double getMaxPv() {
        return maxPv;
    }

    public boolean pointOnScreen(Point2D p) {
        return p.getX() > getCameraPosition().getX() && p.getY() < getCameraPosition().getX() + Game.winParam.getX();
    }
}