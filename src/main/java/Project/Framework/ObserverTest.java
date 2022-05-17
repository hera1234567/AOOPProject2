package main.java.Project.Framework;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ObserverTest  {

    private static List<Observer> observers = new ArrayList<Observer>();
    public void addObserver(Observer so) {
        observers.add(so);}


    @Test
    void updateCurrentState() {
        //TODO KOLLA SÅ ATT DET RETURNERAR RÄTT SAKER
        ObserverTest test = new ObserverTest();
        ((Observer<?>) test).updateCurrentState(null, false, false, 0);
        ObserverTest check = new ObserverTest();
        test.addObserver((Observer) check);
        for (Observer o : observers)
            o.updateCurrentState(null, false, false, 0);
        test.equals(check);

    }

}