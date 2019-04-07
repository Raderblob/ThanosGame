package ThanosGame.enemies;

import ThanosGame.*;
import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.Weapon;
import ThanosGame.weapons.projectiles.FXEffect;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class Personnage extends PlayerClass {
    public boolean turned;
    Weapon myGun;
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

    Personnage(Point2D pos, TerrainMap myTerrain, World myWorld, int hp, Image mySprite) {
        turned = false;

        if (Main.numberGenerator.nextInt(10) < 4) {
            myState = AiState.STATIC;
        } else {
            patrolMe();
        }

        this.myTerrain = myTerrain;
        player = myWorld.thanos;
        myPosition = pos;
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(0, 0);

        this.PV = hp * myWorld.getDifficulty();
        myAnimation = new AnimatedPerson(mySprite, new Point2D(322, 400), 64);


        maxPv = PV;

    }

    public static Personnage getEnemy(Point2D pos, TerrainMap myTerrain, World myWorld) {
        switch (myTerrain.myTerrainVersion) {
            case CITY:
                int rndGen = Main.numberGenerator.nextInt(10);
                if (rndGen < 1) {
                    return new Wakandan(pos, myTerrain, myWorld);
                } else {
                    return new Agent(pos, myTerrain, myWorld);
                }

            case COUNTRY:
                return new Wakandan(pos, myTerrain, myWorld);
            default:
                return new Wakandan(pos, myTerrain, myWorld);
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
        boolean shouldJump = (myState != AiState.ATTACK || player.myPosition.getY() - myPosition.getY() < 0);
        if (obsClear == 0 || (obstacleAtDistance(dir * TERRAINRANGE) && shouldJump)) {
            jump();
        }
    }


    @Override
    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        if (myPosition.getX() > getCameraPosition().getX() - 500 && myPosition.getX() < getCameraPosition().getX() + Game.winParam.getX() + 1000) {
            myTarget = player.myPosition;
            for (FXEffect fx : currentWorld.worldExplosions) {
                if (fx.myType() == 1) {
                    if (Main.getMagnitudeSquared(fx.position.add(myPosition.multiply(-1))) < Math.pow(SIGHTRANGE, 2)) {
                        myTarget = fx.position;
                    }
                }
            }

            Point2D pDir = myTarget.add(myPosition.multiply(-1));
            double playerDistance = pDir.distance(0, 0);
            Point2D pDirN = pDir.multiply(1 / playerDistance);

            if (myState != AiState.STUNNED && myState != AiState.ATTACK) {
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

                    if (Main.numberGenerator.nextInt(1000) < 80) {
                        changeMind = true;
                    }

                    if (myTarget.getY() - myPosition.getY() < 0) {
                        if (Main.numberGenerator.nextInt(1000) < 50) {
                            jump();
                        }
                    }
                    if (changeMind) {
                        if (pDir.getX() > 0) {
                            movingState = Main.numberGenerator.nextInt(7) - 1;
                        } else {
                            movingState = Main.numberGenerator.nextInt(7) - 6;
                        }
                        if (movingState != 0) {
                            movingState = movingState / Math.abs(movingState);
                        }
                        changeMind = false;
                    }
                    moveDir(movingState);

                    if (playerDistance > SIGHTRANGE * 10) {
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
                    movingState = 0;
                    if (System.currentTimeMillis() - timeKeeper > timeLapse) {
                        myState = AiState.ATTACK;
                    }
                    break;
            }
            super.run(currentTerrain, currentWorld, currentNanoTime);
        }

    }

    public void patrolMe() {
        myState = AiState.PATROL;
        timeKeeper = System.currentTimeMillis();
        timeLapse = Main.numberGenerator.nextInt(1000) + 500;
    }

    public void stunMe(int power) {
        myState = AiState.STUNNED;
        timeKeeper = System.currentTimeMillis();
        timeLapse = Main.numberGenerator.nextInt(power * 50) + 500;
    }

    private Point2D playerDirection() {
        return player.myPosition.add(myPosition.multiply(-1)).normalize();
    }


    private boolean obstacleAtDistance(int atDistance) {
        for (int y = (int) (myPosition.getY() - mySize.getY() * 2); y < myPosition.getY() - mySize.getY(); y += 4) {
            if (myTerrain.getTerrainValCollision(Math.max(myPosition.getX() + atDistance, 0), Math.max(y, 0)) != 0) {
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
        STATIC, PATROL, ATTACK, STUNNED;
    }
}
