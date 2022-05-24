package Project.Sokoban;

import java.io.*;

class SerializationObject implements Serializable {
    Level state;
    Boolean winFlag;
    Boolean loseFlag;
    int level;
    int playerX;
    int playerY;
    int boxX;
    int boxY;


    public SerializationObject(int level, int playerX, int playerY, int boxX, int boxY, boolean winFlag, boolean loseFlag) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.boxX = boxX;
        this.boxY = boxY;
        this.winFlag = winFlag;
        this.loseFlag = loseFlag;
        this.level = level;

    }
/*
    public SerializationObject(Level state, boolean winFlag, boolean loseFlag, int level) {
        this.state = this.state;
        this.winFlag = winFlag;
        this.loseFlag = loseFlag;
        this.level = level;

    }

}
   class Serialization implements Serializable{
    public void serialization(Level state, boolean winFlag, boolean loseFlag, int level){

        SerializationObject ser = new SerializationObject(state, winFlag, loseFlag, level);
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
            ex.printStackTrace();
            System.out.println("IOException is caught");
        }
    }*/
}

class Serialization implements Serializable{
    public void serialization(int level, int playerX, int playerY, int boxX, int boxY, boolean winFlag, boolean loseFlag){
        SerializationObject ser = new SerializationObject(level,playerX,playerY,boxX, boxY, winFlag,loseFlag);
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
            ex.printStackTrace();
            System.out.println("IOException is caught");
        }
    }

    public SerializationObject deSerialization()
    {
        try {

            // Reading the object from a file
            FileInputStream file = new FileInputStream
            (System.getProperty("user.dir")+"/src/main/java/Project/Saved");
            ObjectInputStream in = new ObjectInputStream
                    (file);

            // Method for deserialization of object
            SerializationObject serialization = (SerializationObject) in.readObject();

            in.close();
            file.close();
            System.out.println("Object has been deserialized\n"
                    + "Data after Deserialization.");
            return serialization;
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
            return null;
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
            return null;
        }

    }
}
