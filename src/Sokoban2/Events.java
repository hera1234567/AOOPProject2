package Sokoban2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.annotation.Annotation;

public class Events {

    //region keyListener
    public static KeyListener keyListener() {
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_RIGHT: {
                        Inputs.walkRight();
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        Inputs.walkLeft();
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        Inputs.walkDown();
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        Inputs.walkUp();
                        break;
                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return keyListener;
    }


    //endregion



    public static ActionListener actionListenerUpdateLevel() {
        JButton temp = Frame.getTextButton();

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//System.out.println("I event");
                System.out.println("I event: " + temp.getText());
                Inputs.updateLevel(temp.getText());
            }
        };
        return actionListener;
    }
}
