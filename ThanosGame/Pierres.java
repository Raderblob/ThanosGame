package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

import java.util.LinkedList;

public class Pierres{
    int numero ;
    public Pierres(int numero){
        this.numero = numero ;
    }
    public void action(int numero, Thanos Thanos1, TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){
        switch (this.numero) {
            case 0 : if(Thanos1.degats!=150){
                         Thanos1.degats+=100;
                     }
                     if (Thanos1.range!=150){
                         Thanos1.range+=100;
                     }
            case 1 : realityStone(currentTerrain, currentWorld, destroyAt) ;
            case 2 :
            case 3 : 
            case 4 : 
            case 5 : 
        }
    }

    public void realityStone(TerrainMap currentTerrain, World currentWorld, Point2D destroyAt) {
        LinkedList<Point2D> pTD = new LinkedList<>();

        for (int x = -40; x < 40; x++) {
            for (int y = -40; y < +40; y++) {
                if (Main.getMagnitudeSquared(x, y) < Math.pow(40, 2)) {
                    pTD.add(currentTerrain.clampPoint(new Point2D(x, y).add(destroyAt)));
                }
            }
        }
        byte bTD[] = new byte[pTD.size()];
        for (int i = 0; i < bTD.length; i++) {
            bTD[i] = 0;
        }
        currentTerrain.changeTerrain(pTD.toArray(new Point2D[pTD.size()]), bTD);
        destroyAt = new Point2D(-1, -1);
    }
}
