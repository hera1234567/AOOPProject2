package Project.Sokoban;

import Project.Framework.Observer;

import javax.swing.*;

/**
 * The type Position observer.
 * An observer class that takes prints each move made by the player (in columns and rows) and the current level
 */
public class PositionObserver implements Observer<Level> {

    private JFrame frame;
    private JTextArea position;
    private int level;

    /**
     * Instantiates a new Position observer.
     */
    public PositionObserver() {
        level = 1;
        frame = new JFrame();
        frame.setTitle("Player Position");
        position = new JTextArea(100,25);
        position.append("Level " + level + "\n");
        frame.add(position);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter) {
        if (level!=lvlCounter+1){
            level = lvlCounter+1;
            position.append("Level " + level + "\n");
        }
        position.append("Row: " + state.getPlayerRow() + ", Col: " + state.getPlayerCol() + "\n");
        frame.repaint();
        frame.revalidate();
    }

}