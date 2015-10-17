package hu.petert.lab6;

public class Application {

	public static void main(String[] args){

		PercentCounter percentCounter = new PercentCounter();
		percentCounter.register(new StdOutLogger());
		percentCounter.register(new FileLogger("log.txt"));
		percentCounter.run();

	}

}
