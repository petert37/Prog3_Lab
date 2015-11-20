package hu.petert.lab10.sax;

import java.util.Comparator;

public class BusStopSorter implements Comparator {

    private final double latitude;
    private final double longitude;

    public BusStopSorter(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int compare(Object o1, Object o2) {

        double dst1 = ((BusStop) o1).getDistance(latitude, longitude);
        double dst2 = ((BusStop) o2).getDistance(latitude, longitude);

        if(dst1 == dst2) return 0;
        if(dst1 < dst2) return 1;
        else return -1;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
