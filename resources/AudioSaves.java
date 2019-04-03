package resources;

import ThanosGame.SoundEffect;

public class AudioSaves {
    public static SoundEffect mainMusic;
    public static SoundEffect explosionSound;
    public static void loadMusic(){
        mainMusic = new SoundEffect("mainMusic.wav");
        explosionSound = new SoundEffect("explosionSound.wav");
    }
}
