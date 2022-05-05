package Sokoban;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class OptionScreen extends Frame{

    private JPanel centerComponent;
    public abstract ActionListener exit();
    public abstract ActionListener contin();

    public OptionScreen(){
        JButton exit = new JButton();
        JButton contin = new JButton();
        exit.addActionListener(exit());
        contin.addActionListener(contin());

        centerComponent.add(exit);
        centerComponent.add(contin);
    }

    @Override
    public JComponent createCenterComponent() {
        centerComponent = new JPanel();
        return centerComponent;
    }
}
