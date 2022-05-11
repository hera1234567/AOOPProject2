package Sokoban2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Observer interface
 * */

public interface Observer {
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter);
}