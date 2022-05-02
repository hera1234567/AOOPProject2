package Sokoban;

import Test.src.Position;
import Test.src.Tower;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sokoban{

    private JFrame frame = new JFrame();
    private static JLabel[] arrayLabel;
    private Level level;
    private JPanel[][][] map;

    public Sokoban(Level level){
        this.level = level;
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        panel.setBorder(new EmptyBorder(new Insets(25, 30, 100, 100)));

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(500,500));
        mainPanel.setLayout(new GridLayout(level.getHeight(), level.getWidth()));
        panel.add(mainPanel);
        frame.add(panel);

        map = new JPanel[level.getWidth()*level.getHeight()][level.getHeight()][level.getWidth()];

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                JPanel positionPanel = new JPanel();
                positionPanel.setSize(300,300);

                if(!level.getPassable()[row][col]){ //if there is wall
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\wall.png")));
                    map[row+col][row][col] = positionPanel;
                }
                else if(row==level.getStartRow() && col==level.getStartCol()){ //if there its the starting position for the player
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\player.png")));
                }
                else if(row==level.getBoxStartRow() && col== level.getBoxStartCol()){ //if it's the boxes starting position
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\crate.png")));
                    positionPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) { //mouselistner added on the green steps, that will add a tower on click
                            pushBox();
                        }
                    });
                }
                else if(row==level.getTargetRow() && col== level.getTargetCol()){ //if it's the targets position
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blankmarked.png")));
                    positionPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) { //mouselistner added on the green steps, that will add a tower on click
                            finishLine();
                        }
                    });
                }
                else { //if it's a blank
                    positionPanel.add(new JLabel(new ImageIcon("C:\\Users\\hanna\\IdeaProjects\\AOOPProject\\src\\Sokoban\\icons\\blank.png")));
                    positionPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) { //mouselistner added on the green steps, that will add a tower on click
                            walkOnBlank();
                        }
                    });
                }
                mainPanel.add(positionPanel);
            }
        }

        frame.setVisible(true);
        //frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public void walkOnBlank() {
        System.out.println("Flytta!");
        //if the character is allowed to go there move there
        //check if it's pushing a box
            //check if the box is allowed to move there as well
            //check if box gets stuck after move then lose
        //change images at
    }

    public void pushBox() {
        System.out.println("Push!");
        //
    }

    public void finishLine() {
        System.out.println("Finish!");
        //
    }

    //main klassen som startar spelet
    public static void main(String[] args){
        Level level = Level.level1();
        Sokoban l = new Sokoban(level);
    }
}
