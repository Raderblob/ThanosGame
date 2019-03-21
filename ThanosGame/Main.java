package ThanosGame;

import ThanosGame.Menus.MenuPrincipal;
import ThanosGame.terrain.buildings.BuildingSaves;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Transform;

import java.util.Random;

public class Main {
    public static Random numberGenerator;

    public static void main(String[] args) {
        numberGenerator = new Random(System.nanoTime());
        BuildingSaves.loadBuildings();
        MenuPrincipal mn = new MenuPrincipal();
        //  ThanosGame.Game.launchGame();


    }

    public static double getMagnitudeSquared(Point2D p) {
        return getMagnitudeSquared(p.getX(), p.getY());
    }

    public static double getMagnitudeSquared(double x, double y) {
        return (Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static void applyMatrixTransform(GraphicsContext gc, Transform tr){
        gc.setTransform(tr.getMxx(),tr.getMyx(),tr.getMxy(),tr.getMyy(),tr.getTx(),tr.getTy());
    }
}
