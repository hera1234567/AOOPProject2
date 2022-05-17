package Project.Sokoban;

import Project.Framework.*;
import Project.Framework.Frame;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The type Replay observer.
 */
public class ReplayObserver implements Observer<Level> {

    File[] file = new File[100];
    int counter =0;


    private void saveScreenShot() {
        BufferedImage img = new BufferedImage(Frame.getFrame().getWidth(), Frame.getFrame().getHeight(), BufferedImage.TYPE_INT_RGB);
        Frame.getFrame().paint(img.getGraphics());
        file[counter]= new File("saved.png");
        try {
            ImageIO.write(img, "png", file[counter]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter++;
    }

    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter){
        saveScreenShot();

    }

    /*private GridLayout grid;

    JComponent centerComponent;

    Level[] level = new Level[20];

    int counter;
    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter) {

        level[counter] = state;
        counter++;
        System.out.println(counter);
        if (winFlag||loseFlag)
        {
            Timer t = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int i = 0;
                   buildLevel(level[i], winFlag);
                    i ++;
                }
            });
            t.start();
            //TODO sätta timer och buildLevel utifrån det
        }
    }


     * Build level.
     *
     * @param state   the state
     * @param winFlag the win flag

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
    }*/
}
