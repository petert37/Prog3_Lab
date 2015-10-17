package hu.petert.lab6;

import java.util.ArrayList;
import java.util.List;

public class Observable{

    private List<Observer> observers;

    public Observable(){
        observers = new ArrayList<>();
    }

	public void register(Observer observer){
        observers.add(observer);
	}
	
	public void unregister(Observer observer){
        observers.remove(observer);
	}
	
	public void reportToObservers(){
        for(Observer o : observers)
            o.report(this);
	}
}
