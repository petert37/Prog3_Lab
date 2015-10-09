package hu.petert.lab5;

public class Application {

    public static void main(String[] args){

        Fifo fifo = new Fifo();

        new Thread(new Producer("P-I", fifo)).start();
        new Thread(new Producer("P-II", fifo)).start();
        new Thread(new Producer("P-III", fifo)).start();
        new Consumer(fifo, "C-I").start();
        new Consumer(fifo, "C-II").start();
        new Consumer(fifo, "C-III").start();
        new Consumer(fifo, "C-IV").start();
    }
}
