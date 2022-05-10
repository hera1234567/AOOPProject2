package Sokoban2;

/**
 * Graphic Setup
 * */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class Frame extends JFrame{

    private JLabel t = new JLabel();
    private static JButton button= new JButton();
    public JComponent centercomponent;
    public static JFrame frame = new JFrame();

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
        //button.addActionListener(Events.actionListenerUpdateLevel());
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);

    }

    public JButton setTextButton(String args){
        button.setText(args);
        return button;
    }

    public static JButton getTextButton(){return button;}

    public static JFrame getFrame(){return frame;}

    public static JButton getButton(){return button;}

}