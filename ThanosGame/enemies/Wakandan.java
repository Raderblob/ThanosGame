package ThanosGame.enemies;

import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.ReboundRifle;
import ThanosGame.weapons.Rifle;
import ThanosGame.weapons.Spear;
import javafx.geometry.Point2D;
import resources.ImagesSaves;

class Wakandan extends Personnage {
    Wakandan(Point2D pos, TerrainMap myTerrain, World myWorld) {
        super(pos, myTerrain, myWorld,100,ImagesSaves.wakandaisSprites);
        int rndgen = Main.numberGenerator.nextInt(100);
        if(rndgen<20){
            myGun = new ReboundRifle(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }else if(rndgen<50){
            myGun = new Rifle(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }else{
            myGun = new Spear(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }
    }
}
