package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Gant{
    public Thanos owner;
    private int selectedStone;
    Stone[] stones ;
    int nbPierres ;

    public Gant(Thanos owner){
        this.owner=owner;
        stones = new Stone[5];
        for(int i=0;i<stones.length;i++){
            stones[i] = new Stone(this);
        }
        selectedStone = 0;
    }
    public void addStones(Stone nStone){
        for(int i = 0;i<stones.length;i++){
            if(!stones[i].isReal()){
                stones[i]=nStone;
            }
        }
    }

    public void action( TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){

           stones[selectedStone].doAction(currentTerrain,currentWorld,destroyAt);

    }
}
