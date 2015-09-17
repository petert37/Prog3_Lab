package hu.petert.lab2;

public class Robot extends Jatekos {

    @Override
    public void lep() {
        super.lep();
    }

    @Override
    public String toString() {
        if(asztal == null) return "Table missing!";
        return "Robot_" + id + ", Kor: " + asztal.getKor();
    }
}
