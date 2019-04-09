package ThanosGame;

import resources.ImagesSaves;

public class SoundEffect {
    private boolean loopMe = false;
    private javafx.scene.media.AudioClip myClip;

    public SoundEffect(String wavFile) {
        try {
            // AudioInputStream sound = AudioSystem.getAudioInputStream(ImagesSaves.class.getResource("sound/" + wavFile));
            myClip = new javafx.scene.media.AudioClip(ImagesSaves.class.getResource("sound/" + wavFile).toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        play(1);
    }

    public void play(double volume) {
        try {
            myClip.play(volume * Keyboard.gameVolume);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean isPlaying() {
        return myClip.isPlaying();
    }

    public void loop() {
        loopMe = true;
        try {
            new Thread(() -> {
                myClip.play(Keyboard.gameVolume);
                while (myClip.isPlaying()) {
                }
                if (loopMe) {
                    loop();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        myClip.stop();
        loopMe = false;
    }

    public void dispose() {
        myClip = null;
    }
}
