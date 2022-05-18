package Project.Sokoban;

import Project.Framework.Frame;
import Project.Framework.MousePad;

public class SokobanMousePosition {
    public static void main(String[] args) {

        GraphicObserver sok = new GraphicObserver();
        PositionObserver pos = new PositionObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.setInputMethod(new MousePad(Frame.getFrame()));
        s.addObserver(sok);
        s.addObserver(pos);
        s.addObserver(sound);
    }
}
