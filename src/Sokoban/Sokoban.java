package Sokoban;

import Test.src.Position;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Sokoban.Level.*;

public class Sokoban extends Frame{
    private JPanel centerComponent;
    private JPanel losewin = new JPanel();
    private int lvlCounter;
    private GridLayout grid;
    private Level current;
    private boolean winFlag;

    public Sokoban(){
        current = level1();
        lvlCounter=0;
        buildLevel();
        winFlag = false;
    }

    private void buildLevel(){
        losewin.removeAll();
        grid = new GridLayout(current.getHeight(),current.getWidth());
        centerComponent.removeAll();
        centerComponent.setLayout(grid);

        for (int row = 0; row < current.getHeight(); row++) {
            for (int col = 0; col < current.getWidth(); col++) {
                JLabel positionPanel = new JLabel();
                if(!current.getPassable()[row][col]){ //if there is wall
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban/icons/wall.png"));
                    positionPanel.setName("wall");
                }
                else if(row==current.getPlayerRow() && col==current.getPlayerCol()){ //if there its the starting position for the player
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban/icons/player.png"));
                    positionPanel.setName("player");
                }
                else if(row==current.getTargetRow() && col== current.getTargetCol()){ //if it's the targets position
                    if (!winFlag) {
                        positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/Sokoban/icons/blankmarked.png"));
                        positionPanel.setName("target");
                        positionPanel.addMouseListener(walkOnBlank(row,col));
                    }  else{
                            positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban/icons/crate.png"));
                            positionPanel.setName("box");
                    }
                }
                else if(row==current.getBoxRow() && col== current.getBoxCol()){ //if it's the boxes starting position
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban/icons/crate.png"));
                    positionPanel.setName("box");
                    positionPanel.addMouseListener(pushBox(row,col));
                }
                else { //if it's a blank
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban/icons/blank.png "));
                    positionPanel.setName("blank");
                    positionPanel.addMouseListener(walkOnBlank(row,col));
                }
                centerComponent.add(positionPanel);
            }
        }
        frame.repaint();
        frame.revalidate();
        if(winFlag)
            losewin();
    }

    public void losewin (){
        losewin.removeAll();
        if (winFlag) { // if game is won
            BoxLayout box = new BoxLayout(losewin, BoxLayout.Y_AXIS);
            losewin.setLayout(box);
            JLabel t = new JLabel();
            if(lvlCounter!= levels.length-1){
            t.setText("YOU MADE IT!");
            JButton eastButton = new JButton("Next level");
            eastButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lvlCounter++;
                    current= new Level(Level.levels[lvlCounter].getHeight(), Level.levels[lvlCounter].getWidth(), Level.levels[lvlCounter].getPassable(),
                            Level.levels[lvlCounter].getPlayerRow(), Level.levels[lvlCounter].getPlayerCol(),
                            Level.levels[lvlCounter].getTargetRow(), Level.levels[lvlCounter].getTargetCol(),
                            Level.levels[lvlCounter].getBoxRow(), Level.levels[lvlCounter].getBoxCol());
                    winFlag=false;
                buildLevel();
                }});

