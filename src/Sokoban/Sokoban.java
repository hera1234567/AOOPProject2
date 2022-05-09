package Sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static Sokoban.Level.*;

public class Sokoban extends Frame{
    private JPanel centerComponent;
    private JPanel losewin = new JPanel();
    private int lvlCounter;
    private GridLayout grid;
    public Level current;
    private boolean winFlag;
    String step= "";


    public Sokoban(){
        lvlCounter=0;
        current = new Level(Level.levels[lvlCounter].getHeight(), Level.levels[lvlCounter].getWidth(), Level.levels[lvlCounter].getPassable(),
                Level.levels[lvlCounter].getPlayerRow(), Level.levels[lvlCounter].getPlayerCol(),
                Level.levels[lvlCounter].getTargetRow(), Level.levels[lvlCounter].getTargetCol(),
                Level.levels[lvlCounter].getBoxRow(), Level.levels[lvlCounter].getBoxCol());

        buildLevel();
        winFlag = false;
    }


   /* public Signal() {
            public void actionPerformed(ActionEvent e) {

                for(Observer o : observers)
                    o.updateSignal();
        };
    }*/
    public void buildLevel(){
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

    public void keys(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_RIGHT:
            {
                System.out.println("höger");
                //if it's walk on blank
                if(current.getPassable()[current.getPlayerRow()][current.getPlayerCol()+1] && (current.getPlayerRow()!=current.getBoxRow()&&current.getPlayerCol()+1!= current.getBoxCol()))
                {
                    current.setPlayerRow(current.getPlayerRow());
                    current.setPlayerCol(current.getPlayerCol()+1);
                    buildLevel();
                }
                //if it pushes a box
                if((current.getPlayerRow()== current.getBoxRow()
                        && current.getPlayerCol()+1 == current.getBoxCol())
                        && current.getPassable()[current.getBoxRow()][current.getBoxCol()+1])
                {
                    if(current.getBoxRow()==current.getTargetRow()&&current.getBoxCol()+1==current.getTargetCol())
                    {
                        winFlag=true;
                    }
                    current.setPlayerRow(current.getPlayerRow());
                    current.setPlayerCol(current.getPlayerCol()+1);
                    current.setBoxRow(current.getBoxRow());
                    current.setBoxCol(current.getBoxCol()+1);
                    buildLevel();
                }
                break;
            }

            case KeyEvent.VK_LEFT:
            {
                //if walk on blank back
                if(current.getPassable()[current.getPlayerRow()][current.getPlayerCol()-1])
                {
                    current.setPlayerRow(current.getPlayerRow());
                    current.setPlayerCol(current.getPlayerCol()-1);
                    buildLevel();
                }
                //if push box back
                if((current.getPlayerRow()== current.getBoxRow()
                        && current.getPlayerCol()-1 == current.getBoxCol())
                        && current.getPassable()[current.getBoxRow()][current.getBoxCol()-1])
                {
                    if(current.getBoxRow()==current.getTargetRow()&&current.getBoxCol()-1==current.getTargetCol())
                    {
                        winFlag=true;
                    }
                    current.setPlayerRow(current.getPlayerRow());
                    current.setPlayerCol(current.getPlayerCol()-1);
                    current.setBoxRow(current.getBoxRow());
                    current.setBoxCol(current.getBoxCol()-1);
                    buildLevel();
                }
            break;
            }

            case KeyEvent.VK_DOWN:
            {
                //if walk on blank back
                if(current.getPassable()[current.getPlayerRow()+1][current.getPlayerCol()])
                {
                    current.setPlayerRow(current.getPlayerRow()+1);
                    current.setPlayerCol(current.getPlayerCol());
                    buildLevel();
                }
                //if push box down
                if((current.getPlayerRow()== current.getBoxRow()+1
                        && current.getPlayerCol() == current.getBoxCol())
                        && current.getPassable()[current.getBoxRow()+1][current.getBoxCol()])
                {
                    if(current.getBoxRow()+1==current.getTargetRow()&&current.getBoxCol()==current.getTargetCol())
                    {
                        winFlag=true;
                    }
                    current.setPlayerRow(current.getPlayerRow()+1);
                    current.setPlayerCol(current.getPlayerCol());
                    current.setBoxRow(current.getBoxRow()+1);
                    current.setBoxCol(current.getBoxCol());
                    buildLevel();
                }
                break;
            }

            case KeyEvent.VK_UP:
            {
                System.out.println("upp");
                //if walk on blank back
                if(current.getPassable()[current.getPlayerRow()-1][current.getPlayerCol()])
                {
                    current.setPlayerRow(current.getPlayerRow()-1);
                    current.setPlayerCol(current.getPlayerCol());
                    buildLevel();
                }
                //if push box back
                if((current.getPlayerRow()+1== current.getBoxRow()
                        && current.getPlayerCol() == current.getBoxCol())
                        && current.getPassable()[current.getBoxRow()-1][current.getBoxCol()])
                {
                    if(current.getBoxRow()-1==current.getTargetRow()&&current.getBoxCol()==current.getTargetCol())
                    {
                        winFlag=true;
                    }
                    current.setPlayerRow(current.getPlayerRow()-1);
                    current.setPlayerCol(current.getPlayerCol());
                    current.setBoxRow(current.getBoxRow()-1);
                    current.setBoxCol(current.getBoxCol());
                    buildLevel();
                }
                break;
            }

        }
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
                        new Sokoban();
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
                    new Sokoban();
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

   /* public static void main(String[] args){
         new Sokoban();

    }*/
}
