package ThanosGame;

import ThanosGame.terrain.LargeBase;
import ThanosGame.terrain.Teleporter;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.Explosion;
import ThanosGame.weapons.FXEffect;
import ThanosGame.weapons.Projectile;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import resources.AudioSaves;
import resources.BuildingSaves;

import java.util.LinkedList;

public class World {
    public Game myGame;
    public Thanos thanos;
    private TerrainMap terrain;
    public  LinkedList<Personnage> enemies;
    public LinkedList<FXEffect> worldExplosions;
    public LinkedList<Projectile> worldProjectiles;
    public LinkedList<Heal> worldHeal;
    public LinkedList<Pierre> worldPierre;

    private Point2D starterPos;
    private int teleportTo;
    private LinkedList<Teleporter> teleporters;
    private int myDifficulty;
    public World(int worldType, Thanos p,Game myGame) {
        this.myGame = myGame;
        thanos = p;
        worldProjectiles = new LinkedList<>();
        worldExplosions = new LinkedList<>();
        worldHeal = new LinkedList<>();
        worldPierre = new LinkedList<>();
        teleporters = new LinkedList<>();
        enemies = new LinkedList<>();
        switch (worldType) {
            case 1:
                starterPos = new Point2D(50,50);
                myDifficulty=1;
                teleportTo = 0;
                terrain = new TerrainMap(30,true,this,enemies);
                new LargeBase(BuildingSaves.pal,new Point2D(10000,0)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.ironManBase,new Point2D(2500,0)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.captainBase,new Point2D(2000,40)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.thorBase,new Point2D(3500,0)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.SpidermanBase,new Point2D(1500,0)).changeTerrain(terrain);
                System.out.println(enemies.size());
                enemies.add(new Personnage(new Point2D(1000,50),terrain,this));
                break;
            case 2:
                starterPos = new Point2D(50,50);
                myDifficulty=2;
                teleportTo = 0;
                terrain = new TerrainMap(30,true,this,enemies);
                new LargeBase(BuildingSaves.pal,new Point2D(10000,0)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.ironManBase,new Point2D(1500,0)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.captainBase,new Point2D(2500,40)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.thorBase,new Point2D(2000,0)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.SpidermanBase,new Point2D(3000,0)).changeTerrain(terrain);
                   break;
            case 0:
                starterPos = new Point2D(720,320);
                teleportTo = 1;
                terrain = new TerrainMap(2,false,this,enemies);
                System.out.println("loading base");
                new LargeBase(BuildingSaves.thanosBase,new Point2D(0,0)).changeTerrain(terrain);//generate home base
                teleporters.add(new Teleporter(new Point2D(600,190),1,myGame));
                break;
            default:
                starterPos = new Point2D(50,50);
                break;
        }
    }

    public void runWorld(double currentNanoTime) {
        thanos.run(terrain, this, currentNanoTime);
        //run physics for the player



        LinkedList<Personnage> ennToRemove = new LinkedList<>();
        LinkedList<Projectile> pToRemove = new LinkedList<>();
        LinkedList<FXEffect> eToRemove = new LinkedList<>();
        LinkedList<Heal> hToRemove = new LinkedList<>();
        LinkedList<Pierre> sToRemove = new LinkedList<>();

        for(Personnage enemy: enemies){//run physics for ai
            enemy.run(terrain,this,currentNanoTime);//run ai
            if(enemy.PV<=0){
                ennToRemove.add(enemy);
                thanos.infinity.PierreAme();
                int vieOuPas = (int)(Math.random()*1000);
                if(vieOuPas <= 200){
                    worldHeal.add(new Heal(enemy.myPosition,new Point2D (5,5),50));
                }
            }
        }

        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.runLogic(this, terrain, currentNanoTime); //run collisions for projectiles
            if (cProjectile.mylife <= 0) {
                pToRemove.add(cProjectile);
            }
        }
        for (FXEffect cExplosion : worldExplosions) {
            cExplosion.runExplosion(this, terrain, currentNanoTime); //run explosion logic
            if (cExplosion.mylife <= 0) {
                eToRemove.add(cExplosion);
            }
        }

        for (Heal cHeal : worldHeal){//run collision for healitems
            cHeal.runLogic(this);
            if (cHeal.mylife <= 0) {
                hToRemove.add(cHeal);
            }
        }

        for (Pierre cPierre : worldPierre){
            cPierre.runLogic(this);
            if (cPierre.mylife <= 0) {
                sToRemove.add(cPierre);
            }
        }


        //remove all unwanted items
        for (Projectile p : pToRemove) {
            if(thanos.pointOnScreen(p.position)) {
                AudioSaves.explosionSound.play(p.degats / 100d);
            }
            worldExplosions.add(new Explosion(p.position, 28, p.degats, terrain,p.enemyOwned));
            worldProjectiles.remove(p);
        }
        for (FXEffect e : eToRemove) {
            worldExplosions.remove(e);
        }
        for (Personnage e : ennToRemove) {
            worldExplosions.add(new Explosion(e.myPosition, 28, 10, terrain,true));
            enemies.remove(e);
        }

        for (Heal h : hToRemove) {
            worldHeal.remove(h);
        }

        for (Pierre s : sToRemove) {
            worldPierre.remove(s);
        }



        //run collision for teleporters
        for(Teleporter teleporter:teleporters){
            teleporter.checkForTeleport(thanos);
        }
        //test for temp teleport
        if(thanos.myPosition.getX()<20){
            myGame.switchWorlds(teleportTo);
        }
    }



    public void renderWorld(GraphicsContext gc, Group root) {
        terrain.draw(gc, new Point2D((float) thanos.getCameraPosition().getX(), 0f), root);//draw terrain
        thanos.draw(gc);//draw the player
        for(Personnage enemy: enemies){
            enemy.draw(gc);//draw ai
        }
        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.renderMe(gc, thanos.getCameraPosition());
        }
        for (Heal cHeal : worldHeal) {
            cHeal.renderMe(gc, thanos.getCameraPosition());
        }
        for (Pierre cPierre : worldPierre) {
            cPierre.renderMe(gc,thanos.getCameraPosition());
        }
        for (FXEffect cExplosion : worldExplosions) {
            cExplosion.renderMe(gc, thanos.getCameraPosition());
        }

        for(Teleporter teleporter:teleporters){
            teleporter.renderMe(gc,thanos.getCameraPosition());
        }
    }

    public void dispose(Group root){
        terrain.removeCanvas(root);
    }

    public Point2D getStarterPos() {
        return new Point2D(starterPos.getX(),starterPos.getY());
    }
    public int getDifficulty(){
        return myDifficulty;
    }
}




