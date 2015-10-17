package hu.petert.lab6;

import java.io.*;

public class FileLogger implements Observer {

	PrintWriter writer;

	public FileLogger(String fileName){
		try {
			writer = new PrintWriter(fileName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void report(Observable observable){
		writer.println(observable.toString());
		writer.flush();
	}

}
