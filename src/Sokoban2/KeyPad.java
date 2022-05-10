package Sokoban2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPad implements InputMethod, KeyListener {

    private Controller c;
    private JFrame f;

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void setFrame(JFrame f) {
        this.f = f;
        f.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT: {
                System.out.println("Höger");
                c.walkRight();
                break;
            }
            case KeyEvent.VK_LEFT: {
                System.out.println("vänster");
                c.walkLeft();
                break;
            }
            case KeyEvent.VK_DOWN: {
                System.out.println("ner");
                c.walkDown();
                break;
            }
            case KeyEvent.VK_UP: {
                System.out.println("upp");
                c.walkUp();
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
