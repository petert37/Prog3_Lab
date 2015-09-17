package hu.petert.lab2;

import java.util.ArrayList;

public class Asztal {

    public static final int MAX_JATEKOS = 10;
    private ArrayList<Jatekos> jatekosok;
    private double tet;
    private int korok;

    public Asztal(){

        jatekosok = new ArrayList<Jatekos>(MAX_JATEKOS);

    }

    public void addJatakes(Jatekos jatekos){

        if(jatekosok.size() >= MAX_JATEKOS) return;

        jatekosok.add(jatekos);
        jatekos.setAsztal(this);

    }

    public void ujJatek(){
        tet = 0d;
        korok = 0;
    }

    public void kor() throws NincsJatekos {

        if(jatekosok.isEmpty()) throw new NincsJatekos();

        for(Jatekos j : jatekosok){
            j.lep();
        }
        korok++;
    }

    public void emel(double tet){
        this.tet += tet;
    }

    public int getKor(){
        return korok;
    }

    public double getTet(){
        return tet;
    }

}
