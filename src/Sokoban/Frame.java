package Sokoban;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Frame extends JFrame{

    private JLabel t = new JLabel();
    public JComponent centercomponent;
    public JFrame frame = new JFrame();
    public abstract JComponent createCenterComponent();


    public Frame(){
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        panel.setBorder(new EmptyBorder(new Insets(20, 20, 10, 10)));
        centercomponent = createCenterComponent();
        panel.add(centercomponent);
        panel.add(t);
        frame.add(panel);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keys(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    protected abstract void keys(KeyEvent e);


}
