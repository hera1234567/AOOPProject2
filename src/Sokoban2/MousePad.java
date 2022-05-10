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
       int xPlayer = Sokoban.current.getPlayerCol()*32+100;
       int yPlayer = Sokoban.current.getPlayerRow()*32+100;
        System.out.println("MOUSEPAD");
        System.out.println("X: " + x + "    Y: " + y);
        System.out.println("Player X: " + xPlayer + "    Player Y: " + yPlayer);

       /**Kolla om det är höger/vänster/upp/ner från gubben*/
       if ((x>xPlayer+32 && x<xPlayer+64) && (y<yPlayer+32 && y>yPlayer)){
           c.walkRight();
           System.out.println("RIGHT");
       }
       else if (x<xPlayer && (y<yPlayer+32 && y>yPlayer)){
           c.walkLeft();
           System.out.println("LEFT");
       }
       else if (y>yPlayer+32 && (x<xPlayer+32 && x>xPlayer)){
           c.walkDown();
           System.out.println("DOWN");
       }
       else if (y<yPlayer && (x<xPlayer+32 && x>xPlayer)){
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