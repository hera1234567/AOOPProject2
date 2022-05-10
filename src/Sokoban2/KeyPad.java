package Sokoban2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPad implements InputMethod, KeyListener, ActionListener {
    JFrame frame;
    JButton button;
    private Controller c;

    public KeyPad(JFrame frame){
       this.frame=frame;
       frame.addKeyListener(this);
       button=Frame.getButton();
       button.addActionListener(this);
    }

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


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton temp = Frame.getTextButton();
        switch (temp.getText()) {
            case "Restart Level": {
                c.restartLevel();
                break;
            }
            case "Next Level": {
                c.nextLevel();
                break;
            }
            case "Restart the game!": {
                c.restartGame();
                break;
            }
        }
    }
}