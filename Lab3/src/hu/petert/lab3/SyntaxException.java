package hu.petert.lab3;

public class SyntaxException extends Exception {

    @Override
    public String getMessage() {
        return "Syntax Error";
    }
}
