package hu.petert.lab4;

public class StrengthComparator implements java.util.Comparator<Beer> {
    @Override
    public int compare(Beer o1, Beer o2) {
        return (int)(o1.getStrength() - o2.getStrength());
    }
}
