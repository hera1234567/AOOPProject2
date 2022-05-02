package Test.src;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;

/**
 * Basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence extends JFrame {

  // A map that assigns a panel to each position in the game
  private final Map<Position, JPanel> positionPanels = new HashMap<>();
  // The size of each position panel
  private static final int POSITION_SIZE = 100;

  // A timer that will automatically advance the game each second.
  private final Timer timer;
  private static final int SPEED = 1000;
  private static final int PAUSE = 5000;

  private Monster monster;
  private Tower tower;
  private ArrayList<Tower> allTowers = new ArrayList<>();

  // A representation of the complete game
  private TowerDefenceLevel level;

  public static void main(String[] args) {

    // Change this to try out different levels
    TowerDefenceLevel level = TowerDefenceLevel.buildGridLevel();

    // Create the GUI and set it to be visible
    GUITowerDefence gui = new GUITowerDefence(level);
    gui.setVisible(true);

  }

  public GUITowerDefence(TowerDefenceLevel level) {

    this.setTitle("Tower Defence");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.level = level;
    int levelHeight = level.getHeight();
    int levelWidth = level.getWidth();

    this.setSize(levelWidth*POSITION_SIZE, levelHeight*POSITION_SIZE);
    this.setResizable(false);

    // A 'main panel' that contains all other components of the GUI
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(levelHeight, levelWidth));
    this.add(mainPanel);
    monster = new Monster(new Position(1,0),level, new Position(2,7));

    for (int row = 0; row < levelHeight; row++) {
      for (int col = 0; col < levelWidth; col++) {
        JPanel positionPanel = new JPanel();
        positionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if(!level.getpassable()[row][col]){ //if the step is false
          positionPanel.setBackground(Color.green); //make the steps green
          int finalRow = row;
          int finalCol = col;
          positionPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { //mouselistner added on the green steps, that will add a tower on click
                    tower = new Tower(new Position(finalRow, finalCol));
                    positionPanel.add(buildTowerLabel());
                    allTowers.add(tower);
                    revalidate();
                    repaint();

            }
          });

        }
        else if(row==1 && col==0){
          positionPanel.add(buildMonsterPanel(monster.getHealth())); //adding the monster label and health label
        }
        mainPanel.add(positionPanel);
        // Add the panel to the 'positionPanels' map so we can access it
        // later (with positionPanels.get(position)).
        Position position = level.getPosition(row, col);
        positionPanels.put(position, positionPanel);
      }
    }

    // Start the timer and set it to call the event loop each second
    EventLoop loop = new EventLoop();
    timer = new Timer(SPEED, loop);
    timer.setInitialDelay(PAUSE);
    timer.start();

  }

  // ---------- Event handling --------------------
  class EventLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      boolean gameOver = false;
      if(monster.getHealth()==0) { //if the monster is at 0 health the game is over
        gameOver = true;
      }
      if (gameOver) { //if the game is over timer stops and title is changed
        setTitle("Winner!");
        timer.stop();
      } else if (monster.getMonCol() == 7 && monster.getMonRow() == 2) { //if the monster is at the goal, timer stops and title changes
        setTitle("Loooser!");
        timer.stop();
      }

      positionPanels.get(monster.getPos()).removeAll(); //removes previous image on the positionspanel
      monster.MonsterMoving(); //monster moves one step
      // add buildMonsterPanel on the monsters new step
      JPanel m = new JPanel();
      JPanel next = new JPanel();

      m= buildMonsterPanel(monster.getHealth());
      next = positionPanels.get(monster.getPos());
      next.add(m);

      for (Tower t:allTowers) { //if the there is a tower, shoot the monster, once per step
          if (monster.getPos().shortestDistance(t.getTowerPos())<=1) {
            monster.monsterIsHit();
          }
      }
      // These two commands are necessary to properly
      // display all the updated elements of the GUI.
      revalidate();
      repaint();

    }
  }


  // ----------- Helper methods ---------------------

  // Helper method to construct a JLabel with a given image
  private JLabel getIconLabel(String fileName) {
    URL url = this.getClass().getResource(fileName);
    ImageIcon ii = new ImageIcon(url);
    return new JLabel(ii);
  }

  // Just some examples, you can change them however you like.
  private JLabel buildTowerLabel() {
    return getIconLabel("Resources/tower-icon.png");

  }
  private JPanel buildMonsterPanel(int monsterHealth) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BorderLayout());

    JLabel monsterIcon = getIconLabel("monster10.gif");
    panel.add(monsterIcon, BorderLayout.CENTER);

    JLabel healthLabel = new JLabel(Integer.toString(monsterHealth));
    healthLabel.setFont(new Font("Serif", Font.BOLD, 10));
    panel.add(healthLabel, BorderLayout.SOUTH);

    return panel;
  }

}
