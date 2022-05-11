package Sokoban2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayObserver extends Frame implements Observer{
    private GridLayout grid;
    JComponent centerComponent;
    Level [] level = new Level[20];
    int counter;
    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter) {
        level[counter] = state;
        counter++;
        System.out.println(level[counter]);
        if (winFlag||loseFlag)
        {
            final int[] i = {0};
            Timer t = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   buildLevel(level[i[0]], winFlag);
                    i[0] += 1;
                }
            });
            t.start();
            //TODO sätta timer och buildLevel utifrån det
        }
    }

    public void buildLevel(Level state, Boolean winFlag){
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
        frame.repaint();
        frame.validate();
    }

    @Override
    public JComponent createCenterComponent() {
        centerComponent = new JPanel();
        centerComponent.setPreferredSize(new Dimension(250, 200));
        return centerComponent;
    }
}
