package Project.Sokoban;

import Project.Framework.Observer;
import Project.Sokoban.Level;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The type Sound observer.
 * An observer class that plays a sound at events.
 */
public class SoundObserver implements Observer<Level> {

/** Play.
 *
 * Plays the file given through the input
 *
 * @param filename , name of soundfile
 */
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
            play(System.getProperty("user.dir") + "/src/main/java/Project/icons/win (1).wav");
        }
        else if (loseFlag){
            //LOSE SOUND
            play(System.getProperty("user.dir") + "/src/main/java/Project/icons/lose.wav");
        }
        else{
            //WALK SOUND
            play(System.getProperty("user.dir") + "/src/main/java/Project/icons/step.wav");
        }
    }
}
