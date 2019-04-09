package ThanosGame;

import ThanosGame.enemies.Personnage;
import ThanosGame.terrain.LargeBase;
import ThanosGame.terrain.Teleporter;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.player.StoneItem;
import ThanosGame.weapons.projectiles.Explosion;
import ThanosGame.weapons.projectiles.FXEffect;
import ThanosGame.weapons.projectiles.Projectile;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import resources.AudioSaves;
import resources.BuildingSaves;
import resources.ImagesSaves;

import java.util.LinkedList;

public class World {
    public Game myGame;
    public Thanos thanos;
    private TerrainMap terrain;
    public LinkedList<Personnage> enemies;
    public LinkedList<FXEffect> worldExplosions;
    public LinkedList<Projectile> worldProjectiles;
    public LinkedList<Heal> worldHeal;
    public LinkedList<StoneItem> worldStoneItem;

    private Point2D starterPos;
    private int unlockLevel;
    public LinkedList<Teleporter> teleporters;
    private double myDifficulty;
    public Image myBackground;

    public World(int worldType, Thanos p, Game myGame) {
        this.myGame = myGame;
        thanos = p;
        worldProjectiles = new LinkedList<>();
        worldExplosions = new LinkedList<>();
        worldHeal = new LinkedList<>();
        worldStoneItem = new LinkedList<>();
        teleporters = new LinkedList<>();
        enemies = new LinkedList<>();
        switch (worldType) {
            case 1: //generates very first world
                myBackground = ImagesSaves.desertBackground; //sets background
                starterPos = new Point2D(50, 50); //Position where the player spawns
                myDifficulty = 0.5; //difficulty of this world
                unlockLevel = 1; //unlock level required for this world
                terrain = new TerrainMap(6000, true, this, enemies, TerrainMap.TerrainVType.COUNTRY); //generate terrain with random enemies
                new LargeBase(BuildingSaves.SpidermanBase, new Point2D(5000, 0)).changeTerrain(terrain);


                enemies.add(Personnage.getEnemy(new Point2D(500, 50), terrain, this));//generate first hurdle for player

                StoneItem.addStone(new Point2D(600, 40), 0, worldStoneItem, thanos.infinity);//create stone items
                myGame.gui.addTutorial("You should get this stone to make it easier\nYou can select the stone use want to use with numbers or scroll the mouse\nThe two skills for this one are punch(leftClick) and meteor(rightClick)",new Point2D(600,40),1);
                StoneItem.addStone(new Point2D(5000, 40), 1, worldStoneItem, thanos.infinity);
                myGame.gui.addTutorial("The second Stone is here! Don't forget it\nThis stone will allow you to shield yourself with left click and place damaging bricks with right click",new Point2D(5000,40),1);


                //StoneItem.addStone(new Point2D(50, 40), 0, worldStoneItem, thanos.infinity);
                // StoneItem.addStone(new Point2D(60, 40), 1, worldStoneItem, thanos.infinity);
                // StoneItem.addStone(new Point2D(70, 40), 2, worldStoneItem, thanos.infinity);
                // StoneItem.addStone(new Point2D(80, 40), 3, worldStoneItem, thanos.infinity); //Test Stones
                break;
            case 2://generates second world
                myBackground = ImagesSaves.desertBackground;
                starterPos = new Point2D(50, 50);
                myDifficulty = 1;
                unlockLevel = 2;
                terrain = new TerrainMap(11000, true, this, enemies, TerrainMap.TerrainVType.COUNTRY);
                new LargeBase(BuildingSaves.captainBase, new Point2D(10000, 40)).changeTerrain(terrain);

                StoneItem.addStone(new Point2D(150, 40), 2, worldStoneItem, thanos.infinity);
                myGame.gui.addTutorial("Third stone you are supposed to collect\nThis one will allow you to teleport and spawn tnt, although the tnt will cost 1 soul",new Point2D(150,40),2);
                StoneItem.addStone(new Point2D(10000, 40), 3, worldStoneItem, thanos.infinity);
                myGame.gui.addTutorial("And the very last stone, Don't forget to upgrade them.\nThe final one will allow you to stun people and distract them",new Point2D(10000,40),2);
                break;
            case 3:
                myBackground = ImagesSaves.cityBackground;
                starterPos = new Point2D(50, 50);
                myDifficulty = 2;
                unlockLevel = 3;
                terrain = new TerrainMap(50000, true, this, enemies, TerrainMap.TerrainVType.CITY);
                new LargeBase(BuildingSaves.ironManBase, new Point2D(7500, 0)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.captainBase, new Point2D(5000, 40)).changeTerrain(terrain);
                new LargeBase(BuildingSaves.thorBase, new Point2D(10000, 0)).changeTerrain(terrain);
                break;
            case 0://generate home base
                myBackground = ImagesSaves.spaceBackground;
                starterPos = new Point2D(720, 320);
                unlockLevel = 0;
                terrain = new TerrainMap(2000, false, this, enemies, TerrainMap.TerrainVType.CITY);
                new LargeBase(BuildingSaves.thanosBase, new Point2D(0, 0)).changeTerrain(terrain);//generate home base
                for (int i = 1; i <= Math.min(myGame.unlockedWorld, 3); i++) {
                    teleporters.add(new Teleporter(new Point2D(300 + 300 * i, 190), i, myGame)); // add the teleporters
                }

                break;
            default:
                starterPos = new Point2D(50, 50);
                break;
        }
    }

