package Project.Test;

import Project.Sokoban.Serialization;
import Project.Sokoban.SerializationObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerializationTest {


    @Test //Both for serialization and deSerialization
    void deSerialization() {
        SerializationObject test = new SerializationObject(1, 2, 3, 4, 5, false, false);
        Serialization.serialization(1, 2, 3, 4, 5, false, false);
        SerializationObject test2 = Serialization.deSerialization();
        assertEquals(test2.getLevelSerialization(), test.getLevelSerialization());
        assertEquals(test2.getPlayerX(), test.getPlayerX());
        assertEquals(test2.getPlayerY(), test.getPlayerY());
        assertEquals(test2.getBoxX(), test.getBoxX());
        assertEquals(test2.getBoxY(), test.getBoxY());
        assertEquals(test2.getWinFlag(), test.getWinFlag());
        assertEquals(test2.getLoseFlag(), test.getLoseFlag());
    }
}