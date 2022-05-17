package Project.Framework;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrameTest {

    @Test
    void createCenterComponent() {
    }

    @Test
    void setTextButton() {
        String s = "hej";
        JButton test = Frame.setTextButton(s);
        Frame.frame.add(test);
        assertEquals(s, test.getText());
        //What we write as the button label is what comes out when we take the button text
    }

    @Test
    void getTextButton() {
        String s = "hej";
        Frame.frame.add(Frame.setTextButton(s));
        JButton test = Frame.getTextButton();
        assertEquals(s, test.getText());
    }

    @Test
    void getFrame() {
    assertEquals(Frame.frame, Frame.getFrame());
        System.out.println(Frame.frame.equals(Frame.getFrame()));

    }

    @Test
    void getButton() {
        JButton test = Frame.setTextButton("hej");
        Frame.frame.add(test);
        assertEquals(Frame.getButton(), test);
        System.out.println(Frame.getButton().equals(test));
    }
}