package ThanosGame.terrain;

import ThanosGame.Game;
import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.enemies.Personnage;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import resources.BuildingSaves;

import java.util.LinkedList;

public class TerrainMap {
    private TerrainChunck chunk[];
    private int numChunks;
    private int maxPixelsX;
    private int maxPixelsY;
    public boolean terrainRendered;
    public Point2D mySize;
    public TerrainVType myTerrainVersion;

    public TerrainMap(int numP, boolean withBuildings, World myWorld, LinkedList<Personnage> enemyList, TerrainVType terrainVersion) {
        myTerrainVersion = terrainVersion;
        mySize = new Point2D(10, 10);
        numChunks = (int) (numP / (TerrainChunck.chunkParam.getX() * 4)) + 1;
        maxPixelsX = (int) (TerrainChunck.chunkParam.getX() * 4 * (numChunks));
        maxPixelsY = (int) (TerrainChunck.chunkParam.getY() * 4 - 1);
        terrainRendered = false;
        SimplexNoise geny = new SimplexNoise((int) System.nanoTime(), 8, 0.0035, 0.5);
        SimplexNoise biomeGeny = new SimplexNoise((int) System.nanoTime() + 1, 8, 0.0035, 0.7);


        chunk = new TerrainChunck[numChunks];
        for (int i = 0; i < chunk.length; i++) {
            chunk[i] = new TerrainChunck();
        }

        if (withBuildings) {
            for (int x = 0; x < (int) TerrainChunck.chunkParam.getX() * chunk.length; x++) {
                for (int y = 0; y < (int) TerrainChunck.chunkParam.getY(); y++) {
                    double a = Math.min(1, Math.abs(geny.getNoise(x, y)) * Math.pow(y / TerrainChunck.chunkParam.getY() + 0.6, 10));
                    double b = Math.abs(biomeGeny.getNoise(x, y));
                    switch (terrainVersion) {
                        case COUNTRY:
                            generateCountry(a, b, x, y);
                            break;
                        case CITY:
                            generateCity(a, b, x, y);
                            break;
                    }

                }
            }

            int maxMap = (int) TerrainChunck.chunkParam.getX() * chunk.length * 4 - 1000;
            for (int i = 400; i < maxMap; i += 700) {//generate buildings
                if (Main.numberGenerator.nextInt(100) < terrainVersion.myVal) {
                    int y = 0;
                    int x = Main.numberGenerator.nextInt(200) + i;
                    do {
                        y += 1;
                    } while (getTerrainVal(x, y) == 0);
                    new Building(new Point2D(x, y), enemyList, myWorld, this).changeTerrain(this);
                }
            }

            for (int i = 100; i < maxMap; i += 500) {
                if (Main.numberGenerator.nextInt(maxMap) < i) {
                    int y = 50;
                    int x = Main.numberGenerator.nextInt(200) + i;

                    enemyList.add(Personnage.getEnemy(new Point2D(x, y), this, myWorld));
                }
            }


            myWorld.teleporters.add(new Teleporter(new Point2D(getEndPos() - 500, 0), 0, myWorld.myGame));
            new LargeBase(BuildingSaves.largeBases[0], new Point2D(getEndPos(), 0)).changeTerrain(this); //end of World
        }
    }

    public enum TerrainVType {
        COUNTRY(50), CITY(75);
        private int myVal;

        TerrainVType(int i) {
            myVal = i;
        }
    }

    public int getEndPos() {
        return (int) TerrainChunck.chunkParam.getX() * chunk.length * 4 - 1000;
    }

    private void generateCountry(double a, double b, int x, int y) {
        if (y > TerrainChunck.chunkParam.getY() - 4) {
            setTerrainVal(x, y, PixelBlockType.BEDROCK.getMyVal());//bedrock
        } else if (a > 0.38) {//dirt stone
            if (b < 0.2) {
                setTerrainVal(x, y, PixelBlockType.DIRT.getMyVal());
            } else {
                setTerrainVal(x, y, PixelBlockType.STONE.getMyVal());
            }
        } else if (a > 0.30) {
            if (b < 0.6) {
                setTerrainVal(x, y, PixelBlockType.DIRT.getMyVal());
            } else {
                setTerrainVal(x, y, PixelBlockType.STONE.getMyVal());
            }
        } else if (a > 0.25) {
            setTerrainVal(x, y, PixelBlockType.GRASS.getMyVal());//grass
        } else {
            setTerrainVal(x, y, PixelBlockType.NOTHING.getMyVal());
        }
    }

