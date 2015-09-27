package hu.petert.lab4;

public class EmptyQueueException extends Exception {

    @Override
    public String getMessage() {
        return "Queue is empty";
    }
}
