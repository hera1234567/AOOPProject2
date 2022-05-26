package Project.Sokoban;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SerializationTest {


    @Test //Both for serialization and deSerialization
    void deSerialization() {
        SerializationObject test = new SerializationObject(1, 2, 3, 4, 5, false, false);
        Serialization.serialization(1, 2, 3, 4, 5, false, false);
        SerializationObject test2 = Serialization.deSerialization();
        assertEquals(test2.level, test.level);
        assertEquals(test2.playerX, test.playerX);
        assertEquals(test2.playerY, test.playerY);
        assertEquals(test2.boxX, test.boxX);
        assertEquals(test2.boxY, test.boxY);
        assertEquals(test2.winFlag, test.winFlag);
        assertEquals(test2.loseFlag, test.loseFlag);
    }
}