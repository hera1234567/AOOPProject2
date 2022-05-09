package Sokoban2;

public class Test {

    public static void main(String[] args) {

        GraphicObserver sok = new GraphicObserver();
        Sokoban s = new Sokoban();
        s.addObserver(sok);
    }
}
