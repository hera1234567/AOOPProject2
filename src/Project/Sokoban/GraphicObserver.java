package Project.Sokoban;

import Project.Framework.Frame;
import Project.Framework.Observer;

import javax.swing.*;
import java.awt.*;


/**
 * The type Graphic observer.
 * An extention of Frame that defines the graphics
 */
public class GraphicObserver extends Frame implements Observer {
    private GridLayout grid;
    private JPanel centerComponent;
    private JPanel losewin = new JPanel();
    private int lvlCounter;
    private boolean winFlag = false;
    private Level current;
    private boolean loseFlag = false;

    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter) {
        this.winFlag=winFlag;
        this.loseFlag=loseFlag;
        this.lvlCounter=lvlCounter;
        this.current=state;
        if (winFlag)
            win();

        else if (loseFlag)
            lose();
        buildLevel(current);
    }

    /**
     * Build level.
     *
     * Builds the graphics on a grid-pattern every time an update has been sent out.
     *
     * @param state the current state of the level
     */
    public void buildLevel(Level state){
        losewin.removeAll();
        grid = new GridLayout(state.getHeight(),state.getWidth());
        centerComponent.removeAll();
        centerComponent.setLayout(grid);

        for (int row = 0; row < state.getHeight(); row++) {
            for (int col = 0; col < state.getWidth(); col++) {
                JLabel positionPanel = new JLabel();
                if(!state.getPassable()[row][col]){ //if there is wall
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Project/icons/wall.png"));
                    positionPanel.setName("wall");
                }
                else if(row==state.getPlayerRow() && col==state.getPlayerCol()){ //if there its the starting position for the player
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Project/icons/player.png"));
                    positionPanel.setName("player");
                }
                else if(row==state.getTargetRow() && col== state.getTargetCol()){ //if it's the targets position
                    if (!winFlag) {
                        positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/Project/icons/blankmarked.png"));
                        positionPanel.setName("target");
                    }  else{
                        positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Project/icons/crate.png"));
                        positionPanel.setName("box");
                    }
                }
                else if(row==state.getBoxRow() && col== state.getBoxCol()){ //if it's the boxes starting position
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Project/icons/crate.png"));
                    positionPanel.setName("box");
                }
                else { //if it's a blank
                    positionPanel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Project/icons/blank.png "));
                    positionPanel.setName("blank");
                }
                centerComponent.add(positionPanel);

            }
        }
        frame.repaint();
        frame.validate();
        if(winFlag)
            win();
        else if(loseFlag)
            lose();
    }

    @Override
    public JComponent createCenterComponent() {
        centerComponent = new JPanel();
        centerComponent.setPreferredSize(new Dimension(250, 200));
        return centerComponent;
    }


    /**
     * Win.
     *
     * Adds a text and a button to the frame if the game is won.
     */
    public void win () {
        BoxLayout box = new BoxLayout(losewin, BoxLayout.Y_AXIS);
        losewin.setLayout(box);
        JLabel t = new JLabel();
        if (winFlag) { // if game is won
            if (lvlCounter != Level.levels.length - 1) {
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
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Lose.
     *
     * Adds a text and a button to the frame if the game is lost.
     */
    public void lose(){
        BoxLayout box = new BoxLayout(losewin, BoxLayout.Y_AXIS);
        losewin.setLayout(box);
        JLabel t = new JLabel();
        t.setText("You lost the game :(");
        JPanel buttons = new JPanel();
        buttons.add(setTextButton("Restart Level"));
        losewin.add(t);
        losewin.add(buttons);
        frame.add(losewin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}