package Project.Framework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The type Frame.
 * A basic graphic setup that can be extended
 */
public abstract class Frame extends JFrame{

    private JLabel t = new JLabel();
    private static JButton button= new JButton();
    /**
     * The Centercomponent.
     * public instance variable that makes it possible to manipulate the graphics
     */
    public JComponent centercomponent;
    /**
     * The constant frame.
     */
    public static JFrame frame = new JFrame();

    /**
     * Create center component j component.
     * Method that defines centerComponent in class extending Frame
     * @return the j component
     */
    public abstract JComponent createCenterComponent();

    /**
     * Instantiates a new Frame.
     */
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);

    }

    /**
     * Set text button j button.
     *
     * @param args text to be set on button
     * @return the j button
     */
    public static JButton setTextButton(String args){
        button.setText(args);
        return button;
    }

    /**
     * Get text button j button.
     *
     * @return the j button
     */
    public static JButton getTextButton(){return button;}

    /**
     * Get frame j frame.
     *
     * @return the j frame
     */
    public static JFrame getFrame(){return frame;}

    /**
     * Get button j button.
     *
     * @return the j button
     */
    public static JButton getButton(){return button;}

}