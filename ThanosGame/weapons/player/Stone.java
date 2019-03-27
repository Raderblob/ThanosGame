package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Stone {
    protected int stoneType;
    protected Thanos owner;
    protected String stoneName;
    public Stone(Thanos owner){
        stoneType=-1;
        this.owner = owner;
        stoneName = "Empty";
    }

    public int doAction(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){
        Point2D[] pTD = new Point2D[(int)(owner.mySize.getX()*2*owner.mySize.getY()*2)];
        int cnt =0;
        for(int x=0;x<owner.mySize.getX()*2;x++){
            for(int y =(int)-owner.mySize.getY();y<owner.mySize.getY();y++){
                pTD[cnt] = new Point2D((destroyAt.add(owner.myPosition.multiply(-1)).getX()<0? -1:1)*x,y).add(owner.myPosition);
                cnt++;
            }
        }
        byte bTD[] = new byte[pTD.length];
        for (int i = 0; i < bTD.length; i++) {
            bTD[i] = 0;
        }
        currentTerrain.changeTerrain(pTD, bTD);
        return -1;
    }

    public String toString() {
        return stoneName;
    }

    public boolean isReal(){
        return stoneType!=-1;
    }



}
