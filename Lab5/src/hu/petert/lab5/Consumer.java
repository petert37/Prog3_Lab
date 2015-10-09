package hu.petert.lab5;

import java.util.Random;

public class Consumer extends Thread {

    private static final int SLEEP_MAX_MS = 3000;

    final Fifo fifo;
    String s;
    Random random;
    int delay = 0;

    public Consumer(String s, Fifo fifo) {
        this.fifo = fifo;
        this.s = s;
        random = new Random();
    }

    public Consumer(String s, Fifo fifo, int delay) {
        this.fifo = fifo;
        this.s = s;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true){
            String str;
            String timeString = String.valueOf(System.currentTimeMillis());
            timeString = timeString.substring(timeString.length() - 5, timeString.length());

            synchronized (fifo){
                try {
                    str = fifo.get();
                    System.out.println("consumed " + s + " " + str + " " + timeString);
                    fifo.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(delay > 0 ? delay : random.nextInt(SLEEP_MAX_MS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
