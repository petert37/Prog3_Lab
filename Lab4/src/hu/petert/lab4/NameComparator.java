package hu.petert.lab4;

public class NameComparator implements java.util.Comparator<Beer> {
    @Override
    public int compare(Beer o1, Beer o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
