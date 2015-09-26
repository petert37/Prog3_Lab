package hu.petert.lab4;

public class StyleComparator implements java.util.Comparator<Beer>{

    @Override
    public int compare(Beer o1, Beer o2) {
        return o1.getStyle().compareTo(o2.getStyle());
    }
}
