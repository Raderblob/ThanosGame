package ThanosGame.weapons.player;

import ThanosGame.Main;
import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

import java.util.LinkedList;

public class Stone {
    protected int stoneType;
    protected Thanos owner;
    protected String stoneName;
    protected int myPower;
    protected long coolDown;
    private long lastUsage;
    public Stone(Thanos owner) {
        stoneType = -1;
        this.owner = owner;
        stoneName = "Empty";
        myPower = 2;
        lastUsage=System.currentTimeMillis();
        coolDown=1;
    }
    
    private void reset(){
        if(isReset()){
            lastUsage=System.currentTimeMillis();
        }
    }

    public double getCurrentCoolDown(){
        return Math.min(coolDown,System.currentTimeMillis()-lastUsage)/((double)(coolDown));
    }
    private boolean isReset(){
        return getCurrentCoolDown()==1;
    }

    public void doAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){
        if(isReset()) {
            if(doSubAction(currentTerrain, currentWorld, destroyAt)==1){
                reset();
            }
        }
    }

    protected int doSubAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        Point2D destination = new Point2D(destroyAt.getX(), destroyAt.getY());
        Point2D hitDistance = destination.add(currentWorld.thanos.myPosition.multiply(-1));

        hitDistance = hitDistance.normalize().multiply(owner.mySize.getX());
        destination = hitDistance.add(currentWorld.thanos.myPosition);
        doChanges( currentTerrain.getCircleOfPointsLinked(destination,10),(byte)0,currentTerrain);
        return 1;
    }

    protected void doChanges(LinkedList<Point2D> p,byte b,TerrainMap currentTerrain){
        Point2D[] pTD =testMyDamage(p,currentTerrain);


        byte bTD[] = new byte[pTD.length];
        for (int i = 0; i < bTD.length; i++) {
            bTD[i] = b;
        }
        currentTerrain.changeTerrain(pTD, bTD);
    }

    protected Point2D[] testMyDamage(LinkedList<Point2D> pTD, TerrainMap currentTerrain){
        LinkedList<Point2D> res = new LinkedList<>();
        for(Point2D p :pTD){
            if(Main.canDamage(currentTerrain.getTerrainVal(p),myPower)){
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
