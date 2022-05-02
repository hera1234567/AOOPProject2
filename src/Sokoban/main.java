package Sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
- If one box gets stuck = FAIL
- Use an abstract class for the frame
    - don't set anything so that it could be used for other games
- use a public GIT version control system (Github)
    - as a backup site
    - one person creates a private and adds the other as a collaborator
-
 */
public class main extends Frame{
    private JPanel centerComponent;
    private static JLabel[] arrayLabel;
    private Level level;
    private JPanel[][] map;
    private GridLayout grid;
    private int row;
    private int col;
    private int[] position;

    //måste hålla koll på vart playern är hela tiden och uppdatera denna
    //om spelaren är jämte lådan och trycker på den tomma rutan framför lådan flyttar de


    //FUNKA


    public main(Level level){
        this.level = level;
        map = new JPanel[level.getHeight()][level.getWidth()];
        buildLevel();
    }

    private void buildLevel(){
        grid = new GridLayout(level.getHeight(),level.getWidth());
        centerComponent.setLayout(grid);
        centerComponent.setPreferredSize(new Dimension(1000,1000));
        position[0] = level.getStartRow();
        position[1] = level.getStartCol();
        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                this.col = col;
                this.row = row;
                JPanel positionPanel = new JPanel();
                positionPanel.setSize(300,300);

                if(!level.getPassable()[row][col]){ //if there is wall
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\wall.png")));
                    positionPanel.setName("wall");
                    map[row][col] = positionPanel;
                }
                else if(row==level.getStartRow() && col==level.getStartCol()){ //if there its the starting position for the player
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png")));
                    positionPanel.setName("player");
                    map[row][col] = positionPanel;
                }
                else if(row==level.getBoxStartRow() && col== level.getBoxStartCol()){ //if it's the boxes starting position
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\crate.png")));
                    positionPanel.setName("box");
                    map[row][col] = positionPanel;
                    positionPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) { //mouselistner added on the green steps, that will add a tower on click
                           // pushBox(row, col);
                        }
                    });
                }
                else if(row==level.getTargetRow() && col== level.getTargetCol()){ //if it's the targets position
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blankmarked.png")));
                    positionPanel.setName("target");
                    map[row][col] = positionPanel;
                    positionPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) { //mouselistner added on the green steps, that will add a tower on click
                            //finishLine(row, col);
                        }
                    });
                }
                else { //if it's a blank
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png")));
                    positionPanel.setName("blank");
                    map[row][col] = positionPanel;
                    positionPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) { //mouselistner added on the green steps, that will add a tower on click
                            //walkOnBlank(row, col);
                        }
                    });
                }
                centerComponent.add(positionPanel);
            }
        }
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public JComponent createCenterComponent() {
        centerComponent = new JPanel();
        centerComponent.setPreferredSize(new Dimension(200,150));
        return centerComponent;
    }

    public void walkOnBlank(int row, int col) {
        System.out.println("Flytta!");

        //check if player is at any tile around
            //put player on this spot
            //put blank where player was
            //update positions in map
        //else
            // do nothing
    }

    public void pushBox(int row, int col) {
        System.out.println("Push!");
        //check if box is allowed to move there
            //check if player is at any tile around
                //put player on this spot
                //put blank where player was
                //put box on the one infront of it
                //update positions in map
            //else
                // do nothing
        //else
            //do nothing

    }

    public void finishLine(int row, int col) {
        System.out.println("Finish!");
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
        Level l = Level.level2();
        main m = new main(l);
    }
}
