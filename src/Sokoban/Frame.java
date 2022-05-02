package Sokoban;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class Frame extends JFrame{

    private JLabel t = new JLabel();
    public JPanel centercomponent;
    public JFrame frame = new JFrame();
    public abstract JComponent createCenterComponent();


    public Frame(){
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        panel.setBorder(new EmptyBorder(new Insets(25, 30, 50, 50)));
        centercomponent = (JPanel) createCenterComponent();
        panel.add(centercomponent);
        panel.add(t);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

}
