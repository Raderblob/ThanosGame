package ThanosGame;

import ThanosGame.menus.MenuPrincipal;
import ThanosGame.terrain.PixelBlockType;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Transform;
import resources.BuildingSaves;

import java.util.Random;

public class Main {
    public static Random numberGenerator;
    public static MenuPrincipal mainMenu;

    public static void main(String[] args) {
        numberGenerator = new Random(System.nanoTime());
        BuildingSaves.loadBuildings();


        Game.launchGame();


    }

    public static double getMagnitudeSquared(Point2D p) {
        return getMagnitudeSquared(p.getX(), p.getY());
    }

    public static double getMagnitudeSquared(double x, double y) {
        return (Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static void applyMatrixTransform(GraphicsContext gc, Transform tr) {
        gc.setTransform(tr.getMxx(), tr.getMyx(), tr.getMxy(), tr.getMyy(), tr.getTx(), tr.getTy());
    }

    public static boolean canDamage(byte terrainByte, double damageDone) {
        if (terrainByte == PixelBlockType.NOTHING.getMyVal()) {
            return true;
        } else if (terrainByte == PixelBlockType.BEDROCK.getMyVal()) {
            return false;
        } else if (terrainByte == PixelBlockType.GRASS.getMyVal() || terrainByte == PixelBlockType.DIRT.getMyVal()) {
            return damageDone > 1;
        } else if (terrainByte == PixelBlockType.STONE.getMyVal()) {
            return damageDone > 8;
        } else if (terrainByte == PixelBlockType.BRICK.getMyVal()) {
            return damageDone > 12;
        } else if (terrainByte == PixelBlockType.UNDEFINED3.getMyVal()) {
            return damageDone > 20;
        } else if (terrainByte == PixelBlockType.BRICK3.getMyVal()) {
            return damageDone > 15;
        }


        return false;
    }
}
