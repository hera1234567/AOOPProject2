package Project.Sokoban;

import Project.Framework.*;
import java.util.ArrayList;
import java.util.List;

@ClassPreamble(
        author = "Hanna Martinsson, Amanda Törnqvist",
        date = "12/5-2022"
)

/**
 * The type Sokoban.
 */
public class Sokoban implements Controller {

//region static variables
    private static int lvlCounter;
    private static Level current;
    private static boolean winFlag;
    private static boolean loseFlag;

    //TODO FIXA SÅ ATT DET GÅR MED MOUSPAD OCKSÅ
    private InputMethod m = new KeyPad(Frame.getFrame());

    //endregion

    /**
     * Instantiates a new Sokoban.
     */
    public Sokoban() {
        lvlCounter = 0;
        current = setLevel(lvlCounter);
        winFlag = false;
        loseFlag = false;
        m.setController(this);
    }

    /**
     * Set input method.
     *
     * @param m the input method
     */
    public void setInputMethod(InputMethod m){
        this.m = m;
        m.setController(this);
    }

    //region observers & add
    private static List<Observer> observers = new ArrayList<Observer>();

    /**
     * Add observer.
     *
     * @param so the observer to be added
     */
    public void addObserver(Observer so) {
        observers.add(so);
        for (Observer o : observers)
            o.updateCurrentState(current, loseFlag, winFlag, lvlCounter);
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
    @Override
    public void clickedPosition(int xMouse, int yMouse, int frameWidth, int frameHeight) {
        int columns = (frameWidth/current.getWidth()) * current.getPlayerCol();
        int oneCol = frameWidth/current.getWidth();
        int oneRow = frameHeight/(current.getHeight());
        int rows = (frameHeight/(current.getHeight()))*current.getPlayerRow();

        if (columns < (xMouse-10) && (rows<(yMouse-10) && (rows+oneRow)>(yMouse-10))){
            walkRight();
        }
        else if (columns > xMouse && (rows<(yMouse-10) && (rows+oneRow)>(yMouse-10))){
            walkLeft();
        }
        else if (rows+32 > yMouse && (columns+oneCol>(xMouse-10) && (columns-oneCol < (xMouse-10)))){
            walkUp();
        }
        else if ((rows-32) < yMouse && (columns+oneCol>(xMouse-10) && (columns-oneCol < (xMouse-10)))){
            walkDown();
        }
    }

    public void walkRight() {
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() == current.getBoxRow()
                && current.getPlayerCol() + 1 == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()][current.getBoxCol() + 1]) {
            if (current.getBoxRow() == current.getTargetRow() && current.getBoxCol() + 1 == current.getTargetCol()) {
                winFlag = true;
            }
            current.setPlayerCol(current.getPlayerCol() + 1);
            current.setBoxCol(current.getBoxCol() + 1);
            if (!winFlag)
                checkLostGame();
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow()][current.getPlayerCol() + 1]) {
            if (current.getPlayerCol()+1== current.getBoxCol() &&current.getPlayerRow()== current.getBoxRow())
                return;
            current.setPlayerCol(current.getPlayerCol() + 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, loseFlag, lvlCounter);
    }

    public void walkLeft() {
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() == current.getBoxRow()
                && current.getPlayerCol() - 1 == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()][current.getBoxCol() - 1]) {
            if (current.getBoxRow() == current.getTargetRow() && current.getBoxCol() - 1 == current.getTargetCol()) {
                winFlag = true;
            }
            current.setPlayerCol(current.getPlayerCol() - 1);
            current.setBoxCol(current.getBoxCol() - 1);
            checkLostGame();
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow()][current.getPlayerCol() - 1]) {
            if (current.getPlayerCol()-1== current.getBoxCol() &&current.getPlayerRow()== current.getBoxRow())
                return;
            current.setPlayerCol(current.getPlayerCol() - 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, loseFlag, lvlCounter);
    }

    public void walkDown() {
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() +1 == current.getBoxRow()
                && current.getPlayerCol()  == current.getBoxCol())
                && current.getPassable()[current.getBoxRow()+1][current.getBoxCol() ]) {
            if (current.getBoxRow()+1 == current.getTargetRow() && current.getBoxCol()  == current.getTargetCol()) {
                winFlag = true;
            }
            current.setPlayerRow(current.getPlayerRow() + 1);
            current.setBoxRow(current.getBoxRow() + 1);
            checkLostGame();
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow() +1][current.getPlayerCol()]) {
            if (current.getPlayerCol()== current.getBoxCol() &&current.getPlayerRow()+1== current.getBoxRow())
                return;
            current.setPlayerRow(current.getPlayerRow() + 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, loseFlag, lvlCounter);
    }

    public void walkUp() {
        //if walk on blank back
        //Checks if there's a box to be pushed
        if ((current.getPlayerRow() - 1 == current.getBoxRow()
                && current.getPlayerCol() == current.getBoxCol())
                && current.getPassable()[current.getBoxRow() - 1][current.getBoxCol()]) {
            if (current.getBoxRow() - 1 == current.getTargetRow() && current.getBoxCol() == current.getTargetCol()) {
                winFlag = true;
            }
            current.setPlayerRow(current.getPlayerRow() - 1);
            current.setBoxRow(current.getBoxRow() - 1);
            checkLostGame();
            //Check if there's a wall if there wasn't a box
        } else if (current.getPassable()[current.getPlayerRow() - 1][current.getPlayerCol()]) {
            if (current.getPlayerCol()== current.getBoxCol() &&current.getPlayerRow()-1== current.getBoxRow())
                return;
            current.setPlayerRow(current.getPlayerRow() - 1);
        }

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, loseFlag, lvlCounter);
    }

    /**
     * Check lost game.
     */
    public static void checkLostGame(){
        if((((!current.getPassable()[current.getBoxRow()][current.getBoxCol()+1]&&!current.getPassable()[current.getBoxRow()-1][current.getBoxCol()]) )||
                ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()+1]&&!current.getPassable()[current.getBoxRow()+1][current.getBoxCol()]) )||
                ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()-1]&&!current.getPassable()[current.getBoxRow()-1][current.getBoxCol()]) )||
                ((!current.getPassable()[current.getBoxRow()][current.getBoxCol()-1]&&!current.getPassable()[current.getBoxRow()+1][current.getBoxCol()])))
                && !(current.getTargetRow()==current.getBoxRow() && current.getTargetCol()==current.getBoxCol())){
            loseFlag = true;
        }
    }
    //endregion

    //region restart or change of level
    public void nextLevel() {
        winFlag = false;
        lvlCounter++;
        setLevel(lvlCounter);
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag, loseFlag, lvlCounter);

    }

    public void restartGame() {
        winFlag = false;
        lvlCounter = 0;
        setLevel(lvlCounter);
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag,loseFlag, lvlCounter);
    }

    public void restartLevel() {
        loseFlag = false;
        setLevel(lvlCounter);
        for (Observer o : observers)
            o.updateCurrentState(current, winFlag,loseFlag, lvlCounter);
    }
    //endregion

}