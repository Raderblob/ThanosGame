package ThanosGame;
import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import javax.swing.ImageIcon;

public class Personnages {
    private final AnimatedPerson myAnimation;
    private final AnimatedPerson myAnimation2;
    public int compt; // nb de pas du personnage
    public Point2D myPosition;
    private int movingState;
    public Point2D mySize;
    public Point2D mySpeed;

    // CONSTRUCTEUR
    public Personnages(int x, int y) {

        myPosition = new Point2D(x, y);
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(4, 4);
        this.compt=0;
        myAnimation = new AnimatedPerson(ImagesSaves.ShieldSprites, new Point2D(400, 330), 64);
        myAnimation2 = new AnimatedPerson(ImagesSaves.WakandaisSprites, new Point2D(400, 330), 64);
    }
    //GETTERS


    public int getMovingState() { return movingState; }
    public Point2D getMySize() { return mySize; }
    public AnimatedPerson getMyAnimation() { return myAnimation; }
    public AnimatedPerson getMyAnimation2() { return myAnimation2; }
    public int getCompt() { return compt; }
    public Point2D getMyPosition() { return myPosition; }


    //SETTERS


    public void setMovingState(int movingState) { this.movingState = movingState; }
    public void setMySize(Point2D mySize) { this.mySize = mySize; }
    public void setMyPosition(Point2D myPosition) { this.myPosition = myPosition; }
    public void setCompt(int compt) { this.compt = compt; }

    // METHODES UTILISABLES
    // Deplacement du personnage en fontion du timer mis en place

    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        doPlayerMovement(currentTerrain, currentWorld,currentNanoTime);
        myAnimation.setWalkingMode(movingState);
    }

    public void draw(GraphicsContext gc) {
        myAnimation.draw(gc, new Point2D(myPosition.getX() - mySize.getX() - getCameraPosition().getX(), myPosition.getY() - mySize.getY() - getCameraPosition().getY()), new Point2D(mySize.getX() * 2, mySize.getY() * 2));
    }

    public Point2D getCameraPosition() {
        return new Point2D(Math.max(myPosition.getX() - 200, 0), 0);
    }


    private void doPlayerMovement(TerrainMap currentTerrain, World currentWorld,double currentNanoTime) {
        boolean tUnderFoot = terrainUnderFoot(currentTerrain);
        mySpeed = mySpeed.add(0, currentNanoTime * 0.5 * (tUnderFoot ? 0 : 1));


        if (tUnderFoot) {
            // Alors faire demi-tour
        }

        if (playerTrimmingTerrain(currentTerrain)) {
            myPosition = myPosition.add(0, -4);
        }

        if (movingState > 0) {
            myPosition = myPosition.add( currentNanoTime*2 * movingState * (terrainIsObstacleRight(currentTerrain) ? 0 : 1), 0);
        } else if (movingState < 0) {
            myPosition = myPosition.add( currentNanoTime*2 * movingState * (terrainIsObstacleLeft(currentTerrain) ? 0 : 1), 0);
        }
        myPosition = currentTerrain.clampPoint(myPosition.add(mySpeed.multiply(currentNanoTime)), mySize);

    }



    private boolean terrainUnderFoot(TerrainMap currentTerrain) {
        for (int x = (int) (myPosition.getX() - mySize.getX()); x < myPosition.getX() + mySize.getX(); x += 4) {
            if (currentTerrain.getTerrainVal(x, myPosition.getY() + mySize.getY()) != 0) {
                return true;
            }
            if (currentTerrain.getTerrainVal(x , myPosition.getY() + mySize.getY()-3) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean terrainIsObstacleRight(TerrainMap currentTerrain) {
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            if (currentTerrain.getTerrainVal(myPosition.getX() + mySize.getX()+3, y) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean terrainIsObstacleLeft(TerrainMap currentTerrain) {
        for (int y = (int) (myPosition.getY() - mySize.getY()); y < myPosition.getY(); y += 4) {
            if (currentTerrain.getTerrainVal(myPosition.getX() - mySize.getX()-3, y) != 0) {
                return true;
            }
        }
        return false;
    }


    private boolean playerTrimmingTerrain(TerrainMap currentTerrain) {
        for (int x = (int) (myPosition.getX() - mySize.getX()); x < myPosition.getX() + mySize.getX(); x += 4) {
            if (currentTerrain.getTerrainVal(x, myPosition.getY() + mySize.getY() - 1) != 0) {
                return true;
            }
        }
        return false;
    }

}
