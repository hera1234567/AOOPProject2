package Sokoban2;

public class Test {

    public static void main(String[] args) {

        Observer2 obs = new Observer2();
        GraphicObserver sok = new GraphicObserver();
        Sokoban s = new Sokoban();
        s.addObserver(sok);
        s.addObserver(obs);
    }
}
