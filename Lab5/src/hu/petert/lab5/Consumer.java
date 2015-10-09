package hu.petert.lab5;

import java.util.Random;

public class Consumer extends Thread {

    final Fifo fifo;
    String s;
    Random random;

    public Consumer(Fifo fifo, String s) {
        this.fifo = fifo;
        this.s = s;
        random = new Random();
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
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}