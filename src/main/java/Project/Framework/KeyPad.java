package Project.Framework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The type Key pad.
 */
public class KeyPad implements InputMethod, KeyListener, ActionListener {


    private JFrame frame;
    private JButton button;
    private Controller c;

    /**
     * Instantiates a new Key pad.
     *
     * @param frame the frame
     */
    public KeyPad(JFrame frame){
       this.frame=frame;
       frame.addKeyListener(this);
       button= Frame.getButton();
       button.addActionListener(this);
    }

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Key pressed Event
     *
     * Responds to a pressed key, checks if the key is relevant and sends it to the corresponding
     * method through controller
     *
     * @param e the KeyEvent
     */
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
            case KeyEvent.VK_S:{
                c.save();
                break;
            }
            case KeyEvent.VK_L:{
                c.load();
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