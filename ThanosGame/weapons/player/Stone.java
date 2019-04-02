package ThanosGame.weapons.player;

import ThanosGame.Main;
import ThanosGame.Personnage;
import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.FXEffect;
import javafx.geometry.Point2D;

import java.util.LinkedList;

public class Stone {
    protected int stoneType;
    protected Thanos owner;
    protected String stoneName;
    protected int myPower;
    protected long coolDown;
    protected long secondaryCoolDown;
    private long lastUsage;
    private boolean usedSecondary;

    public Stone(Thanos owner) {
        stoneType = -1;
        this.owner = owner;
        stoneName = "Empty";
        myPower = 2;
        lastUsage = System.currentTimeMillis();
        coolDown = 1;
        secondaryCoolDown = 5000;
        usedSecondary = false;
    }

    private void reset() {
        if (isReset()) {
            lastUsage = System.currentTimeMillis();
        }
    }

    public double getCurrentCoolDown() {
        if(!usedSecondary) {
            return Math.min(coolDown, System.currentTimeMillis() - lastUsage) / ((double) (coolDown));
        }else{
            return Math.min(secondaryCoolDown, System.currentTimeMillis() - lastUsage) / ((double) (secondaryCoolDown));
        }
    }

    private boolean isReset() {
        return getCurrentCoolDown() == 1;
    }

    public void doAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        if (isReset()) {
            if (doSubAction(currentTerrain, currentWorld, destroyAt) == 1) {
                reset();
                usedSecondary=false;
            }
        }
    }

    public void doSecondaryAction(TerrainMap currentTerrain,World currentWorld,Point2D destroyAt){
        if(isReset()) {
            if(doSubSecondaryAction(currentTerrain,currentWorld,destroyAt)==1){
                reset();
                usedSecondary=true;
            }
        }
    }
    protected int doSubSecondaryAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){
        currentWorld.thanos.myShield += myPower*10;
        return 1;
    }

    protected int doSubAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        Point2D destination = new Point2D(destroyAt.getX(), destroyAt.getY());
        Point2D hitDistance = destination.add(currentWorld.thanos.myPosition.multiply(-1));

        hitDistance = hitDistance.normalize().multiply(owner.mySize.getX());
        destination = hitDistance.add(currentWorld.thanos.myPosition);
        LinkedList<Point2D> pointsToChange = currentTerrain.getCircleOfPointsLinked(destination, 20);

        doDamage(currentWorld,destination,pointsToChange);
        doChanges(pointsToChange, (byte) 0, currentTerrain);
        currentWorld.worldExplosions.add(new FXEffect(destination,new Point2D(40,40),10,currentTerrain));
        return 1;
    }

    protected void doDamage(World currentWorld,Point2D destroyAt,LinkedList<Point2D> pointsToChange){
        for(Personnage enemy:currentWorld.enemies){
            if(Main.getMagnitudeSquared(destroyAt.add(enemy.myPosition.multiply(-1)))< 1000){
                dmgPerEnemy(enemy,pointsToChange);
            }
        }
    }
    private void dmgPerEnemy(Personnage enemy,LinkedList<Point2D> pointsToChange){
        for(Point2D pT:pointsToChange){
            Point2D dist = pT.add(enemy.myPosition.multiply(-1));
            if(Math.abs(dist.getX())<enemy.mySize.getX()&&Math.abs(dist.getY())<enemy.mySize.getY()){
                enemy.removeHp(myPower);
                return;
            }
        }
    }

    protected void doChanges(LinkedList<Point2D> p, byte b, TerrainMap currentTerrain) {
        Point2D[] pTD = testMyDamage(p, currentTerrain);


        byte bTD[] = new byte[pTD.length];
        for (int i = 0; i < bTD.length; i++) {
            bTD[i] = b;
        }
        currentTerrain.changeTerrain(pTD, bTD);
    }

    protected Point2D[] testMyDamage(LinkedList<Point2D> pTD, TerrainMap currentTerrain) {
        LinkedList<Point2D> res = new LinkedList<>();
        for (Point2D p : pTD) {
            if (Main.canDamage(currentTerrain.getTerrainVal(p), myPower)) {
                res.add(p);
            }
        }
        return res.toArray(new Point2D[0]);
    }

    public String toString() {
        return stoneName;
    }

    public boolean isReal() {
        return stoneType != -1;
    }
}
