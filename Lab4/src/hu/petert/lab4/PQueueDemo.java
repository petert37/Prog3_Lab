package hu.petert.lab4;

public class PQueueDemo {


    public void start(){

        PQueue<String> queue = new PQueue<>();

        queue.push("String1");
        queue.push("String3");
        queue.push("String2");
        queue.push("String5");
        queue.push("String4");

        System.out.println("Queue size: " + queue.size());

        try {
            System.out.println("top: " + queue.top());
            System.out.println("pop: " + queue.pop());
            System.out.println("top: " + queue.top());

            queue.clear();

            queue.top();
        } catch (EmptyQueueException e) {
            System.out.println("Queue is empty");
        }

        System.out.println();

        PQueue<Integer> s = new PQueue<>();
        s.push(1); s.push(2); s.push(3); s.push(4);
        for (Integer i : s) {
            System.out.println(i);
        }

    }

}
