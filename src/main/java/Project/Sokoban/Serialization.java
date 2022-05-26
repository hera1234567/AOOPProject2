package Project.Sokoban;

import java.io.*;

/**
 * The type Serialization.
 */
public class Serialization implements Serializable{

    /**
     * Serialization.
     *
     * Takes the position of the player and box, the level the player is at and if that level is won or lost.
     * These values are saved in a file in the project called Saved.
     *
     * @param level    the level
     * @param playerX  the player x
     * @param playerY  the player y
     * @param boxX     the box x
     * @param boxY     the box y
     * @param winFlag  the win flag
     * @param loseFlag the lose flag
     */
    public static void serialization(int level, int playerX, int playerY, int boxX, int boxY, boolean winFlag, boolean loseFlag){
        SerializationObject ser = new SerializationObject(level,playerX,playerY,boxX, boxY, winFlag,loseFlag);
        try {
            FileOutputStream file = new FileOutputStream
                    (System.getProperty("user.dir")+"/src/main/java/Project/Saved");
            ObjectOutputStream out = new ObjectOutputStream
                    (file);

            out.writeObject(ser);

            out.close();
            file.close();

            System.out.println("Current game status has been saved");

        }

        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException is caught");
        }
    }

    /**
     * De serialization serialization object.
     *
     * Takes out the previous serialized object and returns that to the calling function
     *
     * @return the serialization object
     */
    public static SerializationObject deSerialization()
    {
        try {

            FileInputStream file = new FileInputStream
            (System.getProperty("user.dir")+"/src/main/java/Project/Saved");
            ObjectInputStream in = new ObjectInputStream
                    (file);

            SerializationObject serialization = (SerializationObject) in.readObject();

            in.close();
            file.close();
            System.out.println("A saved game has been restored");
            return serialization;
        }

        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException is caught");
            return null;
        }

        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("ClassNotFoundException" +
                    " is caught");
            return null;
        }

    }
}
