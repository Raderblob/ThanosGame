package ThanosGame;

import resources.ImagesSaves;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundEffect {
    private AudioClip myClip;
    public SoundEffect(String wavFile){
        try {
            myClip = Applet.newAudioClip(ImagesSaves.class.getResource("sound/"+wavFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        try {
            new Thread(() -> myClip.play()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loop(){
        try {
            new Thread(() -> myClip.loop()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stop(){
        myClip.stop();
    }
    public void dispose(){
        myClip = null;
    }
}
