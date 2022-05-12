package Sokoban2;

/**
 * Observer interface
 */
public interface Observer {
    /**
     * Update current state.
     *
     * @param state      the state
     * @param winFlag    the win flag
     * @param loseFlag   the lose flag
     * @param lvlCounter the lvl counter
     */
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter);
}