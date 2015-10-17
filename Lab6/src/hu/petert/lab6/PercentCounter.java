package hu.petert.lab6;

public class PercentCounter extends Observable {

	private int percent = 0;

	public void run(){
		while (percent < 100){
			percent++;
			if(percent % 10 == 0)
				reportToObservers();
		}
	}

	@Override
	public String toString() {
		return percent + "%";
	}
}
