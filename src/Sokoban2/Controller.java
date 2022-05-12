package Sokoban2;

/**
 * The interface Controller.
 */
public interface Controller {
    /**
     * Walk up.
     */
    void walkUp();

    /**
     * Walk down.
     */
    void walkDown();

    /**
     * Walk right.
     */
    void walkRight();

    /**
     * Walk left.
     */
    void walkLeft();

    /**
     * Clicked position.
     *
     * @param xMouse      the x mouse
     * @param yMouse      the y mouse
     * @param frameWidth  the frame width
     * @param frameHeight the frame height
     */
    void clickedPosition(int xMouse,int yMouse,int frameWidth,int frameHeight);

    /**
     * Next level.
     */
    void nextLevel();

    /**
     * Restart level.
     */
    void restartLevel();

    /**
     * Restart game.
     */
    void restartGame();
}