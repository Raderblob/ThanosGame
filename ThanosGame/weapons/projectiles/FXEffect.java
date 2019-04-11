package ThanosGame.weapons.projectiles;

import ThanosGame.Item;
import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import resources.ImagesSaves;

public class FXEffect extends Item {
    final double explosionRadius;
    final Point2D[] explosionPoints;
    private final double maxLife;
    int type;

    public FXEffect(Point2D maPosition, Point2D Size,double myL,TerrainMap mTerrain) {
        super(maPosition, Size,ImagesSaves.explosions[0],new Point2D(15,14),100);
        myAnimation.setMyMode(1);
        mylife = myL;
        maxLife = myL;
        explosionRadius = mySize.getX();
        explosionPoints = mTerrain.getCircleOfPoints(position,explosionRadius);
        type = 0;
    }

    public void runExplosion(World myWorld, TerrainMap terrain, double nanoTime){
        mylife -= nanoTime;
    }

    double getScale(){
        return 1- (mylife/maxLife);
    }

    public int myType(){
        return  type;
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
