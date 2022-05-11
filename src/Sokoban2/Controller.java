package Sokoban2;

public interface Controller {
    void walkUp();
    void walkDown();
    void walkRight();
    void walkLeft();
    void clickedPosition(int xMouse,int yMouse,int frameWidth,int frameHeight);
    void nextLevel();
    void restartLevel();
    void restartGame();
}