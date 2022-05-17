package main.java.Project.Framework;

import javax.swing.*;
import java.awt.event.*;

/**
 * The type Mouse pad.
 */
public class MousePad implements InputMethod, MouseListener, ActionListener {

    private JFrame frame;

    /**
     * Instantiates a new Mouse pad.
     *
     * @param frame the frame
     */
    public MousePad(JFrame frame){
        this.frame=frame;
        frame.addMouseListener(this);
    }

    private Controller c;

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
}