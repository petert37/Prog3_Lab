package hu.petert.lab2;

public abstract class Jatekos {

    public static int nextId = 0;

    protected Asztal asztal;
    protected int id;

    public Jatekos(){
        this.id = nextId;
        nextId++;
    }

    public void setAsztal(Asztal asztal){
        this.asztal = asztal;
    }

    public void lep(){
        if(asztal == null) return;
        System.out.println(this.toString());
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Szemet: " + toString());
    }
}
