package ThanosGame.weapons.player;

import ThanosGame.Thanos;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Gant{
    public Thanos owner;
    public int selectedStone;
    public Stone[] stones ;
    int nbPierres ;

    public Gant(Thanos owner){
        this.owner=owner;
        stones = new Stone[5];
        for(int i=0;i<stones.length;i++){
            stones[i] = new Stone(owner);
        }
        selectedStone = 0;
    }
    public void addStones(Stone nStone){
        for(int i = 0;i<stones.length;i++){
            if(!stones[i].isReal()){
                stones[i]=nStone;
                return;
            }
        }
    }
    public void selectStone(int stoneToSelect){
        selectedStone = stoneToSelect;
    }
    public void selectNextStone(){
        selectedStone+=1;
        if(selectedStone>=stones.length){
            selectedStone=0;
        }
    }
    public void selectPreviousStone(){
        selectedStone-=1;
        if(selectedStone<0){
            selectedStone=stones.length-1;
        }
    }

    public void action( TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){

           stones[selectedStone].doAction(currentTerrain,currentWorld,destroyAt);

    }
}