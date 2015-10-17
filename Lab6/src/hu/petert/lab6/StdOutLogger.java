package hu.petert.lab6;

public class StdOutLogger implements Observer{

	public void report(Observable observable){
		System.out.println(observable.toString());
	}
}
