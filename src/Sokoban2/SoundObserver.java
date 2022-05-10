package Sokoban2;

public class SoundObserver implements Observer{

    @Override
    public void updateCurrentState(Level state, boolean winFlag, boolean loseFlag, int lvlCounter) {
        if (winFlag){
            //WIN SOUND
        }
        else if (loseFlag){
            //LOSE SOUND
        }
        else{
            //WALK SOUND
        }
    }
}
