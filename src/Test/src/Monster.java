package Test.src;

import java.util.ArrayList;

public class Monster {
    private Position pos;
    private TowerDefenceLevel check;
    private Tower tower;
    private int health = 10;
    private ArrayList<Position> previous = new ArrayList<>();
    private int counter = 0;
    private int row;
    private int col;
    private Position endPos;


    public Monster(Position pos, TowerDefenceLevel check, Position endPos) {
        this.pos = pos;
        row = pos.getRow();
        col = pos.getCol();
        this.check = check;
        this.endPos = endPos;
    }

    public void monsterIsHit() { //if the monster is hit it will lose one health-point, but if the health is zero that will remain
        if (health > 0) {
            health = health - 1;
        } else {
            health = 0;
        }
    }

    public Position getPos() {
        return pos;
    }

    public int getHealth() {
        return health;
    }

    public int getMonRow() {
        return row;
    }

    public int getMonCol() {
        return col;
    }

   public Position MonsterMoving() { //monster moves according to the if-else-statements

       System.out.println(row + ":" + col + " test:" + row + ":" + (col-1));
       if (row==endPos.getRow() && col==endPos.getCol()) {
           pos = new Position(row,col);
       }
       else if (col < endPos.getCol() && check.getpassable()[row][col + 1] && !previousPos(row, col + 1)) {
           pos = new Position(row, col + 1);
       }
       else if (row < endPos.getRow() && check.getpassable()[row + 1][col] && !previousPos(row + 1, col)) {
           pos = new Position(row + 1, col);
       }
       else if (row> 0 && check.getpassable()[row - 1][col] && !previousPos(row - 1, col)) {
           pos = new Position(row - 1, col);
       }
       else if (col > 0 && check.getpassable()[row][col - 1] && !previousPos(row, col - 1)) {
           pos = new Position(row, col - 1);
       }
       updatePos(pos);
       previous.add(pos);
       return pos;
   }
    public void updatePos(Position pos){
        col = pos.getCol();
        row = pos.getRow();
    }

    public Position getEndPos() {
       return endPos;
    }

    public Position getPreviousPos() {
            return previous.get(previous.size()-1);

    }

    public boolean previousPos(int rows, int cols) {
        boolean kor;
        if (previous.isEmpty()) {
            kor = false;
        } else {
            if (previous.get(previous.size() - 1).getCol()==cols && previous.get(previous.size()-1).getRow()==rows) {
                kor=true;
            } else {
                kor=false;
            }
        }
        return kor;


    }
}
