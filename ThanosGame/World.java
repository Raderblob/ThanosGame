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
    public LinkedList<Projectile> worldProjectiles;

    public World(int worldType, PlayerClass p) {
        thanos = p;
        worldProjectiles = new LinkedList<>();
        worldExplosions = new LinkedList<>();
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
        thanos.run(terrain, this, currentNanoTime);//run physics for the player

        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.runLogic(this,terrain,currentNanoTime); //run collisions for projectiles
        }
        for (Explosion cExplosion : worldExplosions) {
            cExplosion.runExplosion(this); //run explosion logic (possible repulsion and animation)
        }

        for (Projectile p : worldProjectiles) {
            if (p.mylife == 0) {
                worldProjectiles.remove(p);
            }
        }
        for (Explosion e : worldExplosions) {
            if (e.mylife == 0) {
                worldExplosions.remove(e);
            }
        }

        //run physics for AI
    }

    public void renderWorld(GraphicsContext gc) {
        terrain.draw(gc, new Point2D((float) thanos.getCameraPosition().getX(), 0f));//draw terrain
        thanos.draw(gc);//draw the player
        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.renderMe(gc,thanos.getCameraPosition());
        }
        for (Explosion cExplosion : worldExplosions) {
            cExplosion.renderMe(gc,thanos.getCameraPosition());
        }
    }
}




