package hu.petert.lab2;

public class Kezdo extends Jatekos {

    @Override
    public void lep() {
        super.lep();
        if(asztal.getKor() % 2 == 1) asztal.emel(1d);
    }

    @Override
    public String toString() {
        if(asztal == null) return "Table missing!";
        return "Kezdo_" + id + ", Kor: " + asztal.getKor();
    }
}
