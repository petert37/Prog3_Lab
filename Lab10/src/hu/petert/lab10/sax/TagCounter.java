package hu.petert.lab10.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class TagCounter extends DefaultHandler {

    private Map<String, Integer> tagList;
    private List<BusStop> busStops;
    private BusStop busStop;

    public TagCounter(){
        tagList = new HashMap<>();
        busStops = new ArrayList<>();
        busStop = new BusStop();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(!tagList.containsKey(qName))
            tagList.put(qName, 0);

        tagList.put(qName, tagList.get(qName) + 1);

        if(qName.equals("node")) {
            busStop = new BusStop();
            busStop.setLatitude(Double.valueOf(attributes.getValue("lat")));
            busStop.setLongitude(Double.valueOf(attributes.getValue("lon")));
        }

        if(qName.equals("tag") && attributes.getValue("v").equals("bus_stop"))
            busStop.setValid(true);

        if(qName.equals("tag") && attributes.getValue("k").equals("name"))
            busStop.setName(attributes.getValue("v"));

        if(qName.equals("tag") && attributes.getValue("k").equals("old_name"))
            busStop.setOldName(attributes.getValue("v"));

        if(qName.equals("tag") && attributes.getValue("k").equals("wheelchair"))
            busStop.setWheelChair(attributes.getValue("v"));

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("node")) {
            if(busStop.isValid())
                busStops.add(busStop);
        }
    }

    public void listTags(){
        for(String s : tagList.keySet())
            System.out.println(s + ": " + tagList.get(s));
    }

    public void listBusStops(){
        busStops.forEach(BusStop::print);
    }

    public void listBusStopsSorted(double latitude, double longitude){
        Collections.sort(busStops, new BusStopSorter(latitude, longitude));
        for(BusStop b : busStops)
            b.printWithDistance(latitude, longitude);
    }


}
