package Sokoban2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Observer2 implements Observer{

    private JLabel centerComponent;
    private JFrame frame;
    private JTextField position;

    public Observer2() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        panel.setBorder(new EmptyBorder(new Insets(20, 20, 10, 10)));
        centerComponent = new JLabel();
        centerComponent.setPreferredSize(new Dimension(100,100));
        position = new JTextField("Start");
        centerComponent.add(position);
        panel.add(centerComponent);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void updateCurrentState(Level state, boolean winFlag, int lvlCounter) {
        position.setText("Row: " + state.getPlayerRow() + ", Col: " + state.getPlayerCol());
        System.out.println(position.getText());
        centerComponent.repaint();
        centerComponent.revalidate();
        frame.repaint();
        frame.revalidate();
    }

}
