package Project.Sokoban;

import java.io.Serializable;

/**
 * The type Serialization object.
 */
public class SerializationObject implements Serializable {


    //region instance variables for Serialazable object
    Boolean winFlag;
    Boolean loseFlag;
    int level;
    int playerX;
    int playerY;
    int boxX;
    int boxY;
//endregion

    /**
     * Instantiates a new Serialization object.
     *
     * @param level    the level
     * @param playerX  the player x
     * @param playerY  the player y
     * @param boxX     the box x
     * @param boxY     the box y
     * @param winFlag  the win flag
     * @param loseFlag the lose flag
     */
    public SerializationObject(int level, int playerX, int playerY, int boxX, int boxY, boolean winFlag, boolean loseFlag) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.boxX = boxX;
        this.boxY = boxY;
        this.winFlag = winFlag;
        this.loseFlag = loseFlag;
        this.level = level;

    }

    public Boolean getWinFlag() {return winFlag;}

    public Boolean getLoseFlag() {return loseFlag;}

    public int getLevelSerialization() {return level;}

    public int getPlayerX() {return playerX;}

    public int getPlayerY() {return playerY;}

    public int getBoxX() {return boxX;}

    public int getBoxY() {return boxY;}
}
