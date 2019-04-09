package ThanosGame.enemies;


import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.Hammer;
import javafx.geometry.Point2D;
import resources.ImagesSaves;

public class Thor extends Boss {
    public Thor(Point2D pos, TerrainMap myTerrain, World myWorld) {
        super(pos, myTerrain, myWorld, 4000, ImagesSaves.thorSprite,1);

        myGun = new Hammer(myWorld.worldProjectiles, this, 150 * myWorld.getDifficulty());

    }
}