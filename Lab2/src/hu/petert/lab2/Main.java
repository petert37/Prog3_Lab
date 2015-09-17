package hu.petert.lab2;

public class Main {

    public static int KOROK = 3;

    public static void main(String[] args){

        Asztal asztal = new Asztal();
        asztal.addJatakes(new Kezdo());
        asztal.addJatakes(new Robot());
        asztal.addJatakes(new Kezdo());

        asztal.ujJatek();

        for(int i = 0; i < KOROK; i++){
            System.out.println("Kor: " + asztal.getKor() + ", Tet: " + asztal.getTet());
            try {
                asztal.kor();
            } catch (NincsJatekos nincsJatekos) {
                nincsJatekos.printStackTrace();
                break;
            }
            System.out.println("Tet: " + asztal.getTet());
        }

        asztal = null;
        System.gc();

        Asztal asztal1 = new Asztal();
        try {
            asztal1.kor();
        } catch (NincsJatekos nincsJatekos) {
            nincsJatekos.printStackTrace();
        }

    }

}
