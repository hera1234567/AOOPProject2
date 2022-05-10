package Sokoban2;

public class Test {

    public static void main(String[] args) {

        PositionObserver obs = new PositionObserver();
        GraphicObserver sok = new GraphicObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.addObserver(sok);
        s.addObserver(obs);
        s.addObserver(sound);
        s.setInputMethod(new MousePad(Frame.getFrame()));
    }
}
