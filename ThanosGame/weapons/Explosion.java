package ThanosGame.weapons;

import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.enemies.Personnage;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Explosion extends FXEffect { //does the damage
    private double damagePerTick;
    private boolean damaged;
    private boolean enemyOwned;

    public Explosion(Point2D maPosition, double myL, double damageT, TerrainMap mTerrain,boolean eOwned) {
        super(maPosition, new Point2D(Math.tanh((damageT-10)*0.005)*70+10,Math.tanh((damageT-10)*0.005)*70+10), myL, mTerrain);
        damagePerTick = damageT;
        myAnimation.setMyMode(0);
        damaged = false;
        enemyOwned = eOwned;
    }



    public void runExplosion(World myWorld, TerrainMap terrain, double nanoTime) {
        super.runExplosion(myWorld, terrain, nanoTime);

        if (!damaged) {
            if (!enemyOwned) {
                for(Personnage enemy:myWorld.enemies){
                    if(!enemy.turned && isInRectangle(enemy.myPosition,enemy.mySize)){
                        enemy.removeHp(damagePerTick);
                        damaged=true;
                    }
                }
            }
            if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)){
                myWorld.thanos.removeHp(damagePerTick);
                damaged = true;
            }

        }

        for (Point2D explosionPoint : explosionPoints) {
            double damageD = (1 - Main.getMagnitudeSquared(explosionPoint.add(position.multiply(-1))) / (Math.pow(explosionRadius, 2))) * damagePerTick * nanoTime;
            if (Main.canDamage(terrain.getTerrainVal(explosionPoint.getX(), explosionPoint.getY()), damageD)) {
                terrain.changeTerrain(new Point2D[]{explosionPoint}, new byte[]{0});
            }
        }

    }


}
