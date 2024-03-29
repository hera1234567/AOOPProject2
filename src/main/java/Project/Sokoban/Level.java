package Project.Sokoban;

/**
 * The game level models
 */
public class Level {
    private int height, width;
    private int playerRow;
    private int playerCol;
    private int targetRow;
    private int targetCol;
    private int boxRow;
    private int boxCol;
    private boolean [][] passable;

    public static Level[] levels= {level1(), level2(), level3(), level4()};

    /**
     * Instantiates a new Level.
     *
     * @param height    the height
     * @param width     the width
     * @param passable  the passable
     * @param playerRow the player row
     * @param playerCol the player col
     * @param targetRow the target row
     * @param targetCol the target col
     * @param boxRow    the box row
     * @param boxCol    the box col
     */
    public Level(int height, int width, boolean[][] passable,
                 int playerRow, int playerCol,
                 int targetRow, int targetCol,
                 int boxRow, int boxCol) {
        this.height = height;
        this.width = width;
        this.playerRow = playerRow;
        this.playerCol = playerCol;
        this.passable = passable;
        this.boxCol = boxCol;
        this.boxRow = boxRow;
        this.targetCol = targetCol;
        this.targetRow = targetRow;

    }

    /**
     * Gets height.
     *
     * @return the height
     */
//region getters
    public int getHeight() {return height;}

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {return width;}

    /**
     * Gets player row.
     *
     * @return the player row
     */
    public int getPlayerRow() {return playerRow;}

    /**
     * Gets player col.
     *
     * @return the player col
     */
    public int getPlayerCol() {return playerCol;}

    /**
     * Get target row int.
     *
     * @return the int
     */
    public int getTargetRow(){return targetRow;}

    /**
     * Get target col int.
     *
     * @return the int
     */
    public int getTargetCol(){return targetCol;}

    /**
     * Get box row int.
     *
     * @return the int
     */
    public int getBoxRow(){return boxRow;}

    /**
     * Get box col int.
     *
     * @return the int
     */
    public int getBoxCol(){return boxCol;}
    //endregion

    /**
     * Sets player row.
     *
     * @param row the row
     */
//region setters
    public void setPlayerRow(int row) {
        if((!(row>=height)&&!(row<0))&&passable[row][playerCol]) {
            playerRow = row;
        }
    }


    /**
     * Sets player col.
     *
     * @param col the col
     */
    public void setPlayerCol(int col) {
        if(!(col>=width)&&!(col<0)&&passable[playerRow][col]) {
            playerCol = col;
        }
    }

    /**
     * Sets box row.
     *
     * @param row the row
     */
    public void setBoxRow(int row) {
        if(!(row>=height)&&!(row<0)&&passable[row][boxCol]) {
            boxRow=row;
        }
    }

    /**
     * Sets box col.
     *
     * @param col the col
     */
    public void setBoxCol(int col) {
        if(!(col>=width)&&!(col<0)&&passable[boxRow][col]) {
        boxCol = col;
        }
    }
    //endregion

    /**
     * Get passable boolean [ ] [ ].
     *
     * @return the boolean [ ] [ ], true if there isn't an obstacle
     */
    public boolean[][] getPassable() {return passable;}

    //region Levels

    /**
     * Level 1 level.
     *
     * @return the level
     */
    public static Level level1(){
        boolean[][] passable = {
                {false,false,false,false,false,false},
                {false,true,true,true,true,false},
                {false,false,false,false,false,false}
        };
        return new Level(3,6,passable,1,1,1,4,1,2);
    }

    /**
     * Level 2 level.
     *
     * @return the level
     */
    public static Level level2(){
        boolean[][] passable = {
                {false,false,false,false,false},
                {false,true,true,true,false},
                {true,true,false,true,false},
                {false,true,true,true,false},
                {false,false,true,true,false},
                {false,false,false,false,false}
        };
        return new Level(6,5,passable,2,0, 4, 3,3, 2);
    }

    /**
     * Level 3 level.
     *
     * @return the level
     */
    public static Level level3(){
        boolean[][] passable = {
                {false,false,false,false,false,false},
                {false,false,true,true,true,false},
                {true,true,true,true,true,false},
                {false,true,true,false,true,false},
                {false,true,true,true,true,true},
                {false,false,false,false,false,false}
        };
        return new Level(6,6,passable,2,0,4,5,2,2 );
    }

    /**
     * Level 4 level.
     *
     * @return the level
     */
    public static Level level4(){
        boolean[][] passable = {
                {false,false,false,false,false,false,false},
                {false,true,true,false,true,true,false},
                {false,true,true,true,true,true,false},
                {false,true,false,false,true,true,false},
                {false,true,true,true,true,true,false},
                {false,false,false,false,false,false,false}
        };
        return new Level(6,7,passable,2,1,4,1,2,2 );
    }

    //endregion
}