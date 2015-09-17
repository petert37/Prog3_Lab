package hu.petert.lab2;

public class NincsJatekos extends Exception {
    @Override
    public String getMessage() {
        return "Az asztalnal nem ul jatekos";
    }
}