    private void generateCity(double a, double b, int x, int y) {
        if (y > TerrainChunck.chunkParam.getY() - 4) {
            setTerrainVal(x, y, PixelBlockType.BEDROCK.getMyVal());//bedrock
        } else if (y > TerrainChunck.chunkParam.getY() - 30) {
            if (b < 0.2) {
                setTerrainVal(x, y, PixelBlockType.DIRT.getMyVal());
            } else {
                setTerrainVal(x, y, PixelBlockType.STONE.getMyVal());
            }
        } else if (y > TerrainChunck.chunkParam.getY() - 34) {
            setTerrainVal(x, y, PixelBlockType.BRICK2.getMyVal());
        } else {
            setTerrainVal(x, y, PixelBlockType.NOTHING.getMyVal());
        }
    }


    public void draw(GraphicsContext gc, Point2D pos, Group root) {
        for (int i = 0; i < chunk.length; i++) {
            double drawPos = -pos.getX() + i * TerrainChunck.chunkParam.getX() * 4;// -Math.min(pos.getX(), numChunks * TerrainChunck.chunkParam.getX() * 4 - 1000) + i * TerrainChunck.chunkParam.getX() * 4;
            if (drawPos + TerrainChunck.chunkParam.getX() * 4 >= 0 && drawPos < Game.winParam.getX() * 2) {
                chunk[i].myCanvas.setTranslateX(drawPos);
                if (!chunk[i].addedToRoot) {
                    root.getChildren().add(chunk[i].myCanvas);
                    chunk[i].myCanvas.toBack();
                    chunk[i].addedToRoot = true;
                }
            } else {
                if (chunk[i].addedToRoot) {
                    root.getChildren().remove(chunk[i].myCanvas);
                    chunk[i].addedToRoot = false;
                }
            }
        }
    }

    public void removeCanvas(Group root) {
        for (int i = 0; i < chunk.length; i++) {
            if (chunk[i].addedToRoot) {
                root.getChildren().remove(chunk[i].myCanvas);
                chunk[i].addedToRoot = false;
            }
        }
    }

    private void setTerrainVal(int x, int y, byte val) {
        chunk[x / (int) TerrainChunck.chunkParam.getX()].setVal(x, y, val);
    }

    public byte getTerrainVal(double x, double y) {
        return (byte) Math.abs(getTerrainValRaw(x, y));
    }

    private byte getTerrainValRaw(double x, double y) {
        int nX, nY;
        nX = (int) (x / 4);
        nY = (int) (y / 4);
        return chunk[nX / (int) TerrainChunck.chunkParam.getX()].getVal(nX, nY);
    }

    public byte getTerrainValCollision(double x, double y) {
        return (byte) (Math.max(0, getTerrainValRaw(x, y)));
    }

    public byte getTerrainVal(Point2D p) {
        return getTerrainVal(p.getX(), p.getY());
    }

    public byte getTerrainValCollision(Point2D p) {
        return getTerrainValCollision(p.getX(), p.getY());
    }

    public void changeTerrain(Point2D pointToChange[], byte futurVals[]) {
        Point2D pTC[] = new Point2D[pointToChange.length];
        for (int i = 0; i < pointToChange.length; i++) {
            pTC[i] = pointToChange[i].multiply(0.25);
            setTerrainVal((int) pTC[i].getX(), (int) pTC[i].getY(), futurVals[i]);
        }


    }


    public Point2D[] getCircleOfPoints(Point2D circlePos, double circleRadius) {

        return getCircleOfPointsLinked(circlePos, circleRadius).toArray(new Point2D[0]);


    }

    public LinkedList<Point2D> getCircleOfPointsLinked(Point2D circlePos, double circleRadius) {

        LinkedList<Point2D> pTD = new LinkedList<>();

        for (int x = (int) -circleRadius; x < circleRadius; x++) {
            for (int y = (int) -circleRadius; y < +circleRadius; y++) {
                if (Main.getMagnitudeSquared(x, y) < Math.pow(circleRadius, 2)) {
                    pTD.add(clampPoint(new Point2D(x, y).add(circlePos)));
                }
            }
        }
        return pTD;


    }

    public Point2D getTangent(Point2D tPos, Point2D startPoint) {
        Point2D invertedStartPoint = startPoint.multiply(-1);
        double angle = Math.atan2(invertedStartPoint.getY(), invertedStartPoint.getX());
        Point2D testPoint;

        for (double a = angle; a < angle + Math.PI * 2; a += 0.1 / 180 * Math.PI) {
            testPoint = new Point2D(Math.cos(a), Math.sin(a));
            if (getTerrainValCollision(testPoint.multiply(10).add(tPos)) != 0) {
                return testPoint;
            }
        }

        return new Point2D(Math.cos(angle), Math.sin(angle));
    }

    public Point2D clampPoint(Point2D pIn) {
        return new Point2D(Math.min(Math.max(0, pIn.getX()), maxPixelsX), Math.min(Math.max(0, pIn.getY()), maxPixelsY));
    }

