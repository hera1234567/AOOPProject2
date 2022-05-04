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
                {false,false,false,false,false,false},
                {false,true,true,true,true,false},
                {false,false,false,false,false,false}
        };
        return new Level(3,6,passable,1,1,1,4,1,2);
    }

    public static Level level2(){
        boolean[][] passable = {
                {false,false,false,false,false,false},
                {false,false,true,true,true,false},
                {false,true,true,false,true,false},
                {false,false,true,true,true,false},
                {false,false,false,true,true,false},
                {false,false,false,false,false,false}
        };
        return new Level(6,6,passable,2,1, 4, 4,3, 3);
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

}
