package hu.petert.lab5;

import java.util.Random;

public class Producer implements Runnable{

    private static final int SLEEP_MAX_MS = 3000;

    String message;
    int counter;
    final Fifo fifo;
    Random random;

    public Producer(String message, Fifo fifo) {
        this.message = message;
        this.fifo = fifo;
        counter = 0;
        random = new Random();
    }

    @Override
    public void run() {
        while(true) {
            String timeString = String.valueOf(System.currentTimeMillis());
            timeString = timeString.substring(timeString.length() - 5, timeString.length());

            synchronized (fifo){
                try {
                    fifo.put(message + " " + counter);
                    System.out.println("produced " + message + " " + counter + " " + timeString);
                    counter++;
                    fifo.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(random.nextInt(SLEEP_MAX_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
