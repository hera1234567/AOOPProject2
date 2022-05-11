package Sokoban2;

/**
 * The game level models
 * */

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

    //region getters
    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public int getPlayerRow() {return playerRow;}

    public int getPlayerCol() {return playerCol;}

    public int getTargetRow(){return targetRow;}

    public int getTargetCol(){return targetCol;}

    public int getBoxRow(){return boxRow;}

    public int getBoxCol(){return boxCol;}
    //endregion

    //region setters
    public void setPlayerRow(int row) {playerRow=row;}

    public void setPlayerCol(int col) {playerCol=col;}

    public void setBoxRow(int row) {boxRow=row;}

    public void setBoxCol(int col) {boxCol=col;}
    //endregion

    public boolean[][] getPassable() {return passable;}

    //region Levels

    public static Level level1(){
        boolean[][] passable = {
                {false,false,false,false,false,false},
                {false,true,true,true,true,false},
                {false,false,false,false,false,false}
        };
        return new Level(3,6,passable,1,1,1,4,1,2);
    }

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