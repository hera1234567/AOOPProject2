package Sokoban;

import Test.src.Position;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private JLabel[][] map;
    private GridLayout grid;
    private int row;
    private int col;
    private Position player;
    private ImageIcon boxIm;

    //måste hålla koll på vart playern är hela tiden och uppdatera denna
    //om spelaren är jämte lådan och trycker på den tomma rutan framför lådan flyttar de


    //FUNKA


    public main(Level level){
        this.level = level;
        map = new JLabel[level.getHeight()][level.getWidth()];
        buildLevel();
    }

    private void buildLevel(){
        grid = new GridLayout(level.getHeight(),level.getWidth());
        centerComponent.setLayout(grid);
        //centerComponent.setPreferredSize(new Dimension(level.getWidth()*100,level.getHeight()*100));
        //centerComponent.revalidate();
        //centerComponent.repaint();
        player = new Position(level.getStartRow(), level.getStartCol());

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                this.col = col;
                this.row = row;
                JLabel positionPanel = new JLabel();

                if(!level.getPassable()[row][col]){ //if there is wall
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\wall.png"));
                    positionPanel.setName("wall");
                    map[row][col] = positionPanel;
                }
                else if(row==level.getStartRow() && col==level.getStartCol()){ //if there its the starting position for the player
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png"));
                    positionPanel.setName("player");
                    map[row][col] = positionPanel;
                }
                else if(row==level.getTargetRow() && col== level.getTargetCol()){ //if it's the targets position
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blankmarked.png"));
                    positionPanel.setName("target");
                    map[row][col] = positionPanel;
                }
                else if(row==level.getBoxStartRow() && col== level.getBoxStartCol()){ //if it's the boxes starting position
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\crate.png"));
                    positionPanel.setName("box");
                    map[row][col] = positionPanel;
                    positionPanel.addMouseListener(pushBox(row,col));
                }
                else { //if it's a blank
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png"));
                    positionPanel.setName("blank");
                    map[row][col] = positionPanel;
                    positionPanel.addMouseListener(walkOnBlank(row,col));
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
        centerComponent.setPreferredSize(new Dimension(200,200));
        return centerComponent;
    }

    public MouseListener walkOnBlank(int row, int col) {
        System.out.println("BLANK");
        MouseListener m = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(player.equals(new Position(row+1,col)) || player.equals(new Position(row-1,col))||player.equals(new Position(row,col+1)) ||player.equals(new Position(row,col-1))){

                    map[row][col].setName("player"); //give clicked tile player name
                    map[player.getRow()][player.getCol()].setName("blank");


                    map[row][col].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png"));
                    map[player.getRow()][player.getCol()].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png"));

                    map[player.getRow()][player.getCol()].addMouseListener(walkOnBlank(player.getRow(),player.getCol()));

                    map[row][col].repaint();
                    map[row][col].revalidate();
                    map[player.getRow()][player.getCol()].repaint();
                    map[player.getRow()][player.getCol()].revalidate();
                    frame.repaint();
                    frame.revalidate();

                    player = new Position(row, col);


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
                System.out.println("BOX");
                if(player.equals(new Position(row+1,col)) && map[row-1][col].getName()!="wall"){
                    if(map[row-1][col].getName()=="target"){

                    } else {
                        JLabel temp = map[row][col];



                        //changed names
                        map[row][col].setName("player"); //give clicked tile player name
                        map[player.getRow()][player.getCol()].setName("blank"); //give name for previous tile
                        map[row-1][col].setName("box"); //give name box at next tile

                        //changed pictures
                        map[row][col].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png"));
                        map[player.getRow()][player.getCol()].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png"));
                        map[row-1][col].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\crate.png"));

                        //change the mouselisteners
                        map[player.getRow()][player.getCol()].addMouseListener(walkOnBlank(player.getRow(),player.getCol()));
                        map[row-1][col].addMouseListener(pushBox(row-1,col));

                        map[row][col].repaint();
                        map[row][col].revalidate();
                        map[player.getRow()][player.getCol()].repaint();
                        map[player.getRow()][player.getCol()].revalidate();
                        map[row - 1][col].repaint();
                        map[row - 1][col].revalidate();
                        frame.repaint();
                        frame.revalidate();

                        //update player position
                        player = new Position(row, col);
                        System.out.println("clicked: " + row + " " + col + " name: " + map[row][col].getName());
                    }
                } else if (player.equals(new Position(row-1,col))&& map[row+1][col].getName()!="wall") {
                    //changed names
                    map[row][col].setName("player"); //give clicked tile player name
                    map[player.getRow()][player.getCol()].setName("blank"); //give name for previous tile
                    map[row+1][col].setName("box"); //give name box at next tile

                    //changed pictures
                    map[row][col].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png"));
                    map[player.getRow()][player.getCol()].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png"));
                    map[row+1][col].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\crate.png"));

                    //change the mouselisteners
                    map[player.getRow()][player.getCol()].addMouseListener(walkOnBlank(player.getRow(),player.getCol()));
                    map[row+1][col].addMouseListener(pushBox(row+1,col));

                    map[row][col].repaint();
                    map[row][col].revalidate();
                    map[player.getRow()][player.getCol()].repaint();
                    map[player.getRow()][player.getCol()].revalidate();
                    map[row+1][col].repaint();
                    map[row+1][col].revalidate();
                    frame.repaint();
                    frame.revalidate();

                    //update player position
                    player = new Position(row, col);
                    System.out.println("clicked: " + row + " " + col + " name: " + map[row][col].getName());

                } else if (player.equals(new Position(row,col+1))&& map[row][col-1].getName()!="wall") {
                    //changed names
                    map[row][col].setName("player"); //give clicked tile player name
                    map[player.getRow()][player.getCol()].setName("blank"); //give name for previous tile
                    map[row][col-1].setName("box"); //give name box at next tile

                    //changed pictures
                    map[row][col].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png"));
                    map[player.getRow()][player.getCol()].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png"));
                    map[row][col-1].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\crate.png"));

                    //change the mouselisteners
                    map[player.getRow()][player.getCol()].addMouseListener(walkOnBlank(player.getRow(),player.getCol()));
                    map[row][col-1].addMouseListener(pushBox(row,col-1));

                    map[row][col].repaint();
                    map[row][col].revalidate();
                    map[player.getRow()][player.getCol()].repaint();
                    map[player.getRow()][player.getCol()].revalidate();
                    map[row][col-1].repaint();
                    map[row][col-1].revalidate();
                    frame.repaint();
                    frame.revalidate();

                    //update player position
                    player = new Position(row, col);
                    System.out.println("clicked: " + row + " " + col + " name: " + map[row][col].getName());
                } else if (player.equals(new Position(row,col-1))&& map[row][col+1].getName()!="wall") {
                    //changed names
                    map[row][col].setName("player"); //give clicked tile player name
                    map[player.getRow()][player.getCol()].setName("blank"); //give name for previous tile
                    map[row][col+1].setName("box"); //give name box at next tile

                    //changed pictures
                    map[row][col].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png"));
                    map[player.getRow()][player.getCol()].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png"));
                    map[row][col+1].setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\crate.png"));

                    //change the mouselisteners
                    map[player.getRow()][player.getCol()].addMouseListener(walkOnBlank(player.getRow(),player.getCol()));
                    map[row][col+1].addMouseListener(pushBox(row,col+1));

                    map[row][col].repaint();
                    map[row][col].revalidate();
                    map[player.getRow()][player.getCol()].repaint();
                    map[player.getRow()][player.getCol()].revalidate();
                    map[row][col+1].repaint();
                    map[row][col+1].revalidate();
                    frame.repaint();
                    frame.revalidate();

                    //update player position
                    player = new Position(row, col);
                    System.out.println("clicked: " + row + " " + col + " name: " + map[row][col].getName());
                }
                    //TODO kolla om boxen har två väggar på närliggande sidor har man förlorat



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
        Level l = Level.level3();
        main m = new main(l);

    }
}
