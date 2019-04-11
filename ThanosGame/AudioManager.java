package ThanosGame;

import resources.AudioSaves;

public class AudioManager {
    private final SoundEffect[] musics = new SoundEffect[2];
    private int selectedSong;
    public AudioManager(){
        musics[0] = AudioSaves.mainMusic;
        musics[1] = AudioSaves.thorSound;
        selectedSong=0;
    }

    public void setSong(int sSong){
        selectedSong=sSong;
    }

    public void runMusic(){
        for(int i=0;i<musics.length;i++){
            if(i==selectedSong){
                if(!musics[i].isPlaying()){
                    musics[i].play();
                }
            }else{
                if(musics[i].isPlaying()){
                    musics[i].stop();
                }
            }

        }
    }

    public void restartAll(){
        for (SoundEffect music : musics) {
            if (music.isPlaying()) {
                music.stop();
            }
        }
        runMusic();
    }
}
