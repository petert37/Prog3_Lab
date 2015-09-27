package hu.petert.lab4;

public class Main {

    public static void main(String[] args){
        if(args[0].equals("1"))
            new Looper().start();
        else if(args[0].equals("2"))
            new PQueueDemo().start();
    }

}
