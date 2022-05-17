package Project.Framework;

import Project.Sokoban.GraphicObserver;
import Project.Sokoban.Sokoban;
import Project.Sokoban.SoundObserver;

/**
 * The type Controller tester.
 * Tests the interface Controller
 */
public class ControllerTester implements Controller{

    /**
     * Instantiates a new Controller tester.
     */
    public ControllerTester(){

   }

    @Override
    public void walkUp() {
        System.out.println("UP");
    }

    @Override
    public void walkDown() {
        System.out.println("DOWN");
    }

    @Override
    public void walkRight() {
        System.out.println("RIGHT");
    }

    @Override
    public void walkLeft() {
        System.out.println("LEFT");
    }

    @Override
    public void clickedPosition(int xMouse, int yMouse, int frameWidth, int frameHeight) {
        System.out.println("MOUSE");
    }

    @Override
    public void nextLevel() {
        System.out.println("NEXT");
    }

    @Override
    public void restartLevel() {
        System.out.println("RESTART LEVEL");
    }

    @Override
    public void restartGame() {
        System.out.println("RESTART GAME");
    }

}

/**
 * The type Test.
 */
class Test{
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){

        //Testing keypad
        GraphicObserver sok = new GraphicObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.setInputMethod(new KeyPad(Frame.getFrame()));
        s.addObserver(sok);
        s.addObserver(sound);
        s.setController(new ControllerTester());



        //Testing mousepad
       /* GraphicObserver sok = new GraphicObserver();
        SoundObserver sound = new SoundObserver();
        Sokoban s = new Sokoban();
        s.setInputMethod(new MousePad(Frame.getFrame()));
        s.addObserver(sok);
        s.addObserver(sound);
        s.setController(new ControllerTester());

        */
    }
}
