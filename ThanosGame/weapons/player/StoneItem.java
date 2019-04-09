package ThanosGame.weapons.player;

import ThanosGame.Item;
import ThanosGame.World;
import javafx.geometry.Point2D;
import resources.AudioSaves;
import resources.ImagesSaves;

import java.util.LinkedList;

public class StoneItem extends Item {
    public int typePierre;

    public StoneItem(Point2D position, Point2D Size, int type) {
        super(position, Size, ImagesSaves.stoneSprite, new Point2D(20, 20), 1000);
        typePierre = type;
        mylife = 1;
    }

    public void runLogic(World myWorld) {
        if (isInRectangle(myWorld.thanos.myPosition, myWorld.thanos.mySize)) {
            mylife = 0;
            AudioSaves.powerUpSound.play();
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

    public static void addStone(Point2D pos, int type, LinkedList<StoneItem> stones, Gant infinity) {
        if (!infinity.hasStone(type)) {
            stones.add(new StoneItem(pos, new Point2D(5, 5), type));
        }
    }
}
