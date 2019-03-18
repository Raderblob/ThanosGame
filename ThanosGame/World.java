package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class World {
    PlayerClass thanos;
    TerrainMap terrain;
    double counter = 0;

    public World(int worldType, PlayerClass p) {
        thanos = p;

        switch (worldType) {
            case 0:
                terrain = new TerrainMap(30);
                terrain.createRender();
                break;
            default:

                break;
        }
    }

    public void runWorld(double currentNanoTime) {
        thanos.run(terrain, this,currentNanoTime);//run physics for the player

        //run physics for AI
    }

    public void renderWorld(GraphicsContext gc) {
        terrain.draw(gc, new Point2D((float) thanos.getCameraPosition().getX(), 0f));//draw terrain
        thanos.draw(gc);//draw the player
        counter += 0.5;
    }
}




