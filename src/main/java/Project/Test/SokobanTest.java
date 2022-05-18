package Project.Test;

import Project.Framework.Frame;
import Project.Framework.InputMethod;
import Project.Framework.KeyPad;
import Project.Framework.Observer;
import Project.Sokoban.GraphicObserver;
import Project.Sokoban.PositionObserver;
import Project.Sokoban.Sokoban;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SokobanTest {

    @Test
    void setInputMethod() {
        Sokoban sok = new Sokoban();
        InputMethod m = new KeyPad(Frame.getFrame());
        sok.setInputMethod(m);
        assertEquals(sok.getInputMethod(), m);
    }

    @Test
    void addObserver() {
    GraphicObserver gra1 = new GraphicObserver();
        GraphicObserver gra2 = new GraphicObserver();
        GraphicObserver gra3 = new GraphicObserver();
        GraphicObserver gra4 = new GraphicObserver();
    PositionObserver pos1 = new PositionObserver();
        PositionObserver pos2 = new PositionObserver();
        PositionObserver pos3 = new PositionObserver();
        PositionObserver pos4 = new PositionObserver();
        Sokoban s = new Sokoban();
        s.addObserver(gra1);
        s.addObserver(gra2);
        s.addObserver(gra3);
        s.addObserver(gra4);
        s.addObserver(pos1);
        s.addObserver(pos2);
        s.addObserver(pos3);
        s.addObserver(pos4);
        List<Observer> obs = new ArrayList<Observer>();
        obs.add(gra1);
        obs.add(gra2);
        obs.add(gra3);
        obs.add(gra4);
        obs.add(pos1);
        obs.add(pos2);
        obs.add(pos3);
        obs.add(pos4);

        assertEquals(s.getObservers(), obs);


    }

}