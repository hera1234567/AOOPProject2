package Project.Sokoban;

import Project.Framework.Frame;
import Project.Framework.MousePad;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        //PositionObserver obs = new PositionObserver();
        GraphicObserver sok = new GraphicObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.setInputMethod(new MousePad(Frame.getFrame()));
        s.addObserver(sok);
        //s.addObserver(obs);
        s.addObserver(sound);
    }
}
