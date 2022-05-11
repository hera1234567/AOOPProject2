package Sokoban2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        PositionObserver obs = new PositionObserver();
        GraphicObserver sok = new GraphicObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.addObserver(sok);
        s.addObserver(obs);
        s.addObserver(sound);
    }
}
