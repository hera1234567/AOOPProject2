package Sokoban;

import Test.src.Position;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class main extends Frame{
    private JPanel centerComponent;
    private Level level;
    private JLabel[][] map;
    private GridLayout grid;
    private Position player;


    public main(Level level){
        this.level = level;
        map = new JLabel[level.getHeight()][level.getWidth()];
        buildLevel();
    }

    private void buildLevel(){
        grid = new GridLayout(level.getHeight(),level.getWidth());
        centerComponent.removeAll();
        centerComponent.setLayout(grid);
        player = new Position(level.getPlayerRow(), level.getPlayerCol());

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                JLabel positionPanel = new JLabel();
                if(!level.getPassable()[row][col]){ //if there is wall
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\wall.png"));
                    positionPanel.setName("wall");
                    map[row][col] = positionPanel;
                }
                else if(row==level.getPlayerRow() && col==level.getPlayerCol()){ //if there its the starting position for the player
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png"));
                    positionPanel.setName("player");
                    map[row][col] = positionPanel;
                }
                else if(row==level.getTargetRow() && col== level.getTargetCol()){ //if it's the targets position
                    positionPanel.setIcon(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blankmarked.png"));
                    positionPanel.setName("target");
                    map[row][col] = positionPanel;
                    positionPanel.addMouseListener(walkOnBlank(row,col));
                }
                else if(row==level.getBoxRow() && col== level.getBoxCol()){ //if it's the boxes starting position
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
                    level.setPlayerRow(row);
                    level.setPlayerCol(col);
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
                System.out.println("BOX");
                //TODO sätt if statement i dessa med om nästa steg är the winning move, isåfall spela upp ett ljud och gå till en skärm med två knappar fortsätt eller inte
                if(player.equals(new Position(row+1,col)) && level.getPassable()[row-1][col]){
                    level.setPlayerCol(col);
                    level.setPlayerRow(row);
                    level.setBoxRow(row-1);
                } else if (player.equals(new Position(row-1,col))&& level.getPassable()[row+1][col]) {
                    level.setPlayerCol(col);
                    level.setPlayerRow(row);
                    level.setBoxRow(row+1);
                } else if (player.equals(new Position(row,col+1))&& level.getPassable()[row][col-1]) {
                    level.setPlayerCol(col);
                    level.setPlayerRow(row);
                    level.setBoxCol(col-1);
                } else if (player.equals(new Position(row,col-1))&& level.getPassable()[row][col+1]) {
                    level.setPlayerCol(col);
                    level.setPlayerRow(row);
                    level.setBoxCol(col+1);
                }
                buildLevel();
                if((!level.getPassable()[level.getBoxRow()][level.getBoxCol()+1]&&!level.getPassable()[level.getBoxRow()-1][level.getBoxCol()])||
                        (!level.getPassable()[level.getBoxRow()][level.getBoxCol()+1]&&!level.getPassable()[level.getBoxRow()+1][level.getBoxCol()])||
                        (!level.getPassable()[level.getBoxRow()][level.getBoxCol()-1]&&!level.getPassable()[level.getBoxRow()-1][level.getBoxCol()])||
                        (!level.getPassable()[level.getBoxRow()][level.getBoxCol()-1]&&!level.getPassable()[level.getBoxRow()+1][level.getBoxCol()])){
                    System.out.println("LOSER!!");
                    //TODO sätt ljud
                    //TODO fyll hela skärmen med en text och två knappar, fortsätt: börja om, exit: exit spel
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
        Level l = Level.level3();
        main m = new main(l);

    }
}