            JPanel buttons = new JPanel();
            buttons.add(eastButton);
            losewin.add(t);
            losewin.add(buttons);
            frame.add(losewin);
            } else {
                t.setText("YOU FINISHED THE GAME!");
                JButton eastButton = new JButton("Restart Game");
                eastButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lvlCounter=0;
                        current= new Level(Level.levels[lvlCounter].getHeight(), Level.levels[lvlCounter].getWidth(), Level.levels[lvlCounter].getPassable(),
                                Level.levels[lvlCounter].getPlayerRow(), Level.levels[lvlCounter].getPlayerCol(),
                                Level.levels[lvlCounter].getTargetRow(), Level.levels[lvlCounter].getTargetCol(),
                                Level.levels[lvlCounter].getBoxRow(), Level.levels[lvlCounter].getBoxCol());
                        winFlag=false;
                        buildLevel();
                    }});

                JPanel buttons = new JPanel();
                buttons.add(eastButton);
                losewin.add(t);
                losewin.add(buttons);
                frame.add(losewin);
                }

        }

        if (!winFlag) { // if game is lost
            BoxLayout box = new BoxLayout(losewin, BoxLayout.Y_AXIS);
            losewin.setLayout(box);
            JLabel t = new JLabel();
            t.setText("You lost the game :(");
            JButton eastButton = new JButton("Restart level");
            eastButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current= new Level(Level.levels[lvlCounter].getHeight(), Level.levels[lvlCounter].getWidth(), Level.levels[lvlCounter].getPassable(),
                            Level.levels[lvlCounter].getPlayerRow(), Level.levels[lvlCounter].getPlayerCol(),
                            Level.levels[lvlCounter].getTargetRow(), Level.levels[lvlCounter].getTargetCol(),
                            Level.levels[lvlCounter].getBoxRow(), Level.levels[lvlCounter].getBoxCol());
                    buildLevel();
                }});

            JPanel buttons = new JPanel();
            buttons.add(eastButton);
            losewin.add(t);
            losewin.add(buttons);
            frame.add(losewin);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        System.out.println(winFlag);

    }

    @Override
    public JComponent createCenterComponent() {
        centerComponent = new JPanel();
        centerComponent.setPreferredSize(new Dimension(200,200));
        return centerComponent;
    }

    public MouseListener walkOnBlank(int row, int col) {
        MouseListener m = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(current.getPlayerRow()==row+1 && current.getPlayerCol()==col
                        ||current.getPlayerRow()==row-1 && current.getPlayerCol()==col
                        ||current.getPlayerRow()==row && current.getPlayerCol()==col+1
                        ||current.getPlayerRow()==row && current.getPlayerCol()==col-1 ){
                    current.setPlayerRow(row);
                    current.setPlayerCol(col);
                    buildLevel();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

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
        };
        return m;

    }

    public MouseListener pushBox(int row, int col) {

        MouseListener m = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO sätt if statement i dessa med om nästa steg är the winning move, isåfall spela upp ett ljud och gå till en skärm med två knappar fortsätt eller inte
                if(current.getPlayerRow()==row+1 && current.getPlayerCol()==col && current.getPassable()[row-1][col]){
                    if(current.getTargetCol()==col && current.getTargetRow()==row-1){
                        winFlag = true;
                        current.setPlayerCol(col);
                        current.setPlayerRow(row);
                        current.setBoxRow(row-1);
                        buildLevel();
                    }
                    current.setPlayerCol(col);
                    current.setPlayerRow(row);
                    current.setBoxRow(row-1);
                } else if (current.getPlayerRow()==row-1 && current.getPlayerCol()==col && current.getPassable()[row+1][col]) {
                    if(current.getTargetCol()==col && current.getTargetRow()==row+1){
                        winFlag = true;
                        current.setPlayerCol(col);
                        current.setPlayerRow(row);
                        current.setBoxRow(row+1);
                        buildLevel();
                    }
                    current.setPlayerCol(col);
                    current.setPlayerRow(row);
                    current.setBoxRow(row+1);
                } else if (current.getPlayerRow()==row && current.getPlayerCol()==col+1 && current.getPassable()[row][col-1]) {
                    if(current.getTargetCol()==col-1 && current.getTargetRow()==row){
                        winFlag = true;
                        current.setPlayerCol(col);
                        current.setPlayerRow(row);
                        current.setBoxCol(col-1);
                        buildLevel();
                    }
                    current.setPlayerCol(col);
                    current.setPlayerRow(row);
                    current.setBoxCol(col-1);
                } else if (current.getPlayerRow()==row && current.getPlayerCol()==col-1 && current.getPassable()[row][col+1]) {
                    if(current.getTargetCol()==col+1 && current.getTargetRow()==row){
                        winFlag = true;
                        current.setPlayerCol(col);
                        current.setPlayerRow(row);
                        current.setBoxCol(col+1);
                        buildLevel();
                    }
                    current.setPlayerCol(col);
                    current.setPlayerRow(row);
                    current.setBoxCol(col+1);
                }
                buildLevel();
               if(((!current.getPassable()[current.getBoxRow()][current.getBoxCol()+1]&&!current.getPassable()[current.getBoxRow()-1][current.getBoxCol()]) && !(current.getTargetRow()==row-1 && current.getTargetCol()==col && current.getPassable()[row-1][col]))||
                       ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()+1]&&!current.getPassable()[current.getBoxRow()+1][current.getBoxCol()]) && !(current.getTargetRow()==row+1 && current.getTargetCol()==col && current.getPassable()[row+1][col]))||
                       ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()-1]&&!current.getPassable()[current.getBoxRow()-1][current.getBoxCol()]) && !(current.getTargetRow()==row && current.getTargetCol()==col-1 && current.getPassable()[row][col-1]))||
                       ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()-1]&&!current.getPassable()[current.getBoxRow()+1][current.getBoxCol()]) && !(current.getTargetRow()==row && current.getTargetCol()==col+1 && current.getPassable()[row][col+1]))){

                   losewin();
                    //TODO sätt ljud
                    //TODO kolla om det är winning move först
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
        };
        return m;
    }

    public void finishLine() {

        //check if player is at any tile around
            //put player on this spot
            //put blank where player was
            //put box one step forward
            //update positions in map
            //make winning sound
            //move on to next level
        //else
            // do nothing

    }

    public static void main(String[] args){
        Level l = level3();
        Sokoban m = new Sokoban();

    }
}
