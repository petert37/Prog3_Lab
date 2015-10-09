package hu.petert.lab5;

public class Application {

    public static void main(String[] args){

        Fifo fifo = new Fifo();

//        new Thread(new Producer("P-I", fifo)).start();
//        new Thread(new Producer("P-II", fifo)).start();
//        new Thread(new Producer("P-III", fifo)).start();
//        new Consumer("C-I", fifo).start();
//        new Consumer("C-II", fifo).start();
//        new Consumer("C-III", fifo).start();
//        new Consumer("C-IV", fifo).start();

        new Thread(new Producer("P-I", fifo, 1000)).start();
        new Thread(new Producer("P-II", fifo, 1000)).start();
        new Thread(new Producer("P-III", fifo, 1000)).start();
        new Consumer("C-I", fifo, 100).start();
        new Consumer("C-II", fifo, 100).start();
        new Consumer("C-III", fifo, 100).start();
        new Consumer("C-IV", fifo, 100).start();
    }
}
