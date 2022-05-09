package Sokoban2;

import javax.swing.*;
import java.awt.*;


public class GraphicObserver extends Frame implements Observer {
    private GridLayout grid;
    private JPanel centerComponent;
    private JPanel losewin = new JPanel();
    private int lvlCounter;
    private boolean winFlag = false;
    private Level current;

    @Override
    public void updateCurrentState(Level state, boolean winFlag, int lvlCounter) {
        this.winFlag=winFlag;
        this.lvlCounter=lvlCounter;
        this.current=state;
        if (winFlag)
            losewin();
   buildLevel(current);
    }

    public void buildLevel(Level state){
        System.out.println(state);
        losewin.removeAll();
        grid = new GridLayout(state.getHeight(),state.getWidth());
        centerComponent.removeAll();
        centerComponent.setLayout(grid);

        for (int row = 0; row < state.getHeight(); row++) {
            for (int col = 0; col < state.getWidth(); col++) {
                JLabel positionPanel = new JLabel();
                if(!state.getPassable()[row][col]){ //if there is wall
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban2/icons/wall.png"));
                    positionPanel.setName("wall");
                }
                else if(row==state.getPlayerRow() && col==state.getPlayerCol()){ //if there its the starting position for the player
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban2/icons/player.png"));
                    positionPanel.setName("player");
                }
                else if(row==state.getTargetRow() && col== state.getTargetCol()){ //if it's the targets position
                    if (!winFlag) {
                        positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/Sokoban2/icons/blankmarked.png"));
                        positionPanel.setName("target");
                    }  else{
                        positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban2/icons/crate.png"));
                        positionPanel.setName("box");
                    }
                }
                else if(row==state.getBoxRow() && col== state.getBoxCol()){ //if it's the boxes starting position
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban2/icons/crate.png"));
                    positionPanel.setName("box");
                }
                else { //if it's a blank
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Sokoban2/icons/blank.png "));
                    positionPanel.setName("blank");
                }
                centerComponent.add(positionPanel);

            }
        }
        //TODO FIX
        frame.repaint();
        frame.validate();
        if(winFlag)
            losewin();
    }

    @Override
    public JComponent createCenterComponent() {
        centerComponent = new JPanel();
        centerComponent.setPreferredSize(new Dimension(200, 200));
        return centerComponent;
    }


    /**Set up for won or lost game*/
    public void losewin () {
        BoxLayout box = new BoxLayout(losewin, BoxLayout.Y_AXIS);
        losewin.setLayout(box);
        JLabel t = new JLabel();
        if (winFlag) { // if game is won
            if(lvlCounter!= Level.levels.length-1){
                t.setText("YOU MADE IT!");
                JPanel buttons = new JPanel();
                buttons.add(setTextButton("Next Level"));
                losewin.add(t);
                losewin.add(buttons);
                frame.add(losewin);
            } else {
                t.setText("YOU FINISHED THE GAME!");

                JPanel buttons = new JPanel();
                buttons.add(setTextButton("Restart the game!"));
                losewin.add(t);
                losewin.add(buttons);
                frame.add(losewin);
            }

            if (!winFlag) { // if game is lost
                t.setText("You lost the game :(");

                JPanel buttons = new JPanel();
                buttons.add(setTextButton("Restart Level"));
                losewin.add(t);
                losewin.add(buttons);
                frame.add(losewin);
            }

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }

    }
}
