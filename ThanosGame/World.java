package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.Explosion;
import ThanosGame.weapons.Projectile;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class World {
    public PlayerClass thanos;
    private TerrainMap terrain;
    private LinkedList<Explosion> worldExplosions;
    private LinkedList<Projectile> worldProjectiles;
    double counter = 0;

    public World(int worldType, PlayerClass p) {
        thanos = p;
        worldProjectiles=new LinkedList<>();
        worldExplosions= new LinkedList<>();
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

        for(Projectile cProjectile:worldProjectiles){

        }
        for(Explosion cExplosion:worldExplosions){

        }

        //run physics for AI
    }

    public void renderWorld(GraphicsContext gc) {
        terrain.draw(gc, new Point2D((float) thanos.getCameraPosition().getX(), 0f));//draw terrain
        thanos.draw(gc);//draw the player
        counter += 0.5;
    }
}




