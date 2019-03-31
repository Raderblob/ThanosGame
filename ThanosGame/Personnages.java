package ThanosGame;

import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;


public class Personnages extends PlayerClass {
    private Thanos player;
    public Personnages(Point2D pos,Thanos thanos) {
        player = thanos;
        myPosition = pos;
        mySize = new Point2D(10, 10);
        mySpeed = new Point2D(0, 0);
        myAnimation = new AnimatedPerson(ImagesSaves.wakandaisSprites, new Point2D(335, 430), 64);
    }

    @Override
    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        movingState = 1;
        super.run(currentTerrain, currentWorld, currentNanoTime);
    }

    @Override
    public Point2D getCameraPosition() {
        return player.getCameraPosition();
    }
}
