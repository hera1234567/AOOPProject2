package Project.Framework;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;


/**
 * The type Mouse pad.
 */
public class MousePad implements InputMethod, MouseListener, ActionListener, KeyListener {


    private JFrame frame;
    private JButton button;
    private Controller c;

    /**
     * Instantiates a new Mouse pad.
     *
     * @param frame the frame
     */
    public MousePad(JFrame frame){
        this.frame=frame;
        frame.addMouseListener(this);
        frame.addKeyListener(this);
        button= Frame.getButton();
        button.addActionListener(this);
    }


    @Override
    public void setController(Controller c) {
        this.c = c;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Mouse Pressed Event
     *
     * Responds to if the mouse has been pressed, takes the coordinates of the click as well as the current frame size,
     * sends it to the corresponding method through controller.
     *
     * @param e the MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int xMouse = e.getXOnScreen();
        int yMouse = e.getYOnScreen();
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        c.clickedPosition(xMouse, yMouse, frameWidth, frameHeight);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
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
}