package ThanosGame.weapons;

import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Explosion extends FXEffect { //does the damage
    private double damagePerTick;

    public Explosion(Point2D maPosition, Point2D Size, double myL, double damageT, TerrainMap mTerrain) {
        super(maPosition, Size, myL, mTerrain);
        damagePerTick=damageT;
        myAnimation.setMyMode(0);
    }


    public void runExplosion(World myWorld, TerrainMap terrain, double nanoTime){
        super.runExplosion(myWorld,terrain,nanoTime);
        if(Main.getMagnitudeSquared(position.add(myWorld.thanos.myPosition.multiply(-1)))<Math.pow(explosionRadius,2)){
            //reduce hp of player
        }

        for (Point2D explosionPoint : explosionPoints) {
            double damageD = (1 - Main.getMagnitudeSquared(explosionPoint.add(position.multiply(-1))) / (Math.pow(explosionRadius, 2))) * damagePerTick * nanoTime;
            if (Main.canDamage(terrain.getTerrainVal(explosionPoint.getX(), explosionPoint.getY()), damageD)) {
                terrain.changeTerrain(new Point2D[]{explosionPoint}, new byte[]{0});
            }
        }

    }



}
