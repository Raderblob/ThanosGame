package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.Explosion;
import ThanosGame.weapons.Projectile;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class World {
    public PlayerClass thanos;
    private TerrainMap terrain;
    public LinkedList<Explosion> worldExplosions;
    public LinkedList<Projectile> worldProjectiles;

    public World(int worldType, PlayerClass p) {
        thanos = p;
        worldProjectiles = new LinkedList<>();
        worldExplosions = new LinkedList<>();
        switch (worldType) {
            case 0:
                terrain = new TerrainMap(30);
                break;
            default:

                break;
        }
    }

    public void runWorld(double currentNanoTime) {
        thanos.run(terrain, this, currentNanoTime);//run physics for the player
        LinkedList<Projectile> pToRemove = new LinkedList<>();
        LinkedList<Explosion> eToRemove = new LinkedList<>();
        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.runLogic(this, terrain, currentNanoTime); //run collisions for projectiles
            if (cProjectile.mylife <= 0) {
                pToRemove.add(cProjectile);
            }
        }
        for (Explosion cExplosion : worldExplosions) {
            cExplosion.runExplosion(this, terrain, currentNanoTime); //run explosion logic (possible repulsion and animation)
            if (cExplosion.mylife <= 0) {
                eToRemove.add(cExplosion);
            }
        }

        for (Projectile p : pToRemove) {
            worldExplosions.add(new Explosion(p.position, new Point2D(10, 10), 10, p.degats, terrain));
            worldProjectiles.remove(p);
        }
        for (Explosion e : eToRemove) {
            worldExplosions.remove(e);
        }

        //run physics for AI
    }

    public void renderWorld(GraphicsContext gc, Group root) {
        terrain.draw(gc, new Point2D((float) thanos.getCameraPosition().getX(), 0f), root);//draw terrain
        thanos.draw(gc);//draw the player
        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.renderMe(gc, thanos.getCameraPosition());
        }
        for (Explosion cExplosion : worldExplosions) {
            cExplosion.renderMe(gc, thanos.getCameraPosition());
        }
    }
}




