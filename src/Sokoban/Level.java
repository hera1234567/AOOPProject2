package Sokoban;

import Test.src.Position;

public class Level {

    private int height, width;
    private int startRow;
    private int startCol;
    private int targetRow;
    private int targetCol;
    private int boxStartRow;
    private int boxStartCol;
    private boolean [][] passable;

    public Level(int height, int width, boolean[][] passable,
                             int startRow, int startCol,
                             int targetRow, int targetCol,
                             int boxStartRow, int boxStartCol) {
        this.height = height;
        this.width = width;
        this.startRow = startRow;
        this.startCol = startCol;
        this.passable = passable;
        this.boxStartCol = boxStartCol;
        this.boxStartRow = boxStartRow;
        this.targetCol = targetCol;
        this.targetRow = targetRow;

    }

    public int getStartRow() {return startRow;}

    public int getStartCol() {return startCol;}

    public int getTargetRow(){return targetRow;}

    public int getTargetCol(){return targetCol;}

    public int getBoxStartRow(){return boxStartRow;}

    public int getBoxStartCol(){return boxStartCol;}

    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public boolean[][] getPassable() {return passable;}

    public static Level level1(){
        boolean[][] passable = {
                {false,false,false,false},
                {true,true,true,true},
                {false, false,false,false}
        };
        return new Level(3,4,passable,1,0,1,3,1,1);
    }

    public static Level level2(){
        boolean[][] passable = {
                {false,true,true,true,false},
                {true,true,false,true,false},
                {false,true,true,true,false},
                {false,false,true,true,false}
        };
        return new Level(4,5,passable,1,0, 3, 3,2, 2);
    }

    public static Level level3(){
        boolean[][] passable = {
                {false,false,true,true,true,false},
                {true,true,true,true,true,false},
                {false,true,true,false,true,false},
                {false,true,true,true,true,true}
        };
        return new Level(4,6,passable,1,0,3,5,1,2 );
    }

}
