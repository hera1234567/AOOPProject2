package Sokoban2;

import java.io.File;
import javax.sound.sampled.*;

/***
 * An observer class that plays a sound at events.
 */

public class SoundObserver implements Observer{


    private static void play(String filename)
    {
        try
        {
            Clip audio = AudioSystem.getClip();
            audio.open(AudioSystem.getAudioInputStream(new File(filename)));
            audio.start();
        }
        catch (Exception exception)
        {
            exception.printStackTrace(System.out);
        }
    }

    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter) {

        if (winFlag){
            //WIN SOUND
            play(System.getProperty("user.dir") + "/src/Sokoban2/icons/win (1).wav");
        }
        else if (loseFlag){
            //LOSE SOUND
            play(System.getProperty("user.dir") + "/src/Sokoban2/icons/lose.wav");
        }
        else{
            //WALK SOUND
            play(System.getProperty("user.dir") + "/src/Sokoban2/icons/step.wav");
        }
    }
}
