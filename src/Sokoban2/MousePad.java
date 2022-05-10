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
       int x = e.getXOnScreen();
       int y = e.getYOnScreen();
       int xPlayer = frame.getWidth() -((Sokoban.current.getWidth()- Sokoban.current.getPlayerCol()) * 30 + 20 + Sokoban.current.getPlayerCol()*10);
       int yPlayer = frame.getHeight() - ((Sokoban.current.getHeight() - Sokoban.current.getPlayerRow())*30 + 10 + Sokoban.current.getPlayerRow()*10);
        System.out.println("X: " + x + "    Y: " + y);
        System.out.println("X player: " + xPlayer + "    Y player: " + yPlayer);
       /**Kolla om det är höger/vänster/upp/ner från gubben*/
       if ((x>xPlayer) && (y<yPlayer+50 && y>yPlayer-50)){
           c.walkRight();
           System.out.println("RIGHT");
       }
       else if (x<xPlayer && (y<yPlayer+50 && y>yPlayer-50)){
           c.walkLeft();
           System.out.println("LEFT");
       }
       else if (y>yPlayer && (x<xPlayer+50 && x>xPlayer-50)){
           c.walkDown();
           System.out.println("DOWN");
       }
       else if (y<yPlayer && (x<xPlayer+50 && x>xPlayer-50)){
           c.walkUp();
           System.out.println("UP");
       }
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