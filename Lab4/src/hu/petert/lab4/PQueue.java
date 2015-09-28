package hu.petert.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class PQueue<T extends Comparable> implements Iterable<T>{

    ArrayList<T> list;

    public PQueue(){
        list = new ArrayList<>();
    }

    public void push(T t){
        list.add(t);
    }

    public T pop() throws EmptyQueueException{
        return getBiggestElement(list, true);
    }

    public T top() throws EmptyQueueException{
        return getBiggestElement(list, false);
    }

    public int size(){
        return list.size();
    }

    public void clear(){
        list.clear();
    }

    private T getBiggestElement(ArrayList<T> list, boolean remove) throws EmptyQueueException{

        if(list.isEmpty()) throw new EmptyQueueException();

        T t = (T) Collections.max(list);

        if(remove) list.remove(t);

        return t;
    }

    @Override
    public Iterator<T> iterator() {

        Collections.sort(list);
        Collections.reverse(list);
        return list.iterator();

        /*ArrayList<T> tmp = new ArrayList<>();
        tmp.addAll(list);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return tmp.size() > 0;
            }

            @Override
            public T next() {

                T t = null;

                try {
                    t = getBiggestElement(tmp, true);
                } catch (EmptyQueueException e) {
                    e.printStackTrace();
                }

                return t;
            }
        };*/
    }
}
