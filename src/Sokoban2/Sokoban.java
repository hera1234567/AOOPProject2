package Sokoban2;

import java.util.ArrayList;
import java.util.List;

public class Sokoban implements Controller{

    //region static variables
    public static int lvlCounter;
    public static Level current;
    private static boolean winFlag;
    private static boolean loseFlag;

    //TODO FIXA SÅ ATT DET GÅR MED MOUSPAD OCKSÅ
    private InputMethod m = new KeyPad(Frame.getFrame());


    //endregion


    public Sokoban() {
        lvlCounter = 0;
        current = setLevel(lvlCounter);
        winFlag = false;
        loseFlag = false;
        // m = new KeyPad(Frame.getFrame());
        m.setController(this);
    }

    public void setInputMethod(InputMethod m){
        this.m = m;
        m.setController(this);
    }

    //region observers & add
    private static List<Observer> observers = new ArrayList<Observer>();

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

    @Override
    public void clickedPosition(int xMouse, int yMouse, int frameWidth, int frameHeight) {
    int columns = (frameWidth/current.getWidth()) * current.getPlayerCol();
    int oneCol = frameWidth/current.getWidth();
    int oneRow = frameHeight/(current.getHeight());
    int rows = (frameHeight/(current.getHeight()))*current.getPlayerRow();
        System.out.println("ROWS : " + current.getHeight() + " FRAME : " + frameHeight + " ROWS:" + oneRow);
        System.out.println("COLUMNS : " + current.getWidth() + " FRAME : " + frameWidth + " COLS : " + frameWidth/current.getWidth());
        System.out.println("PLAYER POSITION: " + columns +","+ rows);

        if (columns < (xMouse-10) && (rows<(yMouse-10) && (rows+oneRow)>(yMouse-10))){
        System.out.println("\nGoing Right: " + columns + "< " + xMouse +" and " + rows + "<" + yMouse +"<" + (rows+oneRow));
        walkRight();
    }
    else if (columns > xMouse && (rows<(yMouse-10) && (rows+oneRow)>(yMouse-10))){
        System.out.println("\nGoing Left: " + columns + "< " + xMouse +" and " + rows + "<" + yMouse +"<" + (rows+oneRow));
        walkLeft();
    }
    else if (rows+32 > yMouse && (columns+oneCol>(xMouse-10) && (columns-oneCol < (xMouse-10)))){
            System.out.println("\nGoing Up: " + (rows+32) + "> " + yMouse +" and " + columns + ">" + (xMouse-10) +">" + (columns-oneCol));
            walkUp();
    }
    else if ((rows-32) < yMouse && (columns+oneCol>(xMouse-10) && (columns-oneCol < (xMouse-10)))){
            System.out.println("\nGoing Down: " + (rows-32) + "< " + yMouse +" and " + (columns+oneCol) + ">" + (xMouse-10) +">" + (columns-oneCol));
            walkDown();
    }
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