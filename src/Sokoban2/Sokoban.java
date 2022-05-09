package Sokoban2;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

public class Sokoban {

    //region static variables
    public static int lvlCounter;
    public static Level current;
    private static boolean winFlag;
    //endregion

    public Sokoban() {
        lvlCounter = 0;
        current = setLevel(lvlCounter);
        winFlag = false;
    }

    //region observers & add
    private static List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer so) {
        observers.add(so);
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }
    //endregion

    private static Level setLevel(int i) {
        current = new Level(Level.levels[i].getHeight(), Level.levels[i].getWidth(), Level.levels[i].getPassable(),
                Level.levels[i].getPlayerRow(), Level.levels[i].getPlayerCol(),
                Level.levels[i].getTargetRow(), Level.levels[i].getTargetCol(),
                Level.levels[i].getBoxRow(), Level.levels[i].getBoxCol());
        return current;
    }

    //region updates on same level
    public static void walkRight() {
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() == current.getBoxRow()
                && current.getPlayerCol() + 1 == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()][current.getBoxCol() + 1]) {
            if (current.getBoxRow() == current.getTargetRow() && current.getBoxCol() + 1 == current.getTargetCol()) {
                winFlag = true;
            }
            System.out.println("Walk right with box");
            current.setPlayerCol(current.getPlayerCol() + 1);
            current.setBoxCol(current.getBoxCol() + 1);
            if (!winFlag)
            checkLostGame(current.getPlayerRow(), current.getPlayerCol());
            //TODO kalla funktion som kollar om det är vägg på båda sidorna
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow()][current.getPlayerCol() + 1]) {
            System.out.println("Walk right on blank");
            current.setPlayerCol(current.getPlayerCol() + 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    public static void walkLeft() {
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() == current.getBoxRow()
                && current.getPlayerCol() - 1 == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()][current.getBoxCol() - 1]) {
            if (current.getBoxRow() == current.getTargetRow() && current.getBoxCol() - 1 == current.getTargetCol()) {
                winFlag = true;
            }
            System.out.println("Walk left with box");
            current.setPlayerCol(current.getPlayerCol() - 1);
            current.setBoxCol(current.getBoxCol() - 1);
            checkLostGame(current.getPlayerRow(), current.getPlayerCol());
            //TODO kalla funktion som kollar om det är vägg på båda sidorna
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow()][current.getPlayerCol() - 1]) {
            System.out.println("Walk left on blank");
            current.setPlayerCol(current.getPlayerCol() - 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    public static void walkDown() {
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() +1 == current.getBoxRow()
                && current.getPlayerCol()  == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()+1][current.getBoxCol() ]) {
            if (current.getBoxRow()+1 == current.getTargetRow() && current.getBoxCol()  == current.getTargetCol()) {
                winFlag = true;
            }
            System.out.println("Walk down with box");
            current.setPlayerRow(current.getPlayerRow() + 1);
            current.setBoxRow(current.getBoxRow() + 1);
            checkLostGame(current.getPlayerRow(), current.getPlayerCol());
            //TODO kalla funktion som kollar om det är vägg på båda sidorna
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow() +1][current.getPlayerCol()]) {
            System.out.println("Walk down on blank");
            current.setPlayerRow(current.getPlayerRow() + 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    public static void walkUp() {
        //if walk on blank back
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() - 1 == current.getBoxRow()
                && current.getPlayerCol() == current.getBoxCol())
                && current.getPassable()[current.getBoxRow() - 1][current.getBoxCol()]) {
            if (current.getBoxRow() - 1 == current.getTargetRow() && current.getBoxCol() == current.getTargetCol()) {
                winFlag = true;
            }
            System.out.println("Walk up with box");
            current.setPlayerRow(current.getPlayerRow() - 1);
            current.setBoxRow(current.getBoxRow() - 1);
            checkLostGame(current.getPlayerRow(), current.getPlayerCol());
            //TODO kalla funktion som kollar om det är vägg på båda sidorna
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow() - 1][current.getPlayerCol()]) {
            System.out.println("Walk up on blank");
            current.setPlayerRow(current.getPlayerRow() - 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    public static void checkLostGame(int row, int col){
        System.out.println("check lost game");
        if(((!current.getPassable()[current.getBoxRow()][current.getBoxCol()+1]&&!current.getPassable()[current.getBoxRow()-1][current.getBoxCol()]) && !(current.getTargetRow()==row-1 && current.getTargetCol()==col && current.getPassable()[row-1][col]))||
                ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()+1]&&!current.getPassable()[current.getBoxRow()+1][current.getBoxCol()]) && !(current.getTargetRow()==row+1 && current.getTargetCol()==col && current.getPassable()[row+1][col]))||
                ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()-1]&&!current.getPassable()[current.getBoxRow()-1][current.getBoxCol()]) && !(current.getTargetRow()==row && current.getTargetCol()==col-1 && current.getPassable()[row][col-1]))||
                ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()-1]&&!current.getPassable()[current.getBoxRow()+1][current.getBoxCol()]) && !(current.getTargetRow()==row && current.getTargetCol()==col+1 && current.getPassable()[row][col+1]))){

           winFlag=false;}
    }
    //endregion

    //region restart or change of level
    static void nextLevel() {
        winFlag = false;
        lvlCounter++;
        setLevel(lvlCounter);
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);

    }

    static void restartGame() {
        winFlag = false;
        lvlCounter = 0;
        setLevel(lvlCounter);
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    static void restartLevel() {
        winFlag = false;
        setLevel(lvlCounter);
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }
    //endregion

}