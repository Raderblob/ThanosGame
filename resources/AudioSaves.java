package resources;

import ThanosGame.SoundEffect;

public class AudioSaves {
    public static SoundEffect mainMusic;
    public static SoundEffect explosionSound;
    public static SoundEffect gunSound;
    public static SoundEffect deathSound;
    public static SoundEffect teleportSound;
    public static SoundEffect mindSound;
    public static SoundEffect punchSound;
    public static SoundEffect realityCreateSound;
    public static SoundEffect realityDestroySound;
    public static SoundEffect shieldSound;
    public static void loadMusic(){
        mainMusic = new SoundEffect("mainMusic.wav");
        explosionSound = new SoundEffect("explosionSound.wav");
        gunSound = new SoundEffect("gunSound.wav");
        deathSound = new SoundEffect("deathSound.wav");
        teleportSound = new SoundEffect("teleportSound.wav");
        mindSound = new SoundEffect("mindSound.wav");
        punchSound = new SoundEffect("punchSound.wav");
        realityCreateSound = new SoundEffect("realityCreateSound.wav");
        realityDestroySound = new SoundEffect("realityDestroySound.wav");
        shieldSound = new SoundEffect("shieldSound.wav");
    }
}
