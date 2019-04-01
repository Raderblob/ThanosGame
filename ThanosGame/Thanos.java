package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.player.Gant;
import ThanosGame.weapons.player.Stone;
import javafx.geometry.Point2D;

public class Thanos extends PlayerClass{
    public double PV ;
    private double maxPv;
    public Gant infinity ;

    public Thanos(int PV){
        super();
        this.PV=PV ;
        maxPv = PV;
        this.infinity= new Gant(this);
    }

    @Override
    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        super.run(currentTerrain, currentWorld, currentNanoTime);

        if (destroyAt.getX() != -1) {
            infinity.action(currentTerrain,currentWorld,destroyAt);
            //useTestStone(currentTerrain, currentWorld);
            destroyAt = new Point2D(-1, -1);
        }

        if(PV <=0){

        }
    }

    public void addStone(Stone nStone){
        infinity.addStones(nStone);
    }
    private void useTestStone(TerrainMap currentTerrain, World currentWorld) {
        Point2D[] pTD = currentTerrain.getCircleOfPoints(destroyAt,40);
        byte bTD[] = new byte[pTD.length];
        for (int i = 0; i < bTD.length; i++) {
            bTD[i] = 0;
        }
        currentTerrain.changeTerrain(pTD, bTD);
    }

    public double getHp(){
        return PV/maxPv;
    }
}

 
