package Sokoban2;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.*;

public class MousePad implements InputMethod, MouseListener, ActionListener {
    JFrame frame;

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