package Project.Sokoban;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    void getHeight() {
        int height =3;
        assertEquals(Level.level1().getHeight(), height);
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
        lev.setPlayerRow(2);
        assertEquals(lev.getPlayerRow(), 2);
    }

    @Test
    void setPlayerCol() {
        Level lev = Level.level1();
        lev.setPlayerCol(0);
        assertEquals(lev.getPlayerCol(), 0);
    }

    @Test
    void setBoxRow() {
        Level lev = Level.level1();
        lev.setBoxRow(2);
        assertEquals(lev.getBoxRow(), 2);
    }

    @Test
    void setBoxCol() {
        Level lev = Level.level1();
        lev.setBoxCol(0);
        assertEquals(lev.getBoxCol(), 0);
    }

    @Test
    void getPassable() {
        boolean[][] pass =Level.level1().getPassable();
        assertFalse(pass[0][0]);
    }

}