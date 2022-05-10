package Sokoban2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePad implements InputMethod, MouseListener {

    private Controller c;

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void setFrame(JFrame f) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*int keyCode = e.getKeyCode();
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

         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
