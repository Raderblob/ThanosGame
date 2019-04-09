package ThanosGame.terrain;

import ThanosGame.Game;
import ThanosGame.Item;
import ThanosGame.Thanos;
import javafx.geometry.Point2D;
import resources.AudioSaves;
import resources.ImagesSaves;

public class Teleporter extends Item {
    private int teleportTo;
    private Game game;
    public Teleporter(Point2D maPosition,int teleportTo,Game game) {
        super(maPosition, new Point2D(50,50), ImagesSaves.teleporterSprite, new Point2D(180,255), 50);
        this.teleportTo = teleportTo;
        this.game=game;
    }

    public void checkForTeleport(Thanos player,int currentUnlock){
        if(isInRectangle(player.myPosition,player.mySize)){
            player.unlockedWorld = Math.max(player.unlockedWorld,currentUnlock+1);
            AudioSaves.teleportSound.play();
            game.switchWorlds(teleportTo);
        }
    }
}