    public void runWorld(double currentNanoTime) {
        thanos.run(terrain, this, currentNanoTime);
        //run physics for the player


        LinkedList<Personnage> ennToRemove = new LinkedList<>();//list of thingd that will get removed
        LinkedList<Projectile> pToRemove = new LinkedList<>();
        LinkedList<FXEffect> eToRemove = new LinkedList<>();
        LinkedList<Heal> hToRemove = new LinkedList<>();
        LinkedList<StoneItem> sToRemove = new LinkedList<>();

        for (Personnage enemy : enemies) {//run physics for ai
            enemy.run(terrain, this, currentNanoTime);//run ai
            if (enemy.PV <= 0) {
                ennToRemove.add(enemy);//add enemy to remove-list
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

        for (Heal cHeal : worldHeal) {//run collisions for heal-items
            cHeal.runLogic(this);
            if (cHeal.mylife <= 0) {
                hToRemove.add(cHeal);
            }
        }

        for (StoneItem cStoneItem : worldStoneItem) { //run collisions for stone pickup items
            cStoneItem.runLogic(this);
            if (cStoneItem.mylife <= 0) {
                sToRemove.add(cStoneItem);
            }
        }


        //remove all unwanted items
        for (Projectile p : pToRemove) {
            if (thanos.pointOnScreen(p.position)) {
                AudioSaves.explosionSound.play(p.degats / 100d);
            }
            worldExplosions.add(new Explosion(p.position, 28, p.degats, terrain, p.enemyOwned));
            worldProjectiles.remove(p);
        }
        for (FXEffect e : eToRemove) {
            worldExplosions.remove(e);
        }
        for (Personnage e : ennToRemove) {
            worldExplosions.add(new Explosion(e.myPosition, 28, 2, terrain, true));
            thanos.infinity.PierreAme();
            if (Main.numberGenerator.nextInt(1000) <= 400) {
                worldHeal.add(new Heal(e.myPosition, new Point2D(5, 5), 10));
            }
            enemies.remove(e);
        }

        for (Heal h : hToRemove) {
            worldHeal.remove(h);
        }

        for (StoneItem s : sToRemove) {
            worldStoneItem.remove(s);
        }


        //run collision for teleporters
        for (Teleporter teleporter : teleporters) {
            teleporter.checkForTeleport(thanos, unlockLevel);
        }
        //test for temp teleport
        if (thanos.myPosition.getX() < 20) {
            myGame.switchWorlds(0);
        }
    }


    public void renderWorld(GraphicsContext gc, Group root) {
        terrain.draw(gc, new Point2D((float) thanos.getCameraPosition().getX(), 0f), root);//draw terrain
        thanos.draw(gc);//draw the player
        for (Personnage enemy : enemies) {
            enemy.draw(gc);//draw ai
        }
        for (Projectile cProjectile : worldProjectiles) {
            cProjectile.renderMe(gc, thanos.getCameraPosition());
        }
        for (Heal cHeal : worldHeal) {
            cHeal.renderMe(gc, thanos.getCameraPosition());
        }
        for (StoneItem cStoneItem : worldStoneItem) {
            cStoneItem.renderMe(gc, thanos.getCameraPosition());
        }
        for (FXEffect cExplosion : worldExplosions) {
            cExplosion.renderMe(gc, thanos.getCameraPosition());
        }

        for (Teleporter teleporter : teleporters) {
            teleporter.renderMe(gc, thanos.getCameraPosition());
        }
    }

    public void dispose(Group root) {
        terrain.removeCanvas(root);
    }

    public Point2D getStarterPos() {
        return new Point2D(starterPos.getX(), starterPos.getY());
    }

    public double getDifficulty() {
        return myDifficulty;
    }
}