    public Point2D clampPoint(Point2D pIn, Point2D pSize) {
        Point2D delta = new Point2D(0, 0);
        if (pIn.getX() - pSize.getX() < 0) {
            delta = new Point2D(-pIn.getX() + pSize.getX(), 0);
        }
        if (pIn.getY() - pSize.getY() < 0) {
            delta = delta.add(0, -pIn.getY() + pSize.getY());
        }
        if (pIn.getX() + pSize.getX() > maxPixelsX) {
            delta = delta.add(maxPixelsX - pIn.getX() - pSize.getX(), 0);
        }
        if (pIn.getY() + pSize.getY() > maxPixelsY) {
            delta = delta.add(0, maxPixelsY - pIn.getY() - pSize.getY());
        }


        return pIn.add(delta);
    }


}

class TerrainChunck {
    final public static Point2D chunkParam = new Point2D(500, 125);
    private byte terrain[][];
    public Canvas myCanvas;
    public boolean addedToRoot = false;
    private GraphicsContext gc;

    public TerrainChunck() {
        terrain = new byte[(int) chunkParam.getX()][(int) chunkParam.getY()];
        myCanvas = new Canvas((int) chunkParam.getX() * 4 + 1, (int) chunkParam.getY() * 4);
        gc = myCanvas.getGraphicsContext2D();
    }

    public void setVal(int x, int y, byte val) {
        try {
            int nX = x % (int) chunkParam.getX();
            terrain[nX][y] = val;

            drawVal(nX * 4, y * 4, val);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private void drawVal(int x, int y, byte val) {
        if (val != 0) {
            gc.setFill(getTerrainColor(val));
            if (x < (int) chunkParam.getX() * 4 - 5) {
                gc.fillRect(x, y, 4, 4);
            } else {
                gc.fillRect(x, y, 5, 4);
            }
        } else {
            if (x < (int) chunkParam.getX() * 4 - 5) {
                gc.clearRect(x + 1, y + 1, 3, 3);
            } else {
                gc.clearRect(x + 1, y + 1, 4, 3);
            }

        }
    }

    public byte getVal(int x, int y) {
        return terrain[x % (int) chunkParam.getX()][y];
    }


    private Color getTerrainColor(byte aR) {
        byte a = (byte) Math.abs(aR);

        javafx.scene.paint.Color c;
        if (a == PixelBlockType.DIRT.getMyVal()) {
            c = new javafx.scene.paint.Color((101 + (int) (Math.random() * 50 - 25)) / 254d, (67 + (int) (Math.random() * 50 - 25)) / 254d, (33 + (int) (Math.random() * 50 - 25)) / 254d, 1);
        } else if (a == PixelBlockType.GRASS.getMyVal()) {
            c = new javafx.scene.paint.Color(0, Math.random(), 0, 1);
        } else if (a == PixelBlockType.BEDROCK.getMyVal()) {
            c = new javafx.scene.paint.Color(29 / 254d, 0, 29 / 254d, 1);
        } else if (a == PixelBlockType.STONE.getMyVal()) {
            c = new javafx.scene.paint.Color((64 + (int) (Math.random() * 50 - 25)) / 254d, (63 + (int) (Math.random() * 50 - 25)) / 254d, (64 + (int) (Math.random() * 50 - 25)) / 254d, 1);
        } else if (a == PixelBlockType.BRICK.getMyVal()) {
            c = new javafx.scene.paint.Color(0, 0, 0, 1);
        } else if (a == PixelBlockType.BRICK2.getMyVal()) {
            c = new javafx.scene.paint.Color(0.2, 0.2, 0.2, 1);
        } else if (a == PixelBlockType.BRICK3.getMyVal()) {
            c = new javafx.scene.paint.Color(0.5, 0, 0, 1);
        } else if (a == PixelBlockType.UNDEFINED1.getMyVal()) {
            c = new javafx.scene.paint.Color(1, 1, 0, 1);
        } else if (a == PixelBlockType.UNDEFINED2.getMyVal()) {
            c = new javafx.scene.paint.Color(0.5, 0, 0.5, 1);
        } else if (a == PixelBlockType.UNDEFINED3.getMyVal()) {
            c = new javafx.scene.paint.Color(1, 0, 0, 1);
        } else {
            c = new Color(1, 1, 1, 0);
        }
        if (aR < 0) {
            c = c.desaturate().darker();
            c = new Color(c.getRed() + 0.1, c.getGreen() + 0.1, c.getBlue() + 0.1, 1);
        }
        return c;
    }


    public byte getTVal(double x, double y) {
        return terrain[(int) (x / 4)][(int) (y / 4)];
    }

}