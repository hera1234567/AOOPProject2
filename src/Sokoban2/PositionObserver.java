package Sokoban2;

import javax.swing.*;

public class PositionObserver implements Observer{

    private JFrame frame;
    private JTextArea position;
    private int level;

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
