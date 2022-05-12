package Project.Framework;

import Project.Sokoban.Level;

/**
 * Observer interface
 * Sends out updates to all classes implementing observer
 */
public interface Observer {
    /**
     * Update current state.
     *
     * @param state      the current state of the level
     * @param winFlag    the win flag
     * @param loseFlag   the lose flag
     * @param lvlCounter the level counter
     */
    void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter);
}