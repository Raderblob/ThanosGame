package ThanosGame.enemies;

import ThanosGame.AudioManager;
import ThanosGame.World;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Boss extends Personnage {
    private int myMusic;
    public Boss(Point2D pos, TerrainMap myTerrain, World myWorld, int hp, Image mySprite,int myMusic) {
        super(pos, myTerrain, myWorld, hp, mySprite);
        this.myMusic=myMusic;
    }

    public void playSong(AudioManager myAudio){
        if(myState == AiState.ATTACK && PV>0) {
            myAudio.setSong(myMusic);
        }
        if(PV<=0){
            myAudio.setSong(0);
        }
    }
    public void stopSong(AudioManager myAudio){
        myMusic=0;
        myAudio.setSong(0);
    }
}
