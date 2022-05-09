package Sokoban2;

public class Inputs {

    //region different moves to make
    public static void walkRight() {
        Sokoban.walkRight();
    }

    public static void walkLeft() {
        Sokoban.walkLeft();
    }

    public static void walkDown() {
        Sokoban.walkDown();
    }

    public static void walkUp() {
        Sokoban.walkUp();
    }

    //endregion

    //region update level
    public static void updateLevel(String next) {
        switch (next){
            case "Restart Level":{
                Sokoban.restartLevel();
                break;
            }
            case "Next Level":{
                Sokoban.nextLevel();
                break;
            }
            case "Restart the game!":{
                Sokoban.restartGame();
                break;
            }

        }
    }
    //endregion
}

