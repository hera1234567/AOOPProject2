package Project.Sokoban;

import java.io.*;

public class Serialization implements Serializable {
    Level state;
    Boolean winFlag;
    Boolean loseFlag;
    int level;

    public Serialization(Level state, boolean winFlag, boolean loseFlag, int level){
        this.state = state;
        this.winFlag = winFlag;
        this.loseFlag = loseFlag;
        this.level = level;

}
    public void serialization(Level state, boolean winFlag, boolean loseFlag, int level){

        Serialization ser = new Serialization(state, winFlag, loseFlag, level);
        try {
            FileOutputStream file = new FileOutputStream
                    (System.getProperty("user.dir")+"/src/main/java/Project/Saved");
            ObjectOutputStream out = new ObjectOutputStream
                    (file);

            // Method for serialization of object
            out.writeObject(ser);

            out.close();
            file.close();

            System.out.println("Object has been serialized\n"
                    + "Data before Deserialization.");
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public Serialization deSerialization()
    {
        try {

            // Reading the object from a file
            FileInputStream file = new FileInputStream
                    (System.getProperty("user.dir")+"/src/main/java/Project/Saved");
            ObjectInputStream in = new ObjectInputStream
                    (file);

            // Method for deserialization of object
            Serialization serialization = (Serialization)in.readObject();

            in.close();
            file.close();
            System.out.println("Object has been deserialized\n"
                    + "Data after Deserialization.");
            return serialization;
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
        return null;
    }
    public Level getState(Serialization ser){return ser.state;}

    public boolean getwinFlag(Serialization ser){return ser.winFlag;}

    public boolean getloseFlag(Serialization ser){return ser.loseFlag;}

    public int getlevel(Serialization ser){return ser.level;}
}
