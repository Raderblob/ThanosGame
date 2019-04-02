package ThanosGame.weapons;

import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;

public class DistractionFX extends FXEffect {

    public DistractionFX(Point2D maPosition, Point2D Size, double myL, TerrainMap mTerrain) {
        super(maPosition, Size, myL, mTerrain);
        myAnimation.setMyMode(2);
        type=1;
    }

    @Override
    protected double getScale() {
        return 1;
    }
}
