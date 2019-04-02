package ThanosGame.weapons;

import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class ReboundProjectile extends Projectile {
    public ReboundProjectile(Point2D position, Point2D Size, int degats, Point2D speed) {
        super(position, Size, degats, speed,1000,2);
    }

    public ReboundProjectile(Point2D position, Point2D size, int degats, double speed, double angle) {
        super(position, size, degats, speed, angle);
    }


    public void runLogic(World myWorld, TerrainMap myTerrain, double nanoTime) {
        if (runBasicPhysics(myTerrain, nanoTime)) {
            if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)) {
                mylife=0;
            }
            if (myTerrain.getTerrainValCollision(position.getX(), position.getY()) != 0) {
                mylife-=degats*10;
                if(mylife > maxLife/2) {
                    Point2D terrainTangent = myTerrain.getTangent(position, speed);
                    Point2D projectedVec = terrainTangent.multiply(speed.dotProduct(terrainTangent));
                    Point2D displacement = projectedVec.add(speed.multiply(-1));
                    speed = projectedVec.add(displacement);
                   // position = position.add(speed.multiply(2));
                }else{
                    mylife=0;
                }
            }
        }

    }
}
