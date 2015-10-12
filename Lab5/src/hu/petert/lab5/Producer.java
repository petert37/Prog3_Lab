package hu.petert.lab5;

import java.util.Random;

public class Producer implements Runnable{

    private static final int SLEEP_MAX_MS = 3000;

    private String message;
    private int counter;
    private final Fifo fifo;
    private Random random;
    private int delay = 0;
    private volatile boolean isRunning;

    public Producer(String message, Fifo fifo) {
        this.message = message;
        this.fifo = fifo;
        counter = 0;
        random = new Random();
        isRunning = true;
    }

    public Producer(String message, Fifo fifo, int delay) {
        this(message, fifo);
        this.delay = delay;
    }

    @Override
    public void run() {
        while(isRunning) {
            String timeString = String.valueOf(System.currentTimeMillis());
            timeString = timeString.substring(timeString.length() - 5, timeString.length());

            try {
                fifo.put(message + " " + counter);
                System.out.println("produced " + message + " " + counter + " " + timeString);
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(delay > 0 ? delay : random.nextInt(SLEEP_MAX_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopThread(){
        isRunning = false;
    }

}
