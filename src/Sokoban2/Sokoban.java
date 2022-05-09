package Sokoban2;
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

        //TODO kolla positon istället för row & col
        //if it's walk on blank
        if (current.getPassable()[current.getPlayerRow()][current.getPlayerCol() + 1] && (current.getPlayerRow() != current.getBoxRow() )) {
            System.out.println("Walk right on blank");
            current.setPlayerCol(current.getPlayerCol() + 1);
        }
        //if it pushes a box
        else if ((current.getPlayerRow() == current.getBoxRow()
                && current.getPlayerCol() + 1 == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()][current.getBoxCol() + 1]) {
            if (current.getBoxRow() == current.getTargetRow() && current.getBoxCol() + 1 == current.getTargetCol()) {
                winFlag = true;
            }
            System.out.println("Walk right with box");
            current.setPlayerCol(current.getPlayerCol() + 1);
            current.setBoxCol(current.getBoxCol() + 1);
        }
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    public static void walkLeft() {
        //if walk on blank back
        if (current.getPassable()[current.getPlayerRow()][current.getPlayerCol() - 1]) {
            current.setPlayerRow(current.getPlayerRow());
            current.setPlayerCol(current.getPlayerCol() - 1);
        }
        //if push box back
        if ((current.getPlayerRow() == current.getBoxRow()
                && current.getPlayerCol() - 1 == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()][current.getBoxCol() - 1]) {
            if (current.getBoxRow() == current.getTargetRow() && current.getBoxCol() - 1 == current.getTargetCol()) {
                winFlag = true;
            }
            current.setPlayerRow(current.getPlayerRow());
            current.setPlayerCol(current.getPlayerCol() - 1);
            current.setBoxRow(current.getBoxRow());
            current.setBoxCol(current.getBoxCol() - 1);
        }
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    public static void walkDown() {
        System.out.println("Walk down in Sokoban");
        //if walk on blank back
        if (current.getPassable()[current.getPlayerRow() + 1][current.getPlayerCol()]) {
            current.setPlayerRow(current.getPlayerRow() + 1);
            current.setPlayerCol(current.getPlayerCol());
        }
        //if push box down
        if ((current.getPlayerRow() == current.getBoxRow() + 1
                && current.getPlayerCol() == current.getBoxCol())
                && current.getPassable()[current.getBoxRow() + 1][current.getBoxCol()]) {
            if (current.getBoxRow() + 1 == current.getTargetRow() && current.getBoxCol() == current.getTargetCol()) {
                winFlag = true;
            }
            current.setPlayerRow(current.getPlayerRow() + 1);
            current.setPlayerCol(current.getPlayerCol());
            current.setBoxRow(current.getBoxRow() + 1);
            current.setBoxCol(current.getBoxCol());
        }
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
    }

    public static void walkUp() {
        //if walk on blank back
        if (current.getPassable()[current.getPlayerRow() - 1][current.getPlayerCol()]) {
            current.setPlayerRow(current.getPlayerRow() - 1);
            current.setPlayerCol(current.getPlayerCol());
        }
        //if push box back
        if ((current.getPlayerRow() + 1 == current.getBoxRow()
                && current.getPlayerCol() == current.getBoxCol())
                && current.getPassable()[current.getBoxRow() - 1][current.getBoxCol()]) {
            if (current.getBoxRow() - 1 == current.getTargetRow() && current.getBoxCol() == current.getTargetCol()) {
                winFlag = true;
            }
            current.setPlayerRow(current.getPlayerRow() - 1);
            current.setPlayerCol(current.getPlayerCol());
            current.setBoxRow(current.getBoxRow() - 1);
            current.setBoxCol(current.getBoxCol());
        }
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, lvlCounter);
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