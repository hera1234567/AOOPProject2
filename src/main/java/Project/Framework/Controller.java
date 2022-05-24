package Project.Framework;

/**
 * The interface Controller.
 * //TODO Skriva något här?
 */
public interface Controller {
    /**
     * Walk up.
     * a method to override in the class implementation for a step upwards
     */
    void walkUp();

    /**
     * Walk down.
     * a method to override in the class implementation for a step downwards
     */
    void walkDown();

    /**
     * Walk right.
     * a method to override in the class implementation for a step to the right
     */
    void walkRight();

    /**
     * Walk left.
     * a method to override in the class implementation for a step to the left
     */
    void walkLeft();

    /**
     * Clicked position.
     * a method to override in the class implementation for deciding
     * where the mouse is clicked in comparison to relevant object
     *
     * @param xMouse      position x for the mouse click
     * @param yMouse      position y for the mouse click
     * @param frameWidth  the current frame width
     * @param frameHeight the current frame height
     */
    void clickedPosition(int xMouse,int yMouse,int frameWidth,int frameHeight);

    /**
     * Next level.
     * a method to override in the class implementation to move on to the next stage
     */
    void nextLevel();

    /**
     * Restart level.
     * a method to override in the class implementation to restart current stage
     */
    void restartLevel();

    /**
     * Restart game.
     * a method to override in the class implementation to restart the whole program
     */
    void restartGame();

    void save();

    void load();
}