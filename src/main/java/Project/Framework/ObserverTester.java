package Project.Framework;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Observer tester.
 */
public class ObserverTester implements Observer<String>{
    @Override
    public void updateCurrentState(String state, boolean winFlag, boolean loseFlag, int lvlCounter) {
        assertEquals(state, "hej");
        assertEquals(winFlag, false);
        assertEquals(loseFlag, false);
        assertEquals(lvlCounter, 0);
        System.out.println(state + " " + winFlag + " " + loseFlag + " " + lvlCounter);
    }
}

/**
 * The type Test obs.
 */
class TestObs{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String [] args)
    {
        AddObservers add = new AddObservers();
        ObserverTester tester = new ObserverTester();
        add.addObserver(tester);


    }
}

/**
 * The type Add observers.
 */
class AddObservers{
    /**
     * The constant observers.
     */
    private static List<Observer> observers = new ArrayList<Observer>();

    /**
     * Get observers list.
     *
     * @return the list
     */
    public List<Observer> getObservers(){
        return observers;
    }

    /**
     * Add observer.
     *
     * @param so the so
     */
    public void addObserver(Observer so) {
        observers.add(so);
        for (Observer o : observers)
            o.updateCurrentState("hej", false, false, 0);
    }

}
