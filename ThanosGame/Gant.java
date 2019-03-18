package ThanosGame;

import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class Gant{
    Pierres [] Stones ;
    int nbPierres ;
    public Gant(Pierres [] Stones){
        this.Stones = Stones ;
    }
    public void action(int numero, Thanos Thanos1, TerrainMap currentTerrain, World currentWorld, Point2D destroyAt){
        if(Stones[numero] != null){
            Pierres PierreUtilisée = new Pierres(numero) ;
            PierreUtilisée.action(numero, Thanos1, currentTerrain, currentWorld, destroyAt) ;
        }
    }
}
