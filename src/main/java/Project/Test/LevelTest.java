package Project.Test;

import Project.Sokoban.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    void getHeight() {
        int height =3;
        Assertions.assertEquals(Level.level1().getHeight(), height);
    }

    @Test
    void getWidth() {
        int width = 6;
        assertEquals(Level.level1().getWidth(), width);
    }

    @Test
    void getPlayerRow() {
        int startRowLevel1 = 1;
        assertEquals(Level.level1().getPlayerRow(), startRowLevel1);
    }

    @Test
    void getPlayerCol() {
        int startColLevel1 = 1;
        assertEquals(Level.level1().getPlayerCol(), startColLevel1);
    }

    @Test
    void getTargetRow() {
        int startRowLevel1 = 1;
        assertEquals(Level.level1().getTargetRow(), startRowLevel1);
    }

    @Test
    void getTargetCol() {
        int startColLevel1 = 4;
        assertEquals(Level.level1().getTargetCol(), startColLevel1);
    }

    @Test
    void getBoxRow() {
        int startRowLevel1 = 1;
        assertEquals(Level.level1().getBoxRow(), startRowLevel1);
    }

    @Test
    void getBoxCol() {
        int startColLevel1 = 2;
        assertEquals(Level.level1().getBoxCol(), startColLevel1);
    }

    @Test
    void setPlayerRow() {
        Level lev = Level.level1();
        lev.setPlayerRow(1);
        assertEquals(lev.getPlayerRow(), 1);
        lev.setPlayerRow(-1); //Outside of matrix
        assertNotEquals(lev.getPlayerRow(), -1);
        lev.setPlayerRow(10); //Outside of matrix
        assertNotEquals(lev.getPlayerRow(), 10);
        lev.setPlayerRow(0); //Placing player at a wall position
        assertNotEquals(lev.getPlayerRow(), 0);

    }

    @Test
    void setPlayerCol() {
        Level lev = Level.level1();
        lev.setPlayerCol(1);
        assertEquals(lev.getPlayerCol(), 1);
        lev.setPlayerCol(-1); //Outside of matrix
        assertNotEquals(lev.getPlayerCol(), -1);
        lev.setPlayerCol(10); //Outside of matrix
        assertNotEquals(lev.getPlayerCol(), 10);
        lev = Level.level2();
        lev.setPlayerCol(2); //Placing player at a wall position
        assertNotEquals(lev.getPlayerCol(), 2);
    }

    @Test
    void setBoxRow() {
        Level lev = Level.level1();
        lev.setBoxRow(1);
        assertEquals(lev.getBoxRow(), 1);
        lev.setBoxRow(-1); //Outside of matrix
        assertNotEquals(lev.getBoxRow(), -1);
        lev.setBoxRow(10); //Outside of matrix
        assertNotEquals(lev.getBoxRow(), 10);
        lev.setBoxRow(0); //Placing Box at a wall position
        assertNotEquals(lev.getBoxRow(), 0);
    }

    @Test
    void setBoxCol() {
        Level lev = Level.level1();
        lev.setBoxCol(1);
        assertEquals(lev.getBoxCol(), 1);
        lev.setBoxCol(-1); //Outside of matrix
        assertNotEquals(lev.getBoxCol(), -1);
        lev.setBoxCol(10); //Outside of matrix
        assertNotEquals(lev.getBoxCol(), 10);
        lev = Level.level2();
        lev.setBoxCol(4); //Placing Box at a wall position
        assertNotEquals(lev.getBoxCol(), 4);
    }

    @Test
    void getPassable() {
        boolean[][] pass =Level.level1().getPassable();
        assertFalse(pass[0][0]);
    }

}