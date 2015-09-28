package hu.petert.lab4;

public class StrengthComparator implements java.util.Comparator<Beer> {
    @Override
    public int compare(Beer o1, Beer o2) {
        double diff = (o1.getStrength() - o2.getStrength());
        if(diff == 0) return 0;
        return diff > 0 ? 1 : -1;

    }
}
