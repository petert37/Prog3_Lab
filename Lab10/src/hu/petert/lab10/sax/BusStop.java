package hu.petert.lab10.sax;

import hu.petert.lab10.Distance;

public class BusStop{

    private String name, oldName, wheelChair;
    private boolean valid;
    double longitude, latitude;

    public BusStop(){
        name = "N/A";
        oldName = "N/A";
        wheelChair = "N/A";
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public void setWheelChair(String wheelChair) {
        this.wheelChair = wheelChair;
    }

    public void print(){
        System.out.println("Megálló:");
        System.out.println("   Név: " + name + " (" + oldName + ")");
        System.out.println("   Kerekesszék: " + wheelChair);
    }

    public void printWithDistance(double latitudeFrom, double longitudeFrom){
        print();
        System.out.println("   Távolság: " + Distance.getDistance(latitudeFrom, longitudeFrom, latitude, longitude));
    }

    public boolean isValid() {
        return valid;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getDistance(double latitudeFrom, double longitudeFrom){
        return Distance.getDistance(latitudeFrom, longitudeFrom, latitude, longitude);
    }
}
