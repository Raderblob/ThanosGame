package ThanosGame.terrain;

import ThanosGame.Game;
import ThanosGame.Item;
import ThanosGame.Thanos;
import ThanosGame.graphics.images.ImagesSaves;
import javafx.geometry.Point2D;

public class Teleporter extends Item {
    private int teleportTo;
    private Game game;
    public Teleporter(Point2D maPosition,int teleportTo,Game game) {
        super(maPosition, new Point2D(50,50), ImagesSaves.projectiles[0], new Point2D(20,10), 1000);
        this.teleportTo = teleportTo;
        this.game=game;
    }

    public void checkForTeleport(Thanos player){
        if(isInRectangle(player.myPosition,player.mySize)){
            game.switchWorlds(teleportTo);
        }
    }
}
