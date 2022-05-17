package Project.Framework;

import javax.swing.*;
import java.awt.*;

/**
 * Testing interfaces InputMethod and Controller together
 * */

public class InputMethodControllerTest implements Controller {
    private static InputMethod m;


    public JFrame start(){
        Frame test = new Frame() {
            @Override
            public JComponent createCenterComponent() {
                JPanel centerComponent = new JPanel();
                centerComponent.setPreferredSize(new Dimension(250, 200));
                return centerComponent;
            }
        };
        test.setName("test");
        System.out.println(test.getName());

        return test;
    }



    public void setInputMethod(InputMethod n) {
        m = n;
        m.setController(this);
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

class Hej {

    public static void main(String[] args){
        InputMethodControllerTest test = new InputMethodControllerTest();
        test.setInputMethod(new KeyPad(test.start()));
    }
}

