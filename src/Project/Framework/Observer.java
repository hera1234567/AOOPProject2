package Project.Framework;

import java.io.IOException;

/**
 * Observer interface
 * Sends out updates to all classes implementing observer
 */
public interface Observer<T> {
    /**
     * Update current state.
     *
     * @param state      the current state of the level
     * @param winFlag    the win flag
     * @param loseFlag   the lose flag
     * @param lvlCounter the level counter
     */
    void updateCurrentState(T state, boolean winFlag, boolean loseFlag, int lvlCounter);
}