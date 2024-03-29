package Project.Sokoban;

import Project.Framework.Frame;
import Project.Framework.MousePad;

public class SokobanMouse {
    public static void main(String[] args) {

        GraphicObserver sok = new GraphicObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.setInputMethod(new MousePad(Frame.getFrame()));
        s.addObserver(sok);
        s.addObserver(sound);
    }
}
