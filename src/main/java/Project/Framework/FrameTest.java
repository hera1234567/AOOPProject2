package main.java.Project.Framework;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrameTest {

    @Test
    void createCenterComponent() {
        JPanel centerComponent = new JPanel();
        centerComponent.setPreferredSize(new Dimension(250, 200));
    }

    @Test
    void setTextButton() {
        String s = "hej";
        Frame.frame.add(Frame.setTextButton(s));
        JButton test = Frame.getTextButton();
        assertEquals(s, test.getText());
        //What we write as the button label is what comes out when we take the button text
    }

    @Test
    void getTextButton() {
    }

    @Test
    void getFrame() {

    }

    @Test
    void getButton() {
    }
}