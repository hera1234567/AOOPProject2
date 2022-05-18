package Project.Test;

import Project.Framework.Frame;
import org.junit.jupiter.api.Assertions;
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
        JButton test = Project.Framework.Frame.setTextButton(s);
        Project.Framework.Frame.frame.add(test);
        assertEquals(s, test.getText());
        //What we write as the button label is what comes out when we take the button text
    }

    @Test
    void getTextButton() {
        String s = "hej";
        Project.Framework.Frame.frame.add(Project.Framework.Frame.setTextButton(s));
        JButton test = Project.Framework.Frame.getTextButton();
        assertEquals(s, test.getText());
    }

    @Test
    void getFrame() {
    Assertions.assertEquals(Project.Framework.Frame.frame, Project.Framework.Frame.getFrame());
        System.out.println(Project.Framework.Frame.frame.equals(Project.Framework.Frame.getFrame()));

    }

    @Test
    void getButton() {
        JButton test = Project.Framework.Frame.setTextButton("hej");
        Project.Framework.Frame.frame.add(test);
        Assertions.assertEquals(Project.Framework.Frame.getButton(), test);
        System.out.println(Frame.getButton().equals(test));
    }
}