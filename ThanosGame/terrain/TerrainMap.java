package ThanosGame.terrain;

import ThanosGame.Game;
import ThanosGame.Main;
import ThanosGame.graphics.images.PixelBlockType;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TerrainMap {
    private TerrainChunck chunk[];
    private int numChunks;
    private int maxPixelsX;
    private int maxPixelsY;
    public boolean terrainRendered;

    public TerrainMap(int numC) {
        numChunks = numC;
        maxPixelsX = (int) (TerrainChunck.chunkParam.getX() * 4 * (numChunks + 1) - 1);
        maxPixelsY = (int) (TerrainChunck.chunkParam.getY() * 4 - 1);
        terrainRendered = false;
        SimplexNoise geny = new SimplexNoise((int) System.nanoTime(), 8, 0.0035, 0.5);
        SimplexNoise biomeGeny = new SimplexNoise((int) System.nanoTime() + 1, 8, 0.0035, 0.5);


        chunk = new TerrainChunck[numChunks];
        for (int i = 0; i < chunk.length; i++) {
            chunk[i] = new TerrainChunck(i);
        }


        for (int x = 0; x < (int) TerrainChunck.chunkParam.getX() * chunk.length; x++) {
            for (int y = 0; y < (int) TerrainChunck.chunkParam.getY(); y++) {
                double a = Math.min(1, Math.abs(geny.getNoise(x, y)) * Math.pow(y / TerrainChunck.chunkParam.getY() + 0.6, 10));
                double b = Math.abs(geny.getNoise(x, y));
                if (y > TerrainChunck.chunkParam.getY() - 4) {
                    setTerrainVal(x, y, PixelBlockType.BEDROCK.getMyVal());//bedrock
                } else if (a > 0.35) {//dirt stone

                    if (b > 0.3) {
                        setTerrainVal(x, y, PixelBlockType.DIRT.getMyVal());
                    } else {
                        setTerrainVal(x, y, PixelBlockType.STONE.getMyVal());
                    }

                }else if(a>0.29){
                    if (b > 0.1) {
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
        }


        for (
                int i = 400; i < (int) TerrainChunck.chunkParam.getX() * chunk.length * 4 - 1000; i += 700)

        {//generate buildings
            if (Main.numberGenerator.nextInt(100) > 50) {
                int y = 0;
                int x = Main.numberGenerator.nextInt(200) + i;
                do {
                    y += 1;
                } while (getTerrainVal(x, y) == 0);
                new Building(new Point2D(x, y)).changeTerrain(this);
            }
        }


    }


    public void createRender() {
        if (terrainRendered == false) {
            genTerrainImage();
            terrainRendered = true;
        }
    }

    public void disposeRender() {
        if (terrainRendered == true) {
            for (int i = 0; i < chunk.length; i++) {
                chunk[i].disposeTerrainImage();
            }
        }
    }

    public void draw(GraphicsContext gc, Point2D pos) {
        for (int i = 0; i < chunk.length; i++) {
            double drawPos = -Math.min(pos.getX(), numChunks * TerrainChunck.chunkParam.getX() * 4 - 1000) + i * TerrainChunck.chunkParam.getX() * 4;
            if (drawPos + TerrainChunck.chunkParam.getX() * 4 >= 0 && drawPos < Game.winParam.getX())
                gc.drawImage(chunk[i].imageToDraw, drawPos, 0);
        }
    }

    private void setTerrainVal(int x, int y, byte val) {
        chunk[x / (int) TerrainChunck.chunkParam.getX()].setVal(x, y, val);
    }

    public byte getTerrainVal(double x, double y) {
        int nX, nY;
        nX = (int) (x / 4);
        nY = (int) (y / 4);
        return chunk[nX / (int) TerrainChunck.chunkParam.getX()].getVal(nX, nY);
    }


    public void changeTerrain(Point2D pointToChange[], byte futurVals[], boolean redrawTerrain) {
        Point2D pTC[] = new Point2D[pointToChange.length];
        for (int i = 0; i < pointToChange.length; i++) {
            pTC[i] = pointToChange[i].multiply(0.25);
            setTerrainVal((int) pTC[i].getX(), (int) pTC[i].getY(), futurVals[i]);
        }

        if (redrawTerrain) {
            int indice = (int) pTC[0].getX() / (int) TerrainChunck.chunkParam.getX();
            chunk[indice].genTerrainImage();
            if (indice - 1 > -1) {
                chunk[indice - 1].genTerrainImage();
            }
            if (indice + 1 < chunk.length) {
                chunk[indice + 1].genTerrainImage();
            }
        }
    }

    private void genTerrainImage() {
        for (int i = 0; i < chunk.length; i++) {
            chunk[i].genTerrainImage();
        }
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
    private int chunkNum;
    private byte terrain[][];
    private Graphics terrainDrawer;
    public javafx.scene.image.Image imageToDraw;

    public TerrainChunck(int cN) {
        chunkNum = cN;
        terrain = new byte[(int) chunkParam.getX()][(int) chunkParam.getY()];
    }

    public void setVal(int x, int y, byte val) {
        try {
            terrain[x % (int) chunkParam.getX()][y] = val;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public byte getVal(int x, int y) {
        return terrain[x % (int) chunkParam.getX()][y];
    }

    public void genTerrainImage() {
        BufferedImage terrainImage;
        terrainImage = new BufferedImage((int) chunkParam.getX() * 4, (int) chunkParam.getY() * 4, BufferedImage.TYPE_3BYTE_BGR);
        terrainDrawer = terrainImage.getGraphics();
        for (int x = 0; x < terrainImage.getWidth(); x += 4) {
            for (int y = 0; y < terrainImage.getHeight(); y += 4) {
                byte a = getTVal(x, y);
                if (a == PixelBlockType.DIRT.getMyVal()) {
                    terrainDrawer.setColor(new java.awt.Color(101+(int)(Math.random()*50-25), 67+(int)(Math.random()*50-25), 33+(int)(Math.random()*50-25)));
                } else if (a ==PixelBlockType.GRASS.getMyVal()) {
                    terrainDrawer.setColor(new java.awt.Color(0, (int) (Math.random() * 255), 0));
                } else if (a == PixelBlockType.BEDROCK.getMyVal()) {
                    terrainDrawer.setColor(new Color(29, 0, 29));
                } else if (a == PixelBlockType.STONE.getMyVal()) {
                    terrainDrawer.setColor(new Color(64 +(int)(Math.random()*50-25), 63+(int)(Math.random()*50-25), 64+(int)(Math.random()*50-25)));
                } else if (a == PixelBlockType.BRICK.getMyVal()) {
                    terrainDrawer.setColor(new java.awt.Color(0, 0, 0));
                } else if (a == PixelBlockType.BRICK2.getMyVal()) {
                    terrainDrawer.setColor(new java.awt.Color(0, 254, 0));
                } else {
                    terrainDrawer.setColor(java.awt.Color.blue);
                }

                terrainDrawer.fillRect(x, y, 4, 4);

            }
        }
        imageToDraw = SwingFXUtils.toFXImage(terrainImage, null);

    }

    public void disposeTerrainImage() {
        imageToDraw = null;
    }

    public byte getTVal(double x, double y) {
        return terrain[(int) (x / 4)][(int) (y / 4)];
    }

}