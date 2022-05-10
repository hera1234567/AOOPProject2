package Sokoban2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPad implements InputMethod, KeyListener {

    private Controller c;

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT: {
                c.walkRight();
                break;
            }
            case KeyEvent.VK_LEFT: {
                c.walkLeft();
                break;
            }
            case KeyEvent.VK_DOWN: {
                c.walkDown();
                break;
            }
            case KeyEvent.VK_UP: {
                c.walkUp();
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
