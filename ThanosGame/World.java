package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.Explosion;
import ThanosGame.weapons.Projectile;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;
import java.util.LinkedList;

public class World {
    public PlayerClass thanos;
    private TerrainMap terrain;
    private LinkedList<Explosion> worldExplosions;
    private LinkedList<Projectile> worldProjectiles;

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
            cProjectile.collisionWorld(this,terrain); //run collisions for projectiles
        }
        for (Explosion cExplosion : worldExplosions) {
            cExplosion.runExplosion(this); //run explosion logic (possible repulsion and animation)
        }

        Iterator<Projectile> projs = worldProjectiles.iterator(); //dispose of old projectiles and explosions
        while(projs.hasNext()){
            Projectile p = projs.next();
            if(p.mylife ==0){
                worldProjectiles.remove(p);
            }
        }
        Iterator<Explosion> expls = worldExplosions.iterator();
        while(expls.hasNext()){
            Explosion e = expls.next();
            if(e.mylife ==0){
                worldExplosions.remove(e);
            }
        }

        //run physics for AI
    }

    public void renderWorld(GraphicsContext gc) {
        terrain.draw(gc, new Point2D((float) thanos.getCameraPosition().getX(), 0f));//draw terrain
        thanos.draw(gc);//draw the player
        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.renderMe(gc);
        }
        for (Explosion cExplosion : worldExplosions) {
            cExplosion.renderMe(gc);
        }
    }
}




