package Test.src;

import javax.swing.*;
import java.util.*;
public class Tower extends JButton{
    private Position pos;
    private ArrayList<Position> towers = new ArrayList<>();

    public Tower(Position pos){
        this.pos = pos;
        towers.add(pos);
    }


    public Position getTowerPos(){
        return pos;
    }

}

