package ThanosGame.enemies;

import ThanosGame.Main;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.MachineGun;
import ThanosGame.weapons.MachineReboundGun;
import ThanosGame.weapons.ReboundRifle;
import ThanosGame.weapons.Rifle;
import javafx.geometry.Point2D;
import resources.ImagesSaves;

public class Agent extends Personnage {
    Agent(Point2D pos, TerrainMap myTerrain, World myWorld) {
        super(pos, myTerrain, myWorld,100,ImagesSaves.agentSprites);
        int rndgen = Main.numberGenerator.nextInt(100);

        if(rndgen<20) {
            myGun = new MachineReboundGun(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }else if(rndgen<50){
            myGun = new MachineGun(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }else if(rndgen<75){
            myGun = new ReboundRifle(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }else{
            myGun = new Rifle(myWorld.worldProjectiles, this, 10*myWorld.getDifficulty());
        }
    }
}
