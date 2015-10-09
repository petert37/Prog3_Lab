package hu.petert.lab5;

import java.util.ArrayList;
import java.util.List;

public class Fifo {

    private static final int MAX_ELEMENTS = 10;

    List<String> list;

    public Fifo(){
        list = new ArrayList<>(MAX_ELEMENTS);
    }

    void put(String s) throws InterruptedException {
        while(list.size() >= MAX_ELEMENTS){
            wait();
        }
        list.add(s);
        System.out.println("put " + Thread.currentThread().getId());

    }

    String get() throws InterruptedException {
        while(list.isEmpty()){
            wait();
        }
        String s = list.get(0);
        list.remove(0);
        System.out.println("get " + Thread.currentThread().getId());
        return s;
    }

}
