package Sokoban2;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundObserver implements Observer{

    private Clip step;
    private Clip win;
    private Clip lose;

    public SoundObserver() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream stepAudio = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/Sokoban2/icons/step.wav"));
        step = AudioSystem.getClip();
        step.open(stepAudio);

       /* AudioInputStream winAudio = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/Sokoban2/icons/win.wav"));
        win = AudioSystem.getClip();
        win.open(winAudio);

        AudioInputStream loseAudio = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/Sokoban2/icons/lose.wav"));
        lose = AudioSystem.getClip();
        lose.open(loseAudio);*/

    }

    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter) {

        if (winFlag){
            //WIN SOUND
            //win.start();
        }
        else if (loseFlag){
            //LOSE SOUND
            //lose.start();
        }
        else{
            //WALK SOUND
            step.start();
        }
    }
}
