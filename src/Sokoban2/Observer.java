package Sokoban2;

/**
 * Observer interface
 * */

public interface Observer {
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter);
}

