package ThanosGame;

import ThanosGame.weapons.player.MindStone;
import ThanosGame.weapons.player.PowerStone;
import ThanosGame.weapons.player.RealityStone;
import ThanosGame.weapons.player.SpaceStone;
import resources.ImagesSaves;
import javafx.geometry.Point2D;

public class Pierre extends Item{
    public int typePierre;

    public Pierre(Point2D position, Point2D Size, int type){
        super(position, Size, ImagesSaves.stoneSprite, new Point2D(20,20),1000);
        typePierre = type;
        mylife = 1;
    }

    public void runLogic(World myWorld){
        if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)) {
            mylife = 0;
            switch (typePierre) {
                case 0:
                    myWorld.thanos.addStone(new PowerStone(myWorld.thanos));
                    break;
                case 1:
                    myWorld.thanos.addStone(new RealityStone(myWorld.thanos));
                    break;
                case 2:
                    myWorld.thanos.addStone(new SpaceStone(myWorld.thanos));
                    break;
                case 3:
                    myWorld.thanos.addStone(new MindStone(myWorld.thanos));
                    break;
            }
        }
    }
}
