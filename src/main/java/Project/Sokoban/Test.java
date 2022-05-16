package Project.Sokoban;

import Project.Framework.Frame;
import Project.Framework.KeyPad;
import Project.Framework.MousePad;


public class Test {

    public static void main(String[] args) {

        PositionObserver pos = new PositionObserver();
        GraphicObserver sok = new GraphicObserver();
        //SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.setInputMethod(new KeyPad(Frame.getFrame()));
        s.addObserver(sok);
        s.addObserver(pos);
        //s.addObserver(sound);
    }
}
