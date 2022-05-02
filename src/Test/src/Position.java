package Test.src;

import java.util.Objects;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    } //constructor

    public int getRow() {
        return row;
    } //getRow

    public int getCol() {
        return col;
    } //getCol

    public String toString() {
        return "(" + row + ", " + col + ")";
    } //toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public int shortestDistance(Position other) {
       int deltaCol = Math.abs(other.getCol() - getCol());
       int deltaRow = Math.abs(other.getRow() - getRow());
       int x = (int) (Math.sqrt(Math.pow(deltaCol,2) + Math.pow(deltaRow,2)));
       return x;

    }



}
