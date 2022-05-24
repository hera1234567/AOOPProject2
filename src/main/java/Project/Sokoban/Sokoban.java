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
 * This class collects info from the game model 'Level' and inputs and updates the game field,
 * and sends out information about the changes made to all observers
 */
public class Sokoban implements Controller {


    //region static variables
    private static int lvlCounter;
    private static Level current;
    private static boolean winFlag;
    private static boolean loseFlag;
    private InputMethod m;

    //endregion

    /**
     * Instantiates a new Sokoban.
     */
    public Sokoban() {
        lvlCounter = 0;
        current = setLevel(lvlCounter);
        winFlag = false;
        loseFlag = false;
    }

    /**
     * Set input method.
     * also sets the chosen controller
     *
     * @param m the input method
     */
    public void setInputMethod(InputMethod m){
        this.m = m;
        m.setController(this);
    }
    public InputMethod getInputMethod(){
        return m;
    }

    public void setController(Controller c){
        m.setController(c);
    }

    //region observers & add
    private static List<Observer> observers = new ArrayList<Observer>();
    public List<Observer> getObservers(){
        return observers;
    }

    /**
     * Add observer.
     *
     * Adds the observer to the list and sends out an update so all observers have the data directly from start
     *
     * @param so the observer to be added
     */
    public void addObserver(Observer so) {
        observers.add(so);
        for (Observer o : observers)
            o.updateCurrentState(current, loseFlag, winFlag, lvlCounter);
    }
    //endregion

    /**
     * Sets level.
     *
     * Creates a new Level that can be manipulated from the original model.
     *
     * @param i the level that wants to be reached
     * @return the level
     */
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
     * Checks if the box has walls on 2 adjacent sides, and makes sure that the target isn't reached
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

  /*  public void save(){
        Serialization ser = new Serialization();
        ser.serialization(current, winFlag, loseFlag, lvlCounter);
    }*/

    public void save(){
        Serialization ser = new Serialization();
        ser.serialization(lvlCounter, current.getPlayerCol(),current.getPlayerRow(),
                current.getBoxCol(), current.getBoxRow(), winFlag, loseFlag);

    }

    public void load(){
        Serialization temp = new Serialization();
        SerializationObject ser = temp.deSerialization();
        setLevel(ser.level);
        current.setPlayerCol(ser.playerX);
        current.setPlayerRow(ser.playerY);
        current.setBoxCol(ser.boxX);
        current.setBoxRow(ser.boxY);
        winFlag = ser.winFlag;
        loseFlag = ser.loseFlag;
        lvlCounter = ser.level;

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag,loseFlag, lvlCounter);

    }

  /*  public void load(){
        Serialization temp = new Serialization();
        SerializationObject ser = temp.deSerialization();
        current = ser.state;
        winFlag = ser.winFlag;
        loseFlag = ser.loseFlag;
        lvlCounter = ser.level;

        for (Observer o : observers)
            o.updateCurrentState(current, winFlag,loseFlag, lvlCounter);

    }*/

}