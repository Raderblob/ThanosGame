package ThanosGame.weapons;

import ThanosGame.Item;
import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

public class FXEffect extends Item {
    protected double explosionRadius;
    protected Point2D[] explosionPoints;
    private double maxLife;

    public FXEffect(Point2D maPosition, Point2D Size,double myL,TerrainMap mTerrain) {
        super(maPosition, Size,ImagesSaves.explosions[0],new Point2D(15,14),100);
        myAnimation.setMyMode(1);
        mylife = myL;
        maxLife = myL;
        explosionRadius = mySize.getX();
        explosionPoints = mTerrain.getCircleOfPoints(position,explosionRadius);
    }

    public void runExplosion(World myWorld, TerrainMap terrain, double nanoTime){
        mylife -= nanoTime;
    }

    protected double getScale(){
        return 1- (mylife/maxLife);
    }

    public void renderMe(GraphicsContext gc, Point2D camPos) {
        gc.save();
        double myScale =getScale();
        Scale nS = new Scale(myScale,myScale,position.getX()-camPos.getX(), position.getY()-camPos.getY());
        Rotate nR = new Rotate(Math.random()*360,position.getX()-camPos.getX(),position.getY()-camPos.getY());
        Affine nA = new Affine(nS);
        nA.append(nR);
        Main.applyMatrixTransform(gc, nA);
        gc.setGlobalAlpha(mylife/maxLife);
        super.renderMe(gc,camPos);
        gc.restore();

    }
}
