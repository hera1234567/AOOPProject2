package main.java.Project.Sokoban;

import main.java.Project.Framework.Frame;
import main.java.Project.Framework.KeyPad;

public class SokobanKey {
    public static void main(String[] args) {

        GraphicObserver sok = new GraphicObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.setInputMethod(new KeyPad(Frame.getFrame()));
        s.addObserver(sok);
        s.addObserver(sound);
    }
}
